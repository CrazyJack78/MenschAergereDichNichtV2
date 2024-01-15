var spielerCounter = -1 // spielerID
class Spieler(val name:String) {
    val spielerFiguren = mutableListOf<Spielfiguren>() // Liste mit Spielfiguren des Spielers
    var spielerID = spielerCounter
    private var warteFeldListePlayer = mutableListOf<StandfelderEinzeln>()
    private var zielFeldListePlayer = mutableListOf<StandfelderEinzeln>()
    private var laufFeldListePlayer = mutableListOf<StandfelderEinzeln>()

    var startfeld = 0 // pre wird noch überschrieben von den Wartefeldern

    init {
        spielerCounter++
        for (i in 1..4){
            spielerFiguren.add(Spielfiguren(name[0].toString(),i)) // erstellen einzelner Figuren in eine zum spieler gehörende Liste
        }
        spielerID = spielerCounter



    }

    fun playerFeldListenFuellen(){ // Liste für Wartefelder beim Spieler
        warteFeldListe[spielerID].forEachIndexed{index,field ->
            warteFeldListePlayer.add(field)
            spielerFiguren[index].feldToFigur(field) // der Figur das Feld geben damt sie weiß wo sie steht und ihr eigenes Feld bearbeiten leeren kann, so ist es leichter zu finden
        }

        println("player: $spielerID startfeld: $startfeld")
        for (i in startfeld..startfeld+40){
            /* start des speichers an der stelle des startfeldes bis startfeld + 40
            Beispiel   20 .. 60

            lauffeldlistePlayer startet bei 0 und lauffeldliste bei 20

             */
            laufFeldListePlayer.add(laufFeldListe[i%40]) // Lauffeldliste füllen
        }
        zielFeldListe[spielerID].forEach { field -> // Liste mit eigenen Zielfeldern
            zielFeldListePlayer.add(field) // Liste der Zielfelder
            laufFeldListePlayer.add(field) // anhängen der Zielfelder an die Lauffelder
            println("Zielfeldnummer = ${field.feldIdNummer}")
        }


    }

    fun spielzug(){
        val wurf = wuerfeln()
        if (wurf == 6) println("Glückwunsch du hast eine 6 geworfen, du darfst nach deinem Zug noch einmal würfeln")
        else {
            if (allFigurWait()) {
                println("Leider warten alle deine Figuren auf's einsetzen und dies kannst du nur mit einer 6\n der nächste Spieler ist dran")
                bildAusgabe()
                return
            } else print("Du hast eine $wurf geworfen, was möchtest du machen")
        }
        println(" welche Figur möchtest du bewegen?")

        while (true){
            val gewaehlteFigur = spielerFiguren[figurAuswahl()] // lässt einen eine Figur auswählen

            if (gewaehlteFigur.figurWait){
                if (wurf == 6){
                    if (warteFigurSetzen(gewaehlteFigur)) return // funktion zu setzen aufrufen und ob gesetzt wurde zurückgeben
                }else {
                    println("Diese Figur kannst du nur mit einer 6 setzen, wähle eine andere")
                    // zurück zum while begin
                }
            }else if (gewaehlteFigur.figurImZiel) {
                println("Diese Figur ist bereits am Ziel und kann nicht weiter laufen, wähle eine andere")
            }else if (gewaehlteFigur.figurAufZielGerade){
                // TODO auf zielgerade kann nicht übersprungen werden Überprüfung
                if(aufZielGeradeSetzen(gewaehlteFigur,wurf)) return
            }else if (gewaehlteFigur.figurLaeuft){
                if (laufFigurSetzen(gewaehlteFigur,wurf)) return // funktion zum Setzen aufrufen und ob gestzt wurde zurückgeben
            }
            // wenn die Funktionen kein True zurückgeben muss man eine neue Figur wählen da es aus einem bestimmten grund nicht ging
        }
    }

    private fun figurAuswahl():Int{
        while (true){
            try {
                while (true){
                    val figur = readln().toInt()-1
                    if (figur < 0 || figur > 3){
                        println("Diese Figur hast du nicht, bitte noch einmal wählen, 1-4")
                    }else {
                        println("Du hast die ${figur + 1} gewählt")
                        return figur
                    }
                }
            }catch (e:NumberFormatException){
                println("Keine gültige Zahl, bitte noch einmal wählen 1-4")
            }
        }
    }

    private fun warteFigurSetzen(gewaehlteFigur:Spielfiguren):Boolean{
        gewaehlteFigur.figurFeldSpeicher[0].feldLeeren() // löschen des Wartefeldes
        val zielFeld = laufFeldListePlayer[0]
        //val zielLaufFeldNummer = zielFeld.feldIdNummer

            // Besetztes Feld?
        if (zielFeld.playerOnField){ // Überprüft, ob schon eine Figur auf dem Feld steht
            if (zielFeld.playerID == spielerID){
                println("Da steht schon eine Figur von Dir, wähle eine andere")
                return false
            }else rausWurf(zielFeld) // wen ja wird die Rauswurffunktion angestoßen
        }
        // Status Figur anpassen
        gewaehlteFigur.figurWait = false // Warten Status wird gelöscht
        gewaehlteFigur.figurLaeuft = true // Laufen Status wird gesetzt
        gewaehlteFigur.feldToFigur(zielFeld)
        //gewaehlteFigur.feldNummer = zielFeld.feldIdNummer // die figur bekommt die Id des neuen Feldes
        zielFeld.feldBesetzen(gewaehlteFigur.figurZeichen,spielerID) // Feld wird mit den Daten des Spielers geschrieben
        // gewaehlteFigur.feldNummer = startfeld   // die Figur bekommt die Daten des Feldes geschrieben zum Abruf wo die Figur ist

        bildAusgabe()
        return true
    }

    private fun laufFigurSetzen(gewaehlteFigur: Spielfiguren, wurf:Int): Boolean{
        val altesFeld = gewaehlteFigur.figurFeldSpeicher[0]
        val altesFeldIDNummer = altesFeld.feldIdNummer // LaufFeld auf dem die Figur gerade steht
        // aus der identnummer des feldes kann man ableiten auf welchem Feld in der PlayerListe die Figur steht
         // Feld, auf das die Figur steht, zum Bearbeiten und Werte Lesen nutzen
        //var keineRundeRum = true
        val altesFeldIndexOnPlayer = ((altesFeldIDNummer - startfeld)+40)%40 // wenn altesFeldNummer - startfeld kleiner ist als null, bringen wir sie ins Positive und falls sie dann größer ist als 40
                                                                    // mit % auf Spielfeldlänge
        val neuesZielFeldIndexOnPlayer = altesFeldIndexOnPlayer + wurf // ab hier größer 39 möglich (felder 0 - 39  zusammen 40 stück)
        /*
        var neuesZielFeldNummer = altesFeldNummer + wurf // die FeldId von derzeitigem Feld weiterzählen und wenn über 40 neu beginnen mit zählen
        if (neuesZielFeldNummer > 39){
            keineRundeRum = false
            neuesZielFeldNummer %= 40
        }
         */
        if (neuesZielFeldIndexOnPlayer < 40){ // Überprüfen, ob du schon eine Runde rum bist und dein Zielfeld immer noch kleiner ist als dein Startfeld
            /* man könnte auch den Modulu direkt auf die rechnung anwenden

            man könnte aber auch eine Liste bauen die beim jeweiligen Spieler liegt so dass er immer bei feld null startet

             Man läuft los, und würde mit einer 5 von 38 auf 44 landen.
            Der Modulu macht aus der 44 ein 4, danach muss ich überprüfen ob ich damit schon über mein Startfeld hinaus bin
            ((altes Feld + wurf) % 40) < startfeld V1
            oder neues feld 44 - start 10 < 40 = 34 True wir sind noch im lauf
            ab feld 40 müssen wir wieder neu zählen also modulu %40
             */
            // neuesZielFeldNummer %= 40 // ab Feld 40 neu anfangen zu zählen
            val neuesZielFeld = laufFeldListePlayer[neuesZielFeldIndexOnPlayer] // das neue Zielfeld aus der Liste des Spielers entnehmen
            if (neuesZielFeld.playerOnField){ // steht schon ein spieler auf dem Feld
                if (neuesZielFeld.playerID == spielerID){ // ist es der eigene?
                    println("Da steht schon eine Figur von Dir, wähle eine andere")
                    return false
                }else rausWurf(neuesZielFeld) // fremder spieler
            }
            // TODO was passiert wenn ich zum Zieleinlauf komme
            // Status anpassen
            altesFeld.feldLeeren()
            gewaehlteFigur.feldToFigur(neuesZielFeld) // Feld zur bearbeitung an figur geben
            neuesZielFeld.feldBesetzen(gewaehlteFigur.figurZeichen,spielerID)
        }else { // wenn du eine Runde rum bist
            val wurfZuViel = neuesZielFeldIndexOnPlayer - 41
            if(wurfZuViel < 4){
                // var restLaufWeg = (wurf - wurfZuViel) wohin im Zeillauf
                zielFeldListePlayer.forEachIndexed{feldIndex,feldInhalt ->
                    if (feldInhalt.playerOnField && wurfZuViel > feldIndex){ // sollte der Restwurf größer dem Index sein, wo schon eine Figur steht, kannst du die Figur nicht dahin setzen bzw darüber
                        println("Du kannst diese Figur nicht setzen da du schon eine Figur im Ziellauf hast die du nicht überspringen kannst bzw die schon im Ziel ist")
                        return false
                    }else if (feldIndex == wurfZuViel-1){ // erst und nur wenn der FeldIndex so hoch ist wie der Wurf wird die Figur hineingeschrieben
                        gewaehlteFigur.figurAufZielGerade = true
                        feldInhalt.feldBesetzen(gewaehlteFigur.figurZeichen,spielerID)
                        for (i in feldIndex..3){ // sollte nach dem gewählten Index alle felder playerOnField True sein wird die Figur als im Ziel gewertet
                            if (!zielFeldListePlayer[i].playerOnField){ // wenn ein Platz, nach dem Platz der Figur false ist, wird im Ziel false
                                gewaehlteFigur.figurImZiel = false
                                break // falls ein Platz, vor dem letzten nicht besetzt ist, muss die Schleife schon vorher abbrechen
                            }else gewaehlteFigur.figurImZiel = true
                        }
                    }
                }

            }else {
                println("Dein wurf ist zu hoch um in die Zielgeraden einzulaufen")
                return false
            }
            /*
            val wurfZuViel = neuesZielFeldNummer - startfeld -1
            neuesZielFeldNummer -= - wurfZuViel
            if (wurfZuViel < 4){

            } else println("Dein wurf ist zu hoch um in die Zielgeraden einzulaufen")

            zu viel = zielfeld - startfeld -1
            neues zielfeld = altesZielfeld - zu viel
            wurfrest = wurf - zu viel
            mit dem rest zählen wir in der Zielgeraden hoch
            rest darf max 4 sein
            überprüfen ob schon eine figur in zielgerade steht
            wenn eine figur auf der Zielgeraden steht überprüfen wo / wenn auf 2 darf der Restwurf höchstens 2 betragen da nicht übersprungn werden darf
            , bzw die Figur schon am Ende steht und im Ziel ist und dieses Feld schon belegt ist

             */
        }
        //gewaehlteFigur.figurFeldSpeicher[0].feldLeeren()
        bildAusgabe()
        return true
    }

    private fun aufZielGeradeSetzen(gewaehlteFigur: Spielfiguren, wurf:Int): Boolean{
        val altesZielLaufFeld = gewaehlteFigur.figurFeldSpeicher[0]
        val altesZieleinlaufFeldNummer = altesZielLaufFeld.feldIdNummer
        val neuesZieleinlaufFeldNummer = altesZieleinlaufFeldNummer + wurf
        if (zielFeldListePlayer[neuesZieleinlaufFeldNummer].playerOnField){ // ist schon eine deiner Figuren auf deem feld
            println("Du kannst die Figur nicht bewegen, das Feld ist schon besetzt")
            return false
        }
        for (i in 0..neuesZieleinlaufFeldNummer){
            if (zielFeldListePlayer[i].playerOnField){
                println("Du kannst im Ziellauf keine Figur überspringen") // würdest du eine deiner Figuren überspringen
                return false
            }
        }
        zielFeldListePlayer[neuesZieleinlaufFeldNummer].feldBesetzen(gewaehlteFigur.figurZeichen,spielerID)
        for (i in neuesZieleinlaufFeldNummer..3){ // sollte nach dem gewählten Index alle felder playerOnField True sein wird die Figur als im Ziel gewertet
            if (!zielFeldListePlayer[i].playerOnField){ // wenn ein Platz, nach dem Platz der Figur false ist, wird im Ziel false
                gewaehlteFigur.figurImZiel = false
                break // falls ein Platz, vor dem letzten nicht besetzt ist, muss die Schleife schon vorher abbrechen
            }else gewaehlteFigur.figurImZiel = true
        }


        bildAusgabe()
        return true
    }


    private fun allFigurWait():Boolean{
        var figurWait = 0
        spielerFiguren.forEach {
            if (it.figurWait) figurWait++
        }
        return figurWait == 4
    }
    private fun wuerfeln():Int{
        println("Spieler $name, du bist am Zug, zum würfeln bitte >>Enter<< drücken")
        readln()
        val wurf = wurf()
        println("Wurf:  >>>>>>>>> $wurf  <<<<<<<")

        return wurf
    }

    private fun rausWurf(laufFeld:StandfelderEinzeln){ // Das feld das besetzt werden soll übergeben
        // die Daten des Spielers, der auf dem Feld steht, lesen und nutzen
        playerInGame[laufFeld.playerID].warteFeldListePlayer.forEach{// bei entsprechenden Spieler die Wartefelder durchsehen
            if (!it.playerOnField){ // wenn eines frei ist,
                it.feldBesetzen(laufFeld.figurIdOnField,laufFeld.playerID) // wird es mit der Figur beschrieben die auf dem Lauffeld steht
                return
            }
        }
    }
}
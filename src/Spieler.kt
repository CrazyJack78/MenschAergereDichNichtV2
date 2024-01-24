import kotlin.system.exitProcess

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

        when(spielerCounter){
            0 -> {startfeld = 0}
            1 -> {startfeld = 20}
            2 -> {startfeld = 30}
            3 -> {startfeld = 10}


        }
    }

    fun playerFeldListenFuellen(){ // Liste für Wartefelder beim Spieler
        warteFeldListe[spielerID].forEachIndexed{index,field ->
            warteFeldListePlayer.add(field)
            spielerFiguren[index].feldToFigur(field) // der Figur das Feld geben damt sie weiß wo sie steht und ihr eigenes Feld bearbeiten leeren kann, so ist es leichter zu finden
        }

        //println("player: $spielerID startfeld: $startfeld")
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
            //println("Zielfeldnummer = ${field.feldIdNummer}")
        }


    }

    fun spielzug(){
        val wurf = wuerfeln()

        if (wurf == 6) {
            //println("Glückwunsch du hast eine 6 geworfen, du darfst nach deinem Zug noch einmal würfeln")
            bildAnweisungen.add("Glückwunsch du hast eine 6 geworfen, du darfst nach deinem Zug noch einmal würfeln")
        } else {

            if (allFigurWaitOrInGoal()) {
                //println("Leider warten alle deine Figuren auf's einsetzen und dies kannst du nur mit einer 6\n der nächste Spieler ist dran")
                bildAnweisungen.add("Leider warten alle deine Figuren auf's einsetzen und dies kannst du nur mit einer 6")
                bildAnweisungen.add("der nächste Spieler ist dran")
                //bildAusgabe()
                return
            } else {
                //print("Du hast eine $wurf geworfen, was möchtest du machen")
                bildAnweisungen.add("Du hast eine $wurf geworfen, was möchtest du nun machen,")
            }
        }
        //println("welche Figur möchtest du bewegen?")
        bildAnweisungen.add("welche Figur möchtest du bewegen?")
        bildAusgabe()
        while (true){
            val gewaehlteFigur = spielerFiguren[figurAuswahl()-1] // lässt einen eine Figur aus der liste des Spielers auswählen

            if (gewaehlteFigur.figurWait){
                if (wurf == 6){
                    if (warteFigurSetzen(gewaehlteFigur)) {
                        //println("Spieler ${this.name} du darfst noch einmal würfeln")
                        bildAnweisungen.add("Spieler ${this.name} du darfst noch einmal würfeln")
                        spielzug()
                    } // funktion zu setzen aufrufen und ob gesetzt wurde zurückgeben
                }else {
                    //println("${this.name}, diese Figur kannst du nur mit einer 6 setzen, wähle eine andere")
                    bildAnweisungen.add("${this.name}, diese Figur kannst du nur mit einer 6 setzen, wähle eine andere")
                    bildAnweisungen.add("Die ${gewaehlteFigur}. konntest du nicht bewegen, welche Figur möchtest du stattdessen bewegen")
                    // zurück zum while begin
                }
            }else if (gewaehlteFigur.figurImZiel) {
                //println("Diese Figur ist bereits am Ziel und kann nicht weiter laufen, wähle eine andere")
                bildAnweisungen.add("Diese Figur ist bereits am Ziel und kann nicht weiter laufen, wähle eine andere")
                bildAnweisungen.add("Die ${gewaehlteFigur}. konntest du nicht bewegen, welche Figur möchtest du stattdessen bewegen")
            }else if (gewaehlteFigur.figurLaeuft){
                // wenn figur zu nah am Ziel und auch andere Figuren nicht gesetzt werden können
                // TODO keine figur kann gesetzt werden weil sie zu nah am Ziel sind
                if (!kannEineFigurBewegtWerden(wurf)) return
                if (laufFigurSetzen(gewaehlteFigur,wurf)) {
                    if (wurf == 6) {
                        //println("${this.name} du darfst noch einmal würfeln")
                        bildAnweisungen.add("${this.name} du hattest eine 6, du darfst noch einmal würfeln")
                        spielzug()
                    }
                    return
                } // funktion zum Setzen aufrufen und ob gestzt wurde zurückgeben
            }
            // wenn die Funktionen kein True zurückgeben muss man eine neue Figur wählen da es aus einem bestimmten grund nicht ging
            gewonnen()
        }

    }

    fun kannEineFigurBewegtWerden(wurf: Int):Boolean{
        var figurBewegbar = false
        spielerFiguren.forEach{
            if (it.figurWait && wurf == 6 && !laufFeldListePlayer[0].playerOnField) return true
            else figurBewegbar = false
            if (it.figurLaeuft){
                val zielFeld = it.figurPositionAufLaufweg + wurf
                if (zielFeld > 43){
                    figurBewegbar = false
                }else if (laufFeldListePlayer[zielFeld].playerOnField || (zielFeld in 40..43 && figurOnZielgeradeDavor(zielFeld))){
                    // sollte das zeilfeld in der zielgeraden schon besetzt sein oder eine Figur vor dem Feld im Zielfeld stehen
                    figurBewegbar = false
                }else if (laufFeldListePlayer[zielFeld].playerOnField) {
                    figurBewegbar = false
                }else return true
            }
        }
        return figurBewegbar // TODO
    }

    private fun figurAuswahl():Int{
        while (true){
            try {
                val figur = readln().toInt()
                falscheZahlFigurException(figur)
                return figur
            }catch (e:NumberFormatException){
                //println("Keine gültige Zahl, bitte noch einmal wählen 1-4")
                bildAnweisungen.add("${this.name}Keine gültige Zahl, bitte noch einmal wählen 1-4")
                bildAusgabe()
            }catch (e:AnzahlZuGeringException){
                //println("Negative Figuren hast du nicht, bitte eine Zahl von 1 - 4 angeben")
                bildAnweisungen.add("Negative Figuren hast du nicht, bitte eine Zahl von 1 - 4 angeben")
                bildAusgabe()
            }catch (e:AnzahlZuHochException){
                //println("Mehr als 4 figuren hast du nicht, bitte max eine 4 angeben")
                bildAnweisungen.add("Mehr als 4 figuren hast du nicht, bitte max eine 4 angeben")
                bildAusgabe()
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
                //println("Da steht schon eine Figur von Dir, wähle eine andere")
                bildAnweisungen.add("Da steht schon eine Figur von Dir, wähle eine andere")
                bildAnweisungen.add("Die ${gewaehlteFigur}. konntest du nicht bewegen, welche Figur möchtest du stattdessen bewegen")
                return false
            }else rausWurf(zielFeld) // wen ja wird die Rauswurffunktion angestoßen
        }
        // Status Figur anpassen
        gewaehlteFigur.figurWait = false // Warten Status wird gelöscht
        gewaehlteFigur.figurLaeuft = true // Laufen Status wird gesetzt
        gewaehlteFigur.figurPositionAufLaufweg = 0
        gewaehlteFigur.feldToFigur(zielFeld)
        //gewaehlteFigur.feldNummer = zielFeld.feldIdNummer // die figur bekommt die Id des neuen Feldes
        zielFeld.feldBesetzen(gewaehlteFigur,this) // Feld wird mit den Daten des Spielers geschrieben
        // gewaehlteFigur.feldNummer = startfeld   // die Figur bekommt die Daten des Feldes geschrieben zum Abruf wo die Figur ist

        bildAusgabe()
        return true
    }

    private fun laufFigurSetzen(gewaehlteFigur: Spielfiguren, wurf:Int): Boolean{
        val altesFeld = gewaehlteFigur.figurFeldSpeicher[0]
        val altesFeldIndexOnPlayer = gewaehlteFigur.figurPositionAufLaufweg // gespeicherte position der figur
        val neuesZielFeldIndexOnPlayer = altesFeldIndexOnPlayer + wurf // ab hier größer 39 möglich (felder 0 - 39  zusammen 40 stück)
        val neuesZielFeld = laufFeldListePlayer[neuesZielFeldIndexOnPlayer] // das neue Zielfeld aus der Liste des Spielers entnehmen
        if (neuesZielFeldIndexOnPlayer > 43) { // sollte das Ziel rechnerisch größer als 43 sein wird direkt abgewürgt
            //println("Dein Wurf ist zu hoch du kannst diese Figur nicht setzen")
            bildAnweisungen.add("Dein Wurf ist zu hoch du kannst diese Figur nicht setzen")
            return false

        }else if (neuesZielFeldIndexOnPlayer < 40){ // Lauffelder sind kleiner 40 (0-39)

            // neuesZielFeldNummer %= 40 // ab Feld 40 neu anfangen zu zählen
            if (neuesZielFeld.playerOnField){ // steht schon ein spieler auf dem Feld
                if (neuesZielFeld.playerID == spielerID){ // ist es der eigene?
                    println("Da steht schon eine Figur von Dir, wähle eine andere")
                    return false
                }else rausWurf(neuesZielFeld) // Gegner Spieler
            }
            // Status anpassen
            altesFeld.feldLeeren()
            gewaehlteFigur.feldToFigur(neuesZielFeld) // Feld zur verlinkung an figur geben
            neuesZielFeld.feldBesetzen(gewaehlteFigur,this) // Aufruf der Funktion zum Besetzen des neuen Zielfeldes
        }else{
            when(neuesZielFeldIndexOnPlayer){
                43 -> if (laufFeldListePlayer[43].playerOnField){
                    //println("Auf dem Zielplatz steht schon eine figur")
                    bildAnweisungen.add("Auf dem Zielplatz steht schon eine figur")
                    return false // die Figur wurde nicht gesetzt
                }else if (figurOnZielgeradeDavor(neuesZielFeldIndexOnPlayer)){
                    //println("Es steht schon eine oder mehrere figuren davor die du nicht überspringen kannst")
                    bildAnweisungen.add("Es steht schon eine oder mehrere figuren davor die du nicht überspringen kannst")
                    return false // die Figur wurde nicht gesetzt
                }else { // setzen auf Zielfeld
                    if (zielFeld(neuesZielFeldIndexOnPlayer))gewaehlteFigur.figurImZiel = true
                    gewaehlteFigur.figurAufZielGerade = true

                }

            }



        }
        gewaehlteFigur.figurPositionAufLaufweg += wurf
        bildAusgabe()
        return true
    }

    private fun figurOnZielgeradeDavor(zielFeld:Int):Boolean{
        for (index in 40..<zielFeld){
            if (laufFeldListePlayer[index].playerOnField) return true
        }
        return  false
    }

    private fun zielFeld(zielFeld:Int):Boolean{ // wenn vom letzten Feld abwärts auf jedem Feld eine Figur steht soll true zurück gegeben werden
        for (feld in 43 downTo zielFeld+1){
            if (!laufFeldListePlayer[feld].playerOnField) return false // solte auf einem Feld keine Figur stehen kommt direkt false zurück
        }
        return true // sollten auf allen Feldern Figuren stehen läuft die If Abfrage durch und es kommt true zurück
    }

    private fun allFigurWaitOrInGoal():Boolean{
        var figurWait = 0
        spielerFiguren.forEach {
            if (it.figurWait || it.figurImZiel) figurWait++
        }
        return figurWait == 4
    }
    private fun wuerfeln():Int{
        //println("Spieler $name, du bist am Zug, zum würfeln bitte >>Enter<< drücken")
        bildanweisungInitClear()
        bildAnweisungen.add("Spieler $name, du bist am Zug, zum würfeln bitte >>Enter<< drücken")
        bildAusgabe()
        readln()
        val wurf = wurf()
        //println("Wurf:  >>>>>>>>> $wurf  <<<<<<<")
        bildAnweisungen.add("Wurf:  >>>>>>>>> $wurf  <<<<<<<")

        return wurf
    }

    private fun rausWurf(laufFeld:StandfelderEinzeln){ // Das feld das besetzt werden soll übergeben
        // die Daten des Spielers, der auf dem Feld steht, lesen und nutzen
        val playerOnField = laufFeld.playerOnFieldList[0]//playerInGame[laufFeld.playerID]
        val figurOnField = laufFeld.figurOnFieldList[0]//playerInGame[laufFeld.playerID].spielerFiguren[laufFeld.figurNummerFromPlayerlist] // die nummer der figur des spielers auf dem feld
        playerInGame[laufFeld.playerID].warteFeldListePlayer.forEach{// bei entsprechenden Spieler die Wartefelder durchsehen
            if (!it.playerOnField){ // wenn eines frei ist,
                playerInGame[laufFeld.playerID].spielerFiguren[laufFeld.figurIdOnField[1].code].feldToFigur(it)
                it.feldBesetzen(figurOnField,playerOnField) // wird es mit der Figur beschrieben die auf dem Lauffeld steht

                return
            }
        }
    }

    private fun gewonnen(){
        this.spielerFiguren.forEach{
            if (!it.figurImZiel) return
        }
        println("Tatatataraaaaaaa")
        println("------------------")
        print("<<<<<<<<<<>>>>>>>>>>")
        print("Der Spieler ${this.name} hat gewonnen")
        print("<<<<<<<<<<>>>>>>>>>>")
        exitProcess(0)

    }
}
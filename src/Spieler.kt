var spielerCounter = 0 // spielerID
class Spieler(name:String) {
    val name = name // SpielerName
    val spielerFiguren = mutableListOf<Spielfiguren>() // Liste mit Spielfiguren des Spielers
    val spielerID = spielerCounter
    var warteFeldListePlayer = mutableListOf<StandfelderEinzeln>()
    var startfeld = 0
        get() = field

    init {
        for (i in 1..4){
            spielerFiguren.add(Spielfiguren(name[0].toString(),i)) // erstellen einzelner Figuren in eine zum spieler gehörende Liste
        }
        if (anzahlSpieler > spielerCounter+1){
            spielerCounter++
        }


    }

    fun warteFeldListeFuellen(){ // Liste für Wartefelder beim Spieler
        warteFeldListe[spielerID].forEachIndexed{index,field ->
            warteFeldListePlayer.add(field)
            spielerFiguren[index].feldToFigur(field) // der Figur das Feld zur möglichen bearbeitung beim verlassen geben
        }

    }

    fun spielzug(){
        println(" Spieler $name, du bist am Zug, zum würfeln bitte >>Enter<< drücken")
        val temp = readln()
        val wurf = Wurf()
        println("Wurf>>> $wurf")
        var figurWait = 0
        spielerFiguren.forEach {
            if (it.figurWait) figurWait++
        }
        if (wurf == 6) println("Glückwunsch du hast eine 6 geworfen, du darfst nach deinem Zug noch einmal würfeln")
        else {

            if (figurWait == 4) {
                println("Leider warten alle deine Figuren auf's einsetzen und dies kannst du nur mit einer 6\n der nächste Spieler ist dran")
                return
            } else println("Du hast eine $wurf geworfen, was möchtest du machen")
        }

        println("Du kannst eine Figur einsetzen, welche möchtest du ziehen")

        while (true){
            val gewaehlteFigur = spielerFiguren[figurAuswahl()-1] // lässt einen eine Figur auswählen

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
            }else if (gewaehlteFigur.figurLaeuft){
                if (laufFigurSetzen(gewaehlteFigur,wurf)) return // funktion zum setzen aufrufen und ob gestzt wurde zurückgeben

            }

        }


    }

    fun figurAuswahl():Int{
        while (true){
            try {
                while (true){
                    val figur = readln().toInt()
                    if (figur < 0 || figur > 4){
                        println("Diese Figur hast du nicht, bitte noch einmal wählen, 1-4")
                    }else {
                        println("Du hast die $figur gewählt")
                        return figur
                    }
                }
            }catch (e:NumberFormatException){
                println("Keine gültige Zahl, bitte noch einmal wählen 1-4")
            }
        }
    }

    fun warteFigurSetzen(gewaehlteFigur:Spielfiguren):Boolean{
        gewaehlteFigur.figurFeld[0].feldLeeren()
        val zielFeld = laufFeldListe[startfeld]
        val zielLaufFeldNummer = zielFeld.feldIdNummer

            // Besetztes Feld?
            if (zielFeld.playerOnField){ // Überprüft ob schon eine Figur auf dem Feld steht
                if (zielFeld.playerID == spielerID){
                    println("Da steht schon eine Figur von Dir, wähle eine andere")
                    return false
                }else rausWurf(zielFeld) // wen ja wird die Rauswurffunktion angestoßen
            }
            // Status Figur anpassen
            gewaehlteFigur.figurWait = false // Warten Status wird gelöscht
            gewaehlteFigur.figurLaeuft = true // Laufen Status wird gesetzt
            gewaehlteFigur.feldNummer = zielLaufFeldNummer // die figur bekommt di Id des neuen Feldes
            zielFeld.feldBesetzen(gewaehlteFigur.figurZeichen,spielerID) // Feld wird mit den Daten des Spielers geschrieben
            // gewaehlteFigur.feldNummer = startfeld   // die Figur bekommt die Daten des Feldes geschrieben zum Abruf wo die Figur ist



        BildAusgabe()
        return true
    }

    fun laufFigurSetzen(gewaehlteFigur: Spielfiguren,wurf:Int): Boolean{
        var neuesZielFeldNummer = 0
        val standFeldFigur = gewaehlteFigur.figurFeld[0].feldIdNummer // LaufFeld auf dem die Figur gerade steht
        val altesFeld = gewaehlteFigur.figurFeld[0]
        neuesZielFeldNummer = standFeldFigur + wurf // die FeldId von derzeitigen Feld weiterzählen
        if (neuesZielFeldNummer-startfeld < 40){ // Überprüfen ob du schon eine Runde rum bist
            neuesZielFeldNummer = neuesZielFeldNummer%40 // ab Feld 40 neu anfangen zu zählen
            val neuesZielFeld = laufFeldListe[neuesZielFeldNummer]
            if (neuesZielFeld.playerOnField){ // steht schon ein spieler auf dem Feld
                if (neuesZielFeld.playerID == spielerID){ // ist es der eigene?
                    println("Da steht schon eine Figur von Dir, wähle eine andere")
                    return false
                }else rausWurf(neuesZielFeld) // fremder spieler
            }
            // Status anpassen
            altesFeld.feldLeeren()
            gewaehlteFigur.feldToFigur(neuesZielFeld) // Feld zur bearbeitung an figur geben
            neuesZielFeld.feldBesetzen(gewaehlteFigur.figurZeichen,spielerID)

        }
        BildAusgabe()
        return true
    }

    fun rausWurf(laufFeld:StandfelderEinzeln){ // Das feld das besetzt werden soll übergeben
        // die Daten des Spielers, der auf dem Feld steht, lesen und nutzen
        playerInGame[laufFeld.playerID].warteFeldListePlayer.forEach{// bei entsprechenden Spieler die Wartefelder durchsehen
            if (!it.playerOnField){ // wenn eines frei ist,
                it.feldBesetzen(laufFeld.figurIdOnField,laufFeld.playerID) // wird es mit der Figur beschrieben die auf dem Lauffeld steht
            }
        }
    }
}
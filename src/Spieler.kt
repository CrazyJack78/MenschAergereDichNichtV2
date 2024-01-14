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
            val gewaehlteFigur = spielerFiguren[figurAuswahl()-1]
            println("figur angekommen")

            if (wurf < 6 && gewaehlteFigur.figurWait){
                println("Diese Figur kannst du nur mit einer 6 setzen, wähle eine andere")
            }else if (gewaehlteFigur.figurImZiel){
                println("Diese Figur ist schon im Ziel und kann nicht mehr bewegt werden, bitte wähle eine andere")
            }else if (gewaehlteFigur.figurAufZielGerade){
                // TODO auf zielgerade kann nicht übersprungen werden Überprüfung
            }else {
                println("6 setzen")
                figurSetzen(gewaehlteFigur)
                return
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

    fun figurSetzen(gewaehlteFigur:Spielfiguren){
        gewaehlteFigur.figurFeld[0].feldLeeren()
        println("Feld: ${gewaehlteFigur.figurFeld[0].feldNummer}")
        if (gewaehlteFigur.figurWait){
            // Besetztes Feld?
            if (laufFeldListe[startfeld].playerOnField){
                // Gegner zurück auf Wartefeld
                rausWurf(laufFeldListe[startfeld])
            }
            // Status Figur anpassen
            gewaehlteFigur.figurWait = false // Warten Status wird gelöscht
            gewaehlteFigur.figurLaeuft = true // Laufen Status wird gesetzt
            laufFeldListe[startfeld].feldBesetzen(gewaehlteFigur.figurZeichen,spielerID) // Feld wird mit den Daten des Spielers geschrieben
            gewaehlteFigur.feldNummer = startfeld   // die Figur bekommt die Daten des Feldes geschrieben zum Abruf wo die Figur ist


        }else if (gewaehlteFigur.figurLaeuft){

        }else if (gewaehlteFigur.figurAufZielGerade){

        }
        BildAusgabe()
        return
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
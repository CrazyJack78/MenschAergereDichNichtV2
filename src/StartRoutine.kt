fun startRoutine(){
    spielerAbfrage()

    playerInGame.forEachIndexed{index,player->
        println("name player $index in game ${player.name}")
    }

    ersterAufbau()

    startBrettAufbau()

    bildAusgabe()



}
//var spielerzahl = 0
lateinit var playerInGame:Array<Spieler>
var anzahlSpieler = 0
fun spielerAbfrage(){

    println("Wieviele Spieler nehmen an dem Spiel teil? 2-4 Spieler")
    while (true){
        try {
            while (true){
                anzahlSpieler = readln().toInt()
                if (anzahlSpieler < 2 || anzahlSpieler > 4){ // Die Überprüfung der Anzahl der Spieler könnte auch über eine eigene Exception passieren
                    println("Diese Eingabe hatte zu viel oder zu wenig Spieler, bitte nur 2-4 ")
                }else break // sollte die Anzahl der eingegebenen Spieler passen bricht die Schleife hier ab
            }
            break // sollte keine Exception geworfen worden sein bricht die Schleife hier ab
        }catch (e:NumberFormatException){
            println("Diese Eingabe war falsch, bitte nur eine Ziffer von 2 bis 4 eingeben")
        }
    }
    //spielerzahl = anzahlSpieler // TODO noch frei geben
    println("Spielerzahl: $anzahlSpieler") // Ausgabe Anzahl Spieler

    playerInGame = Array(anzahlSpieler){// Array mit spielenden Spielern wird erstellt
        Spieler(spielerNamen()) // SpielerKlassen mit Name werden erstellt / mit jeder Erstellung wird der Counter für die Spielerzahl und ID erhöht
    }
    /*
    playerInGame.forEachIndexed{indexPlayerID,player ->
        println("Spieler ${indexPlayerID+1}: ${player.name}")
        println("Die Namen der Figuren dieses Spielers sind:")
        player.spielerFiguren.forEach{figur -> // eindeutige Namen statt it sind eindeutiger
            println("Das Name der FigurNr: ${figur.figurNummer} lautet: ${figur.figurZeichen}")
        }
        println()
    }

     */
    println("Anzahl der Spieler im Spiel ${spielerCounter + 1}")
}

fun spielerNamen():String{
    println("Welchen Namen hat Spieler ${spielerCounter+2}") // Zuteilung des Namen an entsprechende Id
    while (true){ // Überprüfung das der Name wenigstens ein Zeichen lang ist
        val name = readln()
        if (name.isEmpty()){ // alt>>   name.length == 0
            println("Name zu Kurz")
        }else {
            return name
        }
    }
}

fun startBrettAufbau(){
    playerInGame.forEachIndexed{spielerIndex,spieler->

        warteFeldListe[spielerIndex].forEachIndexed{fieldIndex,field -> // Aufruf der Wartefeldliste in der die 4 Spieler gespeichert sind
            field.feldBesetzen(spieler.spielerFiguren[fieldIndex].figurZeichen,spieler.spielerID) // Aufruf der Figurenliste des jeweiligen Spielers um das Figurenzeichen seiner Figuren zu lesen
            // funktion besetzen aufrufen, des ausgewählten Feldes, um die Felder zu besetzen und das vorher gefundene Zeichen darauf zu schreiben

        }
        spieler.playerFeldListenFuellen()
    }
    /*
    for (spielerID in 0..spielerCounter){
        warteFeldListe[spielerID].forEachIndexed{fieldIndex,field -> // Aufruf der Wartefeldliste in der die 4 Spieler gespeichert sind
            field.feldBesetzen(playerInGame[spielerID].spielerFiguren[fieldIndex].figurZeichen,playerInGame[spielerID].spielerID) // Aufruf der Figurenliste des jeweiligen Spielers um das Figurenzeichen seiner Figuren zu lesen
            // funktion besetzen aufrufen, des ausgewählten Feldes, um die Felder zu besetzen und das vorher gefundene Zeichen darauf zu schreiben
        }
        playerInGame[spielerID].playerFeldListenFuellen()
    }

     */
}

// Ende
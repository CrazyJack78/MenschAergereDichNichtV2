
fun startRoutine(){
    spielerAbfrage()
    bildanweisungInitClear()
    bildAnweisungen.add("Es nehmen ${playerInGame.size} Spieler am Spiel teil")
    playerInGame.forEachIndexed{index,player->
        //println("Name von Spieler ${index+1} ${player.name}")
        //bildanweisungSchreiben()
        bildAnweisungen.add("Der ${index+1}. Spieler mit dem Startfeld:${player.startfeld} ist ${player.name}")
    }

    ersterAufbau()

    startBrettAufbau()

    //bildAusgabe()



}
//var spielerzahl = 0
lateinit var playerInGame:Array<Spieler>
var anzahlSpieler = 0
fun spielerAbfrage(){

    println("Wieviele Spieler nehmen an dem Spiel teil? 2-4 Spieler")
    while (true){
        try {
            anzahlSpieler = readln().toInt()
            falscheZahlSpielerException(anzahlSpieler)
            break // sollte keine Exception geworfen worden sein bricht die Schleife hier ab
        }catch (e:NumberFormatException){
            println("Diese Eingabe war falsch, bitte nur eine Ziffer von 2 bis 4 eingeben")
        }catch (e:AnzahlZuGeringException){
            println("Du hast zu wenig Spieler angegeben\n" +
                    "Bitte nur 2-4 Spieler eingeben")
        }catch (e:AnzahlZuHochException){
            println("Du hast zu viele Spieler angegeben\n" +
                    "Bitte nur 2-4 Spieler eingeben")
        }
    }
    /*
    println("Spielerzahl: $anzahlSpieler") // Ausgabe Anzahl Spieler
     */

    playerInGame = Array(anzahlSpieler){// Array mit spielenden Spielern wird erstellt
        Spieler(spielerNamen()) // SpielerKlassen mit Name werden erstellt / mit jeder Erstellung wird der Counter für die Spielerzahl und ID erhöht
    }


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
            val figur = spieler.spielerFiguren[fieldIndex]
            field.feldBesetzen(figur,spieler) // Aufruf der Figurenliste des jeweiligen Spielers um das Figurenzeichen seiner Figuren zu lesen
            // funktion besetzen aufrufen, des ausgewählten Feldes, um die Felder zu besetzen und das vorher gefundene Zeichen darauf zu schreiben

        }
        spieler.playerFeldListenFuellen()
    }
}

// Ende
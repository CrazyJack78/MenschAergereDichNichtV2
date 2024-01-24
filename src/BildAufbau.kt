lateinit var bildAnweisungen:MutableList<String>
var bildClearCounter = 0

fun bildanweisungInitClear(){
    bildAnweisungen = MutableList<String>(0){""}
    bildAnweisungen.clear()

        playerInGame.forEachIndexed { index, spieler -> bildAnweisungen.add("Spieler ${index+1}: ${spieler.name}") }

}


fun bildAusgabe(){
    //bildanweisungSchreiben()

    val bildufbauList = mutableListOf<String>()
    // Spielfeldausgabe Komplett
    var screenSign = ""

    for (yGanz in 0..10){          // Auswahl Y Zeile
        for (yKlein in 0..2){       // jedes kleine y Feld muss 3x angelaufen werden, nachdem alle seiner Reihe/Zeile abgelaufen wurden, für die jeweilige Zeile
            for (xGanz in 0..10){   // jedes kleine y Feld nach x muss durchlaufen werden, am Ende noch 2x jedes Mal eine innere Zeile weiter
                screenSign += grossesFeld[yGanz][xGanz].zeilenAusgabeYKlein(yKlein) // Ausdruck der jeweiligen inneren x Spalte der jeweligen Y Reihe / X Spalte / y Zeile
                // der zurückkommende String wird zum screenSign dazugeschrieben
            }
            bildufbauList.add(screenSign) // Die Zeie in einem neuen Werte die Liste Speichern
            //println(screenSign)
            screenSign = "" // Screen zurücksetzen

            //println() // nach kompletten Ausdruck einer Zeile, eine Zeile weiter
        }
    }
    println()
    //println("bildbau: " + bildufbauList.size)
    // abruf der zusätzlichen Ausgaben die rechts daneben geschrieben werden sollen


    //println("Ende Ausdruck") // nach abschluss noch eine Zeile weiter gehen / Zeile beenden

    // Bildausgabe

    bildAnweisungen.forEachIndexed{index,inhalt ->
        bildufbauList[index] += ":  $inhalt"
    }
    bildufbauList.forEachIndexed{index,inhaltString ->
        println(inhaltString)
    }


}
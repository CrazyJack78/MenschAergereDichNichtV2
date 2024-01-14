fun BildAusgabe(){

    // Spielfeldausgabe Komplett


    for (yGanz in 0..10){          // Auswahl Y Zeile
        for (yKlein in 0..2){       // jedes kleine y Feld muss 3x angelaufen werden, nachdem alle seiner Reihe/Zeile abgelaufen wurden, für die jeweilige Zeile
            for (xGanz in 0..10){   // jedes kleine y Feld nach x muss durchlaufen werden, am Ende noch 2x jedes Mal eine innere Zeile weiter
                grossesFeld[yGanz][xGanz].zeilenAusgabeYKlein(yKlein) // Ausdruck der jeweiligen inneren x Spalte der jeweligen Y Reihe / X Spalte / y Zeile
            }
            println() // nach kompletten Ausdruck einer Zeile, eine Zeile weiter
        }
    }

    println("Ende Ausdruck") // nach abschluss noch eine Zeile weiter gehen / Zeile beenden

}
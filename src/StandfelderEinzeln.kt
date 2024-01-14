

class StandfelderEinzeln(private val typ:String, feldZaehler:Int) {

    // erste Erstellung leer, dann auffüllen mit Inhalten


    private var kleinesFeld = MutableList(3){ _->MutableList(8){(" ")} }

    // EinzelFeleigenschaften


    // Status des Feldes
    var playerOnField = false
    var playerID:Int = -1
    var feldIdNummer = feldZaehler

    var figurIdOnField = ""


    init { // Aussehen des Feldes
        kleinesFeld.forEachIndexed{indexY,inhaltY ->
            inhaltY.forEachIndexed{ indexX, _ -> // unterstrich statt value-Name wenn der Wert nicht gebraucht wird
                if (indexY == 0 || indexY == 2){
                    kleinesFeld[indexY][indexX] = typ
                }else{
                    kleinesFeld[1][0] = typ
                    kleinesFeld[1][7] = typ
                    }
                }
            }
        }

    fun zeilenAusgabeYKlein(yKLein:Int){
        kleinesFeld[yKLein].forEach{x->
            print(x)
        }
    }

    fun feldLeeren(){
        playerOnField = false
        playerID = -1
        for (index in 1..6){
            kleinesFeld[1][index] = " "
        }
        /*
        kleinesFeld.forEachIndexed{indexY,inhaltY ->
            inhaltY.forEachIndexed{indexX,inhaltX ->
                kleinesFeld[indexY][indexX] //komplett leeren
                if (indexY == 0 || indexY == 2){ // und neu schreiben wo gebraucht
                    kleinesFeld[indexY][indexX] = typ
                }else{
                    kleinesFeld[1][0] = typ
                    kleinesFeld[1][7] = typ
                }
            }
        }
        */
    }

    fun feldBesetzen(figurID:String,playerID:Int){
        feldLeeren()
        playerOnField = true                        // ein spielersteht auf
        for (index in figurID.indices){ // rangeTo oder ..< können eine -1 ersetzen alt>>        for (index in 0..figurID.length-1)    <<<<<
            kleinesFeld[1][index+3] = figurID[index].toString()   // Spieler ID auf Feld schreiben (String Bereich)
        }
        this.playerID = playerID
        figurIdOnField = figurID
    }



}




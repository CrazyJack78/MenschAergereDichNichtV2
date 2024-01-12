import com.sun.jdi.Field

class StandfelderEinzeln(typ:String) {

    // erste Erstellung leer, dann auffüllen mit Inhalten


    var kleinesFeld = MutableList(3){_->MutableList(8){(" ")} }


    var playerOnField = false
    var playerID:Int = -1
    var feldNummer = 0
        get() = field

    init { // Aussehen des Feldes
        kleinesFeld.forEachIndexed{indexY,inhaltY ->
            inhaltY.forEachIndexed{indexX,inhaltX ->
                if (indexY == 0 || indexY == 2){
                    kleinesFeld[indexY][indexX] = typ
                }else{
                    kleinesFeld[1][0] = typ
                    kleinesFeld[1][7] = typ
                    }
                }
            }
        }




}



var waitcounter = 0
class StandfelderEinzeln(private val typ:String, feldZaehler:Int) {

    // erste Erstellung leer, dann auffüllen mit Inhalten


    private var kleinesFeld = MutableList(3){ _->MutableList(8){(" ")} }

    // EinzelFeleigenschaften


    // Status des Feldes
    var playerOnField = false
    var playerID:Int = -1
    var feldIdNummer = feldZaehler
    var figurNummerFromPlayerlist = 0
    var figurIdOnField = ""
    // figur klasse auf dem feld
    // spieler klasse auf dem feld
    /* den kompletten spieler auf das feld bringen zur besseren werte abfrage
    vielleicht auch/oder nur die figur

    in einer liste mit löschbarem inhalt damit das feld auch wieder verlassen werden kann
     */
    var figurOnFieldList = mutableListOf<Spielfiguren>()
    var playerOnFieldList = mutableListOf<Spieler>()
    //var spielerWerte = mutableListOf(figurOnFieldList,playerOnFieldList)


    init { // Aussehen des Feldes
        kleinesFeld.forEachIndexed { indexY, inhaltY ->
            inhaltY.forEachIndexed { indexX, _ -> // unterstrich statt value-Name wenn der Wert nicht gebraucht wird
                if (indexY == 0 || indexY == 2) {
                    kleinesFeld[indexY][indexX] = typ
                } else {
                    kleinesFeld[1][0] = typ
                    kleinesFeld[1][7] = typ
                }
            }
            if (typ == "L"){
                if (feldIdNummer.toString().length == 2) {
                    kleinesFeld[1][0] = feldIdNummer.toString()[0].toString()
                    kleinesFeld[1][1] = feldIdNummer.toString()[1].toString()
                } else {
                    kleinesFeld[1][0] = feldIdNummer.toString()
                }
            }
            if (typ == "W") {
                kleinesFeld[0][0] = waitcounter.toString()
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
        if (playerOnFieldList.size > 0) playerOnFieldList.removeAt(0)
        if (figurNummerFromPlayerlist > 0) figurOnFieldList.removeAt(0) // TODO beide werte noch schreiben
        for (index in 2..5){
            kleinesFeld[1][index] = " " // SpielerId von Feld löschen
        }
    }

    fun feldBesetzen(figur:Spielfiguren,player:Spieler){
        feldLeeren()
        playerOnField = true                        // ein spielersteht auf
        for (index in figur.figurZeichen.indices){ // rangeTo oder ..< können eine -1 ersetzen alt>>        for (index in 0..figurID.length-1)    <<<<<
            kleinesFeld[1][index+3] = figur.figurZeichen[index].toString()   // Spieler ID auf Feld schreiben (String Bereich)
        }
        this.playerID = player.spielerID
        figurIdOnField = figur.figurZeichen
        figurNummerFromPlayerlist = figur.figurNummer
        figurOnFieldList.add(figur)
        playerOnFieldList.add(player)
        //spielerWerte[0].add(player)

    }



}




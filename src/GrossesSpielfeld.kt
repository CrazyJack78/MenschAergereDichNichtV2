lateinit var grossesFeld:MutableList<MutableList<StandfelderEinzeln>>
lateinit var laufFeldListe:MutableList<StandfelderEinzeln>


lateinit var warteFeldListe:MutableList<MutableList<StandfelderEinzeln>>
lateinit var zielFeldListe:MutableList<MutableList<StandfelderEinzeln>>

// Spieler - Feld Zuweisung
var playerOne =     0
var playerTwo =     0
var playerThree =   0
var playerFour =    0


fun playerZuteilung(){
    when (spielerCounter) {
        1 -> {
            playerOne =     0
            playerInGame[0].startfeld = 30
            playerTwo =     2
            playerInGame[1].startfeld = 10
            playerThree =   3
            playerFour =    1
        }
        2 -> {
            when((0..1).random()){
                0 -> {
                    playerOne =     0
                    playerInGame[0].startfeld = 30
                    playerTwo =     1
                    playerInGame[1].startfeld = 0
                    playerThree =   2
                    playerInGame[2].startfeld = 10
                    playerFour =    3
                }

                else -> {
                    playerOne =     0
                    playerInGame[0].startfeld = 30
                    playerTwo =     3
                    playerInGame[1].startfeld = 20
                    playerThree =   2
                    playerInGame[2].startfeld = 10
                    playerFour =    1
                }
            }
        }
        else -> {
            playerOne =     0
            playerInGame[0].startfeld = 30
            playerTwo =     1
            playerInGame[1].startfeld = 0
            playerThree =   2
            playerInGame[2].startfeld = 10
            playerFour =    3
            playerInGame[3].startfeld = 20
        }
    }



}



fun ersterAufbau(){
    // initialisieren der Listen
    // Spielfeld im Ganzen mit Leerfelder initialisiert wegen Abstände und Zuordnung m großen Raster
    grossesFeld = MutableList(11){_->MutableList(11){(StandfelderEinzeln(" ",0))} } // Leerer Aufbau des SpielfeldRasters
    // OrdnungsListen mit Size = 0 Initialisiert damit die schon Initialisierten kleinen Felder/Objekt-StandardFelderEinzeln und im großen Feld angeordneten nur noch in dei Listen geaddet werden müssen
    laufFeldListe = MutableList(0){(StandfelderEinzeln("",0))}


    // WartefelderListe
    warteFeldListe = MutableList(4){MutableList(0){StandfelderEinzeln("",0)}}
    zielFeldListe = MutableList(4){MutableList(0){StandfelderEinzeln("",0)}}

    // Anlegen der einzelnen FeldTypen
    playerZuteilung()

    lauffelderaufbau()
    wartefelderAufbau()
    zielFelderAufbau()

}

fun lauffelderaufbau(){
    // Lauffelder
    var laufFeldCounter = -1

    for (y in 0..4){
        laufFeldCounter++
        val x = 6
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])

    }
    for (x in 7..10){
        laufFeldCounter++
        val y = 4
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])
    }
    laufFeldCounter++
    var yMitte = 5
    var xMitte = 10
    grossesFeld[yMitte][xMitte] = StandfelderEinzeln("L",laufFeldCounter)
    laufFeldListe.add(grossesFeld[yMitte][xMitte])

    for (x in 10 downTo 6){
        laufFeldCounter++
        val y = 6
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])
    }
    for (y in 7 ..10){
        laufFeldCounter++
        val x = 6
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])
    }
    laufFeldCounter++
    yMitte = 10
    xMitte = 5
    grossesFeld[yMitte][xMitte] = StandfelderEinzeln("L",laufFeldCounter)
    laufFeldListe.add(grossesFeld[yMitte][xMitte])

    for (y in 10 downTo  6){
        laufFeldCounter++
        val x = 4
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])
    }
    for (x in 3  downTo 0){
        laufFeldCounter++
        val y = 6
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])
    }
    laufFeldCounter++
    yMitte = 5
    xMitte = 0
    grossesFeld[yMitte][xMitte] = StandfelderEinzeln("L",laufFeldCounter)
    laufFeldListe.add(grossesFeld[yMitte][xMitte])

    for (x in 0  .. 4) {
        laufFeldCounter++
        val y = 4
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])
    }
    for (y in 3  downTo 0) {
        laufFeldCounter++
        val x = 4
        grossesFeld[y][x] = StandfelderEinzeln("L",laufFeldCounter)
        laufFeldListe.add(grossesFeld[y][x])
    }
    laufFeldCounter++
    yMitte = 0
    xMitte = 5
    grossesFeld[yMitte][xMitte] = StandfelderEinzeln("L",laufFeldCounter)
    laufFeldListe.add(grossesFeld[yMitte][xMitte])
}

fun wartefelderAufbau(){
    // Wartefelder Optik Klasse

    var feldNummer = 0
    //links oben
    for (y in 0..2){
        for (x in 0..2){
            if (y != 1 && x != 1){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                grossesFeld[y][x].playerOnField = true
                warteFeldListe[playerOne].add(grossesFeld[y][x])


            }
        }
    }
    feldNummer = 0 //Reset Feldnummer

    //rechts oben
    for (y in 0..2){
        for (x in 8..10){
            if (y != 1 && x != 9){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                grossesFeld[y][x].playerOnField = true
                warteFeldListe[playerTwo].add(grossesFeld[y][x])

            }
        }
    }
    feldNummer = 0 //Reset Feldnummer

    //links unten
    for (y in 8..10){
        for (x in 0..2){
            if (y != 9 && x != 1){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                grossesFeld[y][x].playerOnField = true
                warteFeldListe[playerThree].add(grossesFeld[y][x])

            }
        }
    }
    feldNummer = 0 //Reset Feldnummer

    //rechts unten
    for (y in 8..10){
        for (x in 8..10){
            if (y != 9 && x != 9){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                grossesFeld[y][x].playerOnField = true
                warteFeldListe[playerFour].add(grossesFeld[y][x])

            }
        }
    }
}

fun zielFelderAufbau(){
    // ZielEinlaufFelder
    var feldNummer = 0 // Feldnummerierer

    // Zielfelder von rechts oben
    for (y in 1..4){
        val x = 5
        feldNummer++
        grossesFeld[y][x] =  StandfelderEinzeln("Z",feldNummer)
        //playerGoalRO.add(grossesFeld[y][x])
        zielFeldListe[playerOne].add(grossesFeld[y][x])
    }
    feldNummer = 0 // Nummer Neustart

    // Zielfelder von rechts unten
    for (x in 9 downTo 6){
        val y = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("Z",feldNummer)
        //playerGoalRU.add(grossesFeld[y][x])
        zielFeldListe[playerTwo].add(grossesFeld[y][x])
    }
    feldNummer = 0

    // Zielfelder von links unten
    for (y in 9 downTo 6){
        val x = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("Z",feldNummer)
        //playerGoalLU.add(grossesFeld[y][x])
        zielFeldListe[playerTwo].add(grossesFeld[y][x])
    }
    feldNummer = 0

    // Zielfelder von links oben
    for (x in 1..4){
        val y = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("Z",feldNummer)
        //playerGoalLO.add(grossesFeld[y][x])
        zielFeldListe[playerFour].add(grossesFeld[y][x])
    }
}
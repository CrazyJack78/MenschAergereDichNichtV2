lateinit var grossesFeld:MutableList<MutableList<StandfelderEinzeln>>
lateinit var laufFeldListe:MutableList<StandfelderEinzeln>
lateinit var playerWaitLO:MutableList<StandfelderEinzeln>
lateinit var playerWaitRO:MutableList<StandfelderEinzeln>
lateinit var playerWaitRU:MutableList<StandfelderEinzeln>
lateinit var playerWaitLU:MutableList<StandfelderEinzeln>
lateinit var playerGoalRO:MutableList<StandfelderEinzeln>
lateinit var playerGoalRU:MutableList<StandfelderEinzeln>
lateinit var playerGoalLU:MutableList<StandfelderEinzeln>
lateinit var playerGoalLO:MutableList<StandfelderEinzeln>

fun ersterAufbau(){
    grossesFeld = MutableList(11){_->MutableList(11){(StandfelderEinzeln("",0))} } // Leerer Aufbau des SpielfeldRasters

    // Anlegen der einzelnen FeldTypen
    // Lauffelder
    var laufFeldCounter = 0
    var xMitte = -1
    var yMitte = -1

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
    yMitte = 5
    xMitte = 10
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


    // Wartefelder Optik Klasse

    var feldNummer = 0
    //links oben
    for (y in 0..2){
        for (x in 0..2){
            if (y != 1 && x != 1){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                playerWaitLO.add(grossesFeld[y][x])
            }
        }
    }
    feldNummer = 0 //Reset Feldnummer
    /*
    grossesFeld[0][0] = StandfelderEinzeln("W",1)
    grossesFeld[0][2] = StandfelderEinzeln("W",2)
    grossesFeld[2][0] = StandfelderEinzeln("W",3)
    grossesFeld[2][2] = StandfelderEinzeln("W",4)
    */

    //rechts oben
    for (y in 0..2){
        for (x in 8..10){
            if (y != 1 && x != 9){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                playerWaitRO.add(grossesFeld[y][x])
            }
        }
    }
    feldNummer = 0 //Reset Feldnummer
    /*
    grossesFeld[0][8] = StandfelderEinzeln("W",1)
    grossesFeld[0][10] = StandfelderEinzeln("W",2)
    grossesFeld[2][8] = StandfelderEinzeln("W",3)
    grossesFeld[2][10] = StandfelderEinzeln("W",4)
     */
    //links unten
    for (y in 8..10){
        for (x in 0..2){
            if (y != 9 && x != 1){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                playerWaitLU.add(grossesFeld[y][x])
            }
        }
    }
    feldNummer = 0 //Reset Feldnummer
    /*
    grossesFeld[8][0] = StandfelderEinzeln("W",1)
    grossesFeld[8][2] = StandfelderEinzeln("W",2)
    grossesFeld[10][0] = StandfelderEinzeln("W",3)
    grossesFeld[10][2] = StandfelderEinzeln("W",4)
     */
    //rechts unten
    for (y in 8..10){
        for (x in 8..10){
            if (y != 9 && x != 9){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                playerWaitRU.add(grossesFeld[y][x])
            }
        }
    }
    /*
    grossesFeld[8][8] = StandfelderEinzeln("W",1)
    grossesFeld[8][10] = StandfelderEinzeln("W",2)
    grossesFeld[10][8] = StandfelderEinzeln("W",3)
    grossesFeld[10][10] = StandfelderEinzeln("W",4)
     */

    // ZielEinlaufFelder
    feldNummer = 0 // Feldnummerierer

    // Zielfelder von rechts oben
    for (y in 1..4){
        var x = 5
        feldNummer++
        grossesFeld[y][x] =  StandfelderEinzeln("Z",feldNummer)
        playerGoalRO.add(grossesFeld[y][x])
    }
    feldNummer = 0 // Nummer Neustart

    // Zielfelder von rechts unten
    for (x in 9 downTo 6){
        var y = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
        playerGoalRU.add(grossesFeld[y][x])
    }
    feldNummer = 0

    // Zielfelder von links unten
    for (y in 9 downTo 6){
        var x = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
        playerGoalLU.add(grossesFeld[y][x])
    }
    feldNummer = 0

    // Zielfelder von links oben
    for (x in 1..4){
        var y = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
        playerGoalLO.add(grossesFeld[y][x])
    }
}
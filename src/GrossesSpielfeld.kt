lateinit var grossesFeld:MutableList<MutableList<StandfelderEinzeln>>
lateinit var laufFeldListe:MutableList<StandfelderEinzeln>


lateinit var warteFeldListe:MutableList<MutableList<StandfelderEinzeln>>
lateinit var zielFeldListe:MutableList<MutableList<StandfelderEinzeln>>



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
    waitcounter++

    var feldNummer = -1
    //links oben
    for (y in 0..2){
        for (x in 0..2){
            if (y != 1 && x != 1){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                if (playerInGame.size >= 3) {
                    grossesFeld[y][x].playerOnField = true
                    warteFeldListe[2].add(grossesFeld[y][x]) // einspeichern der

                }

            }
        }
    }
    feldNummer = -1 //Reset Feldnummer
    waitcounter++
    // OR
    //rechts oben
    for (y in 0..2){
        for (x in 8..10){
            if (y != 1 && x != 9){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                grossesFeld[y][x].playerOnField = true
                warteFeldListe[0].add(grossesFeld[y][x])

            }
        }
    }
    feldNummer = -1 //Reset Feldnummer
    waitcounter++
    //links unten
    for (y in 8..10){
        for (x in 0..2){
            if (y != 9 && x != 1){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                grossesFeld[y][x].playerOnField = true
                warteFeldListe[1].add(grossesFeld[y][x])


            }
        }
    }
    feldNummer = -1 //Reset Feldnummer
    waitcounter++
    //rechts unten
    for (y in 8..10){
        for (x in 8..10){
            if (y != 9 && x != 9){
                feldNummer++
                grossesFeld[y][x] = StandfelderEinzeln("W",feldNummer)
                if (playerInGame.size == 4) {
                    grossesFeld[y][x].playerOnField = true
                    warteFeldListe[3].add(grossesFeld[y][x])

                }
            }
        }
    }
}

fun zielFelderAufbau(){
    // ZielEinlaufFelder
    var feldNummer = -1 // Feldnummerierer

    // Zielfelder von rechts oben
    for (y in 1..4){
        val x = 5
        feldNummer++
        grossesFeld[y][x] =  StandfelderEinzeln("Z",feldNummer)
        zielFeldListe[2].add(grossesFeld[y][x])
    }
    feldNummer = -1 // Nummer Neustart

    // Zielfelder von rechts unten
    for (x in 9 downTo 6){
        val y = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("Z",feldNummer)
        zielFeldListe[0].add(grossesFeld[y][x])
    }
    feldNummer = -1

    // Zielfelder von links unten
    for (y in 9 downTo 6){
        val x = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("Z",feldNummer)
        zielFeldListe[1].add(grossesFeld[y][x])
    }
    feldNummer = -1

    // Zielfelder von links oben
    for (x in 1..4){
        val y = 5
        feldNummer++
        grossesFeld[y][x] = StandfelderEinzeln("Z",feldNummer)
        zielFeldListe[3].add(grossesFeld[y][x])
    }
}
fun ersterAufbau(){
    var grossesFeld = MutableList(11){_->MutableList(11){(StandfelderEinzeln(""))} } // Leerer Aufbau des SpielfeldRasters

    // Anlegen der einzelnen FeldTypen
    // Lauffelder
    var laufFeldCounter = 0

    for (y in 0..4){
        laufFeldCounter++
        val x = 6
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter

    }
    for (x in 7..10){
        laufFeldCounter++
        val y = 4
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter
    }
    laufFeldCounter++
    grossesFeld[5][10] = StandfelderEinzeln("L")
    grossesFeld[5][10].feldNummer = laufFeldCounter

    for (x in 10 downTo 6){
        laufFeldCounter++
        val y = 6
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter
    }
    for (y in 7 ..10){
        laufFeldCounter++
        val x = 6
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter
    }
    laufFeldCounter++
    grossesFeld[10][5] = StandfelderEinzeln("L")
    grossesFeld[10][5].feldNummer = laufFeldCounter

    for (y in 10 downTo  6){
        laufFeldCounter++
        val x = 4
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter
    }
    for (x in 3  downTo 0){
        laufFeldCounter++
        val y = 6
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter
    }
    laufFeldCounter++
    grossesFeld[5][0] = StandfelderEinzeln("L")
    grossesFeld[5][0].feldNummer = laufFeldCounter

    for (x in 0  .. 4) {
        laufFeldCounter++
        val y = 4
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter
    }
    for (y in 3  downTo 0) {
        laufFeldCounter++
        val x = 4
        grossesFeld[y][x] = StandfelderEinzeln("L")
        grossesFeld[y][x].feldNummer = laufFeldCounter
    }
    laufFeldCounter++
    grossesFeld[0][5] = StandfelderEinzeln("L")
    grossesFeld[0][5].feldNummer = laufFeldCounter

}
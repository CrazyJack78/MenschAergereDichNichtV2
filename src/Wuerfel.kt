fun Wurf():Int{
    return (1..6).random()
}

fun randomSpielerStart():Int{ // Rückgabe der Id des Startspielers
    return (0..spielerCounter).random()
}

fun nextPlayer(aktuellerSpieler:Int):Int{
    if (aktuellerSpieler == spielerCounter) return 0
    return aktuellerSpieler + 1
}
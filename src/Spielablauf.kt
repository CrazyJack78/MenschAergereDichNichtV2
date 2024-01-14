private var runde = 1
private var aktuellerSpieler = -1
fun spielAblauf(){
    if (aktuellerSpieler == -1){
        aktuellerSpieler = randomSpielerStart()

    }

    //GameLoop
    while (true){
        runde++
        val rundeaus = runde%anzahlSpieler
        println("Runde: ${runde/2}")
        playerInGame[aktuellerSpieler].spielzug()
        aktuellerSpieler = nextPlayer(aktuellerSpieler)

    }



}
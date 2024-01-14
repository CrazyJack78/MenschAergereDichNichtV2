
private var aktuellerSpieler = -1
fun spielAblauf(){
    if (aktuellerSpieler == -1){
        aktuellerSpieler = randomSpielerStart()

    }

    //GameLoop
    while (true){
        playerInGame[aktuellerSpieler].spielzug()
        aktuellerSpieler = nextPlayer(aktuellerSpieler)

    }



}
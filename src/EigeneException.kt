class AnzahlZuHochException(message:String):Exception(message)
class AnzahlZuGeringException(message:String):Exception(message)

fun falscheZahlSpielerException(eingabe:Int){
    if (eingabe < 2){
        throw AnzahlZuGeringException("Ups zu wenig")
    }else if (eingabe > 4){
        throw AnzahlZuHochException("Ups zu viele")
    }
}

fun falscheZahlFigurException(eingabe: Int){
    if (eingabe < 1){
        throw AnzahlZuGeringException("Ups zu wenig")
    }else if (eingabe > 4){
        throw AnzahlZuHochException("Ups zu viele")
    }
}
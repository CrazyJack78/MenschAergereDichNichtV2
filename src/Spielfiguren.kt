class Spielfiguren(spielerZeichen:String, val figurNummer:Int) {
    val figurZeichen:String = spielerZeichen.uppercase() + figurNummer.toString() //Erstellen des Figuren-Namen

    // Figuren Status
    var figurWait = true
    var figurLaeuft = false
    var figurAufZielGerade = false
    var figurImZiel = false

    var feldNummer = 0

    var figurFeldSpeicher = MutableList(1){StandfelderEinzeln("",0)} // Feld zum bearbeiten beim verlassen verlinken

    fun feldToFigur(feld:StandfelderEinzeln){
        figurFeldSpeicher[0] = feld // neues Feld verlinken
        feldNummer = feld.feldIdNummer
        println("neue $feldNummer")
    }

}
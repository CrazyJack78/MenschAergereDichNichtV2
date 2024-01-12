fun main(){
    val erstesFeld = StandfelderEinzeln("X")

    for (i in 1..11){
        erstesFeld.kleinesFeld.forEach{
            it.forEach{
                print(it)
            }
            println()
        }
    }


}
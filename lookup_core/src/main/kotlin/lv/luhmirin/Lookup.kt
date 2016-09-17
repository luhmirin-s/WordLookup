

class Lookup() {

    lateinit var words: List<Word>

    fun init(iter: Iterable<String>) {
        words = listOf(Word("as"))
    }


}

data class Word(val wordString: String) {

    val wordDigits : String;

    init {
        wordDigits = wordString.digitize()
    }

}

fun String.digitize() : String = this.map { it.toDigit() }.toString()

fun Char.toDigit(): Char = when (this) {
    in 'a'..'c' -> '2'
    in 'd'..'f' -> '3'
    in 'g'..'i' -> '4'
    in 'j'..'l' -> '5'
    in 'm'..'o' -> '6'
    in 'p'..'s' -> '7'
    in 't'..'v' -> '8'
    in 'w'..'z' -> '9'
    else -> ' '
}
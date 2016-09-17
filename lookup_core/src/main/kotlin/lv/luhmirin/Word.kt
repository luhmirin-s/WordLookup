package lv.luhmirin

data class Word(val wordString: String) {
    val wordDigits: String

    init {
        wordDigits = wordString.digitize()
    }
}

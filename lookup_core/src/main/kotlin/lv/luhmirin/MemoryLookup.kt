package lv.luhmirin

class MemoryLookup() : WordLookup {

    lateinit private var wordsList: List<Word>

    override fun loadDictionary(words: Iterable<String>) {
        this.wordsList = words.map { Word(it) }.toList()
    }

    override fun lookup(digits: String): List<String> {
        return wordsList
                .filter { it.wordDigits.startsWith(digits) }
                .map { it.wordString }

    }
}

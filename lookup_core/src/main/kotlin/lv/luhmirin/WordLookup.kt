package lv.luhmirin

interface WordLookup {

    fun loadDictionary(words: Iterable<String>): Unit

    fun lookup(digits: String): List<String>

}

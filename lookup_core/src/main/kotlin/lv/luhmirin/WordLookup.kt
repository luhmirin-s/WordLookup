package lv.luhmirin

interface WordLookup {

    /**
     * Loads strings provided by iterable to storage and on the way adds T9 form of
     * provided strings for faster filtering later.
     *
     * @param words iterable list of words for later lookup
     */
    fun loadDictionary(words: Iterable<String>): Unit

    /**
     * @param digits T9 form of prefix for filtering
     * @return subset of stored strings that matches provided prefix.
     */
    fun lookup(digits: String): List<String>

}

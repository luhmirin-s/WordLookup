package lv.luhmirin

import org.amshove.kluent.shouldEqual
import org.junit.Test

class WordTest {

    @Test
    fun constructorTest() {
        val word = Word("post")

        word.wordString shouldEqual "post"
        word.wordDigits shouldEqual "7678"
    }

}
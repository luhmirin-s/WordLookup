package lv.luhmirin

import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.junit.Before
import org.junit.Test
import java.io.File

class MemoryLookupTest {

    lateinit var subject: WordLookup

    @Before
    fun setUp() {
        subject = MemoryLookup()
    }

    @Test
    fun testLookup() {
        val testList = listOf(
                "abaca",
                "aback",
                "abacus",
                "abacuses",
                "abaft",
                "abalone",
                "abalones",
                "abalone's",
                "abandon",
                "abandoned",
                "abandonee",
                "abandoner",
                "abandoning",
                "abandonment",
                "abandons"
        )

        subject.loadDictionary(testList)

        val result = subject.lookup("2222")

        result shouldContain "abaca"
        result shouldContain "aback"

        result shouldNotContain "abaft"
    }

    @Test
    fun loadDictionaryPerformance() {
        val file = File("../app/src/main/assets/wordlist.txt")

        subject.loadDictionary(file.readLines())

        val result = subject.lookup("7678")

        result shouldContain "sort"
        result shouldContain "post"
        result shouldContain "postbag"
    }

}
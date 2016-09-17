package lv.luhmirin

import org.amshove.kluent.shouldBe
import org.junit.Test
import toDigit

class LookupKtTest {

    @Test
    fun toDigit() {
        'a'.toDigit() shouldBe '2'

    }

}
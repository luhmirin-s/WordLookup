package lv.luhmirin

import org.amshove.kluent.shouldEqual
import org.junit.Test

class ExtensionsTest {

    @Test
    fun toDigit() {
        'a'.toDigit() shouldEqual '2'
        'b'.toDigit() shouldEqual '2'
        'c'.toDigit() shouldEqual '2'

        'd'.toDigit() shouldEqual '3'
        'e'.toDigit() shouldEqual '3'
        'f'.toDigit() shouldEqual '3'

        'g'.toDigit() shouldEqual '4'
        'h'.toDigit() shouldEqual '4'
        'i'.toDigit() shouldEqual '4'

        'j'.toDigit() shouldEqual '5'
        'k'.toDigit() shouldEqual '5'
        'l'.toDigit() shouldEqual '5'

        'm'.toDigit() shouldEqual '6'
        'n'.toDigit() shouldEqual '6'
        'o'.toDigit() shouldEqual '6'

        'p'.toDigit() shouldEqual '7'
        'q'.toDigit() shouldEqual '7'
        'r'.toDigit() shouldEqual '7'
        's'.toDigit() shouldEqual '7'

        't'.toDigit() shouldEqual '8'
        'u'.toDigit() shouldEqual '8'
        'v'.toDigit() shouldEqual '8'

        'w'.toDigit() shouldEqual '9'
        'x'.toDigit() shouldEqual '9'
        'y'.toDigit() shouldEqual '9'
        'z'.toDigit() shouldEqual '9'
    }

    @Test
    fun digitize() {
        "post".digitize() shouldEqual "7678"
        "sort".digitize() shouldEqual "7678"

        "zoo".digitize() shouldEqual "966"
        "zoo's".digitize() shouldEqual "966 7"
    }

}
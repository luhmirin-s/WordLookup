package lv.luhmirin


fun String.digitize(): String = this.map { it.toDigit() }.joinToString("")

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
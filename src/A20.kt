/**
 * This is the A20 class
 * Please put some info here.
 *
 * @author Wafer Li
 * @since 16/12/8 16:50
 */

fun main(args: Array<String>) {
    readLine()?.toInt() ?: throw UnsupportedOperationException("Line count is null!")

    var line = readLine()
    while (line != null && line != "") {
        val n:Long = java.lang.Long.decode(("0x" + line))
        printN(n)

        line = readLine()
    }

}

fun flippable(n: Long): Boolean {
    return (n shr 20) % 2 == 1L
}

fun flip(n: Long): Long {
    return n xor (1 shl 20)
}

fun printN(n: Long) {
    if (flippable(n)) {
        println("%08X %08X".format(flip(n), n))
    }
    else {
        println("%08X".format(n))
    }
}

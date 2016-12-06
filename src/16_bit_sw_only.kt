/**
 * This is the `16_bit_sw_only` class
 * Please put some info here.
 *
 * @author Wafer Li
 * @since 16/12/6 13:33
 */

fun main(args: Array<String>) {
    val line_count = readLine()?.toInt() ?: throw NoSuchFieldException("Line count is null!")

    var line = readLine()
    while (line != null && line !=  "") {
        val numbers: List<String> = line.split(" ")

        val a = numbers[0].toLong()
        val b = numbers[1].toLong()

        val c = a * b

        if (numbers[2].toLong() != c) {
            println("16 BIT S/W ONLY")
        }
        else {
            println("POSSIBLE DOUBLE SIGMA")
        }

        line = readLine()
    }
}

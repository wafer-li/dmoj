import java.util.*

/**
 * This is the Rotate3dPoint class
 *
 * This is the algebra problem
 * @author Wafer Li
 * @since 16/12/6 22:58
 * @see [Solution](http://blog.csdn.net/qiuchangyong/article/details/5859628)
 * @see [Problem](https://dmoj.ca/problem/3drotate)
 */

fun main(args: Array<String>) {
    readLine()?.toInt() ?: throw NoSuchElementException("Line count is empty!")

    var line = readLine()
    while (line != null && line != "") {
        val numbers = line.split(" ")

        val originalPoint = obtainOriginalPoint(numbers)
        val unitRotationAxis = unitizeRotationVector(obtainRotationAxis(numbers))
        val angle = obtainAngle(numbers)

        val c = Math.cos(angle)
        val s = Math.sin(angle)

        printPoint(calculateDestinationPoint(c, s, originalPoint, unitRotationAxis))

        line = readLine()
    }
}

fun obtainOriginalPoint(numbers: List<String>): Triple<Double, Double, Double> {
    return Triple(numbers[0].toDouble(), numbers[1].toDouble(), numbers[2].toDouble())
}

fun obtainRotationAxis(numbers: List<String>): Triple<Double, Double, Double> {
    return Triple(numbers[3].toDouble(), numbers[4].toDouble(), numbers[5].toDouble())
}

fun obtainAngle(numbers: List<String>): Double {
    return numbers[6].toDouble()
}

/**
 * 将矢量单位化，否则不能适用于下面的计算式子
 */
fun unitizeRotationVector(rotationAxis: Triple<Double, Double, Double>)
        : Triple<Double, Double, Double>
{
    val (rotationX, rotationY, rotationZ) = rotationAxis

    val model: Double =
            Math.sqrt(rotationX * rotationX + rotationY * rotationY + rotationZ * rotationZ)

    return Triple(rotationX / model, rotationY / model, rotationZ / model)
}

/**
 * 点左乘一个矩阵
 *
 * $$
 * x^2(1-c) + c     &   xy(1-c) - zs    &   xz(1-c) + ys    \\
 * yx(1-c) + zs     &   y^2(1-c) + c    &   yz(1-c) - xs    \\
 * xz(1-c) - ys     &   yz(1-c) + xs    &   z^2(1-c) + c    \\
 * $$
 *
 */
fun calculateDestinationPoint(c: Double, s: Double,
                              originalPoint: Triple<Double, Double, Double>,
                              unitRotationAxis: Triple<Double, Double, Double>)
        : Triple<Double, Double, Double>
{
    val (originalX, originalY, originalZ) = originalPoint
    val (unitRotationX, unitRotationY, unitRotationZ) = unitRotationAxis

    val destinationX = (unitRotationX * unitRotationX * (1 - c) + c) * originalX +
            (unitRotationX * unitRotationY * (1 - c) - unitRotationZ * s) * originalY +
            (unitRotationX * unitRotationZ * (1 - c) + unitRotationY * s) * originalZ

    val destinationY = (unitRotationY * unitRotationX * (1 - c) + unitRotationZ * s) * originalX +
            (unitRotationY * unitRotationY * (1 - c) + c) * originalY +
            (unitRotationY * unitRotationZ * (1 - c) - unitRotationX * s) * originalZ

    val destinationZ = (unitRotationX * unitRotationZ * (1 - c) - unitRotationY * s) * originalX +
            (unitRotationY * unitRotationZ * (1 - c) + unitRotationX * s) * originalY +
            (unitRotationZ * unitRotationZ * (1 - c) + c) * originalZ

    return Triple(destinationX, destinationY, destinationZ)
}

fun printPoint(destinationPoint: Triple<Double, Double, Double>) {
    val (destinationX, destinationY, destinationZ) = destinationPoint
    println("%f %f %f".format(destinationX, destinationY, destinationZ))
}

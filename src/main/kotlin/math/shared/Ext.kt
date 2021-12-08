package math.shared

import org.apache.commons.math3.complex.Complex

fun Double.asComplex() = Complex(this)
fun Int.asComplex() = Complex(this.toDouble())

fun ClosedFloatingPointRange<Double>.linSpace(
    n: Int,
    function: (Double) -> Double = { it }
): List<Double> {

    val hx = (endInclusive - start) / n
    return (0 until n).map { function(start + hx * it) }
}

fun ClosedFloatingPointRange<Double>.linSpace2D(
    n: Int,
    function: (Double, Double) -> Double = { a, b -> a }
): List<List<Double>> {

    val hx = (endInclusive - start) / n

    return (0 until n).map { i ->
        (0 until n).map { j ->
            function(start + hx * i, start + hx * j)
        }
    }
}
import math.shared.asComplex
import org.apache.commons.math3.complex.Complex
import kotlin.math.absoluteValue

typealias Limit = Pair<Int, Int>

typealias Points = List<Pair<Complex, Complex>>

private fun Limit.ksi(sectionNum: Int, count: Int): Pair<Double, Double> {
    return h(count).let { length -> first + length * sectionNum to length }
}

private fun Limit.h(count: Int): Double {
    return (second - first).toDouble() / count
}

private fun k(x: Double, ksi: Double, alpha: Double): Complex {
    return (-alpha * (x - ksi).absoluteValue).asComplex().exp()
}

private fun f(x: Double) = Complex.I.multiply(x / 10).exp()

fun fChart(
    n: Int,
    abLimit: Limit = -5 to 5,
) = (0 until n).map {
    val (x, _) = abLimit.ksi(it, n)
    x.asComplex() to f(x)
}

fun runCharts(
    abLimit: Limit = -5 to 5,
    pqLimit: Limit = -10 to 10,
    alpha: Double = 1.0,
    n: Int = 1000,
    m: Int = 1000
): Points {

    return (0 until m).map { index ->
        val (ksi, _) = pqLimit.ksi(index, m)
        ksi.asComplex() to fl(abLimit, ksi, alpha, n)
    }.toList()
}

fun fl(abLimit: Limit, ksi: Double, alpha: Double, discreteCount: Int): Complex {

    var result = 0.asComplex()

    for (i in 0 until discreteCount) {

        val (x, lengthX) = abLimit.ksi(i, discreteCount)

        val fl = k(x, ksi, alpha)
            .multiply(f(x))
            .multiply(lengthX)

        result = result.add(fl)
    }
    return result
}

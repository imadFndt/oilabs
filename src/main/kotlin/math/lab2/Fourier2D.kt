package math.lab2

import math.shared.asComplex
import math.shared.linSpace
import math.shared.linSpace2D
import org.apache.commons.math3.complex.Complex
import kotlin.math.pow

fun fft2D(
    inputFun: (Double, Double) -> Double,
    N: Int = 200,
    M: Int = 2.0.pow(11).toInt(),
    a: Double = 5.0
): Pair<List<Double>, MutableList<MutableList<Complex>>> {

    val b = N.toDouble().pow(2) / (4.0 * a * M)

//    val complexes = (-b..b).linSpace2D(N) { _, bebe -> bebe }
//        .map { list -> list.map { it.asComplex() } }

    val discreteFun = (-a..a).linSpace2D(N, inputFun)
        .map { list -> list.map { it.asComplex() } }

    val step = 2 * a / (N - 1)

    val result = discreteFun.map { row ->
        row.baseFFT(
            M = M,
            N = N,
            step = step
        )
    }.map { it.toMutableList() }
        .toMutableList()


    for (i in discreteFun.indices) {

        val column = result.column(i).baseFFT(
            M = M,
            N = N,
            step = step
        )
        result.replaceColumn(
            i, column
        )
    }

    return (-b..b).linSpace(N) to result
}

private fun MutableList<MutableList<Complex>>.replaceColumn(i: Int, baseFFT: List<Complex>) {
    (0 until size).forEach { j -> this[j][i] = baseFFT[j] }
}

private fun <E> List<List<E>>.column(i: Int) = (0 until size).map { j -> this[j][i] }

private fun List<Complex>.baseFFT(M: Int, N: Int, step: Double) = fillZeros(M)
    .swapHalves()
    .libFourier()
    .map { it.multiply(step) }
    .swapHalves()
    .cutMiddle(N)

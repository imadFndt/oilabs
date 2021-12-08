package math.lab3

import math.lab2.*
import org.apache.commons.math3.complex.Complex
import kotlin.math.pow

fun fft2DUpdated(
    heatMap: List<List<Complex>>,
    M: Int = 2.0.pow(11).toInt(),
    a: Double = 5.0
): List<List<Complex>> {

    val step = 2 * a / (heatMap.size - 1)

    val result = heatMap.map { row ->
        row.baseFFT(M = M, N = row.size, step = step, padding = true)
    }.map { it.toMutableList() }
        .toMutableList()


    for (i in result.indices) {

        val before = result.column(i)
        val column = before.baseFFT(M = M, N = before.size, step = step, padding = true)
        result.replaceColumn(i, column)
    }

    return result
}

private fun List<Complex>.baseFFT(M: Int, N: Int, step: Double, padding: Boolean = false) =
    fillZeros(M, paddingEnabled = padding)
        .swapHalves()
        .libFourier()
        .map { it.multiply(step) }
        .swapHalves()
        .cutMiddle(N)
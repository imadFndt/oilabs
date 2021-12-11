package math.lab3

import math.shared.linSpace
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.special.BesselJ
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

const val PHI = 0.0

fun main() {
    val m = 3
    val n = 200
    val rMax = 5.0
    val result = mutableListOf<Chart>()

    val xList = (0.0..rMax).linSpace(n)
    val xHeatMapAxis = createDoubleDimAxis(rMax, n)

    fun inputFun(r: Double, phi: Double = 0.0): Complex {

        return myFun(r, phi)
    }

    val inputResult = xList.map { inputFun(it, 0.0) }

    // 1, график f(r)
    result += Chart(
        type = ChartType.COMPLEX,
        name = "Входной сигнал f(r)",
        x = xList,
        y = inputResult.map { it.abs() },
        y_im = inputResult.map { it.argument }
    )

    // Изображение f(r, phi)
    val heatMap = buildHeatMap(n, rMax, m, ::inputFun)
    // График f(r,phi)
    result += Chart(
        type = ChartType.HEIGHT,
        x = xHeatMapAxis,
        y = xHeatMapAxis,
        name = "Изображение функции",
        abs = heatMap.map2D { it.abs() },
        phi = heatMap.map2D { it.argument },
    )

    val hankelN = n
    val hankelList = xList.map { x ->
        hankel(x, 0.0, rMax, hankelN) { myFun(it, 0.0) }
    }

    result += Chart(
        type = ChartType.COMPLEX,
        name = "Ханкель график",
        x = xList,
        y = hankelList.map { it.abs() },
        y_im = hankelList.map { it.argument }
    )

    val hankelHeatMap = buildHeatMap(hankelN, rMax, m) { x ->
        hankel(x, 0.0, rMax, n) { myFun(it, 0.0) }
    }

    result += Chart(
        type = ChartType.HEIGHT,
        x = xHeatMapAxis,
        y = xHeatMapAxis,
        name = "Ханкель изображение",
        abs = hankelHeatMap.map2D { it.abs() },
        phi = hankelHeatMap.map2D { it.argument },
    )


    val loweredR = rMax// * 2
    val fourierN = 300
    val xHeatMapLower = createDoubleDimAxis(loweredR, fourierN)
    val fftList = fft(r = loweredR, N = fourierN, m = m)

    result += Chart(
        type = ChartType.HEIGHT,
        name = "Фурье",
        x = xHeatMapLower,
        y = xHeatMapLower,
        abs = fftList.map2D { it.abs() },
        phi = fftList.map2D { it.argument }
    )

    result.print("/Users/imadfndt/Desktop/3d-vis-update/3d-visualizer/src/result.json")
}

fun buildHeatMap(
    n: Int,
    R: Double, m: Int,
    func: (Double) -> Complex
): List<List<Complex>> {

    val source = (0.0..R).linSpace(n + 1)
        .map { func(it) }

    return (0..2 * n).map { j ->
        (0..2 * n).map { k ->
            val alpha = sqrt((j - n).pow(2) + (k - n).pow(2)).toInt()

            when {
                alpha > n -> Complex.ZERO
                else -> source[alpha]
            }.vortex(j, k, n, m)
        }
    }
}

fun hankel(
    p: Double,
    left: Double,
    right: Double,
    n: Int,
    func: (Double) -> Complex
): Complex {
    val step = (right - left) / n
    val result = (0 until n).fold(Complex.ZERO) { acc, i ->
        val x = left + i * step
        val res = func(x)
            .multiply(BesselJ.value(PARAM_P, 2 * PI * x * p))
            .multiply(x * step)
        acc.add(res)
    }

    return result.multiply(2 * PI).divide(Complex.I.pow(PARAM_P))
}

fun fft(
    M: Int = 2.0.pow(12).toInt(),
    r: Double,
    N: Int,
    m: Int
): List<List<Complex>> {
    val heatMap = buildHeatMap(N, r, m) {
        myFun(it, 0.0)
    }
    return fft2DUpdated(heatMap, M, r)
}

fun Complex.vortex(j: Int, k: Int, n: Int, m: Int): Complex {

    val vortex = Complex.I
        .multiply(atan2(k - n, j - n))
        .multiply(m)
        .exp()
    return this.multiply(vortex)
}

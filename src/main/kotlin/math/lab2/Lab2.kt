package math.lab2

import math.shared.asComplex
import math.shared.linSpace
import org.apache.commons.math3.complex.Complex
import kotlin.math.pow

interface FFT {
    fun execute(
        inputFun: (Double) -> Double = InputsOneDimensional::beam,
        N: Int = 200,
        M: Int = 2.0.pow(11).toInt(),
        a: Double = 5.0
    ): List<Pair<Complex, Complex>>
}

object LibFFT : FFT {

    override fun execute(inputFun: (Double) -> Double, N: Int, M: Int, a: Double): List<Pair<Complex, Complex>> {

        val b = N.toDouble().pow(2) / (4.0 * a * M)

        val complexes = (-b..b).linSpace(N) { it }
            .map { it.asComplex() }

        val discreteFun = (-a..a).linSpace(N, inputFun)
            .map { it.asComplex() }

        val step = 2 * a / (N - 1)

        val result = discreteFun
            .fillZeros(M)
            .swapHalves()
            .libFourier()
            .libFourier()
            .map { it.multiply(step) }
            .swapHalves()
            .cutMiddle(N)

        return complexes.zip(result)
    }
}

object MyFFT : FFT {

    override fun execute(inputFun: (Double) -> Double, N: Int, M: Int, a: Double): List<Pair<Complex, Complex>> {

        val b = N.toDouble().pow(2) / (4.0 * a * M)

        val xRange = (-a..a).linSpace(M)
        val uRange = (-b..b).linSpace(N)

        val step = 2 * a / N

        return uRange.map { ui ->

            ui.asComplex() to xRange.fold(Complex.ZERO) { acc, xi ->
                acc.add(
                    kernel(ui, xi)
                        .multiply(inputFun(xi))
                        .multiply(step)
                )
            }
        }
    }

    private fun kernel(u: Double, x: Double) = Complex.I.multiply(-2 * Math.PI * u * x).exp()
}

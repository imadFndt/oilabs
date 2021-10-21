package math.lab2

import math.shared.asComplex
import math.shared.linSpace
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType
import kotlin.math.pow

fun libFFT(
    inputFun: (Double) -> Double = InputsOneDimensional::beam,
    N: Int = 200,
    M: Int = 2.0.pow(11).toInt(),
    a: Double = 5.0
): List<Pair<Complex, Complex>> {

    val b = N.toDouble().pow(2) / (4.0 * a * M)

    val complexes = (-b..b).linSpace(N) { it }
        .map { it.asComplex() }

    val discreteFun = (-a..a).linSpace(N, inputFun)
        .map { it.asComplex() }

    val step = 2 * a / (N - 1)

    val result = discreteFun
        .fillZeros(M)
        .swapHalves()
        .fourier()
        .map { it.multiply(step) }
        .swapHalves()
        .cutMiddle(N)

    return complexes.zip(result)
}

fun List<Complex>.fillZeros(M: Int): List<Complex> {

    fun zeros() = List((M - size) / 2) { 0.0.asComplex() }

    return zeros() + this + zeros()
}

fun List<Complex>.fourier(): List<Complex> {

    val optimusPrime = FastFourierTransformer(DftNormalization.STANDARD)
    return optimusPrime.transform(toTypedArray(), TransformType.FORWARD)
        .toList()
}

fun <T> List<T>.cutMiddle(N: Int): List<T> {
    val middle = size / 2
    return subList((middle - N / 2), (middle + N / 2))
}

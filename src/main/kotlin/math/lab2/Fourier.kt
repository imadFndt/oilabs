package math.lab2

import math.shared.asComplex
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType

fun List<Complex>.fillZeros(M: Int): List<Complex> {

    fun zeros() = List((M - size) / 2) { 0.0.asComplex() }

    return zeros() + this + zeros()
}

fun <T> List<T>.swapHalves(): List<T> = subList(size / 2, size) + subList(0, size / 2)

fun List<Complex>.libFourier(): List<Complex> {

    val optimusPrime = FastFourierTransformer(DftNormalization.STANDARD)
    return optimusPrime.transform(toTypedArray(), TransformType.FORWARD)
        .toList()
}

fun <T> List<T>.cutMiddle(N: Int): List<T> {
    val middle = size / 2
    return subList((middle - N / 2), (middle + N / 2))
}

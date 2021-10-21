package math.shared

import org.apache.commons.math3.complex.Complex

fun Double.asComplex() = Complex(this)
fun Int.asComplex() = Complex(this.toDouble())

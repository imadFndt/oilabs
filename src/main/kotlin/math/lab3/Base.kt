package math.lab3

import math.shared.asComplex
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.special.BesselJ

const val PARAM_ALPHA = 2.0
const val PARAM_P = 3.0

fun myFun(r: Double, phi: Double): Complex {

    return BesselJ.value(PARAM_P, PARAM_ALPHA * r).asComplex()
        .multiply(
            Complex.I.multiply(PARAM_P * phi)
                .exp()
        )
}
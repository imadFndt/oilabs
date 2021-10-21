package math.lab2

import kotlin.math.exp
import kotlin.math.pow

object InputsOneDimensional {

    fun beam(x: Double) = exp(x.pow(2))
    fun my(x: Double) = 1 / (1 + x.pow(2))
}

object InputsDoubleDimensional {

    fun beam(x: Double, y: Double) = InputsOneDimensional.beam(x) * InputsOneDimensional.beam(y)
    fun my(x: Double, y: Double) = InputsOneDimensional.my(x) * InputsOneDimensional.my(y)
}

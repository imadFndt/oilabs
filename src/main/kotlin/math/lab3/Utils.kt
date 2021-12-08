package math.lab3

import kotlin.math.pow

fun <T, R> List<List<T>>.map2D(block: (T) -> R): List<List<R>> = map { list ->
    list.map { block(it) }
}

fun createDoubleDimAxis(r: Double, n: Int): List<Double> {
    val step = r / n
    return (0 until 2 * n + 1)
        .map { i -> (i - n) * step }
}


fun atan2(a: Int, b: Int) = kotlin.math.atan2(a.toDouble(), b.toDouble())
fun Int.pow(n: Int) = toDouble().pow(n)
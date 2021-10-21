package math.lab2

fun <T> List<T>.swapHalves(): List<T> = subList(size / 2, size) + subList(0, size / 2)

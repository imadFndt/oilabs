package math.charts

import Points
import javafx.event.EventTarget
import org.apache.commons.math3.complex.Complex
import tornadofx.addClass
import tornadofx.text
import tornadofx.toObservable

fun EventTarget.boldText(string: String) = text {
    addClass(TextStyle.myText)
    text = string
}

fun Points.asObservableAbs() = asObservableNumbers {
    it.first.real as Number to it.second.abs() as Number
}

fun Points.asObservableArgs() = asObservableNumbers {
    it.first.real as Number to it.second.argument as Number
}

private fun Points.asObservableNumbers(op: (Pair<Complex, Complex>) -> Pair<Number, Number>) = map { point ->
    val numbers = op(point)
    javafx.scene.chart.XYChart.Data(numbers.first, numbers.second)
}.toObservable()

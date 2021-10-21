package math

import Limit
import Points
import fChart
import javafx.event.EventTarget
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.chart.XYChart
import math.charts.TextStyle
import math.charts.chartview
import org.apache.commons.math3.complex.Complex
import runCharts
import tornadofx.*

fun main() {
    launch<ChartViewApp>()
}

class ChartViewApp : App(ChartsView::class, TextStyle::class)

class ChartsView : View("Charts") {
    override val root: Parent = scrollpane {
        vbox(spacing = 40) {

            style {
                alignment = Pos.CENTER
            }

            section("Согласно варианту")
            section("α = 10", alpha = 10.0)
            section("Пределы интегрирования (-10; 10)", abLimit = -8 to 8)
            section("m = n = 50", m = 70, n = 70)

            add(boldText("Исходная функция"))
            add(
                chartview(
                    points = fChart(n = 1000).asObservableAbs(),
                    title = "Модуль",
                    xAxisLabel = "ξ",
                    yAxisLabel = "Модуль Fl"
                )
            )

            add(
                chartview(
                    points = fChart(n = 1000).asObservableArgs(),
                    title = "Аргумент",
                    xAxisLabel = "ξ",
                    yAxisLabel = "Аргумент Fl"
                )
            )
        }
    }

    private fun EventTarget.section(
        title: String,
        abLimit: Limit = -5 to 5,
        pqLimit: Limit = -10 to 10,
        alpha: Double = 1.0,
        n: Int = 1000,
        m: Int = 1000
    ) {
        add(boldText(title))
        add(
            chartview(
                points = runCharts(abLimit, pqLimit, alpha, n, m).asObservableAbs(),
                title = "Модуль",
                xAxisLabel = "ξ",
                yAxisLabel = "Модуль Fl"
            )
        )

        add(
            chartview(
                points = runCharts(abLimit, pqLimit, alpha, n, m).asObservableArgs(),
                title = "Аргумент",
                xAxisLabel = "ξ",
                yAxisLabel = "Аргумент Fl"
            )
        )
    }
}

fun EventTarget.boldText(string: String) = text {
    addClass(TextStyle.myText)
    text = string
}

private fun Points.asObservableAbs() = asObservableNumbers {
    it.first.real as Number to it.second.abs() as Number
}

private fun Points.asObservableArgs() = asObservableNumbers {
    it.first.real as Number to it.second.argument as Number
}

private fun Points.asObservableNumbers(op: (Pair<Complex, Complex>) -> Pair<Number, Number>) = map { point ->
    val numbers = op(point)
    XYChart.Data(numbers.first, numbers.second)
}.toObservable()

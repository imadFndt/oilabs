package math.lab1.view

import fChart
import javafx.event.EventTarget
import math.charts.asObservableAbs
import math.charts.asObservableArgs
import math.charts.boldText
import math.charts.chartview
import math.lab1.lab1Section
import tornadofx.add

fun EventTarget.drawLab1() {
    lab1Section("Согласно варианту")
    lab1Section("α = 10", alpha = 10.0)
    lab1Section("Пределы интегрирования (-10; 10)", abLimit = -8 to 8)
    lab1Section("m = n = 50", m = 70, n = 70)

    add(boldText("Исходная функция"))
    add(
        chartview(
            points = fChart(n = 1000).asObservableAbs(),
            title = "Модуль",
            xAxisLabel = "ξ",
            yAxisLabel = "Модуль Fl"
        ).root
    )

    add(
        chartview(
            points = fChart(n = 1000).asObservableArgs(),
            title = "Аргумент",
            xAxisLabel = "ξ",
            yAxisLabel = "Аргумент Fl"
        ).root
    )
}

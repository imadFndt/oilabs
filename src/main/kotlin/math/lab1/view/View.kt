package math.lab1

import Limit
import javafx.event.EventTarget
import math.charts.asObservableAbs
import math.charts.asObservableArgs
import math.charts.boldText
import math.charts.chartview
import runCharts
import tornadofx.add

fun EventTarget.lab1Section(
    title: String,
    abLimit: Limit = -5.0 to 5.0,
    pqLimit: Limit = -10.0 to 10.0,
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
        ).root
    )

    add(
        chartview(
            points = runCharts(abLimit, pqLimit, alpha, n, m).asObservableArgs(),
            title = "Аргумент",
            xAxisLabel = "ξ",
            yAxisLabel = "Аргумент Fl"
        ).root
    )
}

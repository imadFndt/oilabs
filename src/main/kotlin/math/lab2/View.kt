package math.lab2

import Points
import javafx.event.EventTarget
import math.charts.asObservableAbs
import math.charts.asObservableArgs
import math.charts.boldText
import math.charts.chartview
import tornadofx.add

fun EventTarget.lab2Section(
    title: String,
) {
    val points = MyFFT.execute()
    add(boldText(title))
    add(
        chartview(
            points = points.asObservableAbs(),
            title = "Модуль",
            xAxisLabel = "ξ",
            yAxisLabel = "Модуль Fl"
        ).root
    )

    add(
        chartview(
            points = points.asObservableArgs(),
            title = "Аргумент",
            xAxisLabel = "ξ",
            yAxisLabel = "Аргумент Fl"
        ).root
    )
}

fun EventTarget.funSection(
    title: String,
    block: () -> Points
) {
    add(boldText(title))
    add(
        chartview(
            points = block().asObservableAbs(),
            title = "Модуль",
            xAxisLabel = "ξ",
            yAxisLabel = "Модуль Fl"
        ).root
    )

    add(
        chartview(
            points = block().asObservableArgs(),
            title = "Аргумент",
            xAxisLabel = "ξ",
            yAxisLabel = "Аргумент Fl"
        ).root
    )
}

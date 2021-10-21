package math.charts

import javafx.collections.ObservableList
import javafx.event.EventTarget
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.text.FontWeight
import tornadofx.*

fun EventTarget.chartview(
    points: ObservableList<XYChart.Data<Number, Number>>,
    title: String = "График",
    xAxisLabel: String = "",
    yAxisLabel: String = ""
): View = ChartView(points, title, xAxisLabel, yAxisLabel).also {
    add(it.root)
}

private class ChartView(

    points: ObservableList<XYChart.Data<Number, Number>>,
    chartTitle: String,
    xAxisLabel: String,
    yAxisLabel: String

) : View("Chart") {
    override val root = gridpane()

    init {

        scatterchart(
            title = chartTitle,
            x = NumberAxis(xAxisLabel),
            y = NumberAxis(yAxisLabel)
        ) {
            series(name = chartTitle, elements = points)
        }
    }

    fun NumberAxis(label: String) = NumberAxis().apply {
        this.label = label
    }
}

class TextStyle : Stylesheet() {

    companion object {
        val myText by cssclass()
    }

    init {

        myText {
            +mixin { fontWeight = FontWeight.EXTRA_BOLD }
            +mixin { fontSize = Dimension(40.0, Dimension.LinearUnits.px) }
        }
    }
}

package math

import javafx.geometry.Pos
import javafx.scene.Parent
import math.charts.TextStyle
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
        }
    }
}

package math

import javafx.geometry.Pos
import javafx.scene.Parent
import math.charts.TextStyle
import math.lab2.InputsOneDimensional
import math.lab2.funSection
import math.lab2.lab2Section
import math.shared.asComplex
import math.shared.linSpace
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

            lab2Section("Библиотека")

            funSection("Хуйня))))") {

                (-5.0..5.0).linSpace(200) { it }
                    .map { it.asComplex() to InputsOneDimensional.beam(it).asComplex() }
            }
        }
    }
}

package math

import com.google.gson.GsonBuilder
import javafx.geometry.Pos
import javafx.scene.Parent
import ksi
import math.charts.TextStyle
import math.lab1.lab1Section
import math.lab2.*
import math.lab2.model.FFTExport
import math.shared.asComplex
import tornadofx.*
import java.io.File
import java.io.FileOutputStream
import kotlin.math.absoluteValue

fun main() {
    val my = fft2D(
        InputsDoubleDimensional::my
    )

    val beam = fft2D(InputsDoubleDimensional::beam)

    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    val file = File("/Users/imadfndt/Desktop/3d-visualizer/src/result.json").apply {
        createNewFile()
    }

    val export = FFTExport(
        x = my.first,
        y = my.first,
        zAbs = my.second.map { list -> list.map { it.abs() } },
        zPhi = my.second.map { list -> list.map { it.argument } },
        gAbs = beam.second.map { list -> list.map { it.abs() } },
        gPhi = beam.second.map { list -> list.map { it.argument } },
    )

    val exported = gson.toJson(export)
    FileOutputStream(file).use {
        it.writer().use { writer ->
            writer.write(exported)
            writer.flush()
        }
    }


    launch<ChartViewApp>()
}

class ChartViewApp : App(ChartsView::class, TextStyle::class)

class ChartsView : View("Charts") {
    override val root: Parent = scrollpane {
        vbox(spacing = 40) {

            lab1Section("пизда")

            style {
                alignment = Pos.CENTER
            }

            lab2Section1d("Библиотека", LibFFT, InputsOneDimensional::my)

            lab2Section1d("Классика", MyFFT, InputsOneDimensional::my)

            val fX = { x: Double ->
                x.absoluteValue.asComplex()
                    .multiply(Math.PI)
                    .multiply(-2)
                    .exp()
                    .multiply(Math.PI)
            }

            funSection("Аналитически") {
                val n = 1000
                (0 until n).map {
                    val (x, _) = (-5.0 to 5.0).ksi(it, n)
                    x.asComplex() to fX(x)
                }
            }

        }
    }
}

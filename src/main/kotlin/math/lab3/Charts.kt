package math.lab3

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.File
import java.io.FileOutputStream

enum class ChartType {
    @SerializedName("float")
    FLOAT,

    @SerializedName("complex")
    COMPLEX,

    @SerializedName("height")
    HEIGHT,

    @SerializedName("complex3d")
    COMPLEX3D
}

data class Chart(
    val type: ChartType,
    val name: String,
    val x: List<Double>,
    val y: List<Double>,
    val y_im: List<Double>? = null,
    val abs: List<List<Double>>? = null,
    val phi: List<List<Double>>? = null,
)

fun List<Chart>.print(fileName: String = "result.json") {
    val gson = GsonBuilder()
        .create()

    val file = File(fileName).apply {
        createNewFile()
    }

    FileOutputStream(file).use {
        it.writer().use { writer ->
            writer.write(gson.toJson(this))
            writer.flush()
        }
    }
}
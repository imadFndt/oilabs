package math.lab2.model

import com.google.gson.annotations.SerializedName

data class FFTExport(

    @SerializedName("x")
    val x: List<Double>,

    @SerializedName("y")
    val y: List<Double>,


    @SerializedName("zAbs")
    val zAbs: List<List<Double>>,

    @SerializedName("zPhi")
    val zPhi: List<List<Double>>,


    @SerializedName("gAbs")
    val gAbs: List<List<Double>>,

    @SerializedName("gPhi")
    val gPhi: List<List<Double>>,
)
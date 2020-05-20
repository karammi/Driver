package ir.brn.remote.model.wall

import com.google.gson.annotations.SerializedName

data class LocationWallModel(@SerializedName("lat") val lat: Double? = null,
                             @SerializedName("lng") val lng: Double? = null,
                             @SerializedName("province") val province: ProvincesModel? = null,
                             @SerializedName("city") val city: CityModel? = null)
package ir.brn.remote.model.wall

import com.google.gson.annotations.SerializedName

data class WallModel(@SerializedName("id") val id: Int?,
                     @SerializedName("user") val user: UserWallModel?,
                     @SerializedName("body") val body: String?,
                     @SerializedName("picture") val picture: String? = null,
                     @SerializedName("like") val like: Int? = 0,
                     @SerializedName("dislike") val disLike: Int? = 0,
                     @SerializedName("created_at") val createdAt: String?,
                     @SerializedName("Location") val location: LocationWallModel?,
                     @SerializedName("carrier_type") val carrierType: CarrierModel? = null,
                     @SerializedName("truck_type") val truckType: TruckModel? = null,
                     @SerializedName("created_at_shamsi") val createdAtShamsi: String?,
                     @SerializedName("hashtags") val hashTags: List<HashTagModel>?)
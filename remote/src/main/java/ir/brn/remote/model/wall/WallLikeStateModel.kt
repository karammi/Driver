package ir.brn.remote.model.wall

import com.google.gson.annotations.SerializedName

data class WallLikeStateModel(@SerializedName("like") val like: Int?,
                              @SerializedName("dislike") val disLike: Int?,
                              @SerializedName("state") val state: String?)
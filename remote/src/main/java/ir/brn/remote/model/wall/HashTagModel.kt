package ir.brn.remote.model.wall

import com.google.gson.annotations.SerializedName

data class HashTagModel(@SerializedName("id") val id: Int?,
                        @SerializedName("text") val text: String?)
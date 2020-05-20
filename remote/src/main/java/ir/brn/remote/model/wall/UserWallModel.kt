package ir.brn.remote.model.wall

import com.google.gson.annotations.SerializedName

data class UserWallModel(@SerializedName("id")val id: Int?,
                         @SerializedName("name")val name: String?)
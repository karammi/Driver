package ir.brn.remote.model.product

import com.google.gson.annotations.SerializedName

data class CategoryModel(@SerializedName("id") var id: Int?,
                         @SerializedName("name") var name: String?,
                         @SerializedName("has_category")var hasCategory: Boolean?)
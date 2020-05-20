package ir.brn.remote.model.product

import com.google.gson.annotations.SerializedName

data class SpecificationModel(@SerializedName("valued") var valued: List<ValuedModel>?,
                              @SerializedName("unvalued") var unValued: List<UnValuedModel>?)
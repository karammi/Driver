package ir.brn.remote.model.product

import com.google.gson.annotations.SerializedName

data class ProductModel(@SerializedName("id") var id: Int?,
                        @SerializedName("product_category") var productCategory: CategoryModel?,
                        @SerializedName("barcode") var barcode: String?,
                        @SerializedName("price") var price: String?,
                        @SerializedName("expiry_months") var expiryMonths: Int?,
                        @SerializedName("title") var title: String?,
                        @SerializedName("description") var description: String?,
                        @SerializedName("introduction") var introduction: String?,
                        @SerializedName("application") var application: String?,
                        @SerializedName("advantage") var advantage: String?,
                        @SerializedName("picture_filename") var pictureFilename: String?,
                        @SerializedName("updated_at") var updatedAt: String?,
                        @SerializedName("product_specifications") var productSpecifications: SpecificationModel?)
package ir.brn.presentation.model.product

data class ProductView(var id: Int?,
                       var productCategory: CategoryView?,
                       var barcode: String?,
                       var price: String?,
                       var expiryMonths: Int?,
                       var title: String?,
                       var description: String?,
                       var introduction: String?,
                       var application: String?,
                       var advantage: String?,
                       var pictureFilename: String?,
                       var updatedAt: String?,
                       var productSpecifications: SpecificationView?)
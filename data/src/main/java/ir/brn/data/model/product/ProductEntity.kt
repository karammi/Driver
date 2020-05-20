package ir.brn.data.model.product

data class ProductEntity(var id: Int?,
                         var productCategory: CategoryEntity?,
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
                         var productSpecifications: SpecificationEntity?)
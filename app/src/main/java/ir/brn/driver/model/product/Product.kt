package ir.brn.driver.model.product

data class Product(var id: Int?,
                   var productCategory: Category?,
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
                   var productSpecifications: Specification?)
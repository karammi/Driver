package ir.brn.driver.mapper.product

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.product.Product
import ir.brn.presentation.model.product.ProductView
import javax.inject.Inject

class ProductViewMapper @Inject constructor(
        private val specificationViewMapper: SpecificationViewMapper,
        private val categoryViewMapper: CategoryViewMapper) : ViewMapper<ProductView, Product> {

    override fun mapToView(presentation: ProductView): Product {
        return Product(presentation.id,
                presentation.productCategory?.let { categoryViewMapper.mapToView(it) },
                presentation.barcode,
                presentation.price,
                presentation.expiryMonths,
                presentation.title,
                presentation.description,
                presentation.introduction,
                presentation.application,
                presentation.advantage,
                presentation.pictureFilename,
                presentation.updatedAt,
                presentation.productSpecifications?.let { specificationViewMapper.mapToView(it) })
    }
}
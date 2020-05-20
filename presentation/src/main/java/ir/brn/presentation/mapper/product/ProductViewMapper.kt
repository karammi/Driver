package ir.brn.presentation.mapper.product

import ir.brn.domain.model.product.Product
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.product.ProductView
import javax.inject.Inject

class ProductViewMapper @Inject constructor(
        private val categoryViewMapper: CategoryViewMapper,
        private val specificationViewMapper: SpecificationViewMapper) : Mapper<ProductView, Product> {

    override fun mapToView(type: Product): ProductView {
        return ProductView(type.id,
                type.productCategory?.let { categoryViewMapper.mapToView(it) },
                type.barcode,
                type.price,
                type.expiryMonths,
                type.title,
                type.description,
                type.introduction,
                type.application,
                type.advantage,
                type.pictureFilename,
                type.updatedAt,
                type.productSpecifications?.let { specificationViewMapper.mapToView(it) })
    }

    override fun mapFromView(view: ProductView): Product {
        return Product(view.id,
                view.productCategory?.let { categoryViewMapper.mapFromView(it) },
                view.barcode,
                view.price,
                view.expiryMonths,
                view.title,
                view.description,
                view.introduction,
                view.application,
                view.advantage,
                view.pictureFilename,
                view.updatedAt,
                view.productSpecifications?.let { specificationViewMapper.mapFromView(it) })
    }
}
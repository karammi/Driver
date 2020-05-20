package ir.brn.remote.mapper.product

import ir.brn.data.model.product.ProductEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.product.ProductModel
import javax.inject.Inject

class ProductMapper @Inject constructor(
        private val specificationMapper: SpecificationMapper,
        private val categoryMapper: CategoryMapper) : ModelMapper<ProductModel, ProductEntity> {

    override fun mapFromModel(model: ProductModel): ProductEntity {
        return ProductEntity(model.id,
                model.productCategory?.let { categoryMapper.mapFromModel(it) },
                model.barcode,
                model.price,
                model.expiryMonths,
                model.title,
                model.description,
                model.introduction,
                model.application,
                model.advantage,
                model.pictureFilename,
                model.updatedAt,
                model.productSpecifications?.let {
                    specificationMapper.mapFromModel(it)
                })
    }
}
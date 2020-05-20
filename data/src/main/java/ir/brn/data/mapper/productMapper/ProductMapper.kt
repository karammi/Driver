package ir.brn.data.mapper.productMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.product.ProductEntity
import ir.brn.domain.model.product.Product
import javax.inject.Inject

class ProductMapper @Inject constructor(
        private val specificationMapper: SpecificationMapper,
        private val categoryMapper: CategoryMapper
) : EntityMapper<ProductEntity, Product> {

    override fun mapFromEntity(entity: ProductEntity): Product {
        return Product(entity.id,
                entity.productCategory?.let { categoryMapper.mapFromEntity(it) },
                entity.barcode,
                entity.price,
                entity.expiryMonths,
                entity.title,
                entity.description,
                entity.introduction,
                entity.application,
                entity.advantage,
                entity.pictureFilename,
                entity.updatedAt,
                entity.productSpecifications?.let { specificationMapper.mapFromEntity(it) })
    }

    override fun mapToEntity(domain: Product): ProductEntity {
        return ProductEntity(domain.id,
                domain.productCategory?.let { categoryMapper.mapToEntity(it) },
                domain.barcode,
                domain.price,
                domain.expiryMonths,
                domain.title,
                domain.description,
                domain.introduction,
                domain.application,
                domain.advantage,
                domain.pictureFilename,
                domain.updatedAt,
                domain.productSpecifications?.let { specificationMapper.mapToEntity(it) })
    }
}
package ir.brn.data.mapper.productMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.product.CategoryEntity
import ir.brn.domain.model.product.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() : EntityMapper<CategoryEntity, Category> {

    override fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(entity.id,
                entity.name,
                entity.hasCategory)
    }

    override fun mapToEntity(domain: Category): CategoryEntity {
        return CategoryEntity(domain.id,
                domain.name,
                domain.hasCategory)
    }
}
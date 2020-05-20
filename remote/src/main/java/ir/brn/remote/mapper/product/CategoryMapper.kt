package ir.brn.remote.mapper.product

import ir.brn.data.model.product.CategoryEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.product.CategoryModel
import javax.inject.Inject

class CategoryMapper @Inject constructor() : ModelMapper<CategoryModel, CategoryEntity> {

    override fun mapFromModel(model: CategoryModel): CategoryEntity {
        return CategoryEntity(model.id,
                model.name,
                model.hasCategory)
    }
}
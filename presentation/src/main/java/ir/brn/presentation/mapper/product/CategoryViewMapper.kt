package ir.brn.presentation.mapper.product

import ir.brn.domain.model.product.Category
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.product.CategoryView
import javax.inject.Inject

class CategoryViewMapper @Inject constructor() : Mapper<CategoryView, Category> {

    override fun mapToView(type: Category): CategoryView {
        return CategoryView(type.id,
                type.name,
                type.hasCategory)
    }

    override fun mapFromView(view: CategoryView): Category {
        return Category(view.id,
                view.name,
                view.hasCategory)
    }
}
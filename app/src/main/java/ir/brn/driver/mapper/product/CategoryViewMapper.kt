package ir.brn.driver.mapper.product

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.product.Category
import ir.brn.presentation.model.product.CategoryView
import javax.inject.Inject

class CategoryViewMapper @Inject constructor() : ViewMapper<CategoryView, Category> {

    override fun mapToView(presentation: CategoryView): Category {
        return Category(presentation.id,
                presentation.name,
                presentation.hasCategory)
    }
}
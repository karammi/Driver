package ir.brn.driver.mapper.product

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.product.UnValued
import ir.brn.presentation.model.product.UnValuedView
import javax.inject.Inject

class UnValuedMapper @Inject constructor() : ViewMapper<UnValuedView, UnValued> {

    override fun mapToView(presentation: UnValuedView): UnValued {
        return UnValued(presentation.name,
                presentation.type)
    }
}
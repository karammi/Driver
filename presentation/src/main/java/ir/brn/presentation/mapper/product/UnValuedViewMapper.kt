package ir.brn.presentation.mapper.product

import ir.brn.domain.model.product.UnValued
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.product.UnValuedView
import javax.inject.Inject

class UnValuedViewMapper @Inject constructor() : Mapper<UnValuedView, UnValued> {

    override fun mapToView(type: UnValued): UnValuedView {
        return UnValuedView(type.name,
                type.type)
    }

    override fun mapFromView(view: UnValuedView): UnValued {
        return UnValued(view.name,
                view.type)
    }
}
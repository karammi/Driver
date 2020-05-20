package ir.brn.presentation.mapper.product

import ir.brn.domain.model.product.Valued
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.product.ValuedView
import javax.inject.Inject

class ValuedViewMapper @Inject constructor() : Mapper<ValuedView, Valued> {
    override fun mapToView(type: Valued): ValuedView {
        return ValuedView(type.name,
                type.type,
                type.value)
    }

    override fun mapFromView(view: ValuedView): Valued {
        return Valued(view.name,
                view.type,
                view.value)
    }
}
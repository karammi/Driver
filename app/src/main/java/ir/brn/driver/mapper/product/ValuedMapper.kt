package ir.brn.driver.mapper.product

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.product.Valued
import ir.brn.presentation.model.product.ValuedView
import javax.inject.Inject

class ValuedMapper @Inject constructor():ViewMapper<ValuedView,Valued> {

    override fun mapToView(presentation: ValuedView): Valued {
        return Valued(presentation.name,
                presentation.type,
                presentation.value)
    }
}
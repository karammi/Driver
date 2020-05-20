package ir.brn.driver.mapper.product

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.product.Specification
import ir.brn.presentation.model.product.SpecificationView
import javax.inject.Inject

class SpecificationViewMapper @Inject constructor(
        private val valuedMapper: ValuedMapper,
        private val unValuedMapper: UnValuedMapper
) : ViewMapper<SpecificationView, Specification> {

    override fun mapToView(presentation: SpecificationView): Specification {
        return Specification(presentation.valued?.let { list -> list.map { valuedMapper.mapToView(it) } },
                presentation.unvalued?.let { list -> list.map { unValuedMapper.mapToView(it) } })
    }
}
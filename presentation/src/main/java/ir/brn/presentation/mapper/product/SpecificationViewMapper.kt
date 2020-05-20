package ir.brn.presentation.mapper.product

import ir.brn.domain.model.product.Specification
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.product.SpecificationView
import javax.inject.Inject

class SpecificationViewMapper @Inject constructor(
        private val valuedViewMapper: ValuedViewMapper,
        private val unValuedViewMapper: UnValuedViewMapper
) : Mapper<SpecificationView, Specification> {

    override fun mapToView(type: Specification): SpecificationView {
        return SpecificationView(type.valued?.let { list -> list.map { valuedViewMapper.mapToView(it) } },
                type.unvalued?.let { list -> list.map { unValuedViewMapper.mapToView(it) } })
    }

    override fun mapFromView(view: SpecificationView): Specification {
        return Specification(view.valued?.let { list -> list.map { valuedViewMapper.mapFromView(it) } },
                view.unvalued?.let { list -> list.map { unValuedViewMapper.mapFromView(it) } })
    }
}
package ir.brn.remote.mapper.product

import ir.brn.data.model.product.SpecificationEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.product.SpecificationModel
import javax.inject.Inject

class SpecificationMapper @Inject constructor(
        private val unValuedMapper: UnValuedMapper,
        private val valuedMapper: ValuedMapper
) : ModelMapper<SpecificationModel, SpecificationEntity> {

    override fun mapFromModel(model: SpecificationModel): SpecificationEntity {
        return SpecificationEntity(model.valued?.let { list -> list.map { valuedMapper.mapFromModel(it) } },
                model.unValued?.let { list -> list.map { unValuedMapper.mapFromModel(it) } })
    }
}
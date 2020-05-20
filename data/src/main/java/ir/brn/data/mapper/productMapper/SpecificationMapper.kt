package ir.brn.data.mapper.productMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.product.SpecificationEntity
import ir.brn.domain.model.product.Specification
import javax.inject.Inject

class SpecificationMapper @Inject constructor(
        private val valuedMapper: ValuedMapper,
        private val unValuedMapper: UnValuedMapper
) : EntityMapper<SpecificationEntity, Specification> {

    override fun mapFromEntity(entity: SpecificationEntity): Specification {
        return Specification(entity.valued?.let { list -> list.map { valuedMapper.mapFromEntity(it) } },
                entity.unValued?.let { list -> list.map { unValuedMapper.mapFromEntity(it) } })
    }

    override fun mapToEntity(domain: Specification): SpecificationEntity {
        return SpecificationEntity(domain.valued?.let { list -> list.map { valuedMapper.mapToEntity(it) } },
                domain.unvalued?.let { list -> list.map { unValuedMapper.mapToEntity(it) } })
    }
}
package ir.brn.data.mapper.productMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.product.ValuedEntity
import ir.brn.domain.model.product.Valued
import javax.inject.Inject

class ValuedMapper @Inject constructor() : EntityMapper<ValuedEntity, Valued> {

    override fun mapFromEntity(entity: ValuedEntity): Valued {
        return Valued(entity.name,
                entity.type,
                entity.value)
    }

    override fun mapToEntity(domain: Valued): ValuedEntity {
        return ValuedEntity(domain.name,
                domain.type,
                domain.value)
    }
}
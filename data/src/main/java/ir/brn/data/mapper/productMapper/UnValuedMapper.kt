package ir.brn.data.mapper.productMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.product.UnValuedEntity
import ir.brn.domain.model.product.UnValued
import javax.inject.Inject

class UnValuedMapper @Inject constructor() : EntityMapper<UnValuedEntity, UnValued> {

    override fun mapFromEntity(entity: UnValuedEntity): UnValued {
        return UnValued(entity.name,
                entity.type)
    }

    override fun mapToEntity(domain: UnValued): UnValuedEntity {
        return UnValuedEntity(domain.name,
                domain.type)
    }
}
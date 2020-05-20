package ir.brn.remote.mapper.product

import ir.brn.data.model.product.ValuedEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.product.ValuedModel
import javax.inject.Inject

class ValuedMapper @Inject constructor() : ModelMapper<ValuedModel, ValuedEntity> {

    override fun mapFromModel(model: ValuedModel): ValuedEntity {
        return ValuedEntity(model.name,
                model.type,
                model.value)
    }
}
package ir.brn.remote.mapper.product

import ir.brn.data.model.product.UnValuedEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.product.UnValuedModel
import javax.inject.Inject

class UnValuedMapper @Inject constructor() : ModelMapper<UnValuedModel, UnValuedEntity> {

    override fun mapFromModel(model: UnValuedModel): UnValuedEntity {
        return UnValuedEntity(model.name,
                model.type)
    }
}
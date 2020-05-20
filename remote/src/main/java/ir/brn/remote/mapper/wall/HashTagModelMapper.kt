package ir.brn.remote.mapper.wall

import ir.brn.data.model.wall.HashTagEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.wall.HashTagModel
import javax.inject.Inject

class HashTagModelMapper @Inject constructor() : ModelMapper<HashTagModel, HashTagEntity> {

    override fun mapFromModel(model: HashTagModel): HashTagEntity {
        return HashTagEntity(model.id,
                model.text)
    }

    fun mapToModel(entity: HashTagEntity): HashTagModel {
        return HashTagModel(entity.id,
                entity.text)
    }
}
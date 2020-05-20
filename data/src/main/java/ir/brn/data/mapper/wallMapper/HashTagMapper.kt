package ir.brn.data.mapper.wallMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.wall.HashTagEntity
import ir.brn.domain.model.wall.HashTag
import javax.inject.Inject

class HashTagMapper @Inject constructor() : EntityMapper<HashTagEntity, HashTag> {

    override fun mapFromEntity(entity: HashTagEntity): HashTag {
        return HashTag(entity.id,
                entity.text)
    }

    override fun mapToEntity(domain: HashTag): HashTagEntity {
        return HashTagEntity(domain.id,
                domain.text)
    }
}
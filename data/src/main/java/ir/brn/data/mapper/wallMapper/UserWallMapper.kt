package ir.brn.data.mapper.wallMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.wall.UserWallEntity
import ir.brn.domain.model.wall.UserWall
import javax.inject.Inject

class UserWallMapper @Inject constructor() : EntityMapper<UserWallEntity, UserWall> {

    override fun mapFromEntity(entity: UserWallEntity): UserWall {
        return UserWall(entity.id,
                entity.name)
    }

    override fun mapToEntity(domain: UserWall): UserWallEntity {
        return UserWallEntity(domain.id,
                domain.name)
    }
}
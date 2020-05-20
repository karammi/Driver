package ir.brn.remote.mapper.wall

import ir.brn.data.model.wall.UserWallEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.wall.UserWallModel
import javax.inject.Inject

class UserWallModelMapper @Inject constructor() : ModelMapper<UserWallModel, UserWallEntity> {
    override fun mapFromModel(model: UserWallModel): UserWallEntity {
        return UserWallEntity(model.id,
                model.name)
    }
}
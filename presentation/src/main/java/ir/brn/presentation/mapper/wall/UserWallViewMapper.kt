package ir.brn.presentation.mapper.wall

import ir.brn.domain.model.wall.UserWall
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.wall.UserWallView
import javax.inject.Inject

class UserWallViewMapper @Inject constructor() : Mapper<UserWallView, UserWall> {

    override fun mapToView(type: UserWall): UserWallView {
        return UserWallView(type.id,
                type.name)
    }

    override fun mapFromView(view: UserWallView): UserWall {
        return UserWall(view.id,
                view.name)
    }
}
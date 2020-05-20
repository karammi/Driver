package ir.brn.driver.mapper.wall

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.wall.UserWall
import ir.brn.presentation.model.wall.UserWallView
import javax.inject.Inject

class UserWallViewMapper @Inject constructor() : ViewMapper<UserWallView, UserWall> {

    override fun mapToView(presentation: UserWallView): UserWall {
        return UserWall(presentation.id,
                presentation.name)
    }

    fun mapFromView(presentation: UserWall): UserWallView {
        return UserWallView(presentation.id,
                presentation.name)
    }

}
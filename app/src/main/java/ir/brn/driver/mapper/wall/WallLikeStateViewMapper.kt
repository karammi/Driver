package ir.brn.driver.mapper.wall

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.wall.WallLikeState
import ir.brn.presentation.model.wall.WallLikeStateView
import javax.inject.Inject

class WallLikeStateViewMapper @Inject constructor():ViewMapper<WallLikeStateView,WallLikeState> {

    override fun mapToView(presentation: WallLikeStateView): WallLikeState {
        return WallLikeState(presentation.like,
                presentation.disLike,
                presentation.state)
    }
}
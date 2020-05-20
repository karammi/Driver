package ir.brn.presentation.mapper.wall

import ir.brn.domain.model.wall.WallLikeState
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.wall.WallLikeStateView
import javax.inject.Inject

class WallLikeStateViewMapper @Inject constructor() : Mapper<WallLikeStateView, WallLikeState> {

    override fun mapToView(type: WallLikeState): WallLikeStateView {
        return WallLikeStateView(type.like,
                type.disLike,
                type.state)
    }

    override fun mapFromView(view: WallLikeStateView): WallLikeState {
        return WallLikeState(view.like,
                view.disLike,
                view.state)
    }
}
package ir.brn.remote.mapper.wall

import ir.brn.data.model.wall.WallLikeStateEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.wall.WallLikeStateModel
import javax.inject.Inject

class WallLikeStateModelMapper @Inject constructor() : ModelMapper<WallLikeStateModel, WallLikeStateEntity> {

    override fun mapFromModel(model: WallLikeStateModel): WallLikeStateEntity {
        return WallLikeStateEntity(model.like,
                model.disLike,
                model.state)
    }
}
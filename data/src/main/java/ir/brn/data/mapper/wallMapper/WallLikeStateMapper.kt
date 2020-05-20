package ir.brn.data.mapper.wallMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.model.wall.WallLikeStateEntity
import ir.brn.domain.model.wall.WallLikeState
import javax.inject.Inject

class WallLikeStateMapper @Inject constructor() : EntityMapper<WallLikeStateEntity, WallLikeState> {

    override fun mapFromEntity(entity: WallLikeStateEntity): WallLikeState {
        return WallLikeState(entity.like,
                entity.disLike,
                entity.state)
    }

    override fun mapToEntity(domain: WallLikeState): WallLikeStateEntity {
        return WallLikeStateEntity(domain.like,
                domain.disLike,
                domain.state)
    }
}
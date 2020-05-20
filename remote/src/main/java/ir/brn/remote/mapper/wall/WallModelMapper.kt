package ir.brn.remote.mapper.wall

import ir.brn.data.model.wall.WallEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.wall.WallModel
import javax.inject.Inject

class WallModelMapper @Inject constructor(
        private val userWallModelMapper: UserWallModelMapper,
        private val locationWallModelMapper: LocationWallModelMapper,
        private val carrierMapper: CarrierMapper,
        private val truckMapper: TruckMapper,
        private val hashTagModelMapper: HashTagModelMapper) : ModelMapper<WallModel, WallEntity> {

    override fun mapFromModel(model: WallModel): WallEntity {
        return WallEntity(model.id,
                model.user?.let { userWallModelMapper.mapFromModel(it) },
                model.body,
                model.picture,
                model.like,
                model.disLike,
                model.createdAt,
                model.location?.let { locationWallModelMapper.mapFromModel(it) },
                model.carrierType?.let { carrierMapper.mapFromModel(it) },
                model.truckType?.let { truckMapper.mapFromModel(it) },
                model.createdAtShamsi,
                model.hashTags?.let { list -> list.map { hashTagModelMapper.mapFromModel(it) } },
                null)
    }
}
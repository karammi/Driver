package ir.brn.presentation.mapper.wall

import ir.brn.domain.model.wall.Wall
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.wall.WallView
import javax.inject.Inject

class WallViewMapper @Inject constructor(
        private val userWallViewMapper: UserWallViewMapper,
        private val locationWallViewMapper: LocationWallViewMapper,
        private val carrierViewMapper: CarrierViewMapper,
        private val truckViewMapper: TruckViewMapper,
        private val hashTagViewMapper: HashTagViewMapper) : Mapper<WallView, Wall> {

    override fun mapToView(type: Wall): WallView {
        return WallView(type.id,
                type.user?.let { userWallViewMapper.mapToView(it) },
                type.body,
                type.picture,
                type.like,
                type.disLike,
                type.createdAt,
                type.location?.let { locationWallViewMapper.mapToView(it) },
                type.carrierType?.let { carrierViewMapper.mapToView(it) },
                type.truckType?.let { truckViewMapper.mapToView(it) },
                type.createdAtShamsi,
                type.hashTags?.let { list ->
                    list.map {
                        hashTagViewMapper.mapToView(it)
                    }
                },
                type.owner)
    }

    override fun mapFromView(view: WallView): Wall {
        return Wall(view.id,
                view.user?.let { userWallViewMapper.mapFromView(it) },
                view.body,
                view.picture,
                view.like,
                view.disLike,
                view.createdAt,
                view.location?.let { locationWallViewMapper.mapFromView(it) },
                view.carrierType?.let { carrierViewMapper.mapFromView(it) },
                view.truckType?.let { truckViewMapper.mapFromView(it) },
                view.createdAtShamsi,
                view.hashTags?.let { list -> list.map { hashTagViewMapper.mapFromView(it) } },
                view.owner)
    }
}
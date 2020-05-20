package ir.brn.driver.mapper.wall

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.mapper.core.CarrierViewMapper
import ir.brn.driver.mapper.core.TruckViewMapper
import ir.brn.driver.model.wall.Wall
import ir.brn.presentation.model.wall.WallView
import javax.inject.Inject

class WallViewMapper @Inject constructor(
        private val userWallViewMapper: UserWallViewMapper,
        private val locationWallViewMapper: LocationWallViewMapper,
        private val carrierViewMapper: CarrierViewMapper,
        private val truckViewMapper: TruckViewMapper,
        private val hashTagViewMapper: HashTagViewMapper) : ViewMapper<WallView, Wall> {

    override fun mapToView(presentation: WallView): Wall {
        return Wall(presentation.id,
                presentation.user?.let { userWallViewMapper.mapToView(it) },
                presentation.body,
                presentation.picture,
                presentation.like,
                presentation.disLike,
                presentation.createdAt,
                presentation.location?.let { locationWallViewMapper.mapToView(it) },
                presentation.carrierType?.let { carrierViewMapper.mapToView(it) },
                presentation.truckType?.let { truckViewMapper.mapToView(it) },
                presentation.createdAtShamsi,
                presentation.hashTags?.let { hashTags ->
                    hashTags.map { hashTagViewMapper.mapToView(it) }
                },
                presentation.owner)
    }

    fun mapFromView(wall: Wall): WallView {
        return WallView(wall.id,
                wall.user?.let { userWallViewMapper.mapFromView(it) },
                wall.body,
                wall.picture,
                wall.like,
                wall.disLike,
                wall.createdAt,
                wall.location?.let { locationWallViewMapper.mapFromView(it) },
                wall.carrierType?.let { carrierViewMapper.mapFromView(it) },
                wall.truckType?.let { truckViewMapper.mapFromView(it) },
                wall.createdAtShamsi,
                wall.hashTags?.let { hashTags ->
                    hashTags.map { hashTagViewMapper.mapFromView(it) }
                },
                wall.owner)
    }

}
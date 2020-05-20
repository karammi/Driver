package ir.brn.presentation.mapper.wall

import ir.brn.domain.model.wall.LocationWall
import ir.brn.presentation.mapper.Mapper
import ir.brn.presentation.model.wall.LocationWallView
import javax.inject.Inject

class LocationWallViewMapper @Inject constructor(
        private val provinceViewMapper: ProvinceViewMapper,
        private val cityViewMapper: CityViewMapper) : Mapper<LocationWallView, LocationWall> {

    override fun mapToView(type: LocationWall): LocationWallView {
        return LocationWallView(type.lat,
                type.lng,
                type.province?.let { provinceViewMapper.mapToView(it) },
                type.city?.let { cityViewMapper.mapToView(it) })
    }

    override fun mapFromView(view: LocationWallView): LocationWall {
        return LocationWall(view.lat,
                view.lng,
                view.province?.let { provinceViewMapper.mapFromView(it) },
                view.city?.let { cityViewMapper.mapFromView(it) })
    }
}
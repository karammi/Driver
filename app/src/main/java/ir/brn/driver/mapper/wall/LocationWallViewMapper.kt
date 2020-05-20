package ir.brn.driver.mapper.wall

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.mapper.core.CityViewMapper
import ir.brn.driver.mapper.core.ProvinceViewMapper
import ir.brn.driver.model.wall.LocationWall
import ir.brn.presentation.model.wall.LocationWallView
import javax.inject.Inject

class LocationWallViewMapper @Inject constructor(
        private val provinceViewMapper: ProvinceViewMapper,
        private val cityViewMapper: CityViewMapper) : ViewMapper<LocationWallView, LocationWall> {

    override fun mapToView(presentation: LocationWallView): LocationWall {
        return LocationWall(presentation.lat,
                presentation.lng,
                presentation.province?.let { provinceViewMapper.mapToView(it) },
                presentation.city?.let { cityViewMapper.mapToView(it) })
    }

    fun mapFromView(locationWall: LocationWall): LocationWallView {
        return LocationWallView(locationWall.lat,
                locationWall.lng,
                locationWall.province?.let { provinceViewMapper.mapFromView(it) },
                locationWall.city?.let { cityViewMapper.mapFromView(it) })
    }
}
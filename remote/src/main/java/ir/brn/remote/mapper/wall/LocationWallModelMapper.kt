package ir.brn.remote.mapper.wall

import ir.brn.data.model.wall.LocationWallEntity
import ir.brn.remote.mapper.ModelMapper
import ir.brn.remote.model.wall.LocationWallModel
import javax.inject.Inject

class LocationWallModelMapper @Inject constructor(
        private val provinceMapper: ProvinceMapper,
        private val cityMapper: CityMapper) : ModelMapper<LocationWallModel, LocationWallEntity> {

    override fun mapFromModel(model: LocationWallModel): LocationWallEntity {
        return LocationWallEntity(model.lat,
                model.lng,
                model.province?.let { provinceMapper.mapFromModel(it) },
                model.city?.let { cityMapper.mapFromModel(it) })
    }
}
package ir.brn.data.mapper.wallMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.mapper.coreMapper.CityMapper
import ir.brn.data.mapper.coreMapper.ProvinceMapper
import ir.brn.data.model.wall.LocationWallEntity
import ir.brn.domain.model.wall.LocationWall
import javax.inject.Inject

class LocationWallMapper @Inject constructor(
        private val provinceMapper: ProvinceMapper,
        private val cityMapper: CityMapper) : EntityMapper<LocationWallEntity, LocationWall> {

    override fun mapFromEntity(entity: LocationWallEntity): LocationWall {
        return LocationWall(entity.lat,
                entity.lng,
                entity.province?.let { provinceMapper.mapFromEntity(it) },
                entity.city?.let { cityMapper.mapFromEntity(it) })
    }

    override fun mapToEntity(domain: LocationWall): LocationWallEntity {
        return LocationWallEntity(domain.lat,
                domain.lng,
                domain.province?.let { provinceMapper.mapToEntity(it) },
                domain.city?.let { cityMapper.mapToEntity(it) })
    }
}
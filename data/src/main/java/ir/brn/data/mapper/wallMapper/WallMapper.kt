package ir.brn.data.mapper.wallMapper

import ir.brn.data.mapper.EntityMapper
import ir.brn.data.mapper.coreMapper.CarrierMapper
import ir.brn.data.mapper.coreMapper.TrucksMapper
import ir.brn.data.model.wall.WallEntity
import ir.brn.domain.model.wall.Wall
import javax.inject.Inject

class WallMapper @Inject constructor(
        private val trucksMapper: TrucksMapper,
        private val carrierMapper: CarrierMapper,
        private val locationWallMapper: LocationWallMapper,
        private val userWallMapper: UserWallMapper,
        private val hashTagMapper: HashTagMapper) : EntityMapper<WallEntity, Wall> {

    override fun mapFromEntity(entity: WallEntity): Wall {
        return Wall(entity.id,
                entity.user?.let { userWallMapper.mapFromEntity(it) },
                entity.body,
                entity.picture,
                entity.like,
                entity.disLike,
                entity.createdAt,
                entity.location?.let { locationWallMapper.mapFromEntity(it) },
                entity.carrierType?.let { carrierMapper.mapFromEntity(it) },
                entity.truckType?.let { trucksMapper.mapFromEntity(it) },
                entity.createdAtShamsi,
                entity.hashTags?.let { list ->
                    list.map { hashTagMapper.mapFromEntity(it) }
                }
        ,entity.owner)
    }

    override fun mapToEntity(domain: Wall): WallEntity {
        return WallEntity(domain.id,
                domain.user?.let { userWallMapper.mapToEntity(it) },
                domain.body,
                domain.picture,
                domain.like,
                domain.disLike,
                domain.createdAt,
                domain.location?.let { locationWallMapper.mapToEntity(it) },
                domain.carrierType?.let { carrierMapper.mapToEntity(it) },
                domain.truckType?.let { trucksMapper.mapToEntity(it) },
                domain.createdAtShamsi,
                domain.hashTags?.let { list ->
                    list.map { hashTagMapper.mapToEntity(it) }
                },
                domain.owner)
    }
}
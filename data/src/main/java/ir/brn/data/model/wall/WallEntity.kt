package ir.brn.data.model.wall

import ir.brn.data.model.core.CarrierEntity
import ir.brn.data.model.core.TruckEntity

data class WallEntity(val id: Int?,
                      val user: UserWallEntity?,
                      val body: String?,
                      val picture: String? = null,
                      val like: Int? = 0,
                      val disLike: Int? = 0,
                      val createdAt: String?,
                      val location: LocationWallEntity?,
                      val carrierType: CarrierEntity? = null,
                      val truckType: TruckEntity? = null,
                      val createdAtShamsi: String?,
                      val hashTags: List<HashTagEntity>?,
                      var owner:Boolean?)
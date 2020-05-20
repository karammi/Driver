package ir.brn.domain.model.wall

import ir.brn.domain.model.core.Carrier
import ir.brn.domain.model.core.Truck

data class Wall(val id: Int?,
                val user: UserWall?,
                val body: String?,
                val picture: String? = null,
                val like: Int? = 0,
                val disLike: Int? = 0,
                val createdAt: String?,
                val location: LocationWall?,
                val carrierType: Carrier? = null,
                val truckType: Truck? = null,
                val createdAtShamsi: String?,
                val hashTags: List<HashTag>?,
                var owner:Boolean?)
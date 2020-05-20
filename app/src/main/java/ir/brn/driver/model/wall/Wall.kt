package ir.brn.driver.model.wall

import ir.brn.driver.model.core.Carrier
import ir.brn.driver.model.core.Truck
import java.io.Serializable

data class Wall(var id: Int? = null,
                var user: UserWall? = null,
                var body: String? = null,
                var picture: String? = null,
                var like: Int? = 0,
                var disLike: Int? = 0,
                var createdAt: String? = null,
                var location: LocationWall? = null,
                var carrierType: Carrier? = null,
                var truckType: Truck? = null,
                var createdAtShamsi: String? = null,
                var hashTags: List<HashTag>? = null,
                var owner: Boolean? = false) : Serializable
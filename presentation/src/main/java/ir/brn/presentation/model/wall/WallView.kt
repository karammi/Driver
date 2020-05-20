package ir.brn.presentation.model.wall

data class WallView(val id: Int?,
                    val user: UserWallView?,
                    val body: String?,
                    val picture: String? = null,
                    val like: Int? = 0,
                    val disLike: Int? = 0,
                    val createdAt: String?,
                    val location: LocationWallView?,
                    val carrierType: CarrierView? = null,
                    val truckType: TruckView? = null,
                    val createdAtShamsi: String?,
                    val hashTags: List<HashTagView>?,
                    var owner:Boolean?)
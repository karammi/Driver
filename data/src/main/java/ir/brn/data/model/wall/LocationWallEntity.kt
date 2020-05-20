package ir.brn.data.model.wall

import ir.brn.data.model.core.CityEntity
import ir.brn.data.model.core.ProvinceEntity

data class LocationWallEntity(val lat: Double? = null,
                              val lng: Double? = null,
                              val province: ProvinceEntity? = null,
                              val city: CityEntity? = null)
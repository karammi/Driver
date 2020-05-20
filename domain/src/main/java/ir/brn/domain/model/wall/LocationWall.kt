package ir.brn.domain.model.wall

import ir.brn.domain.model.core.City
import ir.brn.domain.model.core.Province

data class LocationWall(val lat: Double? = null,
                        val lng: Double? = null,
                        val province: Province? = null,
                        val city: City? = null)
package ir.brn.driver.model.wall

import ir.brn.driver.model.core.City
import ir.brn.driver.model.core.Province

data class LocationWall(val lat: Double? = null,
                        val lng: Double? = null,
                        val province: Province? = null,
                        val city: City? = null)
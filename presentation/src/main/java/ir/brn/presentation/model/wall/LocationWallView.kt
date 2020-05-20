package ir.brn.presentation.model.wall

data class LocationWallView(val lat: Double? = null,
                            val lng: Double? = null,
                            val province: ProvinceView? = null,
                            val city: CityView? = null)
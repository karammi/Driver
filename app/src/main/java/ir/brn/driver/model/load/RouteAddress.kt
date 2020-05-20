package ir.brn.driver.model.load

import ir.brn.driver.model.core.City

data class RouteAddress(val id: Int?,
                        val isDefault: Boolean? = false,
                        val title: String?,
                        val address: String?,
                        val postalCode: String?,
                        val lat: Double?,
                        val lng: Double?,
                        val tel: String?,
                        val transportManager: TransportManager?,
                        val city: City?)
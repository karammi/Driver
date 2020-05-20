package ir.brn.driver.model.load

import ir.brn.driver.model.core.City

data class ImmediateLoad(val firstName: String?,
                         val lastName: String?,
                         val mobile: String?,
                         val loadingCity: City?,
                         val dischargingCity: City?)
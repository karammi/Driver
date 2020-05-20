package ir.brn.driver.mapper.load

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.mapper.core.CityViewMapper
import ir.brn.driver.model.load.RouteAddress
import ir.brn.presentation.model.load.RouteAddressView
import javax.inject.Inject

class RouteAddressViewMapper @Inject constructor(
        private val transportManagerViewMapper: TransportManagerViewMapper,
        private val cityViewMapper: CityViewMapper
) : ViewMapper<RouteAddressView, RouteAddress> {

    override fun mapToView(presentation: RouteAddressView): RouteAddress {
        return RouteAddress(presentation.id,
                presentation.isDefault,
                presentation.title,
                presentation.address,
                presentation.postalCode,
                presentation.lat,
                presentation.lng,
                presentation.tel,
                presentation.transportManagerView?.let { transportManagerViewMapper.mapToView(it) },
                presentation.cityView?.let { cityViewMapper.mapToView(it) })
    }
}
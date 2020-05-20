package ir.brn.driver.mapper.load

import ir.brn.driver.mapper.ViewMapper
import ir.brn.presentation.model.load.RouteView
import javax.inject.Inject

class RouteViewMapper @Inject constructor(
        private val routeAddressViewMapper: RouteAddressViewMapper
) : ViewMapper<RouteView, Route> {

    override fun mapToView(presentation: RouteView): Route {
        return Route(presentation.id,
                presentation.number,
                presentation.linkToken,
                presentation.startAddressView?.let { routeAddressViewMapper.mapToView(it) },
                presentation.endAddressView?.let { routeAddressViewMapper.mapToView(it) })
    }
}
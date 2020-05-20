package ir.brn.driver.mapper.load

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.mapper.core.CarrierViewMapper
import ir.brn.driver.mapper.core.TruckViewMapper
import ir.brn.presentation.model.load.TripView
import javax.inject.Inject

class TripViewMapper @Inject constructor(
        private val tripAddressViewMapper: TripAddressViewMapper,
        private val truckViewMapper: TruckViewMapper,
        private val carrierViewMapper: CarrierViewMapper,
        private val routeViewMapper: RouteViewMapper,
        private val brokerViewMapper: BrokerViewMapper,
        private val immediateLoadViewMapper: ImmediateLoadViewMapper)
    : ViewMapper<TripView, Trip> {

    override fun mapToView(presentation: TripView): Trip {
        return Trip(presentation.id,
                presentation.type,
                presentation.tripIdentity,
                presentation.systematicTripIdentity,
                presentation.title!!,
                presentation.description,
                presentation.color,
                presentation.createdAt,
                presentation.updatedAt,
                presentation.loadingAddress?.let { tripAddressViewMapper.mapToView(it) },
                presentation.loadingTime,
                presentation.dischargingAddress?.let { tripAddressViewMapper.mapToView(it) },
                presentation.truckView?.let { truckViewMapper.mapToView(it) },
                presentation.carrier?.let { carrierViewMapper.mapToView(it) },
                presentation.routes?.map { routeViewMapper.mapToView(it) },
                presentation.broker?.let { brokerViewMapper.mapToView(it) },
                presentation.immediateLoad?.let { immediateLoadViewMapper.mapToView(it) },
                selected = false,
                bookmarked = false,
                seen = false)
    }
}
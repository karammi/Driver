package ir.brn.driver.mapper.load

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.mapper.core.CityViewMapper
import ir.brn.driver.model.load.TripAddress
import ir.brn.presentation.model.load.TripAddressView
import javax.inject.Inject

class TripAddressViewMapper @Inject constructor(
        private val cityViewMapper: CityViewMapper)
    : ViewMapper<TripAddressView, TripAddress> {

    override fun mapToView(presentation: TripAddressView): TripAddress {
        return TripAddress(presentation.id,
                presentation.title,
                presentation.address,
                presentation.cityView?.let { cityViewMapper.mapToView(it) })
    }
}
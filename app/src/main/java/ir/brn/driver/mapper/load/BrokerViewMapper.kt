package ir.brn.driver.mapper.load

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.load.Broker
import ir.brn.presentation.model.load.BrokerView
import javax.inject.Inject

class BrokerViewMapper @Inject constructor() : ViewMapper<BrokerView, Broker> {

    override fun mapToView(presentation: BrokerView): Broker {
        return Broker(presentation.id,
                presentation.name)
    }
}
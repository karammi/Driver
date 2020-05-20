package ir.brn.driver.mapper.load

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.model.load.TransportManager
import ir.brn.presentation.model.load.TransportManagerView
import javax.inject.Inject

class TransportManagerViewMapper @Inject constructor() : ViewMapper<TransportManagerView, TransportManager> {

    override fun mapToView(presentation: TransportManagerView): TransportManager {
        return TransportManager(presentation.firstName,
                presentation.lastName,
                presentation.position,
                presentation.tel)
    }

    fun mapFromView(view: TransportManager): TransportManagerView {
        return TransportManagerView(view.firstName,
                view.lastName,
                view.position,
                view.tel)
    }
}
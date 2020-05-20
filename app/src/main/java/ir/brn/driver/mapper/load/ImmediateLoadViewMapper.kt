package ir.brn.driver.mapper.load

import ir.brn.driver.mapper.ViewMapper
import ir.brn.driver.mapper.core.CityViewMapper
import ir.brn.driver.model.load.ImmediateLoad
import ir.brn.presentation.model.load.ImmediateLoadView
import javax.inject.Inject

class ImmediateLoadViewMapper @Inject constructor(
        private val cityViewMapper: CityViewMapper
):ViewMapper<ImmediateLoadView,ImmediateLoad> {

    override fun mapToView(presentation: ImmediateLoadView): ImmediateLoad {
        return ImmediateLoad(presentation.firstName,
                presentation.lastName,
                presentation.mobile,
                presentation.loadingCityView?.let { cityViewMapper.mapToView(it) },
                presentation.dischargingCityView?.let { cityViewMapper.mapToView(it) })
    }
}
package ir.brn.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import ir.brn.domain.interactor.province.GetProvinceCitiesByNameUseCase
import ir.brn.domain.interactor.province.GetProvinceCitiesUseCase
import ir.brn.domain.interactor.province.GetProvincesUseCase
import ir.brn.domain.model.core.ProvinceCity
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import javax.inject.Inject

class ProvinceViewModel @Inject constructor(
        private val getProvincesUseCase: GetProvincesUseCase,
        private val getProvinceCitiesUseCase: GetProvinceCitiesUseCase,
        private val getProvinceCitiesByNameUseCase: GetProvinceCitiesByNameUseCase,
        private val mapper: ProvinceCityMapper) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProvinceCityView>>> = MutableLiveData()

    override fun onCleared() {
        getProvincesUseCase.dispose()
        getProvinceCitiesUseCase.dispose()
        super.onCleared()
    }

    fun getProvinceCity(): LiveData<Resource<List<ProvinceCityView>>> {
        return liveData
    }

    fun fetchProvinceCities() {
        liveData.postValue(Resource(ResourceState.LOADING))
        getProvinceCitiesUseCase.execute(ProvinceCitiesSubscriber())
    }


    fun fetchProvinceByCityName(cityName: String) {
        liveData.postValue(Resource(ResourceState.LOADING))
        getProvinceCitiesByNameUseCase.execute(ProvinceCitiesSubscriber(),
                GetProvinceCitiesByNameUseCase.Params(cityName))
    }

    inner class ProvinceCitiesSubscriber : DisposableObserver<List<ProvinceCity>>() {

        override fun onComplete() {

        }

        override fun onNext(t: List<ProvinceCity>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    data = t.map { mapper.mapToView(it) }))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR,
                    message = e.localizedMessage))

        }
    }


}
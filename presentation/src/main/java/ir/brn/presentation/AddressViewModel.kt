package ir.brn.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import ir.brn.domain.interactor.address.*
import ir.brn.domain.model.Address.Address
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import javax.inject.Inject

class AddressViewModel @Inject constructor(
        private val getAddressListUseCase: GetAddressListUseCase,
        private val getAddressDetailUseCase: GetAddressDetailUseCase,
        private val addAddressUseCase: AddAddressUseCase,
        private val editAddressUseCase: EditAddressUseCase,
        private val deleteAddressUseCase: DeleteAddressUseCase,
        private val setDefaultAddressUseCase: SetDefaultAddressUseCase,
        private val addressViewMapper: AddressViewMapper) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<AddressView>>> = MutableLiveData()
    private val doneLiveData: MutableLiveData<Resource<Boolean>> = MutableLiveData()

    override fun onCleared() {
        getAddressListUseCase.dispose()
        getAddressDetailUseCase.dispose()
        addAddressUseCase.dispose()
        editAddressUseCase.dispose()
        deleteAddressUseCase.dispose()
        setDefaultAddressUseCase.dispose()
        super.onCleared()
    }

    fun getLiveData(): LiveData<Resource<List<AddressView>>> {
        return liveData
    }

    fun getDoneLiveData(): LiveData<Resource<Boolean>> {
        return doneLiveData
    }


    fun fetchAddressList() {
        liveData.postValue(Resource(ResourceState.LOADING))
        getAddressListUseCase.execute(AddressSubscriber())
    }

    fun addAddress(addressView: AddressView) {
        doneLiveData.postValue(Resource(ResourceState.LOADING))
        addAddressUseCase.execute(DoneSubscriber(), addressViewMapper.mapFromView(addressView))
    }

    fun editAddress(addressView: AddressView) {
        doneLiveData.postValue(Resource(ResourceState.LOADING))
        editAddressUseCase.execute(DoneSubscriber(), addressViewMapper.mapFromView(addressView))
    }

    fun deleteAddress(addressId: Int) {
        doneLiveData.postValue(Resource(ResourceState.LOADING))
        deleteAddressUseCase.execute(DoneSubscriber(), DeleteAddressUseCase.Params.forDeleteAddress(addressId))
    }

    fun setAsDefaultAddress(addressId: Int) {
        doneLiveData.postValue(Resource(ResourceState.LOADING))
        setDefaultAddressUseCase.execute(DoneSubscriber(), SetDefaultAddressUseCase.Params.forSetDefaultAddress(addressId))
    }

    inner class AddressSubscriber : DisposableObserver<List<Address>>() {

        override fun onComplete() {

        }

        override fun onNext(t: List<Address>) {
           liveData.postValue(Resource(ResourceState.SUCCESS,data = t.map { addressViewMapper.mapToView(it) }))
        }

        override fun onError(e: Throwable) {
           liveData.postValue(Resource(ResourceState.ERROR,message = e.localizedMessage))
        }
    }


    inner class DoneSubscriber : DisposableObserver<Boolean>() {
        override fun onComplete() {
        }

        override fun onNext(t: Boolean) {
            println("OK")
        }

        override fun onError(e: Throwable) {
            println("ERROR")
        }
    }

}
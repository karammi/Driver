package ir.brn.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import ir.brn.domain.interactor.user.LoginUseCase
import ir.brn.domain.interactor.wall.*
import ir.brn.domain.model.user.LoginResponse
import ir.brn.domain.model.wall.Wall
import ir.brn.presentation.mapper.wall.WallViewMapper
import ir.brn.presentation.model.wall.WallLikeStateView
import ir.brn.presentation.model.wall.WallView
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import javax.inject.Inject

class WallViewModel @Inject constructor(
        private val getWallsUseCase: GetWallsUseCase,
        private val addWallUseCase: AddWallUseCase,
        private val editWallUseCase: EditWallUseCase,
        private val deleteWallUseCase: DeleteWallUseCase,
        private val likeWallUseCase: LikeWallUseCase,
        private val disLikeWallUseCase: DisLikeWallUseCase,
        private var loginUseCase: LoginUseCase,
        private var loginResponseViewMapper: LoginResponseViewMapper,
        private val wallViewMapper: WallViewMapper) : ViewModel() {
    //region liveData

    private val liveDataWallList: MutableLiveData<Resource<List<WallView>>> = MutableLiveData()
    private val liveDataDone: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    private val liveDataLikeDisLike: MutableLiveData<Resource<WallLikeStateView>> = MutableLiveData()
    private var liveDataLogin: MutableLiveData<Resource<LoginResponseView>> = MutableLiveData()

    //endregion

    //region dispose
    override fun onCleared() {
        getWallsUseCase.dispose()
        addWallUseCase.dispose()
        editWallUseCase.dispose()
        deleteWallUseCase.dispose()
        likeWallUseCase.dispose()
        disLikeWallUseCase.dispose()
        loginUseCase.dispose()
        super.onCleared()
    }

    //endregion

    //region WallList
    fun getLiveDataWallList(): LiveData<Resource<List<WallView>>> {
        return liveDataWallList
    }

    fun fetchWallList() {
        liveDataWallList.postValue(Resource(ResourceState.LOADING))
        getWallsUseCase.execute(WallSubscriber())
    }
    //endregion

    //region Wall Done Action
    fun getLiveDataDone(): LiveData<Resource<Boolean>> {
        return liveDataDone
    }

    fun addWall(wallView: WallView) {
        liveDataDone.postValue(Resource(ResourceState.LOADING))
        addWallUseCase.execute(DoneSubscriber(), wallViewMapper.mapFromView(wallView))
    }

    fun editWall(wallView: WallView) {
        liveDataDone.postValue(Resource(ResourceState.LOADING))
        editWallUseCase.execute(DoneSubscriber(), wallViewMapper.mapFromView(wallView))
    }

    fun deleteWall(wallId: Int) {
        liveDataDone.postValue(Resource(ResourceState.LOADING))
        deleteWallUseCase.execute(WallSubscriber(), DeleteWallUseCase.Params.forDeleteWall(wallId))
    }

    //endregion

    //region like DisLike

    fun getLiveDataLike(): LiveData<Resource<WallLikeStateView>> {
        return liveDataLikeDisLike
    }

    fun likeWall(wallId: Int) {
        liveDataWallList.postValue(Resource(ResourceState.LOADING))
        likeWallUseCase.execute(WallSubscriber(), LikeWallUseCase.Params.forLikeWall(wallId))
    }

    fun disLikeWall(wallId: Int) {
        liveDataWallList.postValue(Resource(ResourceState.LOADING))
        disLikeWallUseCase.execute(WallSubscriber(), DisLikeWallUseCase.Params.forDisLikeWall(wallId))
    }

    //endregion

    //region loginResponse

    fun getLiveDataLogin(): LiveData<Resource<LoginResponseView>> {
        return liveDataLogin
    }

    fun fetchLogin() {
        liveDataLogin.postValue(Resource(ResourceState.LOADING))
        loginUseCase.execute(LoginResponseSubscriber())
    }

    //endregion


    //region inner class

    inner class WallSubscriber : DisposableObserver<List<Wall>>() {

        override fun onComplete() {
        }

        override fun onNext(t: List<Wall>) {
            liveDataWallList.postValue(
                    Resource(
                            ResourceState.SUCCESS,
                            t.map {
                                wallViewMapper.mapToView(it)
                            }))
        }

        override fun onError(e: Throwable) {
            liveDataWallList.postValue(Resource(ResourceState.ERROR,
                    message = e.localizedMessage))
        }
    }

    inner class DoneSubscriber : DisposableObserver<Boolean>() {
        override fun onComplete() {

        }

        override fun onNext(t: Boolean) {
            liveDataDone.postValue(Resource(ResourceState.SUCCESS, data = t))
        }

        override fun onError(e: Throwable) {
            liveDataDone.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))

        }
    }

    inner class LoginResponseSubscriber : DisposableObserver<LoginResponse>() {
        override fun onComplete() {

        }

        override fun onNext(t: LoginResponse) {
            liveDataLogin.postValue(Resource(ResourceState.SUCCESS, data = loginResponseViewMapper.mapToView(t)))
        }

        override fun onError(e: Throwable) {
            liveDataLogin.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
        }
    }


    //endregion
}
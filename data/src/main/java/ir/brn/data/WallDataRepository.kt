package ir.brn.data

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.toObservable
import ir.brn.data.mapper.wallMapper.WallLikeStateMapper
import ir.brn.data.mapper.wallMapper.WallMapper
import ir.brn.data.model.user.LoginResponseEntity
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.source.user.UserDataStoreFactory
import ir.brn.data.source.wall.WallDataStoreFactory
import ir.brn.domain.model.wall.Wall
import ir.brn.domain.model.wall.WallLikeState
import ir.brn.domain.repository.WallRepository
import javax.inject.Inject

class WallDataRepository @Inject constructor(
        private val userDataStoreFactory: UserDataStoreFactory,
        private val wallDataStoreFactory: WallDataStoreFactory,
        private val wallMapper: WallMapper) : WallRepository {

    override fun getWalls(): Observable<List<Wall>> {
        return getCompleteWalls()
    }


    fun getWallsNew(): Observable<List<WallEntity>> {
        return userDataStoreFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    return@flatMap wallDataStoreFactory
                            .getRemoteDataSource()
                            .getWalls(token)
                }
    }


    fun getCompleteWalls(): Observable<List<Wall>> {
        return Observable.zip(
                getWallsNew(),
                userDataStoreFactory.getCacheDataSource().getLoginInfo(),
                BiFunction<List<WallEntity>, LoginResponseEntity, List<WallEntity>> { walls, loginResponse ->
                    return@BiFunction filterList(walls, loginResponse)
                }
        ).map { list ->
            list.map {
                wallMapper.mapFromEntity(it)
            }
        }
    }

    fun filterList(walls: List<WallEntity>, loginResponseEntity: LoginResponseEntity): List<WallEntity> {
        walls.forEach {
            if (it.user?.id == loginResponseEntity.id)
                it.owner = true
        }

        return walls
    }

    override fun addWall(wall: Wall): Observable<Boolean> {
        return userDataStoreFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    return@flatMap wallDataStoreFactory
                            .getRemoteDataSource()
                            .addWall(token, wallMapper.mapToEntity(wall))
                }.map {
                    it
                }
    }

    override fun editWall(wall: Wall): Observable<Boolean> {
        return userDataStoreFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    return@flatMap wallDataStoreFactory
                            .getRemoteDataSource()
                            .editWall(token, wallMapper.mapToEntity(wall))
                }.map {
                    it
                }
    }

    override fun deleteWall(wallId: Int): Observable<List<Wall>> {
        return userDataStoreFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    return@flatMap wallDataStoreFactory
                            .getRemoteDataSource()
                            .deleteWall(token, wallId)
                }.flatMap {
                    getCompleteWalls()
                }

    }

    override fun likeDisLikeWall(wallId: Int, type: String): Observable<List<Wall>> {
        return userDataStoreFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    return@flatMap wallDataStoreFactory
                            .getRemoteDataSource()
                            .likeDisLikeWall(token, wallId, type)
                }.flatMap {
                    getCompleteWalls()
                }
    }
}
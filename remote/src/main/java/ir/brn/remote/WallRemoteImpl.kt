package ir.brn.remote

import io.reactivex.Observable
import ir.brn.data.model.wall.HashTagEntity
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.model.wall.WallLikeStateEntity
import ir.brn.data.repository.wall.WallRemote
import ir.brn.remote.mapper.wall.HashTagModelMapper
import ir.brn.remote.mapper.wall.WallLikeStateModelMapper
import ir.brn.remote.mapper.wall.WallModelMapper
import ir.brn.remote.service.ApiService
import javax.inject.Inject

class WallRemoteImpl @Inject constructor(
        private val apiService: ApiService,
        private val wallModelMapper: WallModelMapper,
        private val wallLikeStateModelMapper: WallLikeStateModelMapper) : WallRemote {

    override fun getWalls(token: String): Observable<List<WallEntity>> {
        return apiService
                .getWalls(token)
                .map { response ->
                    response.data.map {
                        wallModelMapper.mapFromModel(it)
                    }
                }
    }

    override fun addWall(token: String, wallEntity: WallEntity): Observable<Boolean> {
        return apiService
                .addWall(token,
                        wallEntity.body,
                        wallEntity.location?.lat,
                        wallEntity.location?.lng,
                        wallEntity.location?.province?.id,
                        wallEntity.location?.city?.id,
                        wallEntity.carrierType?.id,
                        wallEntity.truckType?.id,
                        wallEntity.hashTags?.let {
                            getHashTagsFromList(it)
                        }).map { response ->
                    response.data.set == "done"
                }
    }

    override fun editWall(token: String, wallEntity: WallEntity): Observable<Boolean> {
        return apiService
                .editWall(token,
                        "PATCH",
                        wallEntity.id!!,
                        wallEntity.body,
                        wallEntity.hashTags?.let { getHashTagsFromList(it) })
                .map { response ->
                    response.data.set == "done"
                }
    }

    override fun deleteWall(token: String, wallId: Int): Observable<Boolean> {
        return apiService
                .deleteWall(token,
                        "DELETE",
                        wallId)
                .map { response ->
                    //                    response.data.set == "done"
                    response.code == 0
                }
    }

    override fun likeDisLikeWall(token: String, wallId: Int, type: String): Observable<WallLikeStateEntity> {
        return apiService
                .likeDisLikeWall(token, wallId, type)
                .map { response ->
                    wallLikeStateModelMapper.mapFromModel(response.data)
                }
    }

    private fun getHashTagsFromList(list: List<HashTagEntity>): String? {
        var result: String? = null
        list.forEach {
            //            hashTagModelMapper.mapToModel(it)
            result = it.text?.replace(" ", "_") + ";"
        }
        return result
    }

}
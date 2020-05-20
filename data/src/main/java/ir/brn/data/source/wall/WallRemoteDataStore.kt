package ir.brn.data.source.wall

import io.reactivex.Completable
import io.reactivex.Observable
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.model.wall.WallLikeStateEntity
import ir.brn.data.repository.wall.WallDataSource
import ir.brn.data.repository.wall.WallRemote
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class WallRemoteDataStore @Inject constructor(
        private val wallRemote: WallRemote) : WallDataSource {

    override fun clearCache(): Completable {
        throw UnsupportedOperationException("clear cache isn't support here!")
    }

    override fun saveWalls(walls: List<WallEntity>): Completable {
        throw UnsupportedOperationException("save walls isn't support here!")
    }

    override fun getWalls(token: String): Observable<List<WallEntity>> {
        return wallRemote
                .getWalls(token)
    }

    override fun addWall(token: String, wallEntity: WallEntity): Observable<Boolean> {
        return wallRemote
                .addWall(token, wallEntity)
    }

    override fun editWall(token: String, wallEntity: WallEntity): Observable<Boolean> {
        return wallRemote
                .editWall(token, wallEntity)
    }

    override fun deleteWall(token: String, wallId: Int): Observable<Boolean> {
        return wallRemote
                .deleteWall(token, wallId)
    }

    override fun likeDisLikeWall(token: String, wallId: Int, type: String): Observable<WallLikeStateEntity> {
        return wallRemote
                .likeDisLikeWall(token, wallId, type)
    }

    override fun getWallsByHashTag(hashTags: String): Observable<List<WallEntity>> {
        throw UnsupportedOperationException("Clear cache isn't support here!")
    }
}
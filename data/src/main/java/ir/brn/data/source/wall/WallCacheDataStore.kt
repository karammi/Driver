package ir.brn.data.source.wall

import io.reactivex.Completable
import io.reactivex.Observable
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.model.wall.WallLikeStateEntity
import ir.brn.data.repository.wall.WallCache
import ir.brn.data.repository.wall.WallDataSource
import javax.inject.Inject

class WallCacheDataStore @Inject constructor(
        private val wallCache: WallCache):WallDataSource {

    override fun clearCache(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveWalls(walls: List<WallEntity>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWalls(token: String): Observable<List<WallEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addWall(token: String, wallEntity: WallEntity): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editWall(token: String, wallEntity: WallEntity): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWall(token: String, wallId: Int): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun likeDisLikeWall(token: String, wallId: Int, type: String): Observable<WallLikeStateEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWallsByHashTag(hashTags: String): Observable<List<WallEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
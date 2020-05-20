package ir.brn.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.brn.cache.db.DriverDatabase
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.model.wall.WallLikeStateEntity
import ir.brn.data.repository.wall.WallCache
import javax.inject.Inject

class WallCacheImpl @Inject constructor(
        private val driverDatabase: DriverDatabase) : WallCache {

    override fun clearCache(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveWalls(walls: List<WallEntity>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWalls(): Observable<List<WallEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addWall(wallEntity: WallEntity): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editWall(wallEntity: WallEntity): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWall(wallEntity: WallEntity): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun likeDisLikeWall(wallId: Int, type: String): Observable<WallLikeStateEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWallsByHashTag(hashtags: String): Observable<List<WallEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areWallCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(time: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCacheExpired(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
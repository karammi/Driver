package ir.brn.data.repository.wall

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.model.wall.WallLikeStateEntity

interface WallCache {

    fun clearCache(): Completable

    fun saveWalls(walls: List<WallEntity>): Completable

    fun getWalls(): Observable<List<WallEntity>>

    fun addWall(wallEntity: WallEntity): Observable<Boolean>

    fun editWall(wallEntity: WallEntity): Observable<Boolean>

    fun deleteWall(wallEntity: WallEntity): Observable<Boolean>

    fun likeDisLikeWall(wallId: Int, type: String): Observable<WallLikeStateEntity>

    fun getWallsByHashTag(hashtags: String): Observable<List<WallEntity>>

    fun areWallCached(): Single<Boolean>

    fun setLastCacheTime(time: Long): Completable

    fun isCacheExpired(): Single<Boolean>
}
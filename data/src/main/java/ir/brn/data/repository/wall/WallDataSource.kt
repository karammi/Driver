package ir.brn.data.repository.wall

import io.reactivex.Completable
import io.reactivex.Observable
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.model.wall.WallLikeStateEntity

interface WallDataSource {

    fun clearCache(): Completable

    fun saveWalls(walls: List<WallEntity>): Completable

    fun getWalls(token: String): Observable<List<WallEntity>>

    fun addWall(token: String, wallEntity: WallEntity): Observable<Boolean>

    fun editWall(token: String, wallEntity: WallEntity): Observable<Boolean>

    fun deleteWall(token: String, wallId: Int): Observable<Boolean>

    fun likeDisLikeWall(token: String, wallId: Int, type: String): Observable<WallLikeStateEntity>

    fun getWallsByHashTag(hashTags: String): Observable<List<WallEntity>>

}
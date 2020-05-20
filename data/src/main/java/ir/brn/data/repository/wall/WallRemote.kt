package ir.brn.data.repository.wall

import io.reactivex.Completable
import io.reactivex.Observable
import ir.brn.data.model.wall.WallEntity
import ir.brn.data.model.wall.WallLikeStateEntity

interface WallRemote {

    fun getWalls(token: String): Observable<List<WallEntity>>

    fun addWall(token: String, wallEntity: WallEntity): Observable<Boolean>

    fun editWall(token: String, wallEntity: WallEntity): Observable<Boolean>

    fun deleteWall(token: String, wallId: Int): Observable<Boolean>

    fun likeDisLikeWall(token: String, wallId: Int, type: String): Observable<WallLikeStateEntity>
}
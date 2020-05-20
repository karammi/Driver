package ir.brn.domain.repository

import io.reactivex.Observable
import ir.brn.domain.model.wall.Wall
import ir.brn.domain.model.wall.WallLikeState

interface WallRepository {

    fun getWalls(): Observable<List<Wall>>

    fun addWall(wall: Wall): Observable<Boolean>

    fun editWall(wall: Wall): Observable<Boolean>

    fun deleteWall(wallId: Int): Observable<List<Wall>>

    fun likeDisLikeWall(wallId: Int, type: String): Observable<List<Wall>>

}
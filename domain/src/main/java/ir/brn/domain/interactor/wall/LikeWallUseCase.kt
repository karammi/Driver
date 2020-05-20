package ir.brn.domain.interactor.wall

import io.reactivex.Observable
import ir.brn.domain.executor.PostExecutionThread
import ir.brn.domain.interactor.ObservableUseCase
import ir.brn.domain.model.wall.Wall
import ir.brn.domain.repository.WallRepository
import javax.inject.Inject

class LikeWallUseCase @Inject constructor(
        private val wallRepository: WallRepository,
        postExecutionThread: PostExecutionThread) : ObservableUseCase<List<Wall>, LikeWallUseCase.Params?>(postExecutionThread) {


    override fun buildUseCaseObservable(params: Params?): Observable<List<Wall>> {
        if (params == null) throw IllegalArgumentException("Params can not be null!")
        return wallRepository.likeDisLikeWall(params.wallId, "like")
    }

    data class Params constructor(val wallId: Int) {
        companion object {
            fun forLikeWall(wallId: Int): Params {
                return Params(wallId)
            }
        }
    }
}
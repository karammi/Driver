package ir.brn.domain.interactor.wall

import io.reactivex.Observable
import ir.brn.domain.executor.PostExecutionThread
import ir.brn.domain.interactor.ObservableUseCase
import ir.brn.domain.model.wall.Wall
import ir.brn.domain.repository.WallRepository
import javax.inject.Inject

class GetWallsUseCase @Inject constructor(
        private val wallRepository: WallRepository,
        postExecutionThread: PostExecutionThread) : ObservableUseCase<List<Wall>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Wall>> {
        return wallRepository.getWalls()
    }
}
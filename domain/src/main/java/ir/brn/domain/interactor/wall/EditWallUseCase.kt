package ir.brn.domain.interactor.wall

import io.reactivex.Observable
import ir.brn.domain.executor.PostExecutionThread
import ir.brn.domain.interactor.ObservableUseCase
import ir.brn.domain.model.wall.Wall
import ir.brn.domain.repository.WallRepository
import javax.inject.Inject

class EditWallUseCase @Inject constructor(
        private val wallRepository: WallRepository,
        postExecutionThread: PostExecutionThread) : ObservableUseCase<Boolean, Wall?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Wall?): Observable<Boolean> {
        if (params == null) throw IllegalArgumentException("Wall can not be null!!!")
        return wallRepository.editWall(params)
    }

}
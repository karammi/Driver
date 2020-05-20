package ir.brn.domain.interactor

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ir.brn.domain.executor.PostExecutionThread

abstract class SingleUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        val single = this.buildUseCaseSingle(params)
                .observeOn(Schedulers.io())
                .subscribeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(observer))
    }


    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
package ir.brn.driver

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import ir.brn.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
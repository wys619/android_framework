package cn.woyeshi.presenter.base

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseDisposableObserver<E>(private val observable: Observable<E>) : DisposableObserver<E>() {

    override fun onComplete() {
        observable.unsubscribeOn(Schedulers.io())
    }

    override fun onNext(t: E) {


    }

    override fun onError(e: Throwable) {

    }

}
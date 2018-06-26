package cn.woyeshi.presenter.base

import cn.woyeshi.entity.BaseResponse
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseDisposableObserver<T : BaseResponse<E>, E>(private val observable: Observable<T>) : DisposableObserver<T>() {

    override fun onComplete() {
        observable.unsubscribeOn(Schedulers.io())
    }

    final override fun onNext(t: T) {
        when (t.code) {
            0 -> {              //成功返回数据
                onSuccess(t.data)
            }
            else -> {           //其他情况就是操作失败了
                onFail(t)
            }
        }
    }

    override fun onError(e: Throwable) {

    }

    open fun onSuccess(e: E?) {

    }

    open fun onFail(t: T) {

    }
}
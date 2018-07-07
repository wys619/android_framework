package cn.woyeshi.presenter.base

import cn.woyeshi.entity.utils.ToastUtils
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

abstract class BaseSubscriber<E>(private val observable: Flowable<E>) : Subscriber<E> {

    override fun onSubscribe(s: Subscription?) {
        s?.request(Long.MAX_VALUE)
    }

    override fun onComplete() {
        observable.unsubscribeOn(Schedulers.io())
    }

    override fun onNext(t: E) {


    }

    final override fun onError(e: Throwable) {
        when (e) {
            is BaseException -> {
                onFail(e)
            }
            else -> {
                ToastUtils.toast("服务器内部错误(${e.javaClass.name})")
                onException(e)
            }
        }
    }

    open fun onException(e: Throwable) {
        e.printStackTrace()
    }

    open fun onFail(e: BaseException) {
        ToastUtils.toast("${e.msg}(${e.code})")
    }

}
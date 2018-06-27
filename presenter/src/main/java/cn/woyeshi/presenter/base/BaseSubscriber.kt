package cn.woyeshi.presenter.base

import cn.woyeshi.entity.BaseResponse
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

abstract class BaseSubscriber<T : BaseResponse<E>, E>(private val observable: Flowable<BaseResponse<E>>) : Subscriber<T> {

    override fun onSubscribe(s: Subscription?) {
        s?.request(Long.MAX_VALUE)
    }

    override fun onComplete() {
        observable.unsubscribeOn(Schedulers.io())
    }

    override fun onNext(t: T) {


    }

    override fun onError(e: Throwable) {

    }

}
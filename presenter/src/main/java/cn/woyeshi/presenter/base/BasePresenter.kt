package cn.woyeshi.presenter.base

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wys on 2017/11/8.
 */
open class BasePresenter<V : IBaseView> constructor(v: V) : IBasePresenter {

    protected val iView = v

    /**
     *
     * @param flowAble
     * @param <T>
     * @return
    </T> */
    protected fun <T> observe(flowAble: Flowable<T>): Flowable<T> {
        return flowAble.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }


}
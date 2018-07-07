package cn.woyeshi.presenter.base

import android.util.Log
import cn.woyeshi.entity.BaseResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter<T : IBaseView>(val iView: T) : IBasePresenter<T> {

    val tag: String = javaClass.simpleName

    /**
     *
     * @param flowAble
     * @param <T>
     * @return
    </T> */
    protected fun <E> observe(observable: Flowable<BaseResponse<E>>): Flowable<E> {
        val o = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        return o.map {
            when (it.code) {
                0 -> {
                    if (it.data == null) {
                        Unit as E
                    } else {
                        it.data
                    }
                }
                else -> {           //非成功的code的时候，就直接抛出异常
                    throw BaseException(it.code, it.msg)
                }
            }
        }
    }

    protected fun observeUnit(observable: Flowable<BaseResponse<Unit>>): Flowable<BaseResponse<Unit>> {
        return observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun onDestroy() {
        Log.i(tag, "onDestroy()")
    }

}
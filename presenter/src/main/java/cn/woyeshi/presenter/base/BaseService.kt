package cn.woyeshi.presenter.base

import cn.woyeshi.entity.BaseResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wys on 2017/11/8.
 */
open class BaseService : IBaseService {
    /**
     *
     * @param flowAble
     * @param <T>
     * @return
    </T> */
    protected fun <E> observe(observable: Flowable<BaseResponse<E>>): Flowable<E> {
        return observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    when (it.code) {
                        0 -> {
                            it.data
                        }
                        else -> {           //非成功的code的时候，就直接抛出异常
                            throw BaseException(it.code, it.msg)
                        }
                    }
                }
    }


}
package cn.woyeshi.presenter.base

import cn.woyeshi.entity.BaseResponse
import io.reactivex.Observable
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
    protected fun <E> observe(observable: Observable<BaseResponse<E>>): Observable<E> {
        return observable
                .map({
                    it.data
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }


}
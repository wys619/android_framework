package cn.woyeshi.presenter.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wys on 2017/11/8.
 */
open class BaseService: IBaseService {
    /**
     *
     * @param flowAble
     * @param <T>
     * @return
    </T> */
    protected fun <T> observe(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }


}
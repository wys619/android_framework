package cn.woyeshi.presenter.base

import cn.woyeshi.entity.BaseResponse
import io.reactivex.Observable

abstract class BaseObservable<E> : Observable<BaseResponse<E>>()
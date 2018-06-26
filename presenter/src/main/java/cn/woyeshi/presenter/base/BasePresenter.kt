package cn.woyeshi.presenter.base

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : IBaseView>(val iView: T) : IBasePresenter<T> {

    val tag: String = javaClass.simpleName

    private var disposables: CompositeDisposable? = null

    override fun addSubscription(disposable: Disposable) {
        Log.i(tag, "addSubscription()")
        if (disposables == null || disposables?.isDisposed!!) {
            disposables = CompositeDisposable()
        }
        disposables?.add(disposable)
    }

    override fun onDestroy() {
        Log.i(tag, "onDestroy()")
        disposables?.clear()
    }

}
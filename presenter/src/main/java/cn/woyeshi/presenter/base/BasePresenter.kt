package cn.woyeshi.presenter.base

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : IBaseView>(val iView: T) : IBasePresenter<T> {

    val tag: String = javaClass.simpleName

    override fun onDestroy() {
        Log.i(tag, "onDestroy()")
    }

}
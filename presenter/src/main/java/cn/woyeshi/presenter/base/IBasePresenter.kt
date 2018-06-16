package cn.woyeshi.presenter.base

import io.reactivex.disposables.Disposable

interface IBasePresenter<T : IBaseView> {
    fun addSubscription(disposable: Disposable)

    fun onDestroy()
}
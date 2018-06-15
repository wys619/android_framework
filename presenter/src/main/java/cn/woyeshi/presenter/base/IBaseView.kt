package cn.woyeshi.presenter.base

import io.reactivex.disposables.Disposable

/**
 * Created by wys on 2017/11/8.
 */
interface IBaseView {

    fun logI(tag: String, content: String)

    fun logW(tag: String, content: String)

    fun logE(tag: String, content: String)

    fun addSubscription(disposable: Disposable)

    fun onDestroy()

    fun showLoading(msg: String, cancelable: Boolean)

    fun hideLoading()

}
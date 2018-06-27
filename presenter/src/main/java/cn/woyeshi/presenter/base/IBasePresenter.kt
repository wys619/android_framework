package cn.woyeshi.presenter.base

interface IBasePresenter<T : IBaseView> {
    fun onDestroy()
}
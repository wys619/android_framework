package cn.woyeshi.base.fragments

import android.support.v4.app.Fragment
import cn.woyeshi.entity.beans.manager.UserInfo
import cn.woyeshi.presenter.base.IBaseActivity
import cn.woyeshi.presenter.base.IBaseFragment

/**
 * Created by wys on 2017/11/8.
 */
class BaseFragment : Fragment(), IBaseFragment {

    override fun getBaseActivity(): IBaseActivity? {
        return activity as IBaseActivity?
    }

    override fun logI(tag: String, content: String) {
        getBaseActivity()?.logI(tag, content)
    }

    override fun logW(tag: String, content: String) {
        getBaseActivity()?.logW(tag, content)
    }

    override fun logE(tag: String, content: String) {
        getBaseActivity()?.logE(tag, content)
    }

    override fun showLoading(msg: String, cancelable: Boolean) {
        getBaseActivity()?.showLoading(msg, cancelable)
    }

    override fun hideLoading() {
        getBaseActivity()?.hideLoading()
    }

    override fun getLoginUserInfo(): UserInfo? {
        return getBaseActivity()?.getLoginUserInfo()
    }

    override fun getToken(): String? {
        return getBaseActivity()?.getToken()
    }

}
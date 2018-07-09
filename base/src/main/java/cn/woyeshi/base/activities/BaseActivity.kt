package cn.woyeshi.base.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.woyeshi.base.R
import cn.woyeshi.base.dialogs.LoadingDialog
import cn.woyeshi.base.utils.AnnotationUtils
import cn.woyeshi.entity.Constants
import cn.woyeshi.entity.beans.manager.UserInfo
import cn.woyeshi.entity.utils.SPHelper
import cn.woyeshi.entity.utils.ToastUtils
import cn.woyeshi.presenter.base.IBaseActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by wys on 2017/11/8.
 */
abstract class BaseActivity : AppCompatActivity(), IBaseActivity {

    val TAG: String = javaClass.simpleName

    private var backBtn: View? = null
    private var tvTitleBar: TextView? = null
    private var editBtn: TextView? = null

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityCreateStart(savedInstanceState)
        setContentView(R.layout.activity_base)
        AnnotationUtils.bind(this)
        val baseViewContainer = findViewById<LinearLayout>(R.id.ll_base_layout_container)
        if (isHaveTitleBar() && getTitleLayoutID() != 0) {
            LayoutInflater.from(this).inflate(getTitleLayoutID(), baseViewContainer)
        }
        if (getContentLayoutID() != 0) {
            LayoutInflater.from(this).inflate(getContentLayoutID(), baseViewContainer)
        }
        backBtn = findViewById(R.id.iv_back)
        tvTitleBar = findViewById(R.id.tv_title)
        editBtn = findViewById(R.id.tv_edit)
        editBtn?.setOnClickListener {
            onEditBtnClick(it as TextView)
        }
        backBtn?.setOnClickListener {
            onBackBtnClick()
        }
        onActivityCreated(savedInstanceState)
    }

    open fun onEditBtnClick(view: TextView) {


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun setEditBtnVisibility(flag: Boolean) {
        if (flag) {
            editBtn?.visibility = View.VISIBLE
        } else {
            editBtn?.visibility = View.GONE
        }
    }

    fun setEditBtnText(text: String) {
        editBtn?.text = text
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackBtnClick()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    open fun isHaveTitleBar(): Boolean {
        return true
    }

    override fun setTitle(title: CharSequence) {
        tvTitleBar?.text = title
    }

    override fun setTitle(titleId: Int) {
        tvTitleBar?.text = resources.getString(titleId)
    }

    open fun onBackBtnClick() {
        finish()
    }                   //点击左上角回退按钮的时候

    fun setBackBtnVisibility(flag: Boolean) {
        if (flag) {
            backBtn?.visibility = View.VISIBLE
        } else {
            backBtn?.visibility = View.GONE
        }
    }

    //输出日志
    override fun logI(tag: String, content: String) {
        Log.i(tag, content)
    }

    override fun logW(tag: String, content: String) {
        Log.w(tag, content)
    }

    override fun logE(tag: String, content: String) {
        Log.e(tag, content)
    }

    open fun getTitleLayoutID(): Int {
        return R.layout.base_titlebar
    }

    fun showBlurringView(flag: Boolean) {

    }

    fun <T> saveToSP(key: String, value: T) {
        SPHelper.saveData(this, key, value)
    }

    fun <T> readFromSP(key: String, c: Class<T>): T? {
        return SPHelper.getData(this, key, c)
    }

    //获取当前登录的用户的信息
    override fun getLoginUserInfo(): UserInfo? {
        return readFromSP(Constants.SPKeys.KEY_LOGIN_USER_INFO, UserInfo::class.java)
    }

    //
    override fun getToken(): String? {
        return getLoginUserInfo()?.token
    }

    override fun showLoading(msg: String, cancelable: Boolean) {
        LoadingDialog(this, msg, cancelable).show()
    }

    override fun hideLoading() {
        LoadingDialog.cancel()
    }

    fun toast(msg: String) {
        ToastUtils.toast(msg)
    }

    open fun onActivityCreateStart(savedInstanceState: Bundle?) {

    }               //activity的onCreate方法开始

    abstract fun getContentLayoutID(): Int

    /**
     *
     */
    abstract fun onActivityCreated(savedInstanceState: Bundle?)         //activity的onCreate方法结束

}
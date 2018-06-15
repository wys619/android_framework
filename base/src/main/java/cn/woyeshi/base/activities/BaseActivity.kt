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
import cn.woyeshi.presenter.base.IBaseActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by wys on 2017/11/8.
 */
abstract class BaseActivity : AppCompatActivity(), IBaseActivity {

    val tag: String = javaClass.simpleName

    private var backBtn: View? = null
    private var tvTitleBar: TextView? = null

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
        backBtn?.setOnClickListener({
            onBackBtnClick()
        })
        onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackBtnClick()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 添加Subscription
     * @param subscription
     */
    override fun <T> addSubscription(flowable: Flowable<T>) {
        Log.d(tag, "addSubscription()")
        flowable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
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

    override fun showLoading(msg: String, cancelable: Boolean) {
        LoadingDialog(this, msg, cancelable).show()
    }

    override fun hideLoading() {
        LoadingDialog.cancel()
    }

    open fun onActivityCreateStart(savedInstanceState: Bundle?) {

    }               //activity的onCreate方法开始

    abstract fun getContentLayoutID(): Int

    /**
     *
     */
    abstract fun onActivityCreated(savedInstanceState: Bundle?)         //activity的onCreate方法结束

}
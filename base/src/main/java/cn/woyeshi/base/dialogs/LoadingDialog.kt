package cn.woyeshi.base.dialogs

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import cn.woyeshi.base.R
import cn.woyeshi.base.activities.BaseActivity
import org.jetbrains.anko.find

class LoadingDialog(activity: BaseActivity, var msg: String?, private var cancelable: Boolean) : BaseDialog(activity) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var loadingDialog: LoadingDialog? = null

        fun cancel() {
            loadingDialog?.cancel()
            loadingDialog = null
        }

    }

    override fun isCancelable(): Boolean {
        return cancelable
    }

    override fun getLayoutID(): Int {
        return R.layout.dialog_loading
    }

    private var tvMsg: TextView? = null

    override fun initViews(v: View?) {
        tvMsg = v?.find(R.id.textView32)
        if (!TextUtils.isEmpty(msg)) {
            tvMsg?.text = msg
        }
    }

    override fun getDimAmount(): Float {
        return 0.4f
    }

    override fun show() {
        super.show()
        loadingDialog = this
    }

    override fun cancel() {
        super.cancel()
        loadingDialog = null
    }

    fun updateMsg(msg: String) {
        this.msg = msg
        tvMsg?.text = msg
    }

    fun setCancelableFlag(cancelable: Boolean) {
        this.cancelable = cancelable
        dialog?.setCancelable(cancelable)
    }

}
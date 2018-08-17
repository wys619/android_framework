package cn.woyeshi.base.dialogs

import android.view.View
import android.widget.TextView
import cn.woyeshi.base.R
import java.lang.ref.WeakReference
import cn.woyeshi.base.activities.BaseActivity
import cn.woyeshi.base.dialogs.BaseDialog

class OneBtnDialog(activity: BaseActivity, private val onOkClick: (() -> Unit)) : BaseDialog(activity) {

    companion object {
        private var dialogReference: WeakReference<OneBtnDialog>? = null
        fun cancelCompanionDialog() {
            dialogReference?.get()?.cancel()
            dialogReference = null
        }
    }

    init {
        initDialog()
    }

    override fun isCancelable(): Boolean {
        return false
    }

    override fun getLayoutID(): Int {
        return R.layout.dialog_one_btn
    }

    override fun show() {
        dialogReference = WeakReference(this)
        super.show()
    }

    override fun cancel() {
        dialogReference = null
        super.cancel()
    }

    fun setMsg(msg: String): OneBtnDialog {
        findView<TextView>(R.id.textView54).text = msg
        return this
    }

    fun setBtnText(text: String): OneBtnDialog {
        findView<TextView>(R.id.textView57).text = text
        return this
    }


    override fun initViews(v: View?) {
        findView<TextView>(R.id.textView57).setOnClickListener {
            onOkClick()
            cancel()
        }
    }
}
package cn.woyeshi.base.dialogs

import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.woyeshi.base.R
import cn.woyeshi.base.activities.BaseActivity
import cn.woyeshi.entity.utils.DensityUtil

/**
 * 底部弹出的选项对话框
 */
class BottomOptionDialog(activity: BaseActivity, val options: List<String>, private val callBack: ((String) -> Unit)) : BaseDialog(activity), View.OnClickListener {

    private var cancelBtn: TextView? = null

    private var doNotHideBlurringViewOptions: List<String>? = null

    init {
        initDialog()
    }

    override fun isCancelable(): Boolean {
        return true
    }

    override fun getLayoutID(): Int {
        return R.layout.dialog_bottom_option
    }

    override fun getWidth(): Int {
        return DensityUtil.getScreenWidth(activity)
    }

    override fun getDialogGravity(): Int {
        return Gravity.BOTTOM
    }

    override fun initViews(v: View?) {
        dialog.window.setWindowAnimations(R.style.dialogWindowAnim)
        val optionLayout: LinearLayout = v?.findViewById(R.id.optionsLayout)!!
        for (opt in options) {
            if (TextUtils.isEmpty(opt)) {
                continue
            }
            val item = activity.layoutInflater.inflate(R.layout.item_option, null)
            val tv: TextView = item.findViewById(R.id.tvOption)
            tv.text = opt
            tv.setOnClickListener(this)
            optionLayout.addView(item)
        }
        cancelBtn = v.findViewById(R.id.textView35)
        cancelBtn?.setOnClickListener(this)
    }

    fun setCancelBtnText(text: String): BottomOptionDialog {
        cancelBtn?.text = text
        return this
    }

    fun setDimAmount(dimAmount: Float): BottomOptionDialog {
        val win = dialog.window
        val params = win!!.attributes
        params.dimAmount = dimAmount
        win.attributes = params
        return this
    }

    fun setDoNotHideBlurringViewOptions(options: List<String>): BottomOptionDialog {
        doNotHideBlurringViewOptions = options
        return this
    }


    override fun onClick(v: View?) {
        if (v is TextView) {
            val text = v.text.toString()
            callBack(text)
            if (doNotHideBlurringViewOptions != null && doNotHideBlurringViewOptions?.contains(text)!!) {
                setDoNotHideBlurringViewFlag(true)
            }
            cancel()
        }
    }

}
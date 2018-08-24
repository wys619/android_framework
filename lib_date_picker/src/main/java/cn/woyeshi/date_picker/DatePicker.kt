package cn.woyeshi.date_picker

import android.view.Gravity
import android.view.View
import android.widget.Toast
import cn.woyeshi.base.activities.BaseActivity

object DatePicker {

    fun showPicker(activity: BaseActivity, rootView: View, callback: ((String) -> Unit)) {
        val mChangeBirthDialog = ChangeDatePopwindow(activity)
        mChangeBirthDialog.setDate("2018", "1", "1")
        mChangeBirthDialog.showAtLocation(rootView, Gravity.BOTTOM, 0, 0)
        mChangeBirthDialog.setBirthdayListener { year, month, day ->
            callback("$year-$month-$day")
        }
    }

}
package cn.woyeshi.datePicker

import android.app.Activity

object DatePicker {

    fun showPicker(activity: Activity, callback: ((String) -> Unit)) {
        val mChangeBirthDialog = ChangeDatePopwindow(activity, R.style.date_picker_shareDialogStyle)
        mChangeBirthDialog.setDate("2018", "1", "1")
        mChangeBirthDialog.window.setWindowAnimations(R.style.date_picker_dialogWindowAnim)
        mChangeBirthDialog.setBirthdayListener { year, month, day ->
            callback("$year$month$day")
        }
        mChangeBirthDialog.show()
    }

}
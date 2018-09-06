package cn.woyeshi.datePicker

import android.app.Activity

object DatePicker {

    fun showPicker(activity: Activity, callback: ((Int, Int, Int) -> Unit)) {
        val mChangeBirthDialog = ChangeDatePopwindow(activity, R.style.date_picker_shareDialogStyle)
        mChangeBirthDialog.setDate("2018", "1", "1")
        mChangeBirthDialog.window.setWindowAnimations(R.style.date_picker_dialogWindowAnim)
        mChangeBirthDialog.setBirthdayListener { year, month, day ->
            val y = year.substring(0, year.length - 1).toInt()
            val m = month.substring(0, month.length - 1).toInt()
            val d = day.substring(0, day.length - 1).toInt()
            callback(y, m, d)
        }
        mChangeBirthDialog.show()
    }

}
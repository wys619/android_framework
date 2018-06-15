package cn.woyeshi.base.views

import android.content.Context
import com.facebook.drawee.view.SimpleDraweeView

/**
 * 给SimpleDraweeView扩展了一个方法，直接设置资源id就OK了
 * Created by wys on 15/03/2018.
 */

fun SimpleDraweeView.setImageById(context: Context, resId: Int) {
    this.setImageURI("res://${context.packageName}/$resId")
}
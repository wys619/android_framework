package cn.woyeshi.base.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cn.woyeshi.base.R;
import cn.woyeshi.base.activities.BaseActivity;
import cn.woyeshi.entity.utils.DensityUtil;

/**
 * Created by WYS on 2016/9/11.
 */
public abstract class BaseDialog implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    protected BaseActivity activity;
    protected Dialog dialog;
    protected final String TAG = getClass().getSimpleName();
    private IOnCancelListener onCancelListener;
    private IOnDismissListener onDismissListener;
    protected View contentView;
    private boolean doNotHideBlurringViewFlag = false;

    public BaseDialog(BaseActivity activity) {
        this.activity = activity;
    }

    protected final Dialog initDialog() {
        dialog = new Dialog(activity, R.style.shareDialogStyle);
        View v = activity.getLayoutInflater().inflate(getLayoutID(), null);
        contentView = v;
        dialog.setContentView(v);
        dialog.setCancelable(isCancelable());
        dialog.setOnCancelListener(this);
        dialog.setOnDismissListener(this);
        if (dialog == null) {
            return null;
        }
        Window win = dialog.getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        params.width = getWidth();
        params.height = getHeight();
        params.dimAmount = getDimAmount();
        params.gravity = getDialogGravity();
        win.setAttributes(params);
        initViews(v);
        return dialog;
    }

    public void onDestroy() {
        dialog = null;
        activity = null;
        System.gc();
    }

    public BaseDialog setOnCancelListener(IOnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public void setOnDismissListener(IOnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }


    protected int getDialogGravity() {
        return Gravity.CENTER;
    }

    public void show() {
        if (activity.isFinishing()) {
            if (dialog != null) {
                dialog.cancel();
                dialog = null;
            }
            return;
        }
        if (dialog == null) {
            initDialog();
        }
        if (dialog == null) {
            return;
        }
        setDoNotHideBlurringViewFlag(false);
        if (isBlurringViewEnabled() && activity != null) {
            activity.showBlurringView(true);
        }
        dialog.show();
    }

    public void cancel() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
        dialog = null;
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
                cancel();
            }
        }
    }

    public void setDoNotHideBlurringViewFlag(boolean doNotHideBlurringViewFlag) {
        this.doNotHideBlurringViewFlag = doNotHideBlurringViewFlag;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public boolean isShowing() {
        if (dialog == null) {
            return false;
        }
        return dialog.isShowing();
    }

    /**
     * 设置是否可以取消
     *
     * @param flag
     */
    public BaseDialog setCancelable(boolean flag) {
        if (dialog != null) {
            dialog.setCancelable(flag);
        }
        return this;
    }

    protected float getDimAmount() {
        return 0.7f;
    }

    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * Dailog的像素宽度
     *
     * @return
     */
    protected int getWidth() {
        return DensityUtil.INSTANCE.dip2px(activity, 280);
    }

    //是否启用高斯模糊遮罩，默认是true，要修改交互请覆盖这个方法
    protected boolean isBlurringViewEnabled() {
        return true;
    }

    /**
     * 取消对话框的时候
     *
     * @param dialogInterface
     */
    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (onCancelListener != null) {
            onCancelListener.onCancel();
            onCancelListener = null;
        }
        if (isBlurringViewEnabled() && activity != null && !doNotHideBlurringViewFlag) {
            activity.showBlurringView(false);
        }
        onDestroy();        //回收资源
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
            onDismissListener = null;
        }
        if (isBlurringViewEnabled() && activity != null && !doNotHideBlurringViewFlag) {
            activity.showBlurringView(false);
        }
    }

    public interface IOnCancelListener {
        void onCancel();
    }

    public interface IOnDismissListener {
        void onDismiss();
    }

    protected abstract boolean isCancelable();

    protected abstract int getLayoutID();

    protected abstract void initViews(View v);

}
package cn.woyeshi.base.views

import android.content.Context
import android.text.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.woyeshi.base.R
import kotlinx.android.synthetic.main.view_input_layout.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textColor

/**
 * Created by wys on 03/04/2018.
 */
class InputLayoutView : LinearLayout {

    private var maxLength: Int = 128

    constructor (context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        val view = LayoutInflater.from(context).inflate(R.layout.view_input_layout, null, false)
        val param = LayoutParams(LayoutParams.MATCH_PARENT, dip2px(context, 56f))
        addView(view, param)
        val array = context.obtainStyledAttributes(attrs, R.styleable.InputLayoutView)
        maxLength = array.getInt(R.styleable.InputLayoutView_maxLength, 128)
        val filters: Array<InputFilter> = arrayOf(InputFilter.LengthFilter(maxLength))
        editText2.filters = filters
        val readonly = array.getBoolean(R.styleable.InputLayoutView_readOnly, false)
        if (readonly) {
            editText2.isEnabled = false
            editText2.textColor = resources.getColor(R.color.n_gray_2)
        }
        editText2.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(editText2.text.toString().trim())) {
                ivClearText.visibility = View.VISIBLE
            } else {
                ivClearText.visibility = View.GONE
            }
        }
        val title: String? = array.getString(R.styleable.InputLayoutView_title)
        if (!TextUtils.isEmpty(title)) {
            textView58.text = title
        }
        val hint: String? = array.getString(R.styleable.InputLayoutView_hint)
        if (!TextUtils.isEmpty(hint)) {
            editText2.hint = hint
        }
        val text: String? = array.getString(R.styleable.InputLayoutView_text)
        if (!TextUtils.isEmpty(text)) {
            editText2.text.append(text)
        }
        val inputType: String? = array.getString(R.styleable.InputLayoutView_inputType)
        if (!TextUtils.isEmpty(inputType)) {
            when (inputType) {
                "number" -> {
                    editText2.inputType = InputType.TYPE_CLASS_NUMBER
                }
                "phone" -> {
                    editText2.inputType = InputType.TYPE_CLASS_PHONE
                }
                "password" -> {
                    editText2.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                }
                "email" -> {
                    editText2.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
            }
        }
        editText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(editText2.text) || TextUtils.isEmpty(editText2.text.trim())) {
                    ivClearText.visibility = View.GONE
                } else {
                    ivClearText.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        ivClearText.onClick {
            editText2.setText("")
        }
        array.recycle()
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }


    fun getText(): String {
        return editText2.text.toString().trim()
    }

    fun setText(text: String) {
        editText2.setText("")
        editText2.append(text)
    }

    fun setTitle(title: String) {
        if (TextUtils.isEmpty(title)) {
            return
        }
        textView58.text = title
    }

    fun addTextWatcher(textWatcher: TextWatcher) {
        editText2.addTextChangedListener(textWatcher)
    }

    fun removeTextWatcher(textWatcher: TextWatcher) {
        editText2.removeTextChangedListener(textWatcher)
    }

}
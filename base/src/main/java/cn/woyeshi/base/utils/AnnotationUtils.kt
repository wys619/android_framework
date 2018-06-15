package cn.woyeshi.base.utils

import cn.woyeshi.entity.annotations.Autowired
import cn.woyeshi.presenter.base.IBaseView
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.util.regex.Pattern

object AnnotationUtils {
    /**
     * 绑定
     *
     * @param view
     */
    fun bind(view: IBaseView) {
        val fields = view.javaClass.declaredFields
        if (fields != null) {
            for (field in fields) {
                try {
                    iniField(field, view)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    private fun iniField(field: Field, view: IBaseView) {
        val atw = field.getAnnotation(Autowired::class.java)
        if (atw != null) {
            field.isAccessible = true
            val clzFullName = field.type.name//全名
            if (match("^cn.woyeshi.presenterimpl.presenters.I(\\w*)$", clzFullName)) {
                val clzName = field.type.simpleName//类名
                val implClzName = clzFullName.replace(clzName, clzName.replaceFirst("^I|$".toRegex(), ""))//实现类
                try {
                    val targetClz = Class.forName(implClzName)
                    try {
                        var constructor: Constructor<*>? = null
                        try {
                            //获取构造函数
                            val constructors = targetClz.constructors
                            if (constructors != null && constructors.isNotEmpty()) {
                                constructor = targetClz.constructors[0]
                            }
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                        val obj = if (constructor != null) {
                            try {
                                constructor.newInstance(view)
                            } catch (e: Exception) {
                                e.printStackTrace()
                                constructor.newInstance()
                            }
                        } else {
                            targetClz.newInstance()
                        }
                        field.set(view, obj)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        throw RuntimeException(e)
                    }
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                    throw RuntimeException(e)
                }
            }
        }
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private fun match(regex: String, str: String): Boolean {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }
}


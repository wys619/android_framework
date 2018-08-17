package cn.woyeshi.presenter.base

class BaseException(val code: Int, val msg: String?) : RuntimeException(msg)
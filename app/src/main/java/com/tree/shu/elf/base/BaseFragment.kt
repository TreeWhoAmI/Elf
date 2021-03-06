package com.tree.shu.elf.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<V: IBaseView, P: IBasePresenter<V, M>, M: IBaseModel>: Fragment() {
    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.attachView(getViewToAttach())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutRes(), container, false)
        onCreateView(view, savedInstanceState)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    /**
     * 对view的操作
     */
    abstract fun onCreateView(view: View, savedInstanceState: Bundle?)

    /**
     * 返回一个用于创建view的资源id
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * 返回需要绑定的IBaseView对象
     */
    abstract fun getViewToAttach(): V

    /**
     * 创建IBasePresenter对象
     */
    abstract fun createPresenter(): P
}
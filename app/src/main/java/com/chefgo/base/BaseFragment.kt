package com.chefgo.base

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

open class BaseFragment : Fragment(), BaseView {

    var mContext: Context? = null
    var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as BaseActivity
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
        mActivity = null
    }

    fun getBooleanLocal(key: String?): Boolean {
        return mActivity!!.getBooleanLocal(key)
    }

    protected fun deleteDataLocal(key: String?) {
        mActivity!!.deleteDataLocal(key)
    }

    fun capitalize(text: String?): String {
        return mActivity!!.capitalize(text!!)
    }

    protected fun loadRecycler(
        orientation: Int = LinearLayout.VERTICAL,
        adapter: RecyclerView.Adapter<*>,
        recyclerView: RecyclerView,
        view: View? = null
    ) {
        if (mActivity != null)
            mActivity!!.loadRecycler(orientation, adapter, recyclerView, view)
    }

    protected fun loadGridLayout(
        adapter: RecyclerView.Adapter<*>,
        recyclerView: RecyclerView,
        view: View? = null
    ) {
        if (mActivity != null)
            mActivity!!.loadGridLayout(adapter, recyclerView, view)
    }

    protected fun updateSateRecycler(
        recyclerView: RecyclerView,
        view: View? = null
    ) {
        if (mActivity != null)
            mActivity!!.updateSateRecycler(recyclerView, view)
    }

    protected fun <T> updateSateRecycler(
        recyclerView: RecyclerView,
        array: ArrayList<T>,
        view: View? = null
    ) {
        if (mActivity != null)
            mActivity!!.updateSateRecycler(recyclerView, array, view)
    }

    override fun showViewMessage(message: String?) {
        if (mActivity != null)
            mActivity!!.showViewMessage(message)
    }

    override fun onFailure(className: String, methodName: String, t: Throwable) {
        if (mActivity != null)
            mActivity!!.onFailure(className, methodName, t)
    }

    protected open fun hideKeyboard() {
        if (mActivity != null)
            mActivity!!.hideKeyboard()
    }

    protected open fun showKeyboard() {
        if (mActivity != null)
            mActivity!!.showKeyboard()
    }

}
package com.chefgo.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chefgo.util.MiddleDividerItemDecoration
import io.paperdb.Paper
import java.util.*

open class BaseActivity : AppCompatActivity(), BaseView {

    private var mContext: Context? = null
    private var mActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
        mContext = this
        mActivity = this
    }

    override fun onDestroy() {
        super.onDestroy()
        mContext = null
        mActivity = null
    }

    private fun initParams() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

    }

    override fun showViewMessage(message: String?) {

    }

    open fun loadRecycler(
        orientation: Int = RecyclerView.VERTICAL,
        adapter: RecyclerView.Adapter<*>,
        recyclerView: RecyclerView,
        view: View? = null
    ) {
        recyclerView.layoutManager = LinearLayoutManager(this, orientation, false)
        recyclerView.adapter = adapter
        updateSateRecycler(recyclerView, view)
    }

    open fun loadGridLayout(
        adapter: RecyclerView.Adapter<*>,
        recyclerView: RecyclerView,
        view: View? = null
    ) {
//        val itemDecoration = MiddleDividerItemDecoration(
//            this,
//            MiddleDividerItemDecoration.ALL
//        )
//        itemDecoration.setDividerColor(ContextCompat.getColor(this, android.R.color.holo_purple))
//        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
        updateSateRecycler(recyclerView, view)
    }

    open fun updateSateRecycler(
        recyclerView: RecyclerView,
        view: View? = null
    ) {
        recyclerView.visibility = View.GONE
        if (view != null)
            view.visibility = View.VISIBLE
    }

    open fun <T> updateSateRecycler(
        recyclerView: RecyclerView,
        array: ArrayList<T> = ArrayList(),
        view: View? = null
    ) {
        recyclerView.visibility = if (array.isEmpty()) View.GONE else View.VISIBLE
        recyclerView.setItemViewCacheSize(if (array.isEmpty()) 0 else array.size)

        if (view != null)
            view.visibility = if (array.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onFailure(className: String, methodName: String, t: Throwable) {
//        App.mFirebaseCrashlytics.log("$className: $methodName: ${t.message}")
//        App.mFirebaseCrashlytics.recordException(t)
    }

    fun capitalize(text: String): String {
        val strArray = text.split(" ").toTypedArray()
        val builder = StringBuilder()
        for (s in strArray) {
            val cap = s.substring(0, 1).uppercase(Locale.getDefault()) + s.substring(1).lowercase(
                Locale.getDefault()
            )
            builder.append(cap).append(" ")
        }
        return builder.toString()
    }

    open fun hideKeyboard() {
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    open fun showKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.showSoftInput(view, 0)
    }

    protected fun showLoading(view: View, llLoad: LinearLayout) {
        enabledView(view, false)
        llLoad.visibility = View.VISIBLE
    }

    protected fun hideLoading(view: View, llLoad: LinearLayout) {
        enabledView(view, true)
        llLoad.visibility = View.GONE
    }

    private fun enabledView(view: View, enabled: Boolean) {
        view.isEnabled = enabled
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                enabledView(child, enabled)
            }
        }
    }

    fun <T> setDataLocal(key: String?, value: T) {
        Paper.book().write(key, value)
    }

    fun <T> setBookDataLocal(book: String?, key: String?, value: T) {
        Paper.book(book).write(key, value)
    }

    fun getDataLocal(key: String?): Any {
        return Paper.book().read(key)
    }

    private fun getBookDataLocal(book: String?, key: String?): Any? {
        return Paper.book(book).read(key)
    }

    fun getBooleanLocal(key: String?): Boolean {
        return Paper.book().read(key, false)
    }

    fun getStringLocal(key: String?): String {
        return Paper.book().read(key)
    }

    fun deleteDataLocal(key: String?) {
        Paper.book().delete(key)
    }

    protected fun deleteBookDataLocal(book: String?) {
        Paper.book(book).destroy()
    }

    override fun onResume() {
        super.onResume()
    }
}
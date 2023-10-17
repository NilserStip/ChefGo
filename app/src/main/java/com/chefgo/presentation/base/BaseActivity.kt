package com.chefgo.presentation.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chefgo.App
import com.chefgo.R
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
        Toast.makeText(this, message ?: getString(R.string.error_service), Toast.LENGTH_LONG).show()
    }

    open fun loadGridLayout(
        adapter: RecyclerView.Adapter<*>,
        recyclerView: RecyclerView,
        view: View? = null
    ) {
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
        App.mFirebaseCrashlytics.log("$className: $methodName: ${t.message}")
        App.mFirebaseCrashlytics.recordException(t)
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

}
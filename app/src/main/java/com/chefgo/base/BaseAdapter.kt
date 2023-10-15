package com.chefgo.base

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter <T : RecyclerView.ViewHolder?> : RecyclerView.Adapter<T>() {

    fun getPX(context: Context, number: Int): Int {
        val r: Resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            number.toFloat(),
            r.displayMetrics
        ).toInt()
    }

}
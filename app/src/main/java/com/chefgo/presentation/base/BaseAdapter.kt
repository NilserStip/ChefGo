package com.chefgo.presentation.base

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<T : RecyclerView.ViewHolder?> : RecyclerView.Adapter<T>() {

    fun getPX(context: Context, number: Int): Int {
        val r: Resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            number.toFloat(),
            r.displayMetrics
        ).toInt()
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

}
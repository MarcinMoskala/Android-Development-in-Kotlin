package com.kotlinacademy.todoapp

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.LocalDate
import org.joda.time.LocalTime

@SuppressLint("ParcelCreator")
@Parcelize
data class Task(
        val id: Int,
        val name: String,
        val time: LocalTime? = null,
        val date: LocalDate? = null
): Parcelable
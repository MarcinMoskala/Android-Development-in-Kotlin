package com.kotlinacademy.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_tasks.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime

fun Context.showDatePicker(initialValue: LocalDate, onChange: (LocalDate) -> Unit) {
    val onChoseListener: (DatePicker, Int, Int, Int) -> Unit = { _, year, month, dayOfMonth ->
        onChange(LocalDate(year, month + 1, dayOfMonth))
    }
    DatePickerDialog(this, onChoseListener, initialValue.year, initialValue.monthOfYear, initialValue.dayOfMonth).show()
}

fun Context.showTimePicker(initialValue: LocalTime, onChange: (LocalTime) -> Unit) {
    val onChoseListener: (TimePicker, Int, Int) -> Unit = { _, hourOfDay, minute ->
        onChange(LocalTime(hourOfDay, minute))
    }
    TimePickerDialog(this, onChoseListener, initialValue.hourOfDay, initialValue.minuteOfHour, true).show()
}

fun RecyclerView.setUpItemTouchCallback(onMove: (fromPosition: Int, toPosition: Int)->Unit, onSwiped: (position: Int)->Unit) {
    val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            onMove(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val itemPosition = viewHolder.adapterPosition
            onSwiped(itemPosition)
        }
    }
    ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(this)
}
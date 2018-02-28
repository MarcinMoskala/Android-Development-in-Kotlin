package com.kotlinacademy.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import org.joda.time.LocalDate
import org.joda.time.LocalTime

fun Context.showDatePicker(initialValue: LocalDate, onChange: (LocalDate) -> Unit) {
    val onChoseListener: (DatePicker, Int, Int, Int) -> Unit = { _, year, month, dayOfMonth ->
        onChange(LocalDate(year, month, dayOfMonth))
    }
    DatePickerDialog(this, onChoseListener, initialValue.year, initialValue.monthOfYear, initialValue.dayOfMonth).show()
}

fun Context.showTimePicker(initialValue: LocalTime, onChange: (LocalTime) -> Unit) {
    val onChoseListener: (TimePicker, Int, Int) -> Unit = { _, hourOfDay, minute ->
        onChange(LocalTime(hourOfDay, minute))
    }
    TimePickerDialog(this, onChoseListener, initialValue.hourOfDay, initialValue.minuteOfHour, true).show()
}

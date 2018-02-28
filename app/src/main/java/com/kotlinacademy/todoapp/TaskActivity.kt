package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_task.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class TaskActivity : AppCompatActivity() {

    var date: LocalDate? = null
    var time: LocalTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        addTaskButton.setOnClickListener {
            val name = taskNameView.text.toString()
            val task = Task(name, time, date)
            val bundle = Intent()
            bundle.putExtra("task", task)
            setResult(Activity.RESULT_OK, bundle)
            finish()
        }
        dateView.setOnClickListener {
            showDatePicker(LocalDate.now()) { chosenDate ->
                dateView.text = "Chosen date is " + chosenDate.toString(DATE_FORMAT)
                date = chosenDate
            }
        }
        timeView.setOnClickListener {
            showTimePicker(LocalTime.now()) { chosenTime ->
                timeView.text = "Chosen time is " + chosenTime.toString(TIME_FORMAT)
                time = chosenTime
            }
        }
    }
}

package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_task.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val id = intent.getIntExtra("id", -1)
        var task: Task = if (id == -1) intent.getParcelableExtra("task") else Task(id, "")

        fun updateDateText() {
            val date = task.date
            dateView.text = if (date == null) "Click to chose date" else "Chosen date is " + date.toString(DATE_FORMAT)
        }

        fun updateTimeText() {
            val time = task.time
            timeView.text = if (time == null) "Click to chose time" else "Chosen time is " + time.toString(TIME_FORMAT)
        }

        taskNameView.setText(task.name)
        updateDateText()
        updateTimeText()

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        addTaskButton.setOnClickListener {
            val name = taskNameView.text.toString()
            val bundle = Intent()
            bundle.putExtra("task", task.copy(name = name))
            setResult(Activity.RESULT_OK, bundle)
            finish()
        }
        dateView.setOnClickListener {
            showDatePicker(LocalDate.now()) { chosenDate ->
                task = task.copy(date = chosenDate)
                updateDateText()
            }
        }
        timeView.setOnClickListener {
            showTimePicker(LocalTime.now()) { chosenTime ->
                task = task.copy(time = chosenTime)
                updateTimeText()
            }
        }
    }
}

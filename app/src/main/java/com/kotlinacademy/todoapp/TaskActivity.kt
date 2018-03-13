package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_task.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class TaskActivity : AppCompatActivity() {

    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val id = intent.getIntExtra(ID_ARG, -1)
        task = if (id == -1) intent.getParcelableExtra(TASK_ARG) else Task(id, "")

        setUpTexts()
        setUpListeners()
    }

    private fun setUpListeners() {
        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        addTaskButton.setOnClickListener {
            acceptTask()
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
        taskNameView.setOnEditorActionListener { _, actionId: Int, _ ->
            val consumeAction = actionId == EditorInfo.IME_ACTION_SEND
            if (consumeAction) acceptTask()
            consumeAction
        }
    }

    private fun setUpTexts() {
        taskNameView.setText(task.name)
        updateDateText()
        updateTimeText()
    }

    private fun updateDateText() {
        val date = task.date
        dateView.text = if (date == null) "Click to chose date" else "Chosen date is " + date.toString(DATE_FORMAT)
    }

    private fun updateTimeText() {
        val time = task.time
        timeView.text = if (time == null) "Click to chose time" else "Chosen time is " + time.toString(TIME_FORMAT)
    }

    private fun acceptTask() {
        val name = taskNameView.text.toString()
        val bundle = Intent()
        bundle.putExtra(TASK_ARG, task.copy(name = name))
        setResult(Activity.RESULT_OK, bundle)
        finish()
    }

    companion object {
        private const val ID_ARG = "id"
        private const val TASK_ARG = "task"

        fun getIntentForNewTask(context: Context, id: Int): Intent {
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(ID_ARG, id)
            return intent
        }

        fun getIntentForEditTask(context: Context, task: Task): Intent {
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(TASK_ARG, task)
            return intent
        }

        fun getTask(data: Intent?) = data?.getParcelableExtra<Task>(TASK_ARG)
    }
}

package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_tasks.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class TasksActivity : AppCompatActivity() {

    private val tasksListAdapter by lazy { TasksListAdapter(this::startEditTaskActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        fab.setOnClickListener {
            startCreateTaskActivity()
        }
        setUpList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
            requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK -> {
                val task = TaskActivity.getTask(data) ?: return
                tasksListAdapter.add(task)
            }
            requestCode == EDIT_TASK_REQUEST && resultCode == Activity.RESULT_OK -> {
                val task = TaskActivity.getTask(data) ?: return
                tasksListAdapter.update(task)
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setUpList() {
        tasksListAdapter.add(Task(1, "Wash dishes"))
        tasksListAdapter.add(Task(2, "Make breakfast", date = LocalDate.now()))
        tasksListAdapter.add(Task(3, "Record awesome course", time = LocalTime.now(), date = LocalDate.now()))
        tasksListView.adapter = tasksListAdapter
        tasksListView.layoutManager = LinearLayoutManager(this)
        tasksListView.setUpItemTouchCallback(
                onMove = tasksListAdapter::moveItem,
                onSwiped = tasksListAdapter::removeAt
        )
    }

    private fun startCreateTaskActivity() {
        val intent = TaskActivity.getIntentForNewTask(this, tasksListAdapter.getNextId())
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    private fun startEditTaskActivity(task: Task) {
        val intent = TaskActivity.getIntentForEditTask(this, task)
        startActivityForResult(intent, EDIT_TASK_REQUEST)
    }

    companion object {
        private const val ADD_TASK_REQUEST = 12342
        private const val EDIT_TASK_REQUEST = 12343
    }
}
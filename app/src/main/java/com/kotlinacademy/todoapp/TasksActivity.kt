package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_tasks.*

class TasksActivity : AppCompatActivity() {

    private val ADD_TASK_REQUEST = 12342
    private val tasksListAdapter by lazy { TasksListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        fab.setOnClickListener {
            startActivityForResult(Intent(this, TaskActivity::class.java), ADD_TASK_REQUEST)
        }
        tasksListAdapter.add(Task("Wash dishes"))
        tasksListView.adapter = tasksListAdapter
        tasksListView.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val task = data?.getParcelableExtra<Task>("task") ?: return
            tasksListAdapter.add(task)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
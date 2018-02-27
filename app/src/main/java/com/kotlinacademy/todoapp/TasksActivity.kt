package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_tasks.*
import kotlin.properties.Delegates.observable

class TasksActivity : AppCompatActivity() {

    var tasks by observable(listOf(Task("Wash dishes"), Task("Make Kotlin course"))) { _, _, _ ->
        refreshList()
    }
    val ADD_TASK_REQUEST = 12342

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        fab.setOnClickListener {
            startActivityForResult(Intent(this, TaskActivity::class.java), ADD_TASK_REQUEST)
        }
        refreshList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            tasks += data?.getParcelableExtra<Task>("task") ?: return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun refreshList() {
        val tasksNames = tasks.map { it.name }
        tasksListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasksNames)
    }
}
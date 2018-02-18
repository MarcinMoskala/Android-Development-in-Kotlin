package com.kotlinacademy.todoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tasks.*

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        fab.setOnClickListener {
            Toast.makeText(this, "Hello, I am toast", Toast.LENGTH_LONG).show()
        }
        val list = (1..100).map { "Task number $it" }
        tasksListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
    }
}
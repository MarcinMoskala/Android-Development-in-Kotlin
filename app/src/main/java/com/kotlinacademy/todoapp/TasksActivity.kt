package com.kotlinacademy.todoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tasks.*

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        fab.setOnClickListener {
            Toast.makeText(this, "Hello, I am toast", Toast.LENGTH_LONG).show()
        }
    }
}
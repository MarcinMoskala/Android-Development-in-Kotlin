package com.kotlinacademy.todoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        Toast.makeText(this, "Hello, I am toast", Toast.LENGTH_LONG).show()
    }
}
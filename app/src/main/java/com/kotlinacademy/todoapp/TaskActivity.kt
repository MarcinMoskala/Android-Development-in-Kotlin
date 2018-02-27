package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        addTaskButton.setOnClickListener {
            val name = taskNameView.text.toString()
            val bundle = Intent()
            bundle.putExtra("name", name)
            setResult(Activity.RESULT_OK, bundle)
            finish()
        }
    }
}

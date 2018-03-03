package com.kotlinacademy.todoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_tasks.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class TasksActivity : AppCompatActivity() {

    private val ADD_TASK_REQUEST = 12342
    private val tasksListAdapter by lazy { TasksListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        fab.setOnClickListener {
            startActivityForResult(Intent(this, TaskActivity::class.java), ADD_TASK_REQUEST)
        }
        setUpList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val task = data?.getParcelableExtra<Task>("task") ?: return
            tasksListAdapter.add(task)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setUpList() {
        tasksListAdapter.add(Task("Wash dishes"))
        tasksListAdapter.add(Task("Make breakfast", date = LocalDate.now()))
        tasksListAdapter.add(Task("Record awesome course", time = LocalTime.now(), date = LocalDate.now()))
        tasksListView.adapter = tasksListAdapter
        tasksListView.layoutManager = LinearLayoutManager(this)

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPositon = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                tasksListAdapter.moveItem(fromPositon, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemPositon = viewHolder.adapterPosition
                tasksListAdapter.removeAt(itemPositon)
            }
        }
        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(tasksListView)
    }
}
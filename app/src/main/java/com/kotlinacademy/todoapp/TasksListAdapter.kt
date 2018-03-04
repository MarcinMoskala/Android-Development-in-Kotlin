package com.kotlinacademy.todoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TasksListAdapter(val onClick: (Task) -> Unit) : RecyclerView.Adapter<TasksListAdapter.ViewHolder>() {

    private val tasks: MutableList<Task> = mutableListOf()

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.nameView.text = task.name

        val date = task.date?.toString(DATE_FORMAT) ?: ""
        val time = task.time?.toString(TIME_FORMAT) ?: ""
        holder.labelsView.text = "$date $time"

        holder.view.setOnClickListener {
            onClick(task)
        }
    }

    fun add(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun update(task: Task) {
        val position = tasks.indexOfFirst { it.id == task.id }
        tasks.removeAt(position)
        tasks.add(position, task)
        notifyItemChanged(position)
    }

    fun moveItem(from: Int, to: Int) {
        val item = tasks[from]
        tasks.removeAt(from)
        tasks.add(to, item)
        notifyItemMoved(from, to)
    }

    fun removeAt(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getNextId() = (tasks.map { it.id }.max() ?: 0) + 1
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.nameVIew)
        val labelsView: TextView = view.findViewById(R.id.labelsView)

    }
}
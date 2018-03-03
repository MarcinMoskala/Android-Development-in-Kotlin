package com.kotlinacademy.todoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TasksListAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TasksListAdapter.ViewHolder>() {

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
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.nameVIew)
        val labelsView: TextView = view.findViewById(R.id.labelsView)
    }
}
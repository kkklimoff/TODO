package com.kk191rdb020.todo.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kk191rdb020.todo.data.Task
import com.kk191rdb020.todo.databinding.ItemTaskBinding

class TasksAdapter(private val listener: OnItemClickListener) : ListAdapter<Task, TasksAdapter.TasksViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class TasksViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){

        init{
            binding.apply{
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position!= RecyclerView.NO_POSITION){
                        val task = getItem(position)
                        listener.onItemClick(task)
                    }
                }
                checkBoxCompltd.setOnClickListener {
                    val position = adapterPosition
                    if (position!= RecyclerView.NO_POSITION){
                        val task = getItem(position)
                        listener.onCheckBoxClick(task, checkBoxCompltd.isChecked)
                    }
                }
            }
        }

        fun bind(task: Task) {
            binding.apply{
                checkBoxCompltd.isChecked = task.completed
                textViewTaskName.text = task.name
                textViewTaskName.paint.isStrikeThruText = task.completed
                labelPriority.isVisible = task.important
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
        fun onCheckBoxClick(task: Task, isChecked: Boolean)
    }

    class DiffCallback: DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem == newItem

    }

}
package inas.anisha.classify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import inas.anisha.classify.R
import inas.anisha.classify.db.entity.TaskData
import java.util.*

class TaskAdapter(
    private val tasks: List<TaskData>,
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: TaskData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position], clickListener)
    }

    override fun getItemCount() = tasks.size

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var taskName: TextView
        var dueDate: TextView
        var description: TextView

        init {
            taskName = view.findViewById(R.id.text_view_task_name)
            dueDate = view.findViewById(R.id.text_view_task_due_date)
            description = view.findViewById(R.id.text_view_task_description)
        }

        fun bind(task: TaskData, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(task) }
            taskName.text = task.taskName
            description.text = task.taskDescription

            val year = task.taskDueDate.get(Calendar.YEAR)
            val month =
                task.taskDueDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
            val day = task.taskDueDate.get(Calendar.DAY_OF_MONTH)
            val time =
                "" + task.taskDueDate.get(Calendar.HOUR_OF_DAY) + ":" + task.taskDueDate.get(
                    Calendar.MINUTE
                )
            dueDate.text = "$day $month $year $time"

        }
    }

}
package inas.anisha.classify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import inas.anisha.classify.R
import java.util.*


class TaskAdapter(private val tasks: MutableList<TaskDataModel>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        tasks[position].let {
            holder.taskName.text = it.taskName
            val year = it.dueDate.get(Calendar.YEAR)
            val month = it.dueDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
            val day = it.dueDate.get(Calendar.DAY_OF_MONTH)
            val time =
                "" + it.dueDate.get(Calendar.HOUR_OF_DAY) + ":" + it.dueDate.get(Calendar.MINUTE)
            holder.dueDate.text = "$day $month $year $time"
            holder.description.text = it.description
        }
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
    }

}
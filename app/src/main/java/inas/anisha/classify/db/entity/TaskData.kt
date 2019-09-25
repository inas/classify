package inas.anisha.classify.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task")
data class TaskData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_id") val id: Long,
    @ColumnInfo(name = "task_name") val taskName: String,
    @ColumnInfo(name = "task_description") var taskDescription: String,
    @ColumnInfo(name = "task_due_date") var taskDueDate: Calendar
)
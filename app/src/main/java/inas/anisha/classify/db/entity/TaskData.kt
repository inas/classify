package inas.anisha.classify.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "task")
@Parcelize
data class TaskData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_id") val id: Long,
    @ColumnInfo(name = "task_name") val taskName: String,
    @ColumnInfo(name = "task_description") var taskDescription: String,
    @ColumnInfo(name = "task_due_date") var taskDueDate: Calendar
) : Parcelable
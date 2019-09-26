package inas.anisha.classify.db.dao

import androidx.room.*
import inas.anisha.classify.db.entity.TaskData

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taskData: TaskData)

    @Update
    fun updateTask(taskData: TaskData)

    @Delete
    fun deleteTask(vararg task: TaskData)

    @Query("DELETE FROM task")
    fun deleteAll()

    @Query("SELECT * from task ORDER BY task_due_date ASC")
    fun getAllTask(): List<TaskData>

}
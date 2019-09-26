package inas.anisha.classify

import android.app.Application
import android.os.AsyncTask
import inas.anisha.classify.db.AppDatabase
import inas.anisha.classify.db.dao.TaskDao
import inas.anisha.classify.db.entity.TaskData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class Repository(application: Application) {

    lateinit var taskDao: TaskDao

    init {
        val db = AppDatabase.getDatabase(application)
        taskDao = db.taskDao()
    }

    fun getAllTasks(): List<TaskData> {
        return Observable.fromCallable { taskDao.getAllTask() }
            .subscribeOn(Schedulers.io())
            .elementAt(0, mutableListOf())
            .blockingGet()
    }

    fun addTask(task: TaskData) {
        insertTaskAsyncTask(taskDao).execute(task)
    }

    fun deleteTask(task: TaskData) {
        deleteTaskAsyncTask(taskDao).execute(task)
    }

    private class insertTaskAsyncTask internal constructor(private val mAsyncTaskDao: TaskDao) :
        AsyncTask<TaskData, Void, Void>() {
        override fun doInBackground(vararg params: TaskData): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class deleteTaskAsyncTask internal constructor(private val mAsyncTaskDao: TaskDao) :
        AsyncTask<TaskData, Void, Void>() {
        override fun doInBackground(vararg params: TaskData): Void? {
            mAsyncTaskDao.deleteTask(params[0])
            return null
        }
    }
}
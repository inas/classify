package inas.anisha.classify.fragments.taskDetail


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import inas.anisha.classify.R
import inas.anisha.classify.Repository
import inas.anisha.classify.db.entity.TaskData
import kotlinx.android.synthetic.main.task_detail_fragment.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class TaskDetailFragment : Fragment() {

    lateinit var data: TaskData
    var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_delete_task.setOnClickListener {
            activity?.application?.let { it1 -> Repository(it1).deleteTask(arguments?.get("task_data") as TaskData) }
            findNavController().navigateUp()
        }

        arguments?.get("task_data")?.let {
            data = it as TaskData
            text_view_task_name.text = data.taskName
            text_view_task_description.text = data.taskDescription

            val year = data.taskDueDate.get(Calendar.YEAR)
            val month =
                data.taskDueDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
            val day = data.taskDueDate.get(Calendar.DAY_OF_MONTH)
            val time =
                "" + data.taskDueDate.get(Calendar.HOUR_OF_DAY) + ":" + data.taskDueDate.get(
                    Calendar.MINUTE
                )
            text_view_task_due_date.text = "$day $month $year $time"

        }
    }

    override fun onStart() {
        super.onStart()
        initCountdown()
    }

    override fun onStop() {
        super.onStop()
        Log.d("debug_detail", "cancel")
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun initCountdown() {
        val current = System.currentTimeMillis()
        val timeLeft = data.taskDueDate.timeInMillis - current

        if (timeLeft > 0) countDownTimer = startTimer(timeLeft)
        text_view_time_left.text = getTimeLeftString(timeLeft)
    }

    private fun getTimeLeftString(timeLeft: Long): String {
        val days = (timeLeft / (1000 * 60 * 60 * 24)).toInt()
        val hours = ((timeLeft - (days * (1000 * 60 * 60 * 24))) / (1000 * 60 * 60)) % 24
        val minutes =
            ((timeLeft - ((days * (1000 * 60 * 60 * 24)) + (hours * (1000 * 60 * 60)))) / (1000 * 60)) % 60

        var timeLeftString = "Time left: "
        if (days >= 1) timeLeftString += "" + days + "d "
        if (days >= 1 || hours >= 1) timeLeftString += "" + hours + "h "
        if (minutes >= 0) timeLeftString += "" + minutes + "m "
        if (days <= 0 && hours <= 0.toLong() && minutes <= 0.toLong()) timeLeftString =
            "Task is overdue"

        return timeLeftString
    }

    private fun startTimer(timeLeft: Long): CountDownTimer {
        return object : CountDownTimer(timeLeft, 1000 * 60) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("debug_detail", "tick")
                text_view_time_left.text = getTimeLeftString(millisUntilFinished)
            }

            override fun onFinish() {
            }
        }.also { it.start() }
    }

}

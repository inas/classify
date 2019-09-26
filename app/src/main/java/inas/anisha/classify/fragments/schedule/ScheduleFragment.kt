package inas.anisha.classify.fragments.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import inas.anisha.classify.R
import inas.anisha.classify.adapter.TaskAdapter
import inas.anisha.classify.adapter.TaskDataModel
import inas.anisha.classify.base.BaseFragment
import inas.anisha.classify.navigation.BackNavigationListener
import inas.anisha.classify.navigation.BackNavigationResult
import kotlinx.android.synthetic.main.schedule_fragment.*
import java.util.*


class ScheduleFragment : BaseFragment(), BackNavigationListener {

    lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::mView.isInitialized) {
            mView = inflater.inflate(R.layout.schedule_fragment, container, false)
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: ScheduleFragmentArgs by navArgs()
        val userName = safeArgs.userName
        text_view_salutation.text = "Hello, $userName!"

        fab_add_task.setOnClickListener {
            val action =
                ScheduleFragmentDirections.actionFragmentScheduleToFragmentAddTask()
            navigateForResult(-1, action, null, null)
        }

        recycler_view_tasks.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val data = mutableListOf(
                TaskDataModel(
                    "task_1",
                    Calendar.getInstance().also { it.set(2019, 10, 9, 23, 55) },
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    "subject_1"
                ),
                TaskDataModel(
                    "task_2",
                    Calendar.getInstance().also { it.set(2019, 10, 10, 23, 55) },
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    "subject_2"
                ),
                TaskDataModel(
                    "task_3",
                    Calendar.getInstance().also { it.set(2019, 10, 10, 20, 55) },
                    "",
                    null
                ),
                TaskDataModel(
                    "task_4",
                    Calendar.getInstance().also { it.set(2019, 10, 12, 23, 55) },
                    "Lorem ipsum dolor sit amet",
                    "subject_2"
                )
            )

            adapter = TaskAdapter(data)
        }
    }

    override fun onNavigationResult(result: BackNavigationResult) {
        if (result.resultCode == RESULT_SUCCESS) {
//            addCourse(result.data?.get("course_name") as String)
        }
    }

    private fun addCourse(courseName: String) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
            .also { it.setMargins(0, 0, 0, 8) }

        val textView = TextView(context).apply {
            layoutParams = params
            text = courseName
        }
    }
}

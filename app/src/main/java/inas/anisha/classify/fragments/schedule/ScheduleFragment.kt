package inas.anisha.classify.fragments.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import inas.anisha.classify.R
import inas.anisha.classify.ScheduleFragmentArgs
import inas.anisha.classify.ScheduleFragmentDirections
import inas.anisha.classify.base.BaseFragment
import inas.anisha.classify.navigation.BackNavigationListener
import inas.anisha.classify.navigation.BackNavigationResult
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : BaseFragment(), BackNavigationListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: ScheduleFragmentArgs by navArgs()
        val userName = safeArgs.userName
        text_view_salutation.text = userName

        fab_add_schedule.setOnClickListener {
            val action =
                ScheduleFragmentDirections.actionScheduleFragmentToAddScheduleFragment()
            navigateForResult(-1, action, null, null)
        }
    }

    override fun onNavigationResult(result: BackNavigationResult) {
        if (result.resultCode == RESULT_SUCCESS) {
            addCourse(result.data?.get("course_name") as String)
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

        course_container.addView(textView)
    }
}

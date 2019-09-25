package inas.anisha.classify.fragments.addSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inas.anisha.classify.R
import inas.anisha.classify.base.BaseFragment
import kotlinx.android.synthetic.main.add_schedule_fragment.*
import kotlinx.android.synthetic.main.main_fragment.button_submit

class AddScheduleFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_submit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("course_name", edit_text_course_name.text.toString())
            navigateBackWithResult(RESULT_SUCCESS, bundle)
        }
    }
}

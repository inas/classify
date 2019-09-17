package inas.anisha.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inas.anisha.classify.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_schedule.*
import kotlinx.android.synthetic.main.fragment_main.button_submit

class AddScheduleFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_schedule, container, false)
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

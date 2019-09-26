package inas.anisha.classify.fragments.addTask


import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import inas.anisha.classify.R
import inas.anisha.classify.Repository
import inas.anisha.classify.base.BaseFragment
import inas.anisha.classify.db.entity.TaskData
import kotlinx.android.synthetic.main.add_task_fragment.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AddTaskFragment : BaseFragment() {

    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    var hour: Int = 0
    var minute: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edit_text_task_due_date.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener { showDatePicker() }
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    showDatePicker()
                }
            }
        }

        edit_text_task_due_time.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener { showTimePicker() }
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    showTimePicker()
                }
            }
        }

        button_add_task.setOnClickListener {
            val date = Calendar.getInstance().also { it.set(year, month, day, hour, minute) }
            val task = TaskData(
                0,
                edit_text_task_name.text.toString(),
                edit_text_task_description.text.toString(),
                date
            )
            activity?.application?.let { it1 -> Repository(it1).addTask(task) }
            navigateBackWithResult(RESULT_SUCCESS)
        }
    }

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog.newInstance(
            { _, hourPick, minutePick, _ ->
                edit_text_task_due_time.setText("$hourPick:$minutePick")
                hour = hourPick
                minute = minutePick
            }, 0, 0, true
        ).also {
            it.accentColor = resources.getColor(R.color.colorPrimaryDark, null)
            it.setCancelColor(resources.getColor(R.color.white, null))
            it.setOkColor(resources.getColor(R.color.white, null))
        }

        fragmentManager?.let { fragmentManager ->
            timePickerDialog.show(
                fragmentManager,
                "TIME_PICKER"
            )
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog.newInstance(
            { _, yearPick, monthPick, dayPick ->
                edit_text_task_due_date.setText("$monthPick-$dayPick-$yearPick")
                year = yearPick
                month = monthPick
                day = dayPick
            }, 0, 0, 0
        ).also {
            it.accentColor = resources.getColor(R.color.colorPrimaryDark, null)
            it.setCancelColor(resources.getColor(R.color.white, null))
            it.setOkColor(resources.getColor(R.color.white, null))
            it.minDate = Calendar.getInstance()
        }

        fragmentManager?.let { fragmentManager ->
            datePickerDialog.show(
                fragmentManager,
                "DATE_PICKER"
            )
        }
    }

}

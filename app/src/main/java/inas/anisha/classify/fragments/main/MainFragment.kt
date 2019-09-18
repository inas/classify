package inas.anisha.classify.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import inas.anisha.classify.R
import inas.anisha.classify.base.BaseContract
import inas.anisha.classify.base.BaseFragment
import inas.anisha.classify.di.DaggerDIComponents
import inas.anisha.classify.fragments.main.MainPresenter.Companion.USERNAME_EMPTY
import inas.anisha.classify.fragments.main.MainPresenter.Companion.USERNAME_TOO_LONG
import inas.anisha.classify.fragments.main.MainPresenter.Companion.USERNAME_VALID
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : BaseFragment(), BaseContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDIComponents.builder()
            .build()
            .inject(this)
        presenter.setView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_submit.setOnClickListener {
            val userName = edit_text_name.text.toString()
            presenter.getUsernameError(userName).also {
                showError(it)
                if (it == USERNAME_VALID) {
                    MainFragmentDirections.actionMainFragmentToScheduleFragment("Hello, $userName!")
                        .also { action ->
                            findNavController().navigate(action)
                        }
                }
            }

        }
    }

    private fun showError(code: Int) {
        when (code) {
            USERNAME_EMPTY -> input_layout_name.error = "Username cannot be empty"
            USERNAME_TOO_LONG -> input_layout_name.error = "Username too long"
        }
    }

}
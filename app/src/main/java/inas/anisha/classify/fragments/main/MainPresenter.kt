package inas.anisha.classify.fragments.main

import inas.anisha.classify.base.BaseContract
import inas.anisha.classify.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<BaseContract.View>() {

    companion object {
        const val USERNAME_EMPTY = 0
        const val USERNAME_TOO_LONG = 2
        const val USERNAME_VALID = 3
    }

    fun getUsernameError(username: String): Int {
        return when {
            username.isEmpty() -> USERNAME_EMPTY
            username.length > 27 -> USERNAME_TOO_LONG
            else -> USERNAME_VALID
        }
    }
}
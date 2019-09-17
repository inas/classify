package inas.anisha.classify.Base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import inas.anisha.classify.R
import inas.anisha.classify.navigation.BackNavigationListener
import inas.anisha.classify.navigation.BackNavigationResult
import kotlin.properties.Delegates

abstract class BaseFragment : Fragment() {

    private val requestCode: Int
        get() = arguments?.getInt(ARGUMENT_NAVIGATION_REQUEST_CODE, REQUEST_CODE_NOT_SET)
            ?: REQUEST_CODE_NOT_SET

    protected fun navigateForResult(
        requestCode: Int, navDirections: NavDirections, navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) =
        navigateForResult(
            resId = navDirections.actionId,
            requestCode = requestCode,
            args = navDirections.arguments,
            navOptions = navOptions,
            navigatorExtras = navigatorExtras
        )

    protected fun navigateForResult(
        @IdRes resId: Int, requestCode: Int, args: Bundle? = null, navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        val argsWithRequestCode = (args ?: Bundle()).apply {
            putInt(ARGUMENT_NAVIGATION_REQUEST_CODE, requestCode)
        }
        findNavController().navigate(resId, argsWithRequestCode, navOptions, navigatorExtras)
    }

    protected fun navigateBackWithResult(resultCode: Int, data: Bundle? = null): Boolean =
        navigateBackWithResult(
            DESTINATION_NOT_SET,
            BackNavigationResult(requestCode, resultCode, data)
        )

    private fun navigateBackWithResult(@IdRes destination: Int, result: BackNavigationResult): Boolean {
        val childFragmentManager =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_nav_host)
                ?.childFragmentManager
        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
        backStackListener = FragmentManager.OnBackStackChangedListener {
            (childFragmentManager?.fragments?.get(0) as? BackNavigationListener)?.onNavigationResult(
                result
            )
            childFragmentManager?.removeOnBackStackChangedListener(backStackListener)
        }
        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
        val backStackPopped = if (destination == DESTINATION_NOT_SET) {
            findNavController().popBackStack()
        } else {
            findNavController().popBackStack(destination, true)
        }
        if (!backStackPopped) {
            childFragmentManager?.removeOnBackStackChangedListener(backStackListener)
        }
        return backStackPopped
    }

    companion object {
        const val ARGUMENT_NAVIGATION_REQUEST_CODE = "NAVIGATION_REQUEST_CODE"
        const val REQUEST_CODE_NOT_SET = -1
        const val DESTINATION_NOT_SET = -1
        const val RESULT_SUCCESS = 1
    }
}
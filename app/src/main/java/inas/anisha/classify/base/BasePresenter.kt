package inas.anisha.classify.base

import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

abstract class BasePresenter<T : BaseView> {

    private var view: WeakReference<T>? = null

    private var isViewAlive = AtomicBoolean()

    fun getView(): T? {
        return view?.get()
    }

    fun setView(view: T) {
        this.view = WeakReference(view)
    }

    fun start() {
        isViewAlive.set(true)
    }

    fun finalizeView() {
        isViewAlive.set(false)
    }

}
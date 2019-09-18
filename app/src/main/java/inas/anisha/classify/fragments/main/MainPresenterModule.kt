package inas.anisha.classify.fragments.main

import dagger.Module
import dagger.Provides
import inas.anisha.classify.base.BaseContract
import javax.inject.Singleton


@Module
class MainPresenterModule(private val view: BaseContract.View) {

    @Provides
    @Singleton
    fun providesContractView(): BaseContract.View {
        return view
    }
}
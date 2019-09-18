package inas.anisha.classify.di

import dagger.Component
import inas.anisha.classify.fragments.main.MainFragment
import inas.anisha.classify.fragments.main.MainPresenterModule
import javax.inject.Singleton

@Singleton
@Component(modules = [MainPresenterModule::class])
interface DIComponents {

    fun inject(mainFragment: MainFragment)

}
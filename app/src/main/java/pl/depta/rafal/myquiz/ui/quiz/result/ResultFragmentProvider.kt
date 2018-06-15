package pl.depta.rafal.myquiz.ui.quiz.result

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.depta.rafal.myquiz.annotation.FragmentScope

@Module
abstract class ResultFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ResultModule::class])
    abstract fun provideResultFragment(): ResultFragment
}
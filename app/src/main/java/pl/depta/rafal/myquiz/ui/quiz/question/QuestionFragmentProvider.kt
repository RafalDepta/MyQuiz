package pl.depta.rafal.myquiz.ui.quiz.question

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.depta.rafal.myquiz.annotation.FragmentScope

@Module
abstract class QuestionFragmentProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [QuestionModule::class])
    abstract fun provideQuestionFragment(): QuestionFragment
}
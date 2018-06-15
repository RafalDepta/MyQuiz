package pl.depta.rafal.myquiz.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.depta.rafal.myquiz.annotation.ActivityScope
import pl.depta.rafal.myquiz.ui.quiz.QuizActivity
import pl.depta.rafal.myquiz.ui.quiz.QuizModule
import pl.depta.rafal.myquiz.ui.quiz.question.QuestionFragmentProvider
import pl.depta.rafal.myquiz.ui.quiz.result.ResultFragmentProvider
import pl.depta.rafal.myquiz.ui.quizzes.QuizzesActivity
import pl.depta.rafal.myquiz.ui.quizzes.QuizzesModule

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [QuizzesModule::class])
    abstract fun bindQuizzesActivity(): QuizzesActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        QuizModule::class,
        QuestionFragmentProvider::class,
        ResultFragmentProvider::class
    ])
    abstract fun bindQuizActivity(): QuizActivity
}
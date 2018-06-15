package pl.depta.rafal.myquiz.ui.quiz

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import pl.depta.rafal.myquiz.annotation.ActivityScope
import pl.depta.rafal.myquiz.annotation.ApplicationContext
import pl.depta.rafal.myquiz.annotation.ViewModelKey
import pl.depta.rafal.myquiz.data.DataManager

@Module
class QuizModule {

    @Provides
    @ActivityScope
    @IntoMap
    @ViewModelKey(QuizViewModel::class)
    fun provideQuizViewModel(dataManager: DataManager, @ApplicationContext application: Application): ViewModel {
        return QuizViewModel(dataManager, application)
    }

    @Provides
    @ActivityScope
    fun provideFragmentManager(quizActivity: QuizActivity): FragmentManager {
        return quizActivity.supportFragmentManager
    }
}
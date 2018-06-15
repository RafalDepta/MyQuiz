package pl.depta.rafal.myquiz.ui.quiz.question

import android.app.Application
import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import pl.depta.rafal.myquiz.annotation.ApplicationContext
import pl.depta.rafal.myquiz.annotation.FragmentScope
import pl.depta.rafal.myquiz.annotation.ViewModelKey
import pl.depta.rafal.myquiz.data.DataManager

@Module
class QuestionModule {

    @Provides
    @FragmentScope
    @IntoMap
    @ViewModelKey(QuestionViewModel::class)
    fun provideQuestionViewModel(dataManager: DataManager, @ApplicationContext application: Application): ViewModel {
        return QuestionViewModel(dataManager, application)
    }

}
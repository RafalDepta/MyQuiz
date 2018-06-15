package pl.depta.rafal.myquiz.ui.quiz.result

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
class ResultModule {

    @Provides
    @FragmentScope
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    fun provideQuestionViewModel(dataManager: DataManager, @ApplicationContext application: Application): ViewModel {
        return ResultViewModel(dataManager, application)
    }
}
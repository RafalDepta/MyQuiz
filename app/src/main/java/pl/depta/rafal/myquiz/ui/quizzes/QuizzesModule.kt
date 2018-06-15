package pl.depta.rafal.myquiz.ui.quizzes

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import pl.depta.rafal.myquiz.annotation.ActivityScope
import pl.depta.rafal.myquiz.annotation.ApplicationContext
import pl.depta.rafal.myquiz.annotation.ViewModelKey
import pl.depta.rafal.myquiz.data.DataManager

@Module
class QuizzesModule {

    @Provides
    @ActivityScope
    @IntoMap
    @ViewModelKey(QuizzesViewModel::class)
    fun provideQuizzesViewModel(dataManager: DataManager, @ApplicationContext application: Application): ViewModel {
        return QuizzesViewModel(dataManager, application)
    }

    @Provides
    @ActivityScope
    fun provideSaggedGridLayoutManager(): StaggeredGridLayoutManager {
        return StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    @Provides
    @ActivityScope
    fun provideQuizzesAdapter(): QuizzesAdapter {
        return QuizzesAdapter()
    }
}
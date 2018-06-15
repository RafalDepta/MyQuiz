package pl.depta.rafal.myquiz.ui.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import pl.depta.rafal.myquiz.data.DataManager
import java.lang.ref.WeakReference


abstract class BaseViewModel(val dataManager: DataManager, application: Application) :
        AndroidViewModel(application) {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

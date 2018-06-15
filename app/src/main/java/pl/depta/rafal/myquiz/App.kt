package pl.depta.rafal.myquiz

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import pl.depta.rafal.myquiz.di.component.DaggerAppComponent

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
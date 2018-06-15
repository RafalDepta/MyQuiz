package pl.depta.rafal.myquiz.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.depta.rafal.myquiz.App
import pl.depta.rafal.myquiz.di.builder.ActivityBuilder
import pl.depta.rafal.myquiz.di.module.AppModule
import javax.inject.Singleton


@Singleton
@Component(modules =
[
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
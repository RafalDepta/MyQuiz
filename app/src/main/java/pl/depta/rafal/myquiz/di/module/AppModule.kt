package pl.depta.rafal.myquiz.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.depta.rafal.myquiz.App
import pl.depta.rafal.myquiz.ViewModelFactory
import pl.depta.rafal.myquiz.annotation.ApplicationContext
import pl.depta.rafal.myquiz.annotation.DatabaseInfo
import pl.depta.rafal.myquiz.data.AppDataManager
import pl.depta.rafal.myquiz.data.DataManager
import pl.depta.rafal.myquiz.data.local.AppDatabase
import pl.depta.rafal.myquiz.data.local.AppDbHelper
import pl.depta.rafal.myquiz.data.local.DbHelper
import pl.depta.rafal.myquiz.data.remote.ApiInterface
import pl.depta.rafal.myquiz.utils.AppUtils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindApplication(app: App): Application

    @Binds
    @ApplicationContext
    @Singleton
    internal abstract fun provideContext(application: Application): Application

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return loggingInterceptor
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(AppUtils.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideApiInterface(retrofit: Retrofit): ApiInterface {
            return retrofit.create(ApiInterface::class.java)
        }

        @JvmStatic
        @Provides
        @Singleton
        @DatabaseInfo
        fun provideDbName(): String {
            return AppUtils.DB_NAME
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideAppDatabase(@DatabaseInfo dbName: String, @ApplicationContext application: Application): AppDatabase {
            return Room.databaseBuilder(application, AppDatabase::class.java, dbName).build()
        }
    }

    @Binds
    @Singleton
    internal abstract fun provideAppDbHelper(appDbHelper: AppDbHelper): DbHelper

    @Binds
    @Singleton
    internal abstract fun provideDataManager(appDataManager: AppDataManager): DataManager

}

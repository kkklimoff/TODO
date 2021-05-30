package com.kk191rdb020.todo.di

import android.app.Application
import androidx.room.Room
import com.kk191rdb020.todo.data.TaskDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        callback: TaskDb.Callback
    ) = Room.databaseBuilder(app, TaskDb::class.java, "task_db")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()
    @Provides
    fun provideTaskDao(db: TaskDb) = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
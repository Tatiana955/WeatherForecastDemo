package by.startandroid.weatherforecastdemo.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import by.startandroid.weatherforecastdemo.data.DataSource
import by.startandroid.weatherforecastdemo.data.local.Local
import by.startandroid.weatherforecastdemo.data.local.WeatherDatabase
import by.startandroid.weatherforecastdemo.data.remote.Remote
import by.startandroid.weatherforecastdemo.repository.IRepository
import by.startandroid.weatherforecastdemo.repository.Repository
import by.startandroid.weatherforecastdemo.viewmodel.ViewModel
import by.startandroid.weatherforecastdemo.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDataSource

    @Singleton
    @RemoteDataSource
    @Provides
    fun provideRemote(): DataSource {
        return Remote()
    }

    @Singleton
    @LocalDataSource
    @Provides
    fun provideLocal(database: WeatherDatabase): DataSource {
        return Local(database.weatherDao())
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase {
        var DATABASE: WeatherDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'city_name' ADD COLUMN 'isSelectedWidget' INTEGER " +
                        "DEFAULT 0 NOT NULL")
            }
        }

        return DATABASE ?: synchronized(this) {
            val database: WeatherDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java, "weather_db"
            )
                    .addMigrations(MIGRATION_1_2)
                    .build()
            DATABASE = database
            database
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object IRepositoryModule {

    @Singleton
    @Provides
    fun provideIRepository(
            @AppModule.RemoteDataSource remoteDataSource: DataSource,
            @AppModule.LocalDataSource localDataSource: DataSource
    ): IRepository {
        return Repository(remoteDataSource, localDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(
            iRepository: IRepository
    ): ViewModelFactory {
        return ViewModelFactory(iRepository)
    }

    @Singleton
    @Provides
    fun provideViewModel(
            iRepository: IRepository
    ): ViewModel {
        return ViewModel(iRepository)
    }
}
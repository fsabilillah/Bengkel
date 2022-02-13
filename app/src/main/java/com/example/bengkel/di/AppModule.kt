package com.example.bengkel.di

import androidx.room.Room
import com.ashokvarma.gander.GanderInterceptor
import com.example.bengkel.data.IRepository
import com.example.bengkel.data.Repository
import com.example.bengkel.data.source.local.LocalDataSource
import com.example.bengkel.data.source.local.room.RoomDatabase
import com.example.bengkel.data.source.remote.RemoteDataSource
import com.example.bengkel.data.source.remote.network.ApiServices
import com.example.bengkel.ui.api.ApiCheckViewModel
import com.example.bengkel.ui.login.LoginViewModel
import com.example.bengkel.ui.main.admin.AdminViewModel
import com.example.bengkel.ui.main.admin.dialog.create.AdminCreateDialogViewModel
import com.example.bengkel.ui.main.admin.dialog.update.AdminUpdateDialogViewModel
import com.example.bengkel.ui.main.cadang.SukuCadangViewModel
import com.example.bengkel.ui.main.cadang.dialog.create.SukuCadangCreateViewModel
import com.example.bengkel.ui.main.cadang.dialog.update.SukuCadangUpdateViewModel
import com.example.bengkel.ui.main.dashboard.DashboardViewModel
import com.example.bengkel.ui.main.history.HistoryViewModel
import com.example.bengkel.ui.main.pelanggan.PelangganViewModel
import com.example.bengkel.ui.main.service.ServiceViewModel
import com.example.bengkel.ui.main.service.usage.UsageViewModel
import com.example.bengkel.utils.UrlPreference
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(GanderInterceptor(androidContext()).showNotification(true))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val urlPreference = UrlPreference(androidContext())
        val host = urlPreference.getUrl()
        val retrofit = Retrofit.Builder()
            .baseUrl("$host/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiServices::class.java)
    }
}

val databaseModule = module {
    factory { get<RoomDatabase>().roomDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            RoomDatabase::class.java,
            "bengkel.db",
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory<IRepository> { Repository(get(), get()) }
}

val viewModelModule = module {
    factory {
        Dispatchers.IO
    }

    viewModel { ApiCheckViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { AdminViewModel(get()) }
    viewModel { AdminUpdateDialogViewModel(get()) }
    viewModel { AdminCreateDialogViewModel(get()) }
    viewModel { SukuCadangViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { ServiceViewModel(get()) }
    viewModel { SukuCadangCreateViewModel(get()) }
    viewModel { SukuCadangUpdateViewModel(get()) }
    viewModel { UsageViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { PelangganViewModel(get()) }
}
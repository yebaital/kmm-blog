package code.yousef.blog.di

import code.yousef.blog.pages.viewmodels.AuthViewModel
import code.yousef.blog.pages.viewmodels.CreatePostViewModel
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module
    get() = module {
        single {
            HttpClient(Js) {
                install(ContentNegotiation) {
                    json()
                }
            }
        }
    }

val authViewModelModule: Module
    get() = module {
        single { AuthViewModel(get()) }
    }

val createPostViewModelModule: Module
    get() = module {
        single { CreatePostViewModel(get()) }
    }

object GetAuthViewModel : KoinComponent {
    fun getAuthViewModel() = get<AuthViewModel>()
}

object GetCreatePostViewModel : KoinComponent {
    fun getCreatePostViewModel() = get<CreatePostViewModel>()
}
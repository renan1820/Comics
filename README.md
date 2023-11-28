# Comics
Este Projeto é de um teste, onde foi feito usando a base do projeto e feito vários updates.

Neste Projeto voce irá encontrar:

- MVVM
  A arquitetura usada neste projeto segue os padroes Model-View-ViewModel
  
- Jetpack Compose
  Codigo de exemplo da implementação 
```kotlin
@Composable
fun RetryContent(error: String, onRetry: () -> Unit, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(text = error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRetry,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

```
- Compose Navigation
  Função onde foi implementado a Navegação
```kotlin
@Composable
fun NavGraphSetup(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = "comics_screen"){
        composable(route = "comics_screen"){
            val viewModel: ComicsScreenViewModel = hiltViewModel()
            ComicsScreen(state = viewModel.state, onEvent = viewModel::onEvent, viewModel = viewModel)
        }
    }
}

```
- Hilt
  Objeto onde mostramos parte da implementação da Injeção de Independencia
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideComicsApi(): ComicsApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ComicsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideComicsRepository(comicsApi: ComicsApi): ComicsRepository{
        return ComicsRepositoryImpl(comicsApi)
    }
}
```
- Coroutines
  Trecho do código dentro da viewModel onde mostrando a usabilidade do coroutines separando da Thread principal
```kotlin
viewModelScope.launch {
            state = state.copy(isLoading = !bySwipe, swipeRefresh = bySwipe)
            when(val result = comicsRepository.getComics()){
                is Resource.Success ->{

                    state = state.copy(
                        isLoading = false,
                        swipeRefresh = false,
                        comicsList = result.data ?: emptyList(),
                        error = null)
                }
                is Resource.Error -> {

                    state = state.copy(
                        isLoading = false,
                        swipeRefresh = false,
                        error = result.message,
                        comicsList = emptyList()
                    )
                }
            }
        }
```
- Material Desing 3
  Implementação do para usar ambos os temas Claro, Escuro e dinamico
```kotlin
    val replyColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> replyDarkColorScheme
        else -> replyLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = replyColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = replyColorScheme,
        shapes = shapes,
        content = content
    )
```
- Retrofit
  Nesta Função usamos nossa injeção de dependencia junto com o Retrofit e tambem um interceptador para analisar retorno da API
```kotlin
@Provides
    @Singleton
    fun provideComicsApi(): ComicsApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ComicsApi::class.java)
    }
```
- PullRefresh
  Adicional um Swiper para realizar o refresh da Lista
```kotlin
    val pullRefreshState = rememberPullRefreshState(state.swipeRefresh, viewModel::getComicsSwiper)

        PullRefreshIndicator(state.swipeRefresh, pullRefreshState, Modifier.align(Alignment.TopCenter))
```

![image](https://github.com/renan1820/Comics/assets/33467920/cba842f5-9086-4ff4-b8a6-9c9e617c39b5)

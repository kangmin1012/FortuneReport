# DI 규칙 (Koin)

의존성 주입은 **Koin** 사용. 수동 생성자 주입 금지.

## 모듈 구성

모듈 정의는 `shared/commonMain`에 작성. 플랫폼별 모듈만 `androidMain`/`iosMain`에 추가.

```kotlin
val dataModule = module {
    single { SupabaseClient }
    single<FortuneRepository> { FortuneRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}

val domainModule = module {
    factory { GetTodayReportUseCase(get()) }
    factory { UpdateNotifyTimeUseCase(get()) }
}

val presentationModule = module {
    viewModel { ReportViewModel(ReportDependencies(get(), get())) }
    viewModel { SettingsViewModel(SettingsDependencies(get(), get())) }
}
```

## 규칙

- ViewModel은 `koinViewModel()` 또는 `koinNavViewModel()`로 주입. Composable 내부에서 직접 생성 금지.
- UseCase는 `factory` (매번 새 인스턴스), Repository/Client는 `single` (싱글턴).
- `ActionDependencies`에 필요한 UseCase/Repository는 Koin 모듈에서 제공.
- Domain 레이어 클래스는 Koin에 의존하지 않는다 (순수 Kotlin).

# FortunePaper 개발 태스크

> 상태: ⬜ 미시작 / 🔄 진행중 / ✅ 완료 / ⏸ 보류

---

## 준비사항 (외부 서비스)

| 상태 | 항목 | 비고 |
|------|------|------|
| ⬜ | Supabase 프로젝트 생성 | Project URL · anon key · service_role key 확보 |
| ⬜ | Firebase 프로젝트 생성 | google-services.json · GoogleService-Info.plist 다운로드, FCM 활성화 |
| ⬜ | 카카오 개발자 앱 등록 | Native 앱 키 확보, 패키지명 `com.fortune.paper` 등록, 카카오 로그인 활성화 |
| ⬜ | Anthropic API 키 발급 | Claude API 키 확보 |

---

## Task 1. 프로젝트 세팅

- [ ] `libs.versions.toml` 의존성 추가
  - [ ] Supabase KMP SDK
  - [ ] Koin (KMP)
  - [ ] Coil (KMP)
  - [ ] Firebase KMP (FCM)
  - [ ] Kotlin Serialization
- [ ] Koin 모듈 초기 구조 생성 (`di/` 디렉토리)
- [ ] 레이어 디렉토리 구조 생성 (`presentation/`, `domain/`, `data/`, `auth/`)
- [ ] Supabase 클라이언트 초기화 (`SupabaseClient.kt`)

---

## Task 2. DB 스키마

- [ ] Supabase에서 `users` 테이블 생성
- [ ] Supabase에서 `fortunes` 테이블 생성
- [ ] `user_id + date` UNIQUE 제약 추가
- [ ] RLS(Row Level Security) 정책 설정

---

## Task 3. 카카오 로그인

- [ ] `KakaoAuth.kt` expect 선언 작성
- [ ] `KakaoAuth.android.kt` actual 구현 (카카오 Android SDK)
- [ ] `KakaoAuth.ios.kt` actual 구현 (카카오 iOS SDK)
- [ ] Supabase Auth 커스텀 토큰 교환 로직 구현
- [ ] `UserRepository` 인터페이스 및 구현체 작성
- [ ] 로그인 화면 UI (TOAD + MVI)
  - [ ] `LoginState`, `LoginEvent`, `LoginDependencies`
  - [ ] `KakaoLoginAction`
  - [ ] `LoginScreen` Composable
- [ ] Koin 모듈 등록

---

## Task 4. 운세 리포트 생성

- [ ] Supabase Edge Function `fortune` 작성 (Deno/TypeScript)
  - [ ] Claude API 호출 로직
  - [ ] `{ grade, summary, advice }` JSON 응답
  - [ ] DB 저장 및 캐시 반환 로직
- [ ] `FortuneRepository` 인터페이스 및 구현체 작성
- [ ] `GetTodayReportUseCase` 작성
- [ ] Edge Function 배포 및 테스트

---

## Task 5. 리포트 화면 UI

- [ ] `FortuneReport` 도메인 모델 정의
- [ ] `ReportState`, `ReportEvent`, `ReportDependencies` 작성
- [ ] Actions 작성
  - [ ] `LoadReport`
  - [ ] `RefreshReport`
- [ ] `ReportViewModel` 작성
- [ ] `ReportScreen` Composable 작성
  - [ ] 등급 표시 컴포넌트
  - [ ] 한 줄 요약 컴포넌트
  - [ ] 오늘의 조언 컴포넌트
  - [ ] 로딩/에러 상태 처리
- [ ] `App()` 에 화면 연결
- [ ] Koin 모듈 등록

---

## Task 6. 푸시 알림

- [ ] Firebase FCM 초기화 (Android / iOS)
- [ ] FCM 토큰 갱신 로직 구현 (`users.fcm_token` 업데이트)
- [ ] Supabase Edge Function `notify` 작성
- [ ] Supabase `pg_cron` 스케줄러 설정 (매분 실행)
- [ ] 알림 설정 화면 UI
  - [ ] `SettingsState`, `SettingsEvent`, `SettingsDependencies`
  - [ ] `UpdateNotifyTime` Action
  - [ ] `SettingsScreen` Composable (시간 선택 UI)
- [ ] `UpdateNotifyTimeUseCase` 작성
- [ ] Koin 모듈 등록

---

## Task 7. 배포

- [ ] Android
  - [ ] 릴리즈 키스토어 생성
  - [ ] `build.gradle.kts` 릴리즈 서명 설정
  - [ ] Google Play Console 앱 등록
  - [ ] AAB 빌드 및 업로드
- [ ] iOS
  - [ ] Apple Developer 앱 ID 등록
  - [ ] App Store Connect 앱 등록
  - [ ] Xcode 아카이브 및 업로드

---

## 미결정 사항

- [ ] 운세 등급 체계 확정 (S·A·B·C·D vs 1~100점)
- [ ] 수익 모델 확정 (출시 후 반응 보고 결정)
- [ ] 커스텀 디자인 파일 적용 시점

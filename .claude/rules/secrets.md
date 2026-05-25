# 시크릿 키 관리 규칙

## local.properties 저장 전 체크리스트

민감한 키를 `local.properties`에 저장하기 **전에** 반드시 아래 질문을 확인한다.

### 저장해도 되는 키
- 앱 코드(Kotlin/Swift)에서 직접 사용하는 키
- 예: `supabase.url`, `supabase.publishableKey`, `kakao.nativeAppKey`

### 저장하면 안 되는 키
- 서버(Edge Function)에서만 사용하는 키
- 외부 대시보드(Supabase, Firebase 등)에만 입력하는 키
- 예: `supabase.secretKey`(service_role), `kakao.clientSecret`

## 판단 기준

> **"이 키가 앱 코드에서 직접 import되거나 사용되는가?"**
>
> - YES → `local.properties`에 저장
> - NO → 해당 서비스 대시보드에만 입력, 저장 안 함

## 키별 저장 위치 정리

| 키 | 저장 위치 |
|----|----------|
| Supabase URL | `local.properties` ✅ |
| Supabase Publishable Key | `local.properties` ✅ |
| Supabase Secret Key (service_role) | Supabase Edge Function 환경변수만 ❌ |
| Kakao Native App Key | `local.properties` ✅ |
| Kakao REST API Key | Supabase 대시보드만 ❌ |
| Kakao Client Secret | Supabase 대시보드만 ❌ |
| Anthropic API Key | Supabase Edge Function 환경변수만 ❌ |
| FCM Server Key | Supabase Edge Function 환경변수만 ❌ |

## 규칙

- 저장 여부가 불확실하면 사용자에게 먼저 확인한다
- `local.properties`는 `.gitignore`에 등록되어 있어 GitHub에 올라가지 않지만,
  불필요한 키는 최소화하는 것이 원칙이다

# FortunePaper 🗞️

> 오늘의 운세를 한 장의 리포트로 — 매일 아침 나만의 운세 리포트를 받아보세요.

---

## 앱 소개

**FortunePaper**는 사주(생년월일) 기반의 운세를 신문 1면처럼 한 장의 리포트 형식으로 보여주는 모바일 앱입니다.

복잡한 운세 해석 대신, 오늘 하루를 위한 핵심만 담았습니다.

- **운세 등급** — 오늘의 운세를 한눈에 파악
- **종합운 한 줄 요약** — 오늘을 압축한 헤드라인
- **오늘의 조언** — AI가 사주를 기반으로 건네는 한 마디

매일 설정한 시간에 "오늘의 리포트가 도착했어요" 알림과 함께 하루를 시작할 수 있습니다.

### 기술 스택

| 영역 | 기술 |
|------|------|
| UI | Compose Multiplatform + Material3 |
| 언어 | Kotlin Multiplatform |
| 인증 | 카카오 로그인 + Supabase Auth |
| 백엔드 | Supabase Edge Functions |
| AI | Claude API (Anthropic) |
| 푸시 알림 | Firebase Cloud Messaging |

---

## AI 바이브 코딩으로 만들어진 앱

이 프로젝트는 **Claude Code**를 활용한 AI 바이브 코딩으로 개발되고 있습니다.

기획부터 아키텍처 설계, 코드 작성까지 — AI와 대화하며 만들어가는 개발 방식으로, 아이디어를 빠르게 실제 앱으로 구현하는 과정을 담고 있습니다.

- 기획 및 문서화 → Claude Code
- 아키텍처 설계 (Clean Architecture + MVI + TOAD) → Claude Code
- 코드 구현 → Claude Code
- 의사결정 → 개발자

> AI가 코드를 쓰고, 사람이 방향을 잡는다.

---

## 빌드 방법

**Android**
```bash
./gradlew :androidApp:assembleDebug
```

**iOS**

`iosApp/` 디렉토리를 Xcode에서 열고 실행.

**테스트**
```bash
./gradlew :shared:testAndroidHostTest
./gradlew :shared:iosSimulatorArm64Test
```

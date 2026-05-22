# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

---

## 프로젝트 개요

**포춘페이퍼 (FortunePaper)** — 사주(생년월일 + 성별) 기반 운세를 한 장의 리포트 형식으로 매일 발행하는 KMP 모바일 앱.
패키지명: `com.fortune.paper`

---

## 빌드 및 실행 명령어

**Java 설정 (Android Studio JDK 사용)**
```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
```

**Android**
```bash
./gradlew :androidApp:assembleDebug        # 디버그 APK 빌드
./gradlew :androidApp:assembleRelease      # 릴리즈 APK 빌드
./gradlew :androidApp:installDebug         # 연결된 기기에 설치
```

**테스트**
```bash
./gradlew :shared:testAndroidHostTest      # Android 공통 로직 테스트
./gradlew :shared:iosSimulatorArm64Test    # iOS 시뮬레이터 테스트
```

**iOS**: `iosApp/` 디렉토리를 Xcode에서 열고 실행.

> `local.properties`의 `sdk.dir=/Users/kangmingu/Library/Android/sdk` 설정 필요.

---

## Rules (자동 로드)

`.claude/rules/` 파일은 세션 시작 시 자동으로 로드됩니다.

| 파일 | 내용 |
|------|------|
| `prd.md` | 제품 요구사항 — 등급 체계, 기능 명세, 페르소나, 성공 지표 |
| `tasks.md` | 개발 태스크 체크리스트 — 단계별 구현 항목 |
| `architecture.md` | Clean Architecture · MVI · TOAD 패턴, 레이어 구조, DB 스키마, 백엔드 흐름 |
| `di.md` | Koin 모듈 구성 및 의존성 주입 규칙 |
| `ui.md` | Composable 규칙, Material3 디자인 시스템, Coil 이미지 로딩 |

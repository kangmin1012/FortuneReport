---
name: git-commit-helper
description: Generate conventional commit messages automatically. Use when user runs git commit, stages changes, or asks for commit message help. Analyzes git diff to create clear, descriptive conventional commit messages. Triggers on git commit, staged changes, commit message requests.
allowed-tools: Bash, Read
---

# Git Commit Skill

변경사항을 성격별로 분리하여 conventional commit 형식으로 커밋하는 스킬.

## 실행 조건

- `/git-commit` 명령 실행 시
- 스테이징된 변경사항이 존재할 때
- 커밋 메시지 작성 도움 요청 시

## 분석 프로세스

### Step 1: 변경사항 파악
```bash
git status
git diff --staged --name-only
git diff --staged
```

### Step 2: 변경 성격 분류
- 새 파일 추가 → `feat`
- 기존 파일 수정 → `fix`, `refactor`, `feat` 중 판단
- 파일 삭제 → `chore` 또는 `refactor`
- 테스트 파일 → `test`
- 문서 파일 → `docs`

### Step 3: 그룹 분리
성격이 다른 변경사항은 **그룹별로 분리하여 순서대로 커밋**.
각 그룹을 파일 단위로 명시적 스테이징 후 커밋.

## 커밋 형식

```
<type>(<scope>): <subject>

<body>

Co-Authored-By: Claude Sonnet 4.6 <noreply@anthropic.com>
```

- `<subject>`: **한국어**로 작성, 명령형(동사 원형), 50자 이내, 끝에 마침표 없음
- `(<scope>)`: 범위가 명확할 때만 추가
- `<body>`: **한국어**로 무엇을(WHAT), 왜(WHY) 변경했는지 기술. HOW는 코드가 말함

## Types

| Type | 설명 |
|------|------|
| `feat` | 새 기능 |
| `fix` | 버그 수정 |
| `docs` | 문서 변경 (README, CLAUDE.md, 룰 파일 등) |
| `style` | 동작 변경 없는 포맷·스타일 수정 |
| `refactor` | 동작 변경 없는 코드 구조 개선 |
| `perf` | 성능 개선 |
| `test` | 테스트 추가·수정 |
| `build` | 빌드 시스템 변경 |
| `ci` | CI/CD 변경 |
| `chore` | 빌드 설정, 의존성, DI 모듈 등 유지보수 |

## 예시

### 기능 추가
```
feat(report): ReportScreen에 스켈레톤 로딩 추가

운세 데이터 로딩 중 스켈레톤 UI를 표시하여
첫 진입 시 체감 성능을 개선함
```

### 버그 수정
```
fix(auth): 로그아웃 시 토큰 미삭제 버그 수정

Kakao SDK signOut 호출 후 Supabase 세션 토큰이
메모리에 잔류하여 인증 상태가 초기화되지 않던 문제 해결

Closes #12
```

### Breaking Change
```
feat(api): 운세 등급을 알파벳에서 날씨 코드로 변경

S/A/B/C/D 등급을 SUNNY/CLEAR/CLOUDY/RAINY/STORM으로
교체하여 직관적인 날씨 표현으로 전환

BREAKING CHANGE: fortune.grade 필드가 날씨 코드만 허용함.
등급 비교 로직 전체 업데이트 필요
```

### 여러 성격의 변경이 섞인 경우

변경 파일:
- `CLAUDE.md` — 룰 파일 목록 업데이트
- `ReportScreen.kt` — 스켈레톤 로딩 추가
- `libs.versions.toml` — Coil 버전 업

→ 3개 커밋으로 분리:
```
docs: CLAUDE.md 룰 파일 목록 업데이트

feat(report): ReportScreen에 스켈레톤 로딩 추가

chore: Coil 버전 업데이트
```

## Subject 작성 가이드

✅ 좋은 예:
- `ReportScreen에 스켈레톤 로딩 추가`
- `ReportViewModel 메모리 누수 수정`
- `카카오 로그인 에러 처리 개선`

❌ 나쁜 예:
- `스켈레톤 로딩을 추가했다` — 과거형 금지
- `버그 수정` — 너무 모호함
- `화면 업데이트.` — 끝에 마침표 금지

## 규칙

- `git add .` / `git add -A` 사용 금지 — 파일 단위로 명시적 스테이징
- `.env`, `local.properties`, 키스토어, `google-services.json` 등 민감 파일 스테이징 전 제외 확인
- Push는 모든 커밋 완료 후 사용자 확인을 받고 진행
- `--no-verify`, `--amend` 등 훅 우회·커밋 수정은 사용자 명시 요청 시에만 사용

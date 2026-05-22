# Git Commit Skill

변경사항을 성격별로 분리하여 conventional commit 형식으로 커밋하는 스킬.

## Usage

```
/git-commit
```

## Behavior

1. `git status` + `git diff` 로 전체 변경사항 파악
2. 성격이 다른 변경사항은 **그룹별로 분리하여 순서대로 커밋**
3. 각 그룹을 파일 단위로 명시적 스테이징 후 커밋

## Commit Format

```
<type>(<scope>): <description>

[optional body]

Co-Authored-By: Claude Sonnet 4.6 <noreply@anthropic.com>
```

- `<description>` 은 영문 소문자 명령형, 50자 이내
- 범위가 명확할 때만 `(<scope>)` 추가

## Types

| Type | 설명 |
|------|------|
| `feat` | 새 기능 |
| `fix` | 버그 수정 |
| `docs` | 문서 변경 (README, CLAUDE.md, 룰 파일 등) |
| `style` | 동작 변경 없는 포맷·스타일 수정 |
| `refactor` | 동작 변경 없는 코드 구조 개선 |
| `test` | 테스트 추가·수정 |
| `chore` | 빌드 설정, 의존성, DI 모듈 등 유지보수 |

## Example

변경 파일이 아래와 같다면:
- `CLAUDE.md` — 룰 파일 목록 업데이트
- `ReportScreen.kt` — 스켈레톤 로딩 추가
- `libs.versions.toml` — Coil 버전 업

→ 3개 커밋으로 분리:

```
docs: update rules file table in CLAUDE.md

feat(report): add skeleton loading to ReportScreen

chore: bump Coil version
```

## Rules

- `git add .` / `git add -A` 사용 금지 — 파일 단위로 명시적 스테이징
- `.env`, `local.properties`, 키스토어 등 민감 파일은 스테이징 전 제외 확인
- Push는 모든 커밋 완료 후 사용자 확인을 받고 진행

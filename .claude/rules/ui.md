# UI 규칙

## 디자인 시스템

Material3 기반으로 시작. 색상·타이포·모양은 `MaterialTheme` 토큰 사용 — 값 하드코딩 금지.
추후 커스텀 디자인 파일 적용 시 `MaterialTheme { }` 내부 토큰만 교체.

## Composable 규칙

- Composable은 `ViewState`를 받아 렌더링만 한다. 비즈니스 로직 포함 금지.
- ViewModel은 `koinViewModel()`로 주입. Composable 내부에서 직접 생성 금지.
- 모든 UI는 `shared/commonMain`에 작성. `androidApp`/`iosApp`은 진입점만.
- 화면 단위 Composable과 재사용 컴포넌트는 분리한다.

## 이미지 로딩 (Coil)

이미지 로딩은 **Coil** 사용. 직접 이미지 처리 로직 작성 금지.
KMP 환경에서 `coil-compose` 멀티플랫폼 버전 사용.

```kotlin
AsyncImage(
    model = imageUrl,
    contentDescription = null,
    modifier = Modifier.fillMaxWidth()
)
```

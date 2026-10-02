# WEARELIVE 사전예약 랜딩

Spring Boot 4.1 · Java 25 · Thymeleaf. Figma: `WEARELIVE-사전예약` (Section 2 — 밴드 탭 `102:819`, 사장님 탭 `102:952`).

## 실행

```bash
./mvnw "-Dspring-boot.run.profiles=dev" spring-boot:run   # http://localhost:8080 , /owner
./mvnw test
```

> PowerShell에서는 `-D...` 인자를 따옴표로 감싸야 합니다(감싸지 않으면 `.` 기준으로 잘려 `Unknown lifecycle phase` 오류).

`dev` 프로필: 템플릿·정적 리소스 핫리로드.

## 일반 유저 / 사장님 분리

| | 밴드 멤버 (일반 유저) | 대관 사장님 |
|---|---|---|
| 페이지 | `GET /` | `GET /owner` |
| 패키지 | `band` | `owner` |
| 템플릿 | `templates/band/` | `templates/owner/` |
| 이미지 | `static/assets/band/` | `static/assets/owner/` |
| 사전예약 버튼 → Google Form | `wearelive.band.pre-registration-url` | `wearelive.owner.pre-registration-url` |

- `common`은 화면 조각·데이터 형태만 공유하고 대상별 문구/로직을 갖지 않습니다.
- `AudienceBoundaryArchTest`(ArchUnit)가 band ↔ owner 간 의존과 common → band/owner 의존을 막고,
  `LandingPagesTest`가 각 페이지에 상대 대상의 문구·이미지가 섞이지 않는지 확인합니다.
- 사전예약 버튼(헤더·히어로·하단 CTA)은 각 페이지 컨트롤러가 넘겨주는 자기 대상의 Google Form으로 이동하는 링크입니다.
  폼 주소는 `application.properties`에서 바꿀 수 있고, `LandingPagesTest`가 상대 대상의 폼이 섞이지 않는지 확인합니다.
- 대상 전환(상단 스위치)은 상대 페이지로 이동합니다.

## TODO

- FAQ 답변은 초안 — `BandLandingContent` / `OwnerLandingContent`에서 교체
- 푸터 SNS·문의·약관 링크(`templates/fragments/layout.html`)

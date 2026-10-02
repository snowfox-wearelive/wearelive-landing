# WEARELIVE 사전예약 랜딩

Spring Boot 4.1 · Java 25 · Thymeleaf · JPA(H2). Figma: `WEARELIVE-사전예약` (Section 2 — 밴드 탭 `102:819`, 사장님 탭 `102:952`, 모달 03/04, 토스트 05).

## 실행

```bash
./mvnw "-Dspring-boot.run.profiles=dev" spring-boot:run   # http://localhost:8080 , /owner
./mvnw test
```

> PowerShell에서는 `-D...` 인자를 따옴표로 감싸야 합니다(감싸지 않으면 `.` 기준으로 잘려 `Unknown lifecycle phase` 오류).

`dev` 프로필: 템플릿 핫리로드, H2 콘솔(`/h2-console`, JDBC URL `jdbc:h2:file:./data/wearelive`).

## 일반 유저 / 사장님 분리

| | 밴드 멤버 (일반 유저) | 대관 사장님 |
|---|---|---|
| 페이지 | `GET /` | `GET /owner` |
| 패키지 | `band` | `owner` |
| 템플릿 | `templates/band/` | `templates/owner/` |
| 이미지 | `static/assets/band/` | `static/assets/owner/` |
| API | `POST /api/band/pre-registrations` | `POST /api/owner/pre-registrations` |
| 테이블 | `band_pre_registration` (+ position) | `owner_pre_registration` |

- `common`은 화면 조각·데이터 형태만 공유하고 대상별 문구/로직을 갖지 않습니다.
- `AudienceBoundaryArchTest`(ArchUnit)가 band ↔ owner 간 의존과 common → band/owner 의존을 막고,
  `LandingPagesTest`가 각 페이지에 상대 대상의 문구·API·이미지가 섞이지 않는지 확인합니다.
- 대상 전환(상단 스위치, 모달의 "어떤 분이신가요?")은 상대 페이지로 이동합니다(`?reserve=1`이면 도착 즉시 모달 오픈).

## TODO

- FAQ 답변은 초안 — `BandLandingContent` / `OwnerLandingContent`에서 교체
- 푸터 SNS·문의·약관 링크(`templates/fragments/layout.html`)
- 운영 DB 설정(`spring.datasource.*`)

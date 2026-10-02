# WEARELIVE 사전예약 랜딩

서버 없이 동작하는 정적 웹페이지(HTML · CSS · JS). Figma: `WEARELIVE-사전예약` (Section 2 — 밴드 탭 `102:819`, 사장님 탭 `102:952`).

## 보기

- `index.html`을 브라우저로 열면 바로 볼 수 있습니다(모든 경로가 상대 경로).
- GitHub Pages · Netlify 등 정적 호스팅에 저장소 루트를 그대로 올리면 됩니다.

## 일반 유저 / 사장님 분리

| | 밴드 멤버 (일반 유저) | 대관 사장님 |
|---|---|---|
| 페이지 | `index.html` | `owner.html` |
| 이미지 | `assets/band/` | `assets/owner/` |
| 사전예약 버튼 → Google Form | `1k9AXAFi…` | `1S69UsyI…` |

- 두 페이지가 함께 쓰는 것은 `css/landing.css`, `js/landing.js`(TOP 버튼), `assets/shared/`(로고 등)뿐입니다.
- 사전예약 버튼(헤더·히어로·하단 CTA)은 각 페이지에 자기 대상의 Google Form 주소로 직접 들어 있습니다.
  폼 주소를 바꿀 때는 해당 HTML 파일의 세 곳을 함께 바꿔주세요.
- 대상 전환(상단 스위치)은 상대 페이지로 이동합니다.
- `node scripts/check-pages.mjs`(선택, 의존성 없음): 로컬 파일 참조가 모두 존재하는지, 각 페이지의 사전예약 링크 3개가
  자기 폼으로만 가는지, 상대 대상의 이미지를 쓰지 않는지 확인합니다.

## TODO

- FAQ 답변은 초안 — `index.html` / `owner.html`에서 교체
- 푸터 SNS·문의·약관 링크

package band.wearelive.landing.band;

import static band.wearelive.landing.common.content.SolutionRow.VisualSide.LEFT;
import static band.wearelive.landing.common.content.SolutionRow.VisualSide.RIGHT;

import band.wearelive.landing.common.content.FaqItem;
import band.wearelive.landing.common.content.FeatureItem;
import band.wearelive.landing.common.content.HeroCopy;
import band.wearelive.landing.common.content.LandingContent;
import band.wearelive.landing.common.content.PanelCopy;
import band.wearelive.landing.common.content.Phone;
import band.wearelive.landing.common.content.PhoneCollage;
import band.wearelive.landing.common.content.ProblemItem;
import band.wearelive.landing.common.content.SectionTitle;
import band.wearelive.landing.common.content.SolutionCopy;
import band.wearelive.landing.common.content.SolutionRow;
import java.util.List;

/** Copy and visuals of the band member page (Figma node 102:819). Coordinates are design px from the frame. */
final class BandLandingContent {

    private BandLandingContent() {
    }

    private static String img(String name) {
        return "/assets/band/" + name + ".webp";
    }

    static LandingContent create() {
        return new LandingContent(
                "WEARELIVE — 밴드의 시작부터 무대에 서는 순간까지",
                "밴드 멤버 일정 조율부터 합주실·공연장 대관까지, 밴드 활동에 필요한 모든 과정을 위올라이브 하나로.",
                new HeroCopy(
                        "밴드 올인원 앱",
                        List.of("밴드의 시작부터", "무대에 서는 순간까지"),
                        List.of("멤버 일정 조율부터 합주실·공연장 대관까지,", "밴드 활동에 필요한 모든 과정을 위올라이브 하나로."),
                        "사전예약하기"),
                PhoneCollage.eager(647.31, 659,
                        Phone.shot(img("phone-home"), "위올라이브 홈 화면", 0, 0, 408.163, 595.365, 287.631, 542.615, -13.73),
                        Phone.shot(img("phone-band-notice"), "밴드 공지사항 화면", 224.58, 58.45, 422.73, 600.549, 288.018, 543.345, 15.5)),
                new PanelCopy(
                        List.of("밴드 공지·일정 관리부터", "합주실·공연장 예약까지 한번에"),
                        List.of("멤버들과 공지를 나누고 일정을 맞추며, 사진과 활동의 순간을 기록하세요.",
                                "연습과 공연에 필요한 공간 예약까지 위올라이브 하나로 관리할 수 있어요.")),
                "밴드 멤버를 위한 기능",
                SectionTitle.plain("PROBLEM", "지금, 이런 고민하고 있지 않나요?"),
                List.of(
                        new ProblemItem("“합주 시간 맞추기가 제일 어려워요”",
                                List.of("멤버마다 되는 시간이 달라 일정 하나 잡는 데 며칠씩 걸립니다.", "단톡방 투표 돌리고 기다리는 사이 합주는 계속 밀리죠.")),
                        new ProblemItem("“이번 공연, 어디서 하지?”",
                                List.of("공연장 잡으려고 전화 돌리고, DM 보내고, 답장 기다리고.", "무대 하나 구하는 데 일주일이 갑니다.")),
                        new ProblemItem("“연합공연 구하기가 제일 힘들어요”",
                                List.of("함께 설 팀을 찾으려면 아는 사람의 아는 사람까지 수소문.", "컨셉 맞는 밴드는 더 찾기 어렵죠."))),
                SectionTitle.emphasized("SOLUTION", "그래서 만들었습니다, ", "WEARELIVE"),
                List.of(myBand(), stage(), connect()),
                faq(),
                PhoneCollage.of(792.2, 880.66,
                        Phone.shot(img("phone-home"), "위올라이브 홈 화면", 0, 55.63, 568.248, 825.029, 399, 751, -13.95),
                        Phone.shot(img("phone-login"), "위올라이브 로그인 화면", 265.8, 0, 526.4, 810.266, 398, 751, 10.35)));
    }

    private static SolutionRow myBand() {
        return SolutionRow.of(
                new SolutionCopy("01 · MY BAND", "밴드 멤버 일정 조율과 운영", List.of(
                        "공지·일정·사진·프로필을 한 곳에서 관리합니다. 멤버들이 되는 시간을 모아 합주 일정을",
                        "확정하고, 셋리스트부터 축제 준비까지 단톡방 스크롤 지옥 없이 굴러갑니다.")),
                List.of(
                        new FeatureItem("밴드 전체 공지", "중요한 공지는 고정하고, 확인 여부까지 한눈에. 단톡방에 묻히지 않습니다."),
                        new FeatureItem("합주 일정 조율", "“언제 돼?” 안 물어봐도, 멤버들이 되는 시간이 자동으로 뜹니다."),
                        new FeatureItem("곡 정하기", "후보 올리고 투표로 셋리스트 확정. 취향 싸움은 이제 끝."),
                        new FeatureItem("추억 갤러리", "공연과 합주의 사진·영상을 밴드 갤러리에 차곡차곡. 우리 팀의 기록이 쌓입니다.")),
                RIGHT,
                PhoneCollage.of(677, 669,
                        Phone.shot(img("phone-band-notice"), "밴드 공지사항 화면", 0, 0, 308, 581),
                        Phone.shot(img("phone-band-schedule"), "밴드 일정 캘린더 화면", 179, 31, 311, 588),
                        Phone.shot(img("phone-band-photos"), "밴드 사진 갤러리 화면", 365, 82, 312, 587)));
    }

    private static SolutionRow stage() {
        return SolutionRow.of(
                new SolutionCopy("02 · STAGE", "공연장·합주실 대관 예약", List.of(
                        "공연장·합주실의 조건과 가능한 날짜를 앱에서 비교하고, 신청부터 결제까지 원클릭으로 끝냅니다.",
                        "전화 돌리던 과거는 잊으세요.")),
                List.of(),
                LEFT,
                PhoneCollage.of(676, 664,
                        Phone.shot(img("phone-rental-list"), "합주실·공연장 대관 목록 화면", 0, 0, 308, 581),
                        Phone.shot(img("phone-rental-detail"), "합주실 상세 및 예약 화면", 179, 33, 308, 580),
                        Phone.shot(img("phone-chat-list"), "대관 채팅 목록 화면", 368, 83, 308, 581)));
    }

    private static SolutionRow connect() {
        return SolutionRow.of(
                new SolutionCopy("03 · CONNECT", "공연 홍보와 밴드 간 소통", List.of(
                        "공연 홍보부터 연합공연 팀 구하기, 팀원 모집까지 —",
                        "흩어져 있던 밴드 간 소통을 앱 안에서 해결합니다.")),
                List.of(),
                RIGHT,
                PhoneCollage.of(615, 580,
                        Phone.shot(img("phone-home"), "공연·모집 소식 피드 화면", 0, 0, 307, 580),
                        Phone.shot(img("phone-audition"), "보컬 오디션 공고 화면", 307, 0, 308, 580)));
    }

    // 답변 문구는 디자인에 없어 초안으로 작성 — 확정 문구로 교체 필요.
    private static List<FaqItem> faq() {
        return List.of(
                new FaqItem("출시 예정일은 언제인가요?",
                        "정확한 출시일은 확정되는 대로 사전예약자분들께 이메일로 가장 먼저 안내해 드립니다."),
                new FaqItem("사전예약은 무료인가요?",
                        "네, 사전예약은 완전히 무료입니다. 사전예약자에게는 비공개 베타 초대 소식을 먼저 보내드려요."),
                new FaqItem("개인 뮤지션도 사용할 수 있나요?",
                        "물론입니다. 팀 없이도 합주실·공연장 예약과 팀원 모집 게시판을 이용할 수 있어요."),
                new FaqItem("공연장·합주실 사장님은 어떻게 입점하나요?",
                        "‘대관 사장님이라면’ 탭에서 사장님 전용 사전예약을 남겨주시면 입점 절차를 따로 안내해 드립니다."));
    }
}

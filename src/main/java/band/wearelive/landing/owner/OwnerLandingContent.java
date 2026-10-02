package band.wearelive.landing.owner;

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

/** Copy and visuals of the venue owner page (Figma node 102:952). Coordinates are design px from the frame. */
final class OwnerLandingContent {

    private OwnerLandingContent() {
    }

    private static String img(String name) {
        return "/assets/owner/" + name + ".webp";
    }

    static LandingContent create() {
        return new LandingContent(
                "WEARELIVE 사장님 — 공연장·합주실의 빈 시간, 예약으로 채우세요",
                "공간의 빈 시간대를 등록하면 밴드가 확인하고 예약할 수 있어요. 대관 신청부터 예약 일정 관리까지 위올라이브에서.",
                // The owner hero intentionally carries the same brand copy as the band page in the Figma design.
                new HeroCopy(
                        "밴드 올인원 앱",
                        List.of("밴드의 시작부터", "무대에 서는 순간까지"),
                        List.of("멤버 일정 조율부터 합주실·공연장 대관까지,", "밴드 활동에 필요한 모든 과정을 위올라이브 하나로."),
                        "사전예약하기"),
                PhoneCollage.eager(647.31, 659,
                        Phone.shot(img("phone-home"), "위올라이브 홈 화면", 0, 0, 408.163, 595.365, 287.631, 542.615, -13.73),
                        Phone.shot(img("phone-band-notice"), "밴드 공지사항 화면", 224.58, 58.45, 422.73, 600.549, 288.018, 543.345, 15.5)),
                new PanelCopy(
                        List.of("공연장·합주실의 빈 시간,", "예약으로 채우세요"),
                        List.of("공간의 빈 시간대를 등록하면 밴드가 확인하고 예약할 수 있어요.",
                                "대관 신청부터 예약 일정 관리까지 한곳에서 간편하게 관리하세요.")),
                "대관 사장님을 위한 기능",
                SectionTitle.plain("PROBLEM", "사장님, 이런 고민 있으시죠?"),
                List.of(
                        new ProblemItem("“평일 무대가 그냥 비어 있어요”",
                                List.of("비어 있는 시간대는 그대로 손해입니다.", "채우고 싶어도 공연할 팀을 찾을 방법이 마땅치 않죠.")),
                        new ProblemItem("“홍보는 어렵고, 비용은 부담돼요”",
                                List.of("대학 밴드에게 직접 닿는 채널이 없어서,", "SNS 광고를 돌려도 정작 공연 문의로 이어지지 않습니다.")),
                        new ProblemItem("“예약 문의 응대에 시간이 다 가요”",
                                List.of("전화와 DM으로 오는 문의, 일정 조율, 입금 확인까지 —", "대관 하나에 손이 너무 많이 갑니다."))),
                SectionTitle.emphasized("SOLUTION", "등록만 해두세요, ", "밴드가 찾아옵니다"),
                List.of(
                        // The design's solution rows only had blank phone placeholders, so they are text-only.
                        SolutionRow.textOnly(
                                new SolutionCopy("01 · LIST", "공간 등록과 노출", List.of(
                                        "공연장·합주실 정보와 대관 가능 시간대를 올려두면, 무대를 찾는 대학 밴드에게 바로 노출됩니다.",
                                        "별도 광고 없이 공간이 홍보되고, 예약 신청부터 결제까지 앱 안에서 끝납니다.")),
                                List.of(
                                        new FeatureItem("일정·예약 관리", "대관 신청과 확정, 시간대 관리를 한 화면에서. 전화 응대 없이 예약이 정리됩니다."),
                                        new FeatureItem("빈 시간대 채우기", "비는 날짜를 열어두면 공연·합주 수요가 자동으로 연결됩니다."))),
                        SolutionRow.textOnly(
                                new SolutionCopy("02 · CHAT", "예약한 밴드와 채팅으로 소통", List.of(
                                        "예약이 잡히면 밴드와 앱 안에서 바로 대화할 수 있습니다.",
                                        "장비 목록, 입장 시간, 무대 세팅 같은 조율도 전화 없이 채팅으로 간편하게 끝내세요.")),
                                List.of())),
                faq(),
                PhoneCollage.of(660, 700,
                        Phone.mockup(img("screen-login"), "위올라이브 로그인 화면", 20, 13.64, 427.584, 673.393, 295, 623, -13),
                        Phone.mockup(img("screen-chat-list"), "예약 밴드 채팅 목록 화면", 241.13, 0, 408.454, 667.842, 295, 623, 11)));
    }

    // 질문은 디자인 그대로, 답변 문구는 디자인에 없어 사장님 관점의 초안으로 작성 — 확정 문구로 교체 필요.
    private static List<FaqItem> faq() {
        return List.of(
                new FaqItem("출시 예정일은 언제인가요?",
                        "정확한 출시일은 확정되는 대로 사전예약하신 사장님께 이메일로 가장 먼저 안내해 드립니다."),
                new FaqItem("사전예약은 무료인가요?",
                        "네, 사전예약과 공간 등록은 무료입니다. 정식 출시 후 수수료 정책은 사전예약자분들께 먼저 공유드려요."),
                new FaqItem("개인 뮤지션도 사용할 수 있나요?",
                        "네. 밴드뿐 아니라 개인 연습, 보컬 레슨, 촬영 등 다양한 목적의 예약을 받으실 수 있습니다."),
                new FaqItem("공연장·합주실 사장님은 어떻게 입점하나요?",
                        "이 페이지에서 사전예약을 남겨주시면 담당자가 공간 정보 등록과 입점 절차를 개별로 안내해 드립니다."));
    }
}

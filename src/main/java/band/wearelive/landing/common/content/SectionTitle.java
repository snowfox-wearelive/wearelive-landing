package band.wearelive.landing.common.content;

/** Section heading whose trailing part is highlighted in red (e.g. "그래서 만들었습니다, <em>WEARELIVE</em>"). */
public record SectionTitle(String label, String lead, String emphasis) {

    public static SectionTitle plain(String label, String text) {
        return new SectionTitle(label, text, null);
    }

    public static SectionTitle emphasized(String label, String lead, String emphasis) {
        return new SectionTitle(label, lead, emphasis);
    }
}

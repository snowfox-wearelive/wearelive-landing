package band.wearelive.landing.common.content;

/**
 * A phone placed inside a {@link PhoneCollage}, using the coordinates from the Figma frame.
 *
 * @param kind   SHOT: exported screenshot that already includes the device frame;
 *               MOCKUP: CSS-drawn "Phone Mockup" component showing {@code src} as its screen
 * @param x      bounding box of the (possibly rotated) phone, in design px
 * @param innerW un-rotated phone size, in design px
 */
public record Phone(Kind kind, String src, String alt, double x, double y, double w, double h,
                    double innerW, double innerH, double rotate) {

    public enum Kind { SHOT, MOCKUP }

    public static Phone shot(String src, String alt, double x, double y, double w, double h) {
        return new Phone(Kind.SHOT, src, alt, x, y, w, h, w, h, 0);
    }

    public static Phone shot(String src, String alt, double x, double y, double w, double h,
                             double innerW, double innerH, double rotate) {
        return new Phone(Kind.SHOT, src, alt, x, y, w, h, innerW, innerH, rotate);
    }

    public static Phone mockup(String screenSrc, String alt, double x, double y, double w, double h,
                               double innerW, double innerH, double rotate) {
        return new Phone(Kind.MOCKUP, screenSrc, alt, x, y, w, h, innerW, innerH, rotate);
    }

    public boolean isShot() {
        return kind == Kind.SHOT;
    }

    public String innerStyle() {
        String style = "width:" + Css.pct(innerW, w) + ";height:" + Css.pct(innerH, h);
        return rotate == 0 ? style : style + ";transform:rotate(" + Css.num(rotate) + "deg)";
    }
}

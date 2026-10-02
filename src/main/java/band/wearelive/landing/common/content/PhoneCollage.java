package band.wearelive.landing.common.content;

import java.util.List;

/**
 * Phones laid out exactly as positioned in Figma, rendered as a fluid, aspect-locked box
 * that scales down from {@code width} design px.
 *
 * @param eager load images eagerly (above-the-fold hero)
 */
public record PhoneCollage(double width, double height, boolean eager, List<Phone> phones) {

    public static PhoneCollage of(double width, double height, Phone... phones) {
        return new PhoneCollage(width, height, false, List.of(phones));
    }

    public static PhoneCollage eager(double width, double height, Phone... phones) {
        return new PhoneCollage(width, height, true, List.of(phones));
    }

    public String style() {
        return "width:" + Css.num(width) + "px;aspect-ratio:" + Css.num(width) + " / " + Css.num(height);
    }

    public String boxStyle(Phone phone) {
        return "left:" + Css.pct(phone.x(), width) + ";top:" + Css.pct(phone.y(), height)
                + ";width:" + Css.pct(phone.w(), width) + ";height:" + Css.pct(phone.h(), height);
    }

    public String loading() {
        return eager ? "eager" : "lazy";
    }
}

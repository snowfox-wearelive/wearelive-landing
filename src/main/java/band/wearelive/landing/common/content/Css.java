package band.wearelive.landing.common.content;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class Css {

    private Css() {
    }

    static String pct(double value, double total) {
        return num(value / total * 100) + "%";
    }

    static String num(double value) {
        return BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }
}

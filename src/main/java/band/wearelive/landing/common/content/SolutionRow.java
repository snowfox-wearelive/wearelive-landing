package band.wearelive.landing.common.content;

import java.util.List;

/**
 * One feature row of the SOLUTION section.
 *
 * @param visualSide which side the phones sit on at desktop widths
 * @param cssClass   optional audience-specific modifier class
 */
public record SolutionRow(SolutionCopy copy, List<FeatureItem> features, VisualSide visualSide, PhoneCollage visual,
                          String cssClass) {

    public enum VisualSide { LEFT, RIGHT }

    public static SolutionRow of(SolutionCopy copy, List<FeatureItem> features, VisualSide side, PhoneCollage visual) {
        return new SolutionRow(copy, features, side, visual, "");
    }

    public SolutionRow withCssClass(String cssClass) {
        return new SolutionRow(copy, features, visualSide, visual, cssClass);
    }

    public String rowClass() {
        return "solution-row solution-row--visual-" + visualSide.name().toLowerCase() + " " + cssClass;
    }
}

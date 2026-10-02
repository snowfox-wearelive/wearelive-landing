package band.wearelive.landing.common.content;

import java.util.List;

/**
 * One feature row of the SOLUTION section.
 *
 * @param visualSide which side the phones sit on at desktop widths (ignored when there is no visual)
 * @param visual     phones shown next to the copy, or {@code null} for a text-only row
 */
public record SolutionRow(SolutionCopy copy, List<FeatureItem> features, VisualSide visualSide, PhoneCollage visual) {

    public enum VisualSide { LEFT, RIGHT }

    public static SolutionRow of(SolutionCopy copy, List<FeatureItem> features, VisualSide side, PhoneCollage visual) {
        return new SolutionRow(copy, features, side, visual);
    }

    public static SolutionRow textOnly(SolutionCopy copy, List<FeatureItem> features) {
        return new SolutionRow(copy, features, VisualSide.RIGHT, null);
    }

    public boolean hasVisual() {
        return visual != null;
    }

    public String rowClass() {
        return hasVisual()
                ? "solution-row solution-row--visual-" + visualSide.name().toLowerCase()
                : "solution-row solution-row--text-only";
    }
}

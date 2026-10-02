package band.wearelive.landing.common.content;

import java.util.List;

/** Everything a landing page renders. Each audience builds its own instance; nothing is shared between them. */
public record LandingContent(
        String title,
        String description,
        HeroCopy hero,
        PhoneCollage heroVisual,
        PanelCopy panel,
        String audienceLabel,
        SectionTitle problemTitle,
        List<ProblemItem> problems,
        SectionTitle solutionTitle,
        List<SolutionRow> solutions,
        List<FaqItem> faq,
        PhoneCollage ctaVisual) {
}

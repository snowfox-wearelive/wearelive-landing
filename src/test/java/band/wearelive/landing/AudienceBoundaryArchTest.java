package band.wearelive.landing;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/** Enforces the strict split between the band (general user) and owner audiences. */
@AnalyzeClasses(packages = "band.wearelive.landing", importOptions = ImportOption.DoNotIncludeTests.class)
class AudienceBoundaryArchTest {

    @ArchTest
    static final ArchRule bandDoesNotDependOnOwner = noClasses()
            .that().resideInAPackage("..landing.band..")
            .should().dependOnClassesThat().resideInAPackage("..landing.owner..");

    @ArchTest
    static final ArchRule ownerDoesNotDependOnBand = noClasses()
            .that().resideInAPackage("..landing.owner..")
            .should().dependOnClassesThat().resideInAPackage("..landing.band..");

    @ArchTest
    static final ArchRule commonIsAudienceAgnostic = noClasses()
            .that().resideInAPackage("..landing.common..")
            .should().dependOnClassesThat().resideInAnyPackage("..landing.band..", "..landing.owner..");
}

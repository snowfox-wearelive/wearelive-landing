package band.wearelive.landing.band;

/** Main position of a band member, asked only in the band pre-registration form. */
public enum Position {
    VOCAL("보컬"),
    GUITAR("기타"),
    BASS("베이스"),
    DRUMS("드럼"),
    KEYBOARD("키보드"),
    OTHER("그 외");

    private final String label;

    Position(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

package academic.model;

public enum Semester {
    ODD("odd"),
    EVEN("even"),
    SHORT("short");

    private final String text;

    private Semester(String text) {
        this.text = text;
    }

    public String text() {
        return this.text;
    }

    public int diff(Semester other) {
        return this.ordinal() - other.ordinal();
    }

    @Override
    public String toString() {
        return this.text;
    }
}

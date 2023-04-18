package academic.model;

public enum Grade {
    A("A", 4.0),
    AB("AB", 3.5),
    B("B", 3.0),
    BC("BC", 2.5),
    C("C", 2.0),
    D("D", 1.0),
    E("E", 0.0),
    T("T", 0.0),
    NONE("None", 0.0);

    private final String text;
    private final double weight;

    private Grade(String text, double weight) {
        this.text = text;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public String text() {
        return this.text;
    }

    public double performance(int credit) {
        return this.weight * credit;
    }

    @Override
    public String toString() {
        return this.text + "|" +
                this.weight;
    }
}

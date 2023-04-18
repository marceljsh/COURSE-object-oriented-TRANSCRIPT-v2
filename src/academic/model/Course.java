package academic.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author NIM Nama
 */
public class Course {

    private String code;
    private String name;
    private int credit;
    private Grade passingGrade;
    private Collection<CourseOpening> courseOpenings;

    public Course() {
        this.code = "";
        this.name = "";
        this.credit = 0;
        this.passingGrade = Grade.NONE;
        this.courseOpenings = new LinkedList<CourseOpening>();
    }

    public Course(String code, String name, int credit, Grade passingGrade) {
        this();
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public int getCredit() {
        return this.credit;
    }

    public Grade getPassingGrade() {
        return this.passingGrade;
    }

    public void setPassingGrade(Grade passingGrade) {
        this.passingGrade = passingGrade;
    }

    public boolean registerOpening(CourseOpening courseOpening) {
        return this.courseOpenings.add(courseOpening);
    }

    public boolean hasOpening(CourseOpening courseOpening) {
        return this.courseOpenings.contains(courseOpening);
    }

    public String history() {
        PriorityQueue<CourseOpening> queue = new PriorityQueue<CourseOpening>(
                new CourseOpeningAscendingComparator<CourseOpening>());

        queue.addAll(this.courseOpenings);

        StringBuilder builder = new StringBuilder();
        CourseOpening courseOpening;

        while ((courseOpening = queue.poll()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }

            builder.append(courseOpening.history());
        }

        return builder.toString();
    }

    @Override
    public String toString() {

        return this.code + "|" +
                this.name + "|" +
                this.credit + "|" +
                this.passingGrade.text();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

}
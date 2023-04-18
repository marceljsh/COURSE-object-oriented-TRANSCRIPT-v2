package academic.model;

/**
 * @author NIM Nama
 */
public class Enrollment {

    private CourseOpening courseOpening;
    private Student student;
    private Grade grade;
    private Grade previousGrade;

    private Enrollment() {
        // hide this constructor from the outside world.
    }

    public Enrollment(CourseOpening courseOpening, Student student) {
        this();
        this.courseOpening = courseOpening;
        this.student = student;
        this.grade = Grade.NONE;
        this.previousGrade = Grade.NONE;
    }

    public CourseOpening getCourseOpening() {
        return this.courseOpening;
    }

    public Course getCourse() {
        return this.courseOpening.getCourse();
    }

    public Student getStudent() {
        return this.student;
    }

    public String getAcademicYear() {
        return this.courseOpening.getAcademicYear();
    }

    public Semester getSemester() {
        return this.courseOpening.getSemester();
    }

    public void setGrade(Grade grade, boolean remedial) {
        if (this.previousGrade != Grade.NONE) {
            return;
        }

        if (remedial && this.grade != Grade.NONE) {
            this.previousGrade = this.grade;
        }

        this.setGrade(grade);
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public double getPerformance() {
        return this.grade.performance(this.courseOpening.getCourse().getCredit());
    }

    public boolean isGraded() {
        return this.grade != Grade.NONE;
    }

    @Override
    public String toString() {
        return this.courseOpening.getCourse().getCode() + "|" +
                this.student.getId() + "|" +
                this.courseOpening.getAcademicYear() + "|" +
                this.courseOpening.getSemester() + "|" +
                this.grade.text() +
                (this.previousGrade != Grade.NONE ? "(" + this.previousGrade.text() + ")" : "");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseOpening == null) ? 0 : courseOpening.hashCode());
        result = prime * result + ((student == null) ? 0 : student.hashCode());
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
        Enrollment other = (Enrollment) obj;
        if (courseOpening == null) {
            if (other.courseOpening != null)
                return false;
        } else if (!courseOpening.equals(other.courseOpening))
            return false;
        if (grade != other.grade)
            return false;
        if (previousGrade != other.previousGrade)
            return false;
        if (student == null) {
            if (other.student != null)
                return false;
        } else if (!student.equals(other.student))
            return false;
        return true;
    }

}
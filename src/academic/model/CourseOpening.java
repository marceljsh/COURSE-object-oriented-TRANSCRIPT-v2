package academic.model;

import java.util.ArrayList;
import java.util.Collection;

public class CourseOpening {
    private String academicYear;
    private Semester semester;
    private Course course;
    private Collection<Lecturer> lecturers;
    private Collection<Enrollment> enrollments;

    public CourseOpening(String academicYear, Semester semester, Course course) {
        this.academicYear = academicYear;
        this.semester = semester;
        this.course = course;
        this.lecturers = new ArrayList<Lecturer>();
        this.enrollments = new ArrayList<Enrollment>();
    }

    public String getAcademicYear() {
        return this.academicYear;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public Course getCourse() {
        return this.course;
    }

    public boolean registerLecturer(Lecturer lecturer) {
        return this.lecturers.add(lecturer);
    }

    public boolean hasLecturer(Lecturer lecturer) {
        return this.lecturers.contains(lecturer);
    }

    public boolean registerEnrollment(Enrollment enrollment) {
        return this.enrollments.add(enrollment);
    }

    public boolean hasEnrollment(Enrollment enrollment) {
        return this.enrollments.contains(enrollment);
    }

    public Enrollment findEnrollmentWithStudent(Student student) {
        for (Enrollment model : this.enrollments) {
            if (model.getStudent().equals(student)) {
                return model;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Lecturer lecturer : this.lecturers) {
            if (builder.length() > 1) {
                builder.append(";");
            }

            builder.append(
                    String.format(
                            "%s (%s)",
                            lecturer.getInitial(),
                            lecturer.getEmail()));
        }

        return this.course + "|" +
                this.academicYear + "|" +
                this.semester.text() +
                ((builder.length() > 0) ? "|" + builder.toString() : "");
    }

    public String history() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.toString());

        for (Enrollment enrollment : this.enrollments) {
            if (builder.length() > 0) {
                builder.append("\n");
            }

            builder.append(enrollment.toString());
        }

        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((academicYear == null) ? 0 : academicYear.hashCode());
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((semester == null) ? 0 : semester.hashCode());
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
        CourseOpening other = (CourseOpening) obj;
        if (academicYear == null) {
            if (other.academicYear != null)
                return false;
        } else if (!academicYear.equals(other.academicYear))
            return false;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (semester != other.semester)
            return false;
        return true;
    }

}
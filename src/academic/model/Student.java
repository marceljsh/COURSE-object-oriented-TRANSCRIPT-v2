package academic.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author NIM Nama
 */
public class Student {

    private String id;
    private String name;
    private String year;
    private String studyProgram;
    private List<Enrollment> enrollments;

    public Student() {
        this.id = "";
        this.name = "";
        this.year = "";
        this.studyProgram = "";
        this.enrollments = new ArrayList<Enrollment>();
    }

    public Student(String id, String name, String year, String studyProgram) {
        this();
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public void enrolledTo(Enrollment enrollment) {
        if (!enrollment.getStudent().equals(this)) {
            return;
        }

        this.enrollments.add(enrollment);
    }

    public Enrollment getEnrollment(CourseOpening courseOpening) {
        for (Enrollment enrollment : this.enrollments) {
            if (enrollment.getCourseOpening().equals(courseOpening)) {
                return enrollment;
            }
        }

        return null;
    }

    public String getDetails(boolean transcripting) {
        Set<String> loggedCourses = new HashSet<String>();
        
        Comparator<Enrollment> ascEnrollmentComparator = new Comparator<Enrollment>(){
            @Override
            public int compare(Enrollment e1, Enrollment e2) {
                int diff = 0;

                diff = e1.getAcademicYear().compareTo(e2.getAcademicYear());

                if (diff != 0) {
                    return diff;
                }

                return e1.getSemester().diff(e2.getSemester());
            }
        };
        
        Comparator<Enrollment> descEnrollmentComparator = new Comparator<Enrollment>(){
            @Override
            public int compare(Enrollment e1, Enrollment e2) {
                int diff = 0;

                diff = -e1.getAcademicYear().compareTo(e2.getAcademicYear());

                if (diff != 0) {
                    return diff;
                }

                return -e1.getSemester().diff(e2.getSemester());
            }
        };

        Queue<Enrollment> descEnrollments = new PriorityQueue<Enrollment>(descEnrollmentComparator);
        Queue<Enrollment> historicalEnrollments = new PriorityQueue<Enrollment>(ascEnrollmentComparator);
        descEnrollments.addAll(this.enrollments);

        while (!descEnrollments.isEmpty()) {
            Enrollment enrollment = descEnrollments.poll();

            if (!enrollment.isGraded()) {
                continue;
            }

            if(loggedCourses.add(enrollment.getCourse().getCode())){
                historicalEnrollments.add(enrollment);
            }
        }

        StringBuilder builder = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");
        double performance = 0.0;
        int credits = 0;
        double gpa = 0.0;

        for (Enrollment enrollment : historicalEnrollments) {
            performance += enrollment.getPerformance();
            credits += enrollment.getCourse().getCredit();

            if (transcripting) {
                if (builder.length() > 0) {
                    builder.append("\n");
                }
                builder.append(enrollment.toString());
            }
        }

        if (performance > 0.0) {
            gpa = performance / credits;
        }

        return this.toString() + "|" +
                df.format(gpa) + "|" +
                credits +
                (builder.length() > 0 ? "\n" : "") +
                builder.toString();
    }

    @Override
    public String toString() {
        return this.id + "|" +
                this.name + "|" +
                this.year + "|" +
                this.studyProgram;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Student other = (Student) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
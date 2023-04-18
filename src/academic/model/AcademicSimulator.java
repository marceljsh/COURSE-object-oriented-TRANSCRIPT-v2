package academic.model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author NIM Nama
 * @author NIM Nama
 */
public class AcademicSimulator {

    private LecturerStorage<Lecturer> lecturers;
    private CourseStorage<Course> courses;
    private StudentStorage<Student> students;
    private CourseOpeningStorage<CourseOpening> courseOpenings;
    private EnrollmentStorage<Enrollment> enrollments;

    public AcademicSimulator() {
        this.lecturers = new LecturerStorage<Lecturer>();
        this.courses = new CourseStorage<Course>();
        this.students = new StudentStorage<Student>();
        this.courseOpenings = new CourseOpeningStorage<CourseOpening>();
        this.enrollments = new EnrollmentStorage<Enrollment>();
    }

    public boolean hasLecturer(Lecturer lecturer) {
        return this.lecturers.has(lecturer);
    }

    public Lecturer registerLecturer(Lecturer lecturer) {
        return this.lecturers.register(lecturer);
    }

    public Lecturer findLecturerWithInitial(String lecturerInitial) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("initial", lecturerInitial);

        return this.lecturers.find(params);
    }

    public boolean hasCourse(Course course) {
        return this.courses.has(course);
    }

    public Course registerCourse(Course course) {
        return this.courses.register(course);
    }

    public Course findCourseWithCode(String courseCode) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("code", courseCode);

        return this.courses.find(params);
    }

    public boolean hasStudent(Student student) {
        return this.students.has(student);
    }

    public Student registerStudent(Student student) {
        return this.students.register(student);
    }

    public boolean hasCourseOpening(CourseOpening courseOpening) {
        return this.courseOpenings.has(courseOpening);
    }

    public CourseOpening registerCourseOpening(CourseOpening courseOpening) {
        courseOpening.getCourse().registerOpening(courseOpening);
        return this.courseOpenings.register(courseOpening);
    }

    public Student findStudentWithId(String studentId) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", studentId);

        return this.students.find(params);
    }

    public boolean hasEnrollment(Enrollment enrollment) {
        return this.enrollments.has(enrollment);
    }

    public Enrollment getEnrollment(String courseCode, String studentId, String academicYear, Semester semester) {
        Course course = this.findCourseWithCode(courseCode);

        if (course == null) {
            return null;
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("academicYear", academicYear);
        params.put("semester", semester.name());
        params.put("courseCode", courseCode);

        CourseOpening courseOpening = this.courseOpenings.find(params);

        if (courseOpening == null) {
            return null;
        }

        return this.getEnrollment(courseOpening, studentId);
    }

    public Enrollment getEnrollment(CourseOpening courseOpening, String studentId) {
        Student student = this.findStudentWithId(studentId);

        if (student == null) {
            return null;
        }

        return courseOpening.findEnrollmentWithStudent(student);
    }

    public Enrollment enroll(String courseCode, String studentId, String academicYear, Semester semester)
            throws Exception {
        Course course = this.findCourseWithCode(courseCode);

        if (course == null) {
            throw new Exception("invalid course|".concat(courseCode));
        }

        Student student = this.findStudentWithId(studentId);

        if (student == null) {
            throw new Exception("invalid student|".concat(studentId));
        }

        return this._enroll(course, student, academicYear, semester);
    }

    public Enrollment enroll(Course course, Student student, String academicYear, Semester semester) throws Exception {
        if (!this.hasCourse(course)) {
            throw new Exception("invalid course|".concat(course.getCode()));
        }

        if (!this.hasStudent(student)) {
            throw new Exception("invalid student|".concat(student.getId()));
        }

        return this._enroll(course, student, academicYear, semester);
    }

    private Enrollment _enroll(Course course, Student student, String academicYear, Semester semester)
            throws Exception {
        Map<String, String> params = new Hashtable<String, String>();
        params.put("academicYear", academicYear);
        params.put("semester", semester.name());
        params.put("courseCode", course.getCode());

        CourseOpening courseOpening = this.courseOpenings.find(params);

        if (courseOpening == null) {
            courseOpening = new CourseOpening(academicYear, semester, course);
        }

        Enrollment enrollment = courseOpening.findEnrollmentWithStudent(student);

        if (enrollment != null) {
            return null;
        }

        enrollment = new Enrollment(courseOpening, student);

        if (this.courseOpenings.register(courseOpening) != null) {
            course.registerOpening(courseOpening);
        }

        if (this.enrollments.register(enrollment) != null) {
            student.enrolledTo(enrollment);
            courseOpening.registerEnrollment(enrollment);

            return enrollment;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(lecturers.toString());
        builder.append("\n");
        builder.append(courses.toString());
        builder.append("\n");
        builder.append(students.toString());
        builder.append("\n");
        builder.append(enrollments.toString());

        return builder.toString();
    }
}

package academic.driver;

import java.util.Arrays;
import java.util.Scanner;

import academic.model.AcademicSimulator;
import academic.model.*;

/**
 * @author MSS
 */
public class Driver1 {

    static final String DATA_SEPARATOR = "#";
    static final String SUBDATA_SEPARATOR = ",";

    public static void main(String[] _args) {
        AcademicSimulator simulator = new AcademicSimulator();
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;
        String line = null;

        Enrollment enrollment = null;

        while (scanner.hasNext() && keepGoing) {
            line = scanner.nextLine();

            keepGoing = !line.equals("---");

            if (keepGoing) {
                String[] data = line.split(DATA_SEPARATOR);
                String command = data[0];
                data = Arrays.copyOfRange(data, 1, data.length);

                switch (command) {
                    case "lecturer-add":
                        addLecturer(simulator, data);
                        break;
                    case "course-add":
                        addCourse(simulator, data);
                        break;
                    case "course-open":
                        openCourse(simulator, data);
                        break;
                    case "course-history":
                        courseHistory(simulator, data);
                        break;
                    case "student-add":
                        addStudent(simulator, data);
                        break;
                    case "enrollment-add":
                        addEnrollment(simulator, data);
                        break;
                    case "enrollment-grade":
                        gradeEnrollment(simulator, data);
                        break;
                    case "enrollment-remedial":
                        remedial(simulator, data);
                        break;

                    case "student-details":
                        detailStudent(simulator, data, false);
                        break;

                    case "student-transcript":
                        detailStudent(simulator, data, true);
                        break;
                }
            }
        }

        System.out.println(simulator.toString());

        scanner.close();
    }

    private static void addLecturer(AcademicSimulator simulator, String data[]) {
        Lecturer lecturer = new Lecturer(data[0], data[1], data[2], data[3], data[4]);

        simulator.registerLecturer(lecturer);
    }

    private static void addCourse(AcademicSimulator simulator, String data[]) {
        Grade grade = Grade.valueOf(data[3]);

        Course course = new Course(data[0], data[1], Integer.parseInt(data[2]), grade);

        simulator.registerCourse(course);
    }

    private static void openCourse(AcademicSimulator simulator, String data[]) {
        Course course = simulator.findCourseWithCode(data[0]);

        if (course != null) {
            Semester semester = Semester.valueOf(data[2].toUpperCase());

            CourseOpening courseOpening = new CourseOpening(data[1], semester, course);

            String[] lecturerInitials = data[3].split(SUBDATA_SEPARATOR);

            for (String lecturerInitial : lecturerInitials) {
                Lecturer lecturer = simulator.findLecturerWithInitial(lecturerInitial);

                if (lecturer != null) {
                    courseOpening.registerLecturer(lecturer);
                }
            }

            simulator.registerCourseOpening(courseOpening);
        }
    }

    private static void courseHistory(AcademicSimulator simulator, String data[]) {
        Course course = simulator.findCourseWithCode(data[0]);

        if (course != null) {
            System.out.println(course.history());
        }
    }

    private static void addStudent(AcademicSimulator simulator, String data[]) {
        Student student = new Student(data[0], data[1], data[2], data[3]);

        simulator.registerStudent(student);
    }

    private static void addEnrollment(AcademicSimulator simulator, String data[]) {
        try {
            Semester semester = Semester.valueOf(data[3].toUpperCase());

            simulator.enroll(data[0], data[1], data[2], semester);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void gradeEnrollment(AcademicSimulator simulator, String data[]) {
        Semester semester = Semester.valueOf(data[3].toUpperCase());

        Enrollment enrollment = simulator.getEnrollment(data[0], data[1], data[2], semester);

        if (enrollment != null) {
            enrollment.setGrade(Grade.valueOf(data[4]), false);
        }
    }

    private static void remedial(AcademicSimulator simulator, String data[]) {
        Semester semester = Semester.valueOf(data[3].toUpperCase());

        Enrollment enrollment = simulator.getEnrollment(data[0], data[1], data[2], semester);

        if (enrollment != null) {
            enrollment.setGrade(Grade.valueOf(data[4]), true);
        }
    }

    private static void detailStudent(AcademicSimulator simulator, String data[], boolean transcripting) {
        Student student = simulator.findStudentWithId(data[0]);

        if (student != null) {
            System.out.println(student.getDetails(transcripting));
        }
    }
}
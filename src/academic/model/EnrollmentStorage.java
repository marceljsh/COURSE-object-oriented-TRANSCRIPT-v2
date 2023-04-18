package academic.model;

import java.util.ArrayList;
import java.util.Map;

public class EnrollmentStorage<T extends Enrollment> extends DataStorage<T> {

  public void initStorage() {
    this.collection = new ArrayList<T>();
  }

  public T find(Map<String, String> params) {
    String courseCode = params.get("courseCode");
    String studentId = params.get("studentId");
    String academicYear = params.get("academicYear");
    String semester = params.get("semester");

    for (T model : this.collection) {
      if (model.getCourse().getCode().equals(courseCode) &&
          model.getStudent().getId().equals(studentId) &&
          model.getAcademicYear().equals(academicYear) &&
          model.getSemester().equals(semester)) {
        return model;
      }
    }

    return null;
  }

}

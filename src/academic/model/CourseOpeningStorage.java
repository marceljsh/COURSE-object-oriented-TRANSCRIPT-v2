package academic.model;

import java.util.ArrayList;
import java.util.Map;

public class CourseOpeningStorage<T extends CourseOpening> extends DataStorage<T> {

  public void initStorage() {
    this.collection = new ArrayList<T>();
  }

  public T find(Map<String, String> params) {
    String academicYear = params.get("academicYear");
    Semester semester = Semester.valueOf(params.get("semester"));
    String courseCode = params.get("courseCode");

    for (T model : this.collection) {
      if (model.getAcademicYear().equals(academicYear) &&
          model.getSemester() == semester &&
          model.getCourse().getCode().equals(courseCode)) {
        return model;
      }
    }

    return null;
  }

}

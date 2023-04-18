package academic.model;

import java.util.Comparator;

public class CourseOpeningAscendingComparator<T extends CourseOpening> implements Comparator<T> {

  public CourseOpeningAscendingComparator() {

  }

  @Override
  public int compare(T o1, T o2) {
    int diff = 0;

    diff = o1.getAcademicYear().compareTo(o1.getAcademicYear());

    if (diff != 0) {
      return diff;
    }

    diff = o1.getSemester().ordinal() - o2.getSemester().ordinal();

    if (diff != 0) {
      diff = o1.getSemester().ordinal() - o2.getSemester().ordinal();
    }

    return diff;
  }

}

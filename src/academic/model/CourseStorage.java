package academic.model;

import java.util.ArrayList;
import java.util.Map;

public class CourseStorage<T extends Course> extends DataStorage<T> {

  public void initStorage() {
    this.collection = new ArrayList<T>();
  }

  public T find(Map<String, String> params) {
    String code = params.get("code");

    for (T model : this.collection) {
      if (model.getCode().equals(code)) {
        return model;
      }
    }

    return null;
  }

}

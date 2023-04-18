package academic.model;

import java.util.ArrayList;
import java.util.Map;

public class LecturerStorage<T extends Lecturer> extends DataStorage<T> {

  public void initStorage() {
    this.collection = new ArrayList<T>();
  }

  public T find(Map<String, String> params) {
    String initial = params.get("initial");

    for (T model : this.collection) {
      if (model.getInitial().equals(initial)) {
        return model;
      }
    }

    return null;
  }

}

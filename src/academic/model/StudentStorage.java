package academic.model;

import java.util.ArrayList;
import java.util.Map;

public class StudentStorage<T extends Student> extends DataStorage<T> {

  public void initStorage() {
    this.collection = new ArrayList<T>();
  }

  public T find(Map<String, String> params) {
    String id = params.get("id");

    for (T model : this.collection) {
      if (model.getId().equals(id)) {
        return model;
      }
    }

    return null;
  }

}

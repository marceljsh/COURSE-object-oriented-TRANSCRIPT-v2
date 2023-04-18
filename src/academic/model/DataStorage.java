package academic.model;

import java.util.Collection;
import java.util.Map;

public abstract class DataStorage<T extends Object> {
  protected Collection<T> collection;

  public DataStorage() {
    initStorage();
  }

  abstract void initStorage();

  public boolean has(T model) {
    return this.collection.contains(model);
  }

  public T register(T model) {
    if (!this.has(model) && this.collection.add(model)) {
      return model;
    }

    return null;
  }

  abstract T find(Map<String, String> params);

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (T model : this.collection) {
      if (builder.length() > 0) {
        builder.append("\n");
      }

      builder.append(model.toString());
    }

    return builder.toString();
  }

}

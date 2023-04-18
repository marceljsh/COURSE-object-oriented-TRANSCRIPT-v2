package academic.model;

/**
 * @author NIM Nama
 */
public class Lecturer {

    private String id;
    private String name;
    private String email;
    private String initial;
    private String studyProgram;

    public Lecturer() {
        this.id = "";
        this.name = "";
        this.initial = "";
        this.email = "";
        this.studyProgram = "";
    }

    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
        this();
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.email = email;
        this.studyProgram = studyProgram;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getInitial() {
        return this.initial;
    }

    public String getEmail() {
        return this.email;
    }

    public String getStudyProgram() {
        return this.studyProgram;
    }

    @Override
    public String toString() {
        return this.id + "|" +
                this.name + "|" +
                this.initial + "|" +
                this.email + "|" +
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
        Lecturer other = (Lecturer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
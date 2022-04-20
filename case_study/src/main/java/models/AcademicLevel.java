package models;

public class AcademicLevel {
    private Integer id;
    private String level;

    public AcademicLevel(Integer id, String level) {
        this.id = id;
        this.level = level;
    }

    public AcademicLevel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

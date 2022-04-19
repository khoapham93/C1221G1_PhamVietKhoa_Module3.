package models;

public class CustomerType {
    private Integer id;
    private String Type;

    public CustomerType(Integer id, String type) {
        this.id = id;
        Type = type;
    }
    public CustomerType( String type) {
        Type = type;
    }

    public CustomerType(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}

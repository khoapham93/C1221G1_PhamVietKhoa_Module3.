package models;

public class CustomerType {
    private Integer id;
    private String type;

    public CustomerType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }
    public CustomerType( String type) {
        this.type = type;
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
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package models;

public class Customer {
    private String name;
    private String birthday;
    private int Gender;
    private double grade;
    private String address;
    private String profileImage;
    private int customerType;

    public Customer(String name, String birthday, int gender, double grade, String address, String profileImage, int customerType) {
        this.name = name;
        this.birthday = birthday;
        Gender = gender;
        this.grade = grade;
        this.address = address;
        this.profileImage = profileImage;
        this.customerType = customerType;
    }
    public Customer(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }
}

package data;

public class Person {
    public int record;
    public String firstName;
    public String lastName;
    public String phone;
    public int age;
    public static final int FIRST_NAME_SIZE = 20;
    public static final int LAST_NAME_SIZE = 25;
    public static final int PHONE_SIZE = 10;
    public Person(int record,String firstName, String lastName, int age, String phone) {
        this.record = record;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }
}

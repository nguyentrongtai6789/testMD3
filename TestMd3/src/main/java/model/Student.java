package model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String address;
    private String phoneNumber;
    private ClassOfStudent classOfStudent;

    public Student(int id, String name, LocalDate dateOfBirth, String email, String address, String phoneNumber, ClassOfStudent classOfStudent) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classOfStudent = classOfStudent;
    }

    public Student(String name, LocalDate dateOfBirth, String email, String address, String phoneNumber, ClassOfStudent classOfStudent) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classOfStudent = classOfStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ClassOfStudent getClassOfStudent() {
        return classOfStudent;
    }

    public void setClassOfStudent(ClassOfStudent classOfStudent) {
        this.classOfStudent = classOfStudent;
    }
}

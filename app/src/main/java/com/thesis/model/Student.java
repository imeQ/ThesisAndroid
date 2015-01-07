package com.thesis.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private long id;
    private String pin;
    private String studentBookNumber;
    private String firstName;
    private String lastName;
    private Thesis thesis;

    public Student(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStudentBookNumber() {
        return studentBookNumber;
    }

    public void setStudentBookNumber(String studentBookNumber) {
        this.studentBookNumber = studentBookNumber;
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

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.pin);
        dest.writeString(this.studentBookNumber);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeParcelable(this.thesis, 0);
    }

    private Student(Parcel in) {
        this.id = in.readLong();
        this.pin = in.readString();
        this.studentBookNumber = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.thesis = in.readParcelable(Thesis.class.getClassLoader());
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}

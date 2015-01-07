package com.thesis.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Thesis implements Parcelable {
    private long id;
    private String name;
    private String description;
    private Student student;
    private Boolean available;

    public Thesis() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.student, flags);
        dest.writeValue(this.available);
    }

    private Thesis(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
        this.student = in.readParcelable(Student.class.getClassLoader());
        this.available = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Thesis> CREATOR = new Parcelable.Creator<Thesis>() {
        public Thesis createFromParcel(Parcel source) {
            return new Thesis(source);
        }

        public Thesis[] newArray(int size) {
            return new Thesis[size];
        }
    };
}

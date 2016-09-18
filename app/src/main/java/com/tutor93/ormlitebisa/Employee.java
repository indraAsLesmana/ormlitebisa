package com.tutor93.ormlitebisa;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by indra on 11/09/2016.
 */
public class Employee {

    @DatabaseField(generatedId = true)
    protected int id;
    @DatabaseField
    protected String name;
    @DatabaseField
    protected String jobs;
    @DatabaseField
    protected int age;
    @DatabaseField
    protected boolean is_male;
    @DatabaseField
    protected String join;
    @DatabaseField
    protected String notes;
    @DatabaseField
    protected boolean bookmark;
    @DatabaseField
    protected String avatar;

   /* public boolean bookmark;*/

   /* public Employee (String name, String jobs, int age, boolean is_male,
                    String join, String notes, boolean bookmark, String avatar) {
        this.name = name;
        this.jobs = jobs;
        this.age = age;
        this.is_male = is_male;
        this.join = join;
        this.notes = notes;
        this.bookmark = bookmark;
        this.avatar = avatar;
    }*/

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


    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIs_male() {
        return is_male;
    }

    public void setIs_male(boolean is_male) {
        this.is_male = is_male;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /*public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }*/

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
       this.bookmark = bookmark;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

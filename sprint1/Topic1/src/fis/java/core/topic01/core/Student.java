package fis.java.core.topic01.core;

import java.util.Date;
import java.util.Objects;

public class Student {
    private int code;
    private String name;
    private Date birthDAte;

    public Student(int code, String name, Date birthDAte) {
        this.code = code;
        this.name = name;
        this.birthDAte = birthDAte;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDAte() {
        return birthDAte;
    }

    public void setBirthDAte(Date birthDAte) {
        this.birthDAte = birthDAte;
    }

    @Override
    public String toString() {
        return "Student{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", birthDAte=" + birthDAte +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return code == student.code && name.equals(student.name) && birthDAte.equals(student.birthDAte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, birthDAte);
    }
}

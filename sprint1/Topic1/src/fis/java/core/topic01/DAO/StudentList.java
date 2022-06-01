package fis.java.core.topic01.DAO;

import fis.java.core.topic01.core.Student;

public class StudentList {

    private final int MAX = 100;

    private Student[] students;
    private int count;
    private ISortStrategy iSortStrategy;


    public void addStudent() {
        //TODO
    }

    public Student removeStudent(int code) {
        //TODO
        return null;
    }

    public void display() {
        //TODO
    }

    public void sort() {
        //TODO
    }

    public void setSortStrategy(ISortStrategy SortStrategy) {

        this.iSortStrategy = SortStrategy;
    }

}

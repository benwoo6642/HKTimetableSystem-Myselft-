package hkts;

import java.util.ArrayList;

public class HKTSData {

    // Shared Data Storage

    private ArrayList<Lecturer> lecturers = new ArrayList<>();

    private ArrayList<Course> courses = new ArrayList<>();

    private ArrayList<StudentGroup> studentGroups = new ArrayList<>();

    private ArrayList<Classroom> classrooms = new ArrayList<>();

    private ArrayList<Laboratory> laboratories = new ArrayList<>();

    private ArrayList<ClassSession> classSessions = new ArrayList<>();


    // Getter Methods

    public ArrayList<Lecturer> getLecturers() {
    	
        return lecturers;
    }

    public ArrayList<Course> getCourses() {
    	
        return courses;
    }

    public ArrayList<StudentGroup> getStudentGroups() {
    	
        return studentGroups;
    }

    public ArrayList<Classroom> getClassrooms() {
    	
        return classrooms;
    }

    public ArrayList<Laboratory> getLaboratories() {
    	
        return laboratories;
    }

    public ArrayList<ClassSession> getClassSessions() {
    	
        return classSessions;
    }
}
package cs2340.gatech.edu.lab3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robertwaters on 1/5/17.
 *
 * Information Holder - represents a single student in model
 *
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 *
 */

public class Student implements Parcelable {

    public static List<String> legalMajors = Arrays.asList("CS", "CM", "ISYE", "MATH", "EE", "CMPE", "NA");
    public static List<String> legalClassStanding = Arrays.asList("FR", "SO", "JR", "SR");
    private static int Next_Id = 0;

    private int _id;
    private String _name;
    private String _major;
    private ClassStanding _standing;

    /* **********************
     * Getters and setters
     */
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    //no setter for this.  id is a read only field
    public int getId() { return _id; }

    public String getMajor() {return _major; }
    public void setMajor(String major) { _major = major; }

    public static int findPosition(String code) {
        int i = 0;
        while (i < legalMajors.size()) {
            if (code.equals(legalMajors.get(i))) return i;
            ++i;
        }
        return 0;
    }


    /**
     * Make a new student
     * @param name      the student's name
     * @param major     the student's major
     */
    public Student(String name, String major) {
        this(name, major, ClassStanding.FRESHMAN);

    }

    public Student(String name, String major, ClassStanding cS) {
        _name = name;
        _major= major;
        _id = Student.Next_Id++;
        _standing = cS;
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new student dialog
     */
    public Student() {
        this("enter new name" , "NA");
    }

    /**
     *
     * @return the display string representation
     */
    @Override
    public String toString() {
        return _name + " " + _major + " " + _standing;
    }


    /* *********************************
     * These methods are required by the parcelable interface
     *
     */

    private Student(Parcel in) {
        _name = in.readString();
        _major = in.readString();
        _id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(_name);
         dest.writeString(_major);
         dest.writeInt(_id);
    }

    public static final Parcelable.Creator<Student> CREATOR
            = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public ClassStanding get_standing() {
        return _standing;
    }

    public void set_standing(ClassStanding cS) {
        _standing = cS;
    }
}

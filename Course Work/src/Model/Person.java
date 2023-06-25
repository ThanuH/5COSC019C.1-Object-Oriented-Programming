package Model;

import java.io.Serializable;

public class Person implements Serializable {
    //name, surName, date of birth and mobile number
    private String name;
    private String surName;
    private String dob;
    private String mobileNumber;

    public Person(String name, String surName, String dob, String mobileNumber) {
        this.name = name;
        this.surName = surName;
        this.dob = dob;
        this.mobileNumber = mobileNumber;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSurName() {

        return surName;
    }

    public void setSurName(String surName) {

        this.surName = surName;
    }

    public String getDob() {

        return dob;
    }

    public void setDob(String dob) {

        this.dob = dob;
    }

    public String getMobileNumber() {

        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {

        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return
                "\n" +"Information \n"+
                "-> Name - " + name + '\n' +
                "-> Surname - " + surName + '\n' +
                "-> Date Of Birth - " + dob + '\n' +
                "-> Mobile Number - " + mobileNumber + '\n';
    }
}

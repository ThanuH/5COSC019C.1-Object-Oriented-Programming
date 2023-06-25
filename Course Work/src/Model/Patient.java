package Model;

public class Patient extends Person {

    private String nationalIdNo;

    private String gender;

    public String getNationalIdNo() {
        return nationalIdNo;
    }

    public void setNationalIdNo(String nationalIdNo) {
        this.nationalIdNo = nationalIdNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Patient(String name, String surname, String dob, String mobileNumber, String nationalIdNo, String gender) {
        super(name, surname, dob, mobileNumber);
        this.nationalIdNo = nationalIdNo;
        this.gender = gender;
    }
}

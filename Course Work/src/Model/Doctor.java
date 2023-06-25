package Model;

import java.io.Serializable;

public class Doctor extends Person implements Serializable {
    private String medicalLicense;
    private String specialization;



    public Doctor(String name, String surName, String dob, String mobNo, String medicalLicense, String specialization) {
        super(name,surName,dob,mobNo);
        this.medicalLicense = medicalLicense;
        this.specialization = specialization;
    }


    public String getMedicalLicense() {
        return medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public  String toString() {
        return super.toString() +
                "-> Medical License No - " + medicalLicense + '\n' +
                "-> Specialization - " + specialization ;
    }
}












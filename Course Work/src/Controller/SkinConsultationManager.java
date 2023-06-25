package Controller;

import Model.Doctor;

import java.util.ArrayList;

public interface SkinConsultationManager {

    void addDoctor();

    Doctor deleteDoctor(ArrayList<Doctor> doctors, String medicalLicense);

    void sortList(ArrayList<Doctor> list);

    void writeData();

    void readData();

    void printMenu();

    void mainProgram();

}

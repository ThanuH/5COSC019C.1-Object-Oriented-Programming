package Controller;

import View.Home;
import Model.Consultation;
import Model.Doctor;
import Model.Patient;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    static WestminsterSkinConsultationManager methodManager = new WestminsterSkinConsultationManager();
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<Doctor> doctor = new ArrayList<Doctor>(10);

    public static ArrayList<Patient> patient = new ArrayList<Patient>();

    public static ArrayList<Consultation> consultation = new ArrayList<>();


    /*
    * addDoctor()
    * validates the user entered data(name,surName,dob,mobNo,medicalLicense,specialization)
    * adds the data entered to the doctor Arraylist
    */
    public void addDoctor() {
        String name = null;
        String surName = null;
        String dob = null;
        String mobNo = null;
        String medicalLicense = null;
        String specialization = null;

        while (true) {
            if (name == null) {
                System.out.print("Enter Doctor's First Name-");
                String nameInput = sc.nextLine();
                String inputName = nameInput.trim();
                    if (!inputName.matches("[a-zA-Z ]*")) {
                        System.out.println("Invalid input for First name\nName can only contain alphabetical characters");
                        continue;
                    } else {
                        name = inputName;
                    }

            }
            if (surName == null) {
                System.out.print("Enter Doctor's Surname-");
                String surNameInput = sc.nextLine();
                String inputSurName = surNameInput.trim();
                if(!surNameInput.isEmpty()) {
                    if (!inputSurName.matches("[a-zA-Z ]*")) {
                        System.out.println("Invalid input for Surname\nSurname can only contain alphabetical characters");
                        continue;
                    } else {
                        surName = inputSurName;
                    }
                }
            }
            if (mobNo == null) {
                System.out.print("Enter Doctor's mobile number-");
                String mobNoInput = sc.nextLine();
                String inputMobNo = mobNoInput.trim();
                if (Pattern.compile("^[0]\\d{9}$").matcher(inputMobNo).find()) {
                    mobNo = inputMobNo;
                } else {
                    System.out.println("Invalid input for Mobile Number\nMobile number can only contain 10 numbers from 0-9");
                    continue;
                }
            }
            if (dob == null) {
                System.out.print("Enter Doctor's date of birth (Enter as yyyy-mm-dd)-");
                String dateInput = sc.nextLine();
                LocalDate date = null;
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    date = LocalDate.parse(dateInput, dateTimeFormatter);
                    LocalDate todayDate = LocalDate.now();

                    if (todayDate.isAfter(date)) {
                        dob = dateInput;
                    } else {
                        System.out.println("Date of birth should be before " + todayDate);
                        continue;
                    }
                } catch (DateTimeParseException exception) {
                    System.out.println("Invalid input for date of birth \nPlease try entering the date in the given format dd-MM-yyyy");
                    continue;
                }
            }
            if (medicalLicense == null) {
                System.out.print("Enter Doctor's medical license number-");
                String medicalLicenseInput = sc.nextLine();
                String inputMedicalLicense = medicalLicenseInput.trim();
                if (Pattern.compile("[a-zA-Z0-9 ]").matcher(inputMedicalLicense).find()) {
                    medicalLicense = inputMedicalLicense;
                } else {
                    System.out.println("Invalid input for Medical License Number\nMedical License Number can only contain numbers from 0-9 and alphabetical characters");
                    continue;
                }
            }
            if (specialization == null) {
                System.out.print("Enter Doctor's specialization-");
                String specializationInput = sc.nextLine();
                String inputSpecialization = specializationInput.trim();
                if (!inputSpecialization.matches("[a-zA-Z ]*")) {
                    System.out.println("Invalid input for Doctor's Specialization\nDoctor's Specialization can only contain alphabetical characters");
                    continue;
                } else {
                    specialization = inputSpecialization;
                }

            }
            break;
        }
        if (isAlreadyExist(medicalLicense)){
            System.out.println("Doctor Already Exists");
        }else {
            Doctor doctorObj = new Doctor(name, surName, dob, mobNo, medicalLicense,specialization);
            doctor.add(doctorObj);
        }


    }
    public boolean isAlreadyExist(String licenseNo){
        for (Doctor doctorObj:doctor) {
            if (doctorObj.getMedicalLicense().equalsIgnoreCase(licenseNo)){
                System.out.println("Doctor Already Exists");
                return true;
            }
            break;
        }
        return false;
    }

    /*
    * deleteDoctor()
    * takes in the doctor arraylist and doctor's medicalLicense numbers as parameters
    * searches if there is a doctor with the given medicalLicense number
    * returns the doctor object
    */
    public Doctor deleteDoctor(ArrayList<Doctor> doctors, String medicalLicense) {
        for (Doctor doctor : doctors) {
            if (doctor.getMedicalLicense().equalsIgnoreCase(medicalLicense)) {
                System.out.println(doctor);
                return doctor;
            }
        }
        return null;
    }

    /*
   * sortList()
   * takes in the doctor arraylist and
   * sorts the given data according to the doctor's surname
   * displays it on the console
   */
    public void sortList(ArrayList<Doctor> list) {
        if(list.size() != 0) {
            List<Doctor> sortedList = list.stream()
                    .sorted(Comparator.comparing(Doctor::getSurName))
                    .collect(Collectors.toList());
            sortedList.forEach(System.out::println);
        }

    }

    /*
    * writeData()
    * writes all the data which is in the doctor object array list to text file
    */
    public void writeData(){
        try {
            FileOutputStream fileOutputStreamPath = new FileOutputStream("doctorInformation.txt");
            ObjectOutputStream streamOfDoctorObjects = new ObjectOutputStream(fileOutputStreamPath);
            streamOfDoctorObjects.writeObject(doctor);

            System.out.println("Doctor's data Backed Up Successfully");
            streamOfDoctorObjects.close();
             fileOutputStreamPath.close();
        } catch (IOException e) {
            System.out.println("Error Occurred While Data backing up doctor's data");
        }
    }

    /*
     * writeData()
     * writes all the data which is in the patient object array list to text file
     */
    public void writePatientData(){
        try {
            FileOutputStream fileOutputStreamPath = new FileOutputStream("patientInformation.txt");
            ObjectOutputStream streamOfPatientObjects1 = new ObjectOutputStream(fileOutputStreamPath);
            streamOfPatientObjects1.writeObject(patient);

            System.out.println("Patient's data Backed Up Successfully");
            streamOfPatientObjects1.close();
            fileOutputStreamPath.close();
        } catch (IOException e) {
            System.out.println("Error Occurred While Data backing up patient's data");
        }
    }

    /*
     * writeData()
     * writes all the data which is in the consultation object array list to text file
     */
    public void writeConsultationData(){
        try {
            FileOutputStream fileOutputStreamPath = new FileOutputStream("consultationInformation.txt");
            ObjectOutputStream streamOfConsultationObjects = new ObjectOutputStream(fileOutputStreamPath);
            streamOfConsultationObjects.writeObject(consultation);

            System.out.println("Consultation data Backed Up Successfully");
            streamOfConsultationObjects.close();
            fileOutputStreamPath.close();
        } catch (IOException e) {
            System.out.println("Error Occurred While Data backing up consultation data");
        }
    }

    /*
    * readData()
    * reads all the data written to doctorInformation.txt
    * add them back to the doctor object array list
    */
    public void readData(){
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tReading Stored Data\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPlease Wait!!");
        try {
            FileInputStream doctorFileInputStreamPath = new FileInputStream("doctorInformation.txt");
            ObjectInputStream streamOfDoctorObjects = new ObjectInputStream(doctorFileInputStreamPath);
            doctor = (ArrayList<Doctor>) streamOfDoctorObjects.readObject();
            System.out.println("Doctor's data loaded to the program successfully");
            streamOfDoctorObjects.close();
            doctorFileInputStreamPath.close();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Currently there are no previously stored doctor data to load");
        }
    }

    /*
     * readData()
     * reads all the data written to patientInformation.txt
     * add them back to the patient object array list
     */
    public void readPatientDetails(){
        try {
            FileInputStream patientFileInputStreamPath = new FileInputStream("patientInformation.txt");
            ObjectInputStream streamOfPatientObjects = new ObjectInputStream(patientFileInputStreamPath);
            patient = (ArrayList<Patient>) streamOfPatientObjects.readObject();
            System.out.println("Patient's data loaded to the program successfully");
            streamOfPatientObjects.close();
            patientFileInputStreamPath.close();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Currently there are no previously stored patient data to load");
        }
    }

    /*
     * readData()
     * reads all the data written to consultationInformation.txt
     * add them back to the consultation object array list
     */
    public void readConsultationDetails(){
        try {
            FileInputStream consultationFileInputStreamPath = new FileInputStream("consultationInformation.txt");
            ObjectInputStream streamOfConsultationObject = new ObjectInputStream(consultationFileInputStreamPath);
            consultation = (ArrayList<Consultation>) streamOfConsultationObject.readObject();
            System.out.println("Consultation data loaded to the program successfully");
            streamOfConsultationObject.close();
            consultationFileInputStreamPath.close();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Currently there are no previously stored consultation data to load");
        }
    }

    /*
     * printMenu()
     * prints the menu on the console
     */
    public void printMenu(){
        System.out.println("""
                \t\t\t\t\t\t\t\t\t\t|------------------------------------------------------------------------------------------------|
                \t\t\t\t\t\t\t\t\t\t|                       |  Welcome To WestMinster Skin Consultation Manager  |                   | 
                \t\t\t\t\t\t\t\t\t\t|                                            | MENU |                                            |
                \t\t\t\t\t\t\t\t\t\t|                                       1. Add a new doctor                                      |
                \t\t\t\t\t\t\t\t\t\t|                                       2. Delete a doctor                                       |
                \t\t\t\t\t\t\t\t\t\t|                                       3. Print the list of the doctor                         |
                \t\t\t\t\t\t\t\t\t\t|                                       4. Save in a file                                        |
                \t\t\t\t\t\t\t\t\t\t|                                       5. Launch GUI                                            |
                \t\t\t\t\t\t\t\t\t\t|                                       6. Exit Program                                          |
                \t\t\t\t\t\t\t\t\t\t|------------------------------------------------------------------------------------------------|
                """);
    }

    /*
     * mainProgram()
     * call the pre-written methods according to the user input
     * executes the accordingly
     */
    public void mainProgram(){
        methodManager.readData();
        methodManager.readPatientDetails();
        methodManager.readConsultationDetails();
        boolean mainLoop = true;
        while (mainLoop) {
            methodManager.printMenu();
            System.out.print("\n\nPlease your choice :");
            int choice = 0;
            try {
                String input = sc.nextLine();
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid Input Please Try Again\n");
                continue;
            }
            switch (choice) {
                case 1:
                    if (doctor.size() < 10) {
                        methodManager.addDoctor();
                        while (true) {
                            System.out.print("Do you want to add another doctor (Enter Y or N) : ");
                            String caseChoice = sc.nextLine();
                            if (caseChoice.equalsIgnoreCase("y")) {
                                methodManager.addDoctor();
                            } else if (caseChoice.equalsIgnoreCase("n")) {
                                break;
                            } else {
                                System.out.println("Invalid input please enter \n" +
                                        "Y if yes" +
                                        "N if no");
                            }
                        }
                    } else {
                        System.out.println("Doctor cannot be added \nMaximum number of doctor have been enrolled");
                        break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.print("Enter the doctor's Medical License that you want to delete:");
                        String medicalLicense = sc.nextLine();
                        Doctor doctorObj = methodManager.deleteDoctor(doctor, medicalLicense);
                        if (doctorObj == null) {
                            System.out.println("Invalid Medical License number\nDo you wish to try again(If Yes enter Y if not enter N)");
                        } else {
                            System.out.println("You have successfully deleted "+"Dr."+doctorObj.getName());
                            doctor.remove(doctorObj);
                            System.out.println("Remaining Number Of doctors : "+doctor.size());
                            System.out.println("\nDo you want to remove another doctor(Y|N): ");
                        }
                        String caseChoice = sc.nextLine();
                        if (caseChoice.equalsIgnoreCase("y")) {
                            continue;
                        } else if (caseChoice.equalsIgnoreCase("n")) {
                            break;
                        } else {
                            System.out.print("Invalid input please enter \n" +
                                    "Y if yes" +
                                    "N if no");
                        }
                        break;
                    }
                    break;
                case 3:
                    if(!doctor.isEmpty()){
                        methodManager.sortList(doctor);
                    }else {
                        System.out.println("No data to display currently");
                    }

                    break;
                case 4:
                    methodManager.writeData();
                    break;
                case 5:
                    Home gui = new Home();
                    break;
                case 6:
                    mainLoop = false;
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tThank you for using WestMinster Skin Consultation Manager");
                    break;
                default:
                    System.out.println("Invalid Input Please try again");
                    break;


            }


        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  Program Terminated");



    }









}

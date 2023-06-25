package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Consultation implements Serializable {
    private String doctorName;
    private String doctorSpecialization;
    private String patientName;
    private String patientId;
    private LocalDate date;
    private ArrayList<LocalTime> appointmentTime;
    private String notes;
    private String consultationFee;


     public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<LocalTime> getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(ArrayList<LocalTime> appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }

    public Consultation(String doctorName, String doctorSpecialization, String patientName, String patientId, LocalDate date, ArrayList<LocalTime> appointmentTime, String notes, String consultationFee) {
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.patientName = patientName;
        this.patientId = patientId;
        this.date = date;
        this.appointmentTime = appointmentTime;
        this.notes = notes;
        this.consultationFee = consultationFee;
    }
}

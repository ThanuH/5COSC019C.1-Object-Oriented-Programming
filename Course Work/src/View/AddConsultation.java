package View;

import Controller.WestminsterSkinConsultationManager;
import Model.Consultation;
import Model.Doctor;
import Model.Patient;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;

import static Controller.WestminsterSkinConsultationManager.*;
import static Controller.WestminsterSkinConsultationManager.consultation;


public class AddConsultation extends JFrame {
    WestminsterSkinConsultationManager methodManager = new WestminsterSkinConsultationManager();
    private JLabel windowHeading;
    private JLabel patientNicLabel;
    private JLabel patientNotesLabel;
    private JComboBox patientNicDropDown;
    private JLabel doctorDropDownLabel;
    private JComboBox doctorDropDown;
    private JLabel appDatedropDownLabel;
    private JComboBox appDatedropDown;
    private JLabel appTimeDropDownLabel;
    private JComboBox appTimeDropDown;
    private JButton checkAvailability;
    private JLabel timeDurationDropDownLabel;
    private JComboBox timeDurationDropDown;
    private JButton bookButton;

    private JTextField patientNote;


    private String uniqueDoctorName;
    private boolean isAssigned = false;
    static byte[] encrypted;

    public AddConsultation() {

        ArrayList<LocalTime> timeList = new ArrayList<>();
        String[] patientIdDropDownItems = new String[patient.size()];
        String[] doctorDropDownItems = new String[doctor.size()];
        String[] appDateDropDownItems = new String[10];
        String[] timeDurationDropDownItems = {"1 Hour", "2 Hours", "3 Hours"};

        for (int i = 0; i < doctor.size(); i++) {
            doctorDropDownItems[i] = doctor.get(i).getName();
        }

        for (int i = 0; i< patient.size(); i++){
            patientIdDropDownItems[i] = patient.get(i).getNationalIdNo();
        }



        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 10; i++) {
            String date = dtf.format(now);
            appDateDropDownItems[i] = date;
            now = now.plusDays(1);
        }


        windowHeading = new JLabel ("Add Consultation");
        windowHeading.setBounds (185, 35, 100, 25);
        add (windowHeading);
        
        doctorDropDown = new JComboBox (doctorDropDownItems);
        doctorDropDownLabel = new JLabel ("Doctor Name :");
        doctorDropDownLabel.setBounds (50, 100, 100, 25);
        doctorDropDown.setBounds (145, 100, 300, 25);
        add (doctorDropDownLabel);
        add (doctorDropDown);

        appDatedropDownLabel = new JLabel ("Appointment Date:");
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);
        DatePicker appDatePicker = new DatePicker(dateSettings);
        appDatedropDownLabel.setBounds (30, 140, 100, 25);
        appDatePicker.setBounds (145, 140, 300, 25);
        add (appDatedropDownLabel);
        add (appDatePicker);

        appTimeDropDownLabel = new JLabel ("Appointment Time:");
        TimePickerSettings timePickerSettings = new TimePickerSettings();
        timePickerSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.OneHour, LocalTime.parse("15:00"),LocalTime.parse("20:00"));
        timePickerSettings.setAllowKeyboardEditing(false);
        timePickerSettings.use24HourClockFormat();
        TimePicker appTimePicker = new TimePicker(timePickerSettings);
        appTimeDropDownLabel.setBounds (25, 180, 120, 25);
        appTimePicker.setBounds (145, 180, 300, 25);
        add (appTimeDropDownLabel);
        add (appTimePicker);

        timeDurationDropDownLabel = new JLabel ("Time Duration:");
        timeDurationDropDown = new JComboBox (timeDurationDropDownItems);
        timeDurationDropDownLabel.setBounds (45, 220, 100, 25);
        timeDurationDropDown.setBounds (145, 220, 300, 25);
        add (timeDurationDropDownLabel);
        add (timeDurationDropDown);

        checkAvailability = new JButton ("Check Availability");
        checkAvailability.setBounds (115, 250, 270, 30);
        checkAvailability.addActionListener(e -> {
            LocalDate appointmentDate = appDatePicker.getDate();
            LocalTime appointmentTime = appTimePicker.getTime();
            if (appointmentTime.equals(null) || appointmentDate.equals(null)){
                JOptionPane.showMessageDialog(null,"Please Select a Date and time for appointment");
            } else if (appointmentDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null,"Appointment Date should be after " + LocalDate.now());
            }

            String selectedTimeDuration = (String) timeDurationDropDown.getSelectedItem();
            if (selectedTimeDuration.equals("3 Hours")){
                timeList.add(appointmentTime);
                timeList.add(appointmentTime.plusHours(1));
                timeList.add(appointmentTime.plusHours(2));
            } else if (selectedTimeDuration.equals("2 Hours")) {
                timeList.add(appointmentTime);
                timeList.add(appointmentTime.plusHours(1));
            } else if (selectedTimeDuration.equals("1 Hour")) {
                timeList.add(appointmentTime);
            }
            checkDoctorAvailability(appointmentDate,timeList,(String) doctorDropDown.getSelectedItem());
            if(isAssigned){
                JOptionPane.showMessageDialog(null,"Doctor is Available");
            }else{
                JOptionPane.showMessageDialog(null,"Doctors are unavailable");
            }
        });
        add (checkAvailability);



        patientNicLabel = new JLabel ("Patient NIC :");
        patientNicDropDown = new JComboBox (patientIdDropDownItems);
        patientNicLabel.setBounds (60, 290, 100, 25);
        patientNicDropDown.setBounds (145, 290, 300, 25);
        add (patientNicLabel);
        add (patientNicDropDown);

        patientNotesLabel = new JLabel ("Patient Notes :");
        patientNotesLabel.setBounds (60, 330, 100, 25);
        patientNote = new JTextField(1);
        patientNote.setBounds(145, 330, 300, 60);
        add (patientNotesLabel);
        add (patientNote);

        bookButton = new JButton ("Book Consultation");
        bookButton.addActionListener(e -> {
            String selectedTimeDuration = (String) timeDurationDropDown.getSelectedItem();
            String selectedPatientNic = (String) patientNicDropDown.getSelectedItem();
            String patientNotes = patientNote.getText();
            LocalDate pickedDate = appDatePicker.getDate();
            LocalTime pickedTime = appTimePicker.getTime();
            //doctorName,patientNic,date,time ,note,duration
            if(patientNotes.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null,"Please Enter A Note");
            }else{
                addConsultation(uniqueDoctorName,selectedPatientNic,pickedDate,pickedTime,patientNotes,selectedTimeDuration);
                JOptionPane.showMessageDialog(null,"Consultation Added Successfully");
                dispose();

            }

        });
        bookButton.setBounds (115, 395, 270, 30);
        add (bookButton);

        setPreferredSize (new Dimension (477, 484));
        setLayout (null);
        setTitle("Add Consultation");
        setSize(477,484);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    public void addConsultation(String doctorName,String patientNic,LocalDate date,LocalTime time ,String note, String duration)  {
        String specialization = null;
        String patientName = null;
        ArrayList<LocalTime> pickedTimeSlots = new ArrayList<>();
        for (Doctor doctor:doctor) {
            if (doctor.getName().equalsIgnoreCase(doctorName))
                specialization = doctor.getSpecialization();
        }
        for (Patient patient:patient) {
            if(patient.getNationalIdNo().equalsIgnoreCase(patientNic))
                patientName = patient.getName();
        }
        if(duration.equalsIgnoreCase("1 Hour")){
            pickedTimeSlots.add(time);
        } else if (duration.equalsIgnoreCase("2 Hours")) {
            pickedTimeSlots.add(time);
            pickedTimeSlots.add(time.plusHours(1));
        } else if (duration.equalsIgnoreCase("3 Hours")) {
            pickedTimeSlots.add(time);
            pickedTimeSlots.add(time.plusHours(1));
            pickedTimeSlots.add(time.plusHours(2));
        }

        String consultationFee = calculateFee(duration,consultation,patientNic);
        String encryptedNote = encryptNote(note);
        Consultation consultationObj = new Consultation(doctorName,specialization,patientName,patientNic,date,pickedTimeSlots,encryptedNote,consultationFee);
        WestminsterSkinConsultationManager.consultation.add(consultationObj);

    }
    public String calculateFee(String duration,ArrayList<Consultation> consultationArrayList,String patientId){
        String consultationFee = null;
        if (checkCustomer(consultationArrayList,patientId)){
            if(duration.equalsIgnoreCase("1 Hour")){
                consultationFee = "£25";
            } else if (duration.equalsIgnoreCase("2 Hours")) {
                consultationFee = "£50";
            }else if (duration.equalsIgnoreCase("3 Hours")) {
                consultationFee = "£75";
            }
        }else {
            if(duration.equalsIgnoreCase("1 Hour")){
                consultationFee = "£15";
            } else if (duration.equalsIgnoreCase("2 Hours")) {
                consultationFee = "£30";
            }else if (duration.equalsIgnoreCase("3 Hours")) {
                consultationFee = "£45";
            }

        }

        return consultationFee;
    }

    public boolean checkCustomer(ArrayList<Consultation> consultationArrayList,String patientId){
        boolean isFirstTime = false;
        for (Consultation consultation:consultationArrayList) {
            if(consultation.getPatientId().equalsIgnoreCase(patientId))
                isFirstTime =true;
        }
        return isFirstTime;
    }


//    public void checkDoctorAvailability(LocalDate date ,ArrayList<LocalTime> timeSlots,String doctorName){
//        uniqueDoctorName = doctorName;
//        for (Consultation consultation:WestminsterSkinConsultationManager.consultation) {
//            if (date.isEqual(consultation.getDate()) &&
//                    doctorName.equalsIgnoreCase(consultation.getDoctorName()) &&
//                    timeSlots.contains(consultation.getAppointmentTime())) {
//                for (Doctor doctorObj : doctor) {
//                    if (!doctorName.equals(doctorObj.getName())) {
//                        JOptionPane.showMessageDialog(null, "Dr " + doctorName + " is unavailable for the chosen time\n" + " Assigning Another Doctor Please Wait ");
//                        uniqueDoctorName = doctorObj.getName();
//                        checkDoctorAvailability(date, timeSlots, uniqueDoctorName);
//                        break;
//                    }
//                }
//                JOptionPane.showMessageDialog(null, "New doctor is assigned\n" + "Dr. " +uniqueDoctorName);
//
//                break;
//            }
//        }
//        isAssigned =true;
//    }
public void checkDoctorAvailability(LocalDate date ,ArrayList<LocalTime> timeSlots,String doctorName){
    uniqueDoctorName = doctorName;
    for (Consultation consultation:WestminsterSkinConsultationManager.consultation) {
        if (date.isEqual(consultation.getDate()) &&
                uniqueDoctorName.equalsIgnoreCase(consultation.getDoctorName()) &&
                timeSlots.retainAll(consultation.getAppointmentTime()
                )
        ) {
            for (Doctor doctorObj : doctor) {
                if (!doctorName.equals(doctorObj.getName())) {
                    JOptionPane.showMessageDialog(null, "Dr " + doctorName + " is unavailable for the chosen time\n" + " Assigning Another Doctor Please Wait ");
                    uniqueDoctorName = doctorObj.getName();
                    checkDoctorAvailability(date, timeSlots, uniqueDoctorName);
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, "New doctor is assigned\n" + "Dr. " +uniqueDoctorName);
            break;
        }
    }
    isAssigned =true;
}

    //https://www.tutorialspoint.com/java_cryptography/java_cryptography_encrypting_data.html
    //https://stackoverflow.com/questions/24968466/how-to-use-cipher-on-this-method-to-decrypt-a-string
    public static String encryptNote(String note) {
        String encryptedText = null;
        try {

            String Input = note;
            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(Input.getBytes());
            encryptedText = DatatypeConverter.printBase64Binary(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    public static String decryptNote(String noteInput) {
        String decryptedText = null;
        try {
            String Input = noteInput;
            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            encrypted = DatatypeConverter.parseBase64Binary(Input);
            decryptedText = new String(cipher.doFinal(encrypted));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

}

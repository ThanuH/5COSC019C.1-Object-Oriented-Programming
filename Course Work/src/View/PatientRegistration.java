package View;

import Model.Patient;
import Controller.WestminsterSkinConsultationManager;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class PatientRegistration extends JFrame {

    private JLabel heading;
    private JLabel patientNicLabel;
    private JTextField patientNicInput;
    private JLabel patientNameLabel;
    private JTextField patientNameInput;
    private JLabel patientSurnameLabel;
    private JTextField patientSurnameInput;
    private JLabel patientMobileLabel;
    private JTextField patientMobNoInput;
    private JLabel patientGenderLabel;
    private JTextField patientGenderInput;
    private JLabel patientDobLabel;
    private JLabel nicError;
    private JLabel nameError;
    private JLabel surNameError;
    private JLabel mobNoError;
    private JLabel genderError;
    private JLabel dobError;
    private JButton registerButton;

    public PatientRegistration(){


        heading = new JLabel ("Patient Registration");
        add (heading);
        heading.setBounds (190, 20, 220, 65);

        patientNicLabel = new JLabel ("Patient NIC :");
        patientNicInput = new JTextField (5);
        nicError = new JLabel ("");
        patientNicInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(nicValidate(patientNicInput.getText())){
                    patientNicInput.setBackground(Color.green);
                    nicError.setText("☑️");
                }else {
                    nicError.setText("❌");
                    patientNicInput.setBackground(Color.red);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
            }
            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        }
        );

        add (patientNicLabel);
        add (patientNicInput);
        add (nicError);
        patientNicLabel.setBounds (60, 115, 95, 30);
        patientNicInput.setBounds (165, 115, 255, 30);
        nicError.setBounds (425, 115, 50, 30);

        patientNameLabel = new JLabel ("Patient Name : ");
        patientNameInput = new JTextField (5);
        nameError = new JLabel ("");
        patientNameInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(nameValidate(patientNameInput.getText())){
                    patientNameInput.setBackground(Color.green);
                    nameError.setText("☑️");
                }else{
                    nameError.setText("❌");
                    patientNameInput.setBackground(Color.red);
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        add (patientNameLabel);
        add (patientNameInput);
        add (nameError);
        patientNameLabel.setBounds (45, 155, 100, 30);
        patientNameInput.setBounds (165, 160, 255, 30);
        nameError.setBounds (425, 160, 50, 30);

        patientSurnameLabel = new JLabel ("Patient Surname : ");
        patientSurnameInput = new JTextField (5);
        surNameError = new JLabel ("");
        patientSurnameInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(nameValidate(patientSurnameInput.getText())){
                    patientSurnameInput.setBackground(Color.green);
                    surNameError.setText("☑️");
                }else{
                    surNameError.setText("❌");
                    patientSurnameInput.setBackground(Color.red);
                }

            }
            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        add (patientSurnameLabel);
        add (patientSurnameInput);
        add (surNameError);
        patientSurnameLabel.setBounds (30, 200, 125, 30);
        patientSurnameInput.setBounds (165, 205, 255, 30);
        surNameError.setBounds (425, 205, 50, 30);

        patientMobileLabel = new JLabel ("Patient Mob No :");
        patientMobNoInput = new JTextField (5);
        mobNoError = new JLabel ("");
        patientMobNoInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(mobNoValidate(patientMobNoInput.getText())){
                    patientMobNoInput.setBackground(Color.green);
                    mobNoError.setText("☑️");
                }else{
                    mobNoError.setText("❌");
                    patientMobNoInput.setBackground(Color.red);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        add (patientMobileLabel);
        add (patientMobNoInput);
        add (mobNoError);
        patientMobileLabel.setBounds (40, 245, 100, 30);
        patientMobNoInput.setBounds (165, 250, 255, 30);
        mobNoError.setBounds (425, 250, 50, 30);

        patientGenderLabel = new JLabel ("Patient Gender :");
        patientGenderInput = new JTextField (5);
        genderError = new JLabel ("");
        patientGenderInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(patientGenderInput.getText().equalsIgnoreCase("male") || patientGenderInput.getText().equalsIgnoreCase("female")){
                    patientGenderInput.setBackground(Color.green);
                    genderError.setText("☑️");
                }else{
                    genderError.setText("❌");
                    patientGenderInput.setBackground(Color.red);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        add (patientGenderLabel);
        add (patientGenderInput);
        add (genderError);
        patientGenderLabel.setBounds (40, 290, 100, 30);
        patientGenderInput.setBounds (165, 295, 255, 30);
        genderError.setBounds (425, 295, 50, 30);

        patientDobLabel = new JLabel ("Patient DOB :");
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);
        DatePicker datePicker = new DatePicker(dateSettings);

        dobError = new JLabel ("");
        add (datePicker);
        add (patientDobLabel);
        add (dobError);
        datePicker.setBounds (165, 340, 255, 30);
        patientDobLabel.setBounds (55, 340, 100, 30);
        dobError.setBounds (425, 340, 55, 30);

        registerButton = new JButton ("Register Patient");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String name, String surname, String dob, String mobileNumber, String nationalIdNo, String gender
                if (patientNameInput.getBackground().equals(Color.red)
                        || patientSurnameInput.getBackground().equals(Color.red)
                        || patientMobNoInput.getBackground().equals(Color.red)
                        || patientNicInput.getBackground().equals(Color.red)
                        || patientGenderInput.getBackground().equals(Color.red)
                        || validateBirthDate(datePicker.getDate())) {
                    JOptionPane.showMessageDialog(null, "Input mismatch please check the entered information");
                } else {
                    //try {
                        Patient patient = new Patient(patientNameInput.getText(),
                                patientSurnameInput.getText(),
                                datePicker.getDate().toString(),
                                patientMobNoInput.getText(),
                                patientNicInput.getText(),
                                patientGenderInput.getText());

                        WestminsterSkinConsultationManager.patient.add(patient);
                        JOptionPane.showMessageDialog(null, "Patient Added Successfully");
                        dispose();
//                    } catch (Exception error) {
//                        JOptionPane.showMessageDialog(null, "Input mismatch please check the entered information");
//                    }

                }
            }
        });
        add (registerButton);
        registerButton.setBounds (135, 400, 240, 45);

        setPreferredSize (new Dimension (491, 517));
        setLayout (null);
        setTitle("Patient Registration");
        setSize(491, 517);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public static boolean nicValidate(String nic){
        if(Pattern.compile("[0-9]{9}[Vv]$").matcher(nic).find() || Pattern.compile("^[0-9]{12}$").matcher(nic).find())
            return true;
        else
            return false;
    }

    public static boolean nameValidate(String name) {
        if (Pattern.compile("[A-Za-z ]+$").matcher(name).find()) {
            return true;
        } else
            return false;
    }

    public static boolean mobNoValidate(String mobNo){
        if (Pattern.compile("^[0]\\d{9}$").matcher(mobNo).find()) {
            return true;
        }else
            return false;
    }

    public static boolean validateBirthDate(LocalDate dob){
        if(dob.isBefore(LocalDate.now())){
            return false;
        }else
            return true;
    }
}

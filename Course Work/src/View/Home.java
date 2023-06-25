package View;

import Controller.WestminsterSkinConsultationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static Controller.WestminsterSkinConsultationManager.consultation;
import static Controller.WestminsterSkinConsultationManager.patient;


public class Home extends JFrame {

WestminsterSkinConsultationManager manger = new WestminsterSkinConsultationManager();
    public Home(){


        JFrame frame = new JFrame();
        JLabel label = new JLabel();

        frame.setSize(900,520);
        frame.setLayout(null);

        label.setText("Welcome To WestMinster Consultation Manager");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        label.setBounds(250, 70, 1000, 20);
        label.setForeground(Color.WHITE);
        frame.add(label);

        JButton viewDoctorsButton = new JButton("View Doctors");
        viewDoctorsButton.setBounds(250,120,430,40);
        viewDoctorsButton.setBackground(Color.lightGray);
        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                new ViewDoctors();
            }
        });
        frame.add(viewDoctorsButton);

        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds(250,170,430,40);
        addPatientButton.setBackground(Color.lightGray);
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new PatientRegistration();
            }
        });
        frame.add(addPatientButton);

        JButton addConsultationButton = new JButton("Add Consultation");
        addConsultationButton.setBounds(250,220,430,40);
        addConsultationButton.setBackground(Color.lightGray);
        addConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddConsultation addConsultation = new AddConsultation();
            }
        });
        frame.add(addConsultationButton);

        JButton viewConsultationButton = new JButton("View Consultation");
        viewConsultationButton.setBounds(250,270,430,40);
        viewConsultationButton.setBackground(Color.lightGray);
        viewConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewConsultation();
            }
        });
        frame.add(viewConsultationButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(650,420,200,40);
        exitButton.setBackground(Color.lightGray);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manger.writePatientData();
                manger.writeConsultationData();
                frame.dispose();
            }
        });
        frame.add(exitButton);


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("WestMinster Skin Westminster Skin Consultation Manager");
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setVisible(true);
        frame.setResizable(false);

    }




}

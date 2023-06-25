package View;

import Model.Doctor;
import Controller.WestminsterSkinConsultationManager;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class ViewDoctors extends JFrame {

    private JTable table;



    public  ViewDoctors(){
        String[] columnNames = {"Name", "Surname", "DOB", "Mobile number", "Licence Number", "Specialization"};
        Object[][] tableData = new Object[WestminsterSkinConsultationManager.doctor.size()][6];
        int index = 0;
        for (Doctor doctorObj : WestminsterSkinConsultationManager.doctor){
            tableData[index][0] = doctorObj.getName();
            tableData[index][1] = doctorObj.getSurName();
            tableData[index][2] = doctorObj.getDob();
            tableData[index][3] = doctorObj.getMobileNumber();
            tableData[index][4] = doctorObj.getMedicalLicense();
            tableData[index][5] = doctorObj.getSpecialization();
            index++;
        }
        DefaultTableModel model = new DefaultTableModel(tableData,columnNames);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,100,700,250);
        table.setDefaultEditor(Object.class, null);



        getContentPane().setBackground(Color.darkGray);
        setResizable(true);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        add(scrollPane,BorderLayout.CENTER);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900,520);


    }



}

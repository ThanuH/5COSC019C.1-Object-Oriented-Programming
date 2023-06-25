package View;

import Controller.WestminsterSkinConsultationManager;
import Model.Consultation;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ViewConsultation extends JFrame {
    private JTable table;
    private String secretKey = "1abc26262htmal02";
    public ViewConsultation(){
        String[] columnNames = {"Doctor's Name", "Specialization", "Patient's Name", "Patient's Nic", "Date", "Reserved Time","Note","Consultation Fee"};
        Object[][] tableData = new Object[WestminsterSkinConsultationManager.consultation.size()][8];
        int index = 0;
        for (Consultation consultationObj: WestminsterSkinConsultationManager.consultation) {
            tableData[index][0] = consultationObj.getDoctorName();
            tableData[index][1] = consultationObj.getDoctorSpecialization();
            tableData[index][2] = consultationObj.getPatientName();
            tableData[index][3] = consultationObj.getPatientId();
            tableData[index][4] = consultationObj.getDate();
            tableData[index][5] = consultationObj.getAppointmentTime();
            String decryptedNote = AddConsultation.decryptNote(consultationObj.getNotes());
            tableData[index][6] = decryptedNote;
            tableData[index][7] = consultationObj.getConsultationFee();
            index++;
        }
        DefaultTableModel model = new DefaultTableModel(tableData,columnNames);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,100,1000,250);
        table.setDefaultEditor(Object.class, null);



        getContentPane().setBackground(Color.darkGray);
        setResizable(true);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        add(scrollPane,BorderLayout.CENTER);
        setLayout(null);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,520);
    }

    public static String decrypt(String encryptedString, String deCryptKey) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(deCryptKey.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedString)));
    }
}

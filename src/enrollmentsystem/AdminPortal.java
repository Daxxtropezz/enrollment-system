/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollmentsystem;

import java.awt.*; // Desktop, Graphics, Image
import java.io.*; //File,FileInputStream,IOException
import javax.swing.*; // uses ImageIcon, JFrame, JLabel, JOptionPane
import java.sql.*; // uses Connection, DriverManager, PreparedStatement, ResultSet, ResultSetMetaData, SQLException
import java.util.*; // uses Vector
import java.util.logging.*; // Level, Logger
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sevhe
 */
public class AdminPortal extends javax.swing.JFrame {

    /**
     * Creates new form AdminPortal
     */
    private static final String URL = "jdbc:mysql://localhost:3306/enrollmentsystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "@theHouseof25";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rset = null;
    int q, i, enumb, deleteItem;
    DefaultTableModel RecordTable;
    ResultSetMetaData rsmData;
    Vector columnData;

    public AdminPortal() {
        String[] link
                = {"/Images/jcvhsHlLogin.jpg",
                    "/Images/NA.jpg",
                    "/Images/imageInsertHere.jpg"};

        initComponents();
        imageCaller();
        imageScale(link[2], imageDisplay);
        imageScale(link[1], imgT);
    }

    public void imageScale(String link, JLabel label) {
        ImageIcon iconLOGO1 = new ImageIcon("C:/Users/daxxtropezz/Documents/NetBeansProjects/EnrollmentSystem/src/enrollmentsystem" + link);
        Image imgLOGO1 = iconLOGO1.getImage();
        Image imgScaleLOGO1 = imgLOGO1.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIconLOGO1 = new ImageIcon(imgScaleLOGO1);
        label.setIcon(scaledIconLOGO1);
    }

    public void imageCaller() {
        String[] link
                = {"/Images/jcvhsHlLogin.jpg",
                    "/Images/newLogo.png",
                    "/Images/imageInsertHere.jpg"};

        imageScale(link[1], logo);
    }

    public JTextField imgSetImgForm(JTextField setFile) {
        String filename = setFile.getText();
        ImageIcon icon = new ImageIcon(filename);
        Image imgScale = icon.getImage().getScaledInstance(imageDisplay.getWidth(), imageDisplay.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        imageDisplay.setIcon(scaledIcon);

        return setFile;
    }

    public JTextField imgSetProfile(JTextField setFile) {
        String filename = setFile.getText();
        ImageIcon icon = new ImageIcon(filename);
        Image imgScale = icon.getImage().getScaledInstance(imgT.getWidth(), imgT.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        imgT.setIcon(scaledIcon);

        return setFile;
    }

    public JTextField imgSetProfileScale(JTextField setFile) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
            setFile.setText(filename);
            ImageIcon icon = new ImageIcon(filename);
            Image imgScale = icon.getImage().getScaledInstance(imgT.getWidth(), imgT.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            imgT.setIcon(scaledIcon);
        } catch (Exception e) {
        }
        return setFile;
    }

    public void enrollee() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM enrollee");

            rset = ps.executeQuery();
            rsmData = rset.getMetaData();

            q = rsmData.getColumnCount();

            RecordTable = (DefaultTableModel) enrolleeTable.getModel();
            RecordTable.setRowCount(0);

            while (rset.next()) {
                columnData = new Vector();

                for (i = 1; i <= q; i++) {
                    columnData.add(rset.getString("e_num"));
                    columnData.add(rset.getString("e_stat"));
                    columnData.add(rset.getString("e_fname"));
                    columnData.add(rset.getString("e_lname"));
                    columnData.add(rset.getString("e_minit"));
                    columnData.add(rset.getString("e_sex"));
                    columnData.add(rset.getString("e_email"));
                    columnData.add(rset.getString("e_address"));
                    columnData.add(rset.getString("e_bdate"));
                    columnData.add(rset.getString("e_religion"));
                    columnData.add(rset.getString("e_nationality"));
                    columnData.add(rset.getString("e_civilstatus"));
                    columnData.add(rset.getString("e_contactnum"));
                    columnData.add(rset.getString("e_guardian"));
                    columnData.add(rset.getString("e_guardiannum"));
                    columnData.add(rset.getString("sec_code"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void forms() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM requirements");

            rset = ps.executeQuery();
            rsmData = rset.getMetaData();

            q = rsmData.getColumnCount();

            RecordTable = (DefaultTableModel) formTable.getModel();
            RecordTable.setRowCount(0);

            while (rset.next()) {
                columnData = new Vector();

                for (i = 1; i <= q; i++) {
                    columnData.add(rset.getString("req_ID"));
                    columnData.add(rset.getString("ReportCard"));
                    columnData.add(rset.getString("Form137"));
                    columnData.add(rset.getString("BirthCertificate"));
                    columnData.add(rset.getString("GoodMoral"));
                    columnData.add(rset.getString("TranscriptOfRecords"));
                    columnData.add(rset.getString("e_num"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void section() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM section WHERE sec_code != 'N/A'");

            rset = ps.executeQuery();
            rsmData = rset.getMetaData();

            q = rsmData.getColumnCount();

            RecordTable = (DefaultTableModel) sectionTable.getModel();
            RecordTable.setRowCount(0);

            while (rset.next()) {
                columnData = new Vector();

                for (i = 0; i <= q; i++) {
                    columnData.add(rset.getString("sec_code"));
                    columnData.add(rset.getString("sec_name"));
                    columnData.add(rset.getString("sec_adviser"));
                    columnData.add(rset.getString("sec_schoolyear"));
                    columnData.add(rset.getString("t_num"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void schedule() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM schedule WHERE s_code != 2021000");

            rset = ps.executeQuery();
            rsmData = rset.getMetaData();

            q = rsmData.getColumnCount();

            RecordTable = (DefaultTableModel) schedTable.getModel();
            RecordTable.setRowCount(0);

            while (rset.next()) {
                columnData = new Vector();

                for (i = 1; i <= q; i++) {
                    columnData.add(rset.getString("s_code"));
                    columnData.add(rset.getString("s_subject"));
                    columnData.add(rset.getString("s_day"));
                    columnData.add(rset.getString("s_starttime"));
                    columnData.add(rset.getString("s_endtime"));
                    columnData.add(rset.getString("s_teacher"));
                    columnData.add(rset.getString("sec_code"));
                    columnData.add(rset.getString("t_num"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void teachers() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM teacher WHERE t_num != 6202100");

            rset = ps.executeQuery();
            rsmData = rset.getMetaData();

            q = rsmData.getColumnCount();

            RecordTable = (DefaultTableModel) teacherTable.getModel();
            RecordTable.setRowCount(0);

            while (rset.next()) {
                columnData = new Vector();

                for (i = 1; i <= q; i++) {
                    columnData.add(rset.getString("t_num"));
                    columnData.add(rset.getString("t_name"));
                    columnData.add(rset.getString("t_email"));
                    columnData.add(rset.getString("t_contact"));
                    columnData.add(rset.getString("t_sex"));
                    columnData.add(rset.getString("t_bdate"));
                    columnData.add(rset.getString("t_img"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminPanel = new javax.swing.JPanel();
        adminTabbedPane = new javax.swing.JTabbedPane();
        userPanel = new javax.swing.JPanel();
        enrolleeScrollPane = new javax.swing.JScrollPane();
        enrolleeTable = new javax.swing.JTable();
        enrolleeFunc = new javax.swing.JPanel();
        lblFName = new javax.swing.JLabel();
        lblLName = new javax.swing.JLabel();
        lblMI = new javax.swing.JLabel();
        lblSex = new javax.swing.JLabel();
        lblBDate = new javax.swing.JLabel();
        lblRel = new javax.swing.JLabel();
        lblCStat = new javax.swing.JLabel();
        lblNum = new javax.swing.JLabel();
        FNametxtField = new javax.swing.JTextField();
        LNametxtField = new javax.swing.JTextField();
        MItxtField = new javax.swing.JTextField();
        BDatetxtField = new javax.swing.JTextField();
        ReljTextField10 = new javax.swing.JTextField();
        NumtxtField = new javax.swing.JTextField();
        StatsCBox = new javax.swing.JComboBox<>();
        lblID = new javax.swing.JLabel();
        IDtxtField = new javax.swing.JTextField();
        lblStat = new javax.swing.JLabel();
        lblSec = new javax.swing.JLabel();
        clrButtonEnrollee = new javax.swing.JButton();
        delButtonEnrollee = new javax.swing.JButton();
        updButtonEnrollee = new javax.swing.JButton();
        sexCBox = new javax.swing.JComboBox<>();
        cStatCBox = new javax.swing.JComboBox<>();
        secCBox = new javax.swing.JComboBox<>();
        lblEMail = new javax.swing.JLabel();
        lblAddr = new javax.swing.JLabel();
        lblGuard = new javax.swing.JLabel();
        lblGNum = new javax.swing.JLabel();
        EmailtxtField = new javax.swing.JTextField();
        GuardtField = new javax.swing.JTextField();
        GNumtxtField = new javax.swing.JTextField();
        lblNat = new javax.swing.JLabel();
        NatltxtField = new javax.swing.JTextField();
        addressScrollPane = new javax.swing.JScrollPane();
        addTxtField = new javax.swing.JTextArea();
        formPanel = new javax.swing.JPanel();
        imgPanel = new javax.swing.JPanel();
        openfile = new javax.swing.JButton();
        imageDisplay = new javax.swing.JLabel();
        formEditPanel = new javax.swing.JPanel();
        insButtonForm = new javax.swing.JButton();
        updButtonForm = new javax.swing.JButton();
        delButtonForm = new javax.swing.JButton();
        clrButtonForm = new javax.swing.JButton();
        rcSel = new javax.swing.JButton();
        f137Sel = new javax.swing.JButton();
        bcSel = new javax.swing.JButton();
        gmSel = new javax.swing.JButton();
        torSel = new javax.swing.JButton();
        lblIDForm = new javax.swing.JLabel();
        txtFormID = new javax.swing.JTextField();
        txtFormRC = new javax.swing.JTextField();
        lblRCForm = new javax.swing.JLabel();
        txtFormBC = new javax.swing.JTextField();
        lblBCForm = new javax.swing.JLabel();
        txtFormF137 = new javax.swing.JTextField();
        lblF137Form = new javax.swing.JLabel();
        txtFormGM = new javax.swing.JTextField();
        lblGMForm = new javax.swing.JLabel();
        txtFormTOR = new javax.swing.JTextField();
        lblTORForm = new javax.swing.JLabel();
        txtFormEID = new javax.swing.JTextField();
        lblEIDForm = new javax.swing.JLabel();
        formScrollPane = new javax.swing.JScrollPane();
        formTable = new javax.swing.JTable();
        subPanel = new javax.swing.JPanel();
        secScrollPane = new javax.swing.JScrollPane();
        sectionTable = new javax.swing.JTable();
        updButtonSec = new javax.swing.JButton();
        lblSchYr = new javax.swing.JLabel();
        lblSecCode = new javax.swing.JLabel();
        lblSecName = new javax.swing.JLabel();
        lblSecAdv = new javax.swing.JLabel();
        txtSchYr = new javax.swing.JTextField();
        txtSecCode = new javax.swing.JTextField();
        txtSecName = new javax.swing.JTextField();
        txtSecAdv = new javax.swing.JTextField();
        clrButtonSec = new javax.swing.JButton();
        txtTID = new javax.swing.JTextField();
        lblSchYr1 = new javax.swing.JLabel();
        schedPanel = new javax.swing.JPanel();
        schedScrollPane = new javax.swing.JScrollPane();
        schedTable = new javax.swing.JTable();
        updButtonSch = new javax.swing.JButton();
        clrButtonSch = new javax.swing.JButton();
        lblSchCode = new javax.swing.JLabel();
        txtSchCode = new javax.swing.JTextField();
        lblSchSub = new javax.swing.JLabel();
        txtSchSub = new javax.swing.JTextField();
        lblSchDay = new javax.swing.JLabel();
        txtSchDay = new javax.swing.JTextField();
        txtSchST = new javax.swing.JTextField();
        txtSchET = new javax.swing.JTextField();
        txtSchTch = new javax.swing.JTextField();
        lblSchTeacher = new javax.swing.JLabel();
        lblSchETime = new javax.swing.JLabel();
        lblSchSTime = new javax.swing.JLabel();
        txtSchSecCode = new javax.swing.JTextField();
        lblSchSecCode = new javax.swing.JLabel();
        lblSchYr2 = new javax.swing.JLabel();
        schedTeachID = new javax.swing.JTextField();
        teacherPanel = new javax.swing.JPanel();
        teachScrollPane = new javax.swing.JScrollPane();
        teacherTable = new javax.swing.JTable();
        txtID = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        txtMail = new javax.swing.JLabel();
        txtCNum = new javax.swing.JLabel();
        tInsMail = new javax.swing.JTextField();
        tInsID = new javax.swing.JTextField();
        tInsName = new javax.swing.JTextField();
        tInsCNum = new javax.swing.JTextField();
        tInsBDate = new javax.swing.JTextField();
        tInsILink = new javax.swing.JTextField();
        txtSex = new javax.swing.JLabel();
        txtBdate = new javax.swing.JLabel();
        txtImgLink = new javax.swing.JLabel();
        tInsSex = new javax.swing.JComboBox<>();
        imgT = new javax.swing.JLabel();
        updTeach = new javax.swing.JButton();
        insTeach = new javax.swing.JButton();
        clrTeach = new javax.swing.JButton();
        delTeach = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        menubarHOME = new javax.swing.JPanel();
        neWelcomeAdmin = new javax.swing.JLabel();
        neLogout = new javax.swing.JButton();
        headTitleHS = new javax.swing.JLabel();
        headTitle = new javax.swing.JLabel();
        neBG = new javax.swing.JLabel();
        filename = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adminPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        enrolleeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "STATUS", "FIRST NAME", "LAST NAME", "M.I.", "SEX", "E-MAIL", "ADDRESS", "BIRTHDATE", "RELIGION", "NATIONALITY", "CIVIL STATUS", "CONTACT #", "GUARDIAN", "GUARDIAN #", "SECTION"
            }
        ));
        enrolleeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enrolleeTableMouseClicked(evt);
            }
        });
        enrolleeScrollPane.setViewportView(enrolleeTable);

        userPanel.add(enrolleeScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 850, 110));

        enrolleeFunc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFName.setText("FNAME");
        enrolleeFunc.add(lblFName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 70, 30));

        lblLName.setText("LNAME");
        enrolleeFunc.add(lblLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 70, 30));

        lblMI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMI.setText("M.I.");
        enrolleeFunc.add(lblMI, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 30, 30));

        lblSex.setText("SEX");
        enrolleeFunc.add(lblSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 80, 30));

        lblBDate.setText("BIRTHDAY");
        enrolleeFunc.add(lblBDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 70, 30));

        lblRel.setText("RELIGION");
        enrolleeFunc.add(lblRel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 80, 30));

        lblCStat.setText("CIVIL STATUS");
        enrolleeFunc.add(lblCStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 80, 30));

        lblNum.setText("CONTACT #");
        enrolleeFunc.add(lblNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 70, 30));

        FNametxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(FNametxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 80, 30));

        LNametxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(LNametxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 150, 30));

        MItxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(MItxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 40, 30));

        BDatetxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(BDatetxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 150, 30));

        ReljTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(ReljTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 130, 30));

        NumtxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(NumtxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 150, 30));

        StatsCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "UNENROLLED", "ENROLLED" }));
        enrolleeFunc.add(StatsCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 130, 30));

        lblID.setText("ID");
        enrolleeFunc.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 70, 30));

        IDtxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(IDtxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 150, 30));

        lblStat.setText("STATUS");
        enrolleeFunc.add(lblStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 80, 30));

        lblSec.setText("SECTION");
        enrolleeFunc.add(lblSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 80, 30));

        clrButtonEnrollee.setText("CLEAR");
        clrButtonEnrollee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrButtonEnrolleeActionPerformed(evt);
            }
        });
        enrolleeFunc.add(clrButtonEnrollee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 100, 50));

        delButtonEnrollee.setText("DELETE");
        delButtonEnrollee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonEnrolleeActionPerformed(evt);
            }
        });
        enrolleeFunc.add(delButtonEnrollee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 50));

        updButtonEnrollee.setText("UPDATE");
        updButtonEnrollee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updButtonEnrolleeActionPerformed(evt);
            }
        });
        enrolleeFunc.add(updButtonEnrollee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 50));

        sexCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "male", "female" }));
        enrolleeFunc.add(sexCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 130, 30));

        cStatCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "SINGLE", "MARRIED", "DIVORCED", "WIDOWED" }));
        enrolleeFunc.add(cStatCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 130, 30));

        secCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "7 - Rizal", "8 - Sampaguita", "9 - Oxygen", "10 - Gold" }));
        enrolleeFunc.add(secCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 130, 30));

        lblEMail.setText("EMAIL");
        enrolleeFunc.add(lblEMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 80, 30));

        lblAddr.setText("ADDRESS");
        enrolleeFunc.add(lblAddr, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 80, 30));

        lblGuard.setText("GUARDIAN");
        enrolleeFunc.add(lblGuard, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 80, 30));

        lblGNum.setText("GUARDIAN #");
        enrolleeFunc.add(lblGNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 80, 30));

        EmailtxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(EmailtxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 160, 30));

        GuardtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(GuardtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 160, 30));

        GNumtxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(GNumtxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 160, 30));

        lblNat.setText("NATIONALITY");
        enrolleeFunc.add(lblNat, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 80, 30));

        NatltxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enrolleeFunc.add(NatltxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 160, 30));

        addTxtField.setColumns(20);
        addTxtField.setLineWrap(true);
        addTxtField.setRows(1);
        addTxtField.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        addressScrollPane.setViewportView(addTxtField);

        enrolleeFunc.add(addressScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 160, 30));

        userPanel.add(enrolleeFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 850, 210));

        adminTabbedPane.addTab("ENROLLEE", userPanel);

        formPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        openfile.setText("Open");
        openfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openfileActionPerformed(evt);
            }
        });
        imgPanel.add(openfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        imageDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgPanel.add(imageDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 310));

        formPanel.add(imgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 240, 330));

        formEditPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        insButtonForm.setText("INSERT");
        insButtonForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insButtonFormActionPerformed(evt);
            }
        });
        formEditPanel.add(insButtonForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 40));

        updButtonForm.setText("UPDATE");
        updButtonForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updButtonFormActionPerformed(evt);
            }
        });
        formEditPanel.add(updButtonForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 100, 40));

        delButtonForm.setText("DELETE");
        delButtonForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonFormActionPerformed(evt);
            }
        });
        formEditPanel.add(delButtonForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, 40));

        clrButtonForm.setText("CLEAR");
        clrButtonForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrButtonFormActionPerformed(evt);
            }
        });
        formEditPanel.add(clrButtonForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 100, 40));

        rcSel.setText("Select");
        rcSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rcSelActionPerformed(evt);
            }
        });
        formEditPanel.add(rcSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 70, 30));

        f137Sel.setText("Select");
        f137Sel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f137SelActionPerformed(evt);
            }
        });
        formEditPanel.add(f137Sel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 70, 30));

        bcSel.setText("Select");
        bcSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcSelActionPerformed(evt);
            }
        });
        formEditPanel.add(bcSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 70, 30));

        gmSel.setText("Select");
        gmSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gmSelActionPerformed(evt);
            }
        });
        formEditPanel.add(gmSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 70, 30));

        torSel.setText("Select");
        torSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                torSelActionPerformed(evt);
            }
        });
        formEditPanel.add(torSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 70, 30));

        lblIDForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDForm.setText("ID");
        formEditPanel.add(lblIDForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 80, 30));

        txtFormID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formEditPanel.add(txtFormID, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 210, 30));

        txtFormRC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formEditPanel.add(txtFormRC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 140, 30));

        lblRCForm.setText("Report Card");
        formEditPanel.add(lblRCForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, 30));

        txtFormBC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formEditPanel.add(txtFormBC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 140, 30));

        lblBCForm.setText("Birth Cert");
        formEditPanel.add(lblBCForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 80, 30));

        txtFormF137.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formEditPanel.add(txtFormF137, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 140, 30));

        lblF137Form.setText("Form 137");
        formEditPanel.add(lblF137Form, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 80, 30));

        txtFormGM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formEditPanel.add(txtFormGM, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 140, 30));

        lblGMForm.setText("Good Moral");
        formEditPanel.add(lblGMForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 80, 30));

        txtFormTOR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formEditPanel.add(txtFormTOR, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 140, 30));

        lblTORForm.setText("TOR");
        formEditPanel.add(lblTORForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 80, 30));

        txtFormEID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formEditPanel.add(txtFormEID, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 210, 30));

        lblEIDForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEIDForm.setText("Enrollee ID");
        formEditPanel.add(lblEIDForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 80, 30));

        formTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Report Card", "Form 137", "Birth Cert", "Good Moral", "TOR", "Enrollee ID"
            }
        ));
        formTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formTableMouseClicked(evt);
            }
        });
        formScrollPane.setViewportView(formTable);
        if (formTable.getColumnModel().getColumnCount() > 0) {
            formTable.getColumnModel().getColumn(1).setHeaderValue("Report Card");
            formTable.getColumnModel().getColumn(2).setHeaderValue("Form 137");
            formTable.getColumnModel().getColumn(3).setHeaderValue("Birth Cert");
            formTable.getColumnModel().getColumn(4).setHeaderValue("Good Moral");
            formTable.getColumnModel().getColumn(5).setHeaderValue("TOR");
        }

        formEditPanel.add(formScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 350, 90));

        formPanel.add(formEditPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 600, 320));

        adminTabbedPane.addTab("FORMS", formPanel);

        subPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sectionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Section Code", "Section Name", "Adviser", "School Year", "Teacher's ID"
            }
        ));
        sectionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sectionTableMouseClicked(evt);
            }
        });
        secScrollPane.setViewportView(sectionTable);

        subPanel.add(secScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 460, 330));

        updButtonSec.setText("UPDATE");
        updButtonSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updButtonSecActionPerformed(evt);
            }
        });
        subPanel.add(updButtonSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 100, 40));

        lblSchYr.setText("School Year");
        subPanel.add(lblSchYr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 80, 30));

        lblSecCode.setText("Section Code");
        subPanel.add(lblSecCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 30));

        lblSecName.setText("Section Name");
        subPanel.add(lblSecName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, 30));

        lblSecAdv.setText("Section Adviser");
        subPanel.add(lblSecAdv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 80, 30));

        txtSchYr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subPanel.add(txtSchYr, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 210, 30));

        txtSecCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subPanel.add(txtSecCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 210, 30));

        txtSecName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subPanel.add(txtSecName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 210, 30));

        txtSecAdv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subPanel.add(txtSecAdv, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 210, 30));

        clrButtonSec.setText("CLEAR");
        clrButtonSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrButtonSecActionPerformed(evt);
            }
        });
        subPanel.add(clrButtonSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 100, 40));

        txtTID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subPanel.add(txtTID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 210, 30));

        lblSchYr1.setText("Teacher's ID");
        subPanel.add(lblSchYr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 80, 30));

        adminTabbedPane.addTab("SECTION", subPanel);

        schedPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        schedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Subject", "Day", "Start Time", "End Time", "Teacher", "Section Code", "Teacher's ID"
            }
        ));
        schedTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedTableMouseClicked(evt);
            }
        });
        schedScrollPane.setViewportView(schedTable);

        schedPanel.add(schedScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 850, 190));

        updButtonSch.setText("UPDATE");
        updButtonSch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updButtonSchActionPerformed(evt);
            }
        });
        schedPanel.add(updButtonSch, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, 80, 30));

        clrButtonSch.setText("CLEAR");
        clrButtonSch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrButtonSchActionPerformed(evt);
            }
        });
        schedPanel.add(clrButtonSch, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, 80, 30));

        lblSchCode.setText("Code");
        schedPanel.add(lblSchCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 60, 30));

        txtSchCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(txtSchCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 180, 30));

        lblSchSub.setText("Subject");
        schedPanel.add(lblSchSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 60, 30));

        txtSchSub.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(txtSchSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 180, 30));

        lblSchDay.setText("Day");
        schedPanel.add(lblSchDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 60, 30));

        txtSchDay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(txtSchDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 180, 30));

        txtSchST.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(txtSchST, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 180, 30));

        txtSchET.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(txtSchET, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 180, 30));

        txtSchTch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(txtSchTch, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 180, 30));

        lblSchTeacher.setText("Teacher");
        schedPanel.add(lblSchTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 60, 30));

        lblSchETime.setText("End Time");
        schedPanel.add(lblSchETime, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 60, 30));

        lblSchSTime.setText("Start Time");
        schedPanel.add(lblSchSTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 60, 30));

        txtSchSecCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(txtSchSecCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 180, 30));

        lblSchSecCode.setText("Section Code");
        schedPanel.add(lblSchSecCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 80, 30));

        lblSchYr2.setText("Teacher's ID");
        schedPanel.add(lblSchYr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 80, 30));

        schedTeachID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        schedPanel.add(schedTeachID, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, 180, 30));

        adminTabbedPane.addTab("SCHEDULE", schedPanel);

        teacherPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        teacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Email", "Contact Number", "Sex", "Birthdate", "Image Link"
            }
        ));
        teacherTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherTableMouseClicked(evt);
            }
        });
        teachScrollPane.setViewportView(teacherTable);

        teacherPanel.add(teachScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 850, 150));

        txtID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtID.setText("ID");
        teacherPanel.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 100, 30));

        txtName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtName.setText("Name");
        teacherPanel.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 100, 30));

        txtMail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMail.setText("E-Mail");
        teacherPanel.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 150, 30));

        txtCNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCNum.setText("Contact Number");
        teacherPanel.add(txtCNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 150, 30));

        tInsMail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        teacherPanel.add(tInsMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 150, 30));

        tInsID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        teacherPanel.add(tInsID, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 100, 30));

        tInsName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        teacherPanel.add(tInsName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 100, 30));

        tInsCNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        teacherPanel.add(tInsCNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 150, 30));

        tInsBDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        teacherPanel.add(tInsBDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 150, 30));

        tInsILink.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tInsILink.setEnabled(false);
        teacherPanel.add(tInsILink, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 230, 30));

        txtSex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSex.setText("Sex");
        teacherPanel.add(txtSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 100, 30));

        txtBdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBdate.setText("Birthdate");
        teacherPanel.add(txtBdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, 150, 30));

        txtImgLink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtImgLink.setText("Image Link");
        teacherPanel.add(txtImgLink, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 130, 30));

        tInsSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "MALE", "FEMALE" }));
        teacherPanel.add(tInsSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 100, 30));

        imgT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imgT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgTMouseClicked(evt);
            }
        });
        teacherPanel.add(imgT, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 90, 90));

        updTeach.setText("UPDATE");
        updTeach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updTeachActionPerformed(evt);
            }
        });
        teacherPanel.add(updTeach, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 80, 30));

        insTeach.setText("INSERT");
        teacherPanel.add(insTeach, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 80, 30));

        clrTeach.setText("CLEAR");
        clrTeach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrTeachActionPerformed(evt);
            }
        });
        teacherPanel.add(clrTeach, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 80, 30));

        delTeach.setText("DELETE");
        teacherPanel.add(delTeach, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 80, 30));

        adminTabbedPane.addTab("TEACHERS", teacherPanel);

        adminPanel.add(adminTabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 880, 380));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enrollmentsystem/Images/newLogo.png"))); // NOI18N
        adminPanel.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 40, 40));

        menubarHOME.setBackground(new java.awt.Color(140, 0, 1));
        menubarHOME.setForeground(new java.awt.Color(140, 0, 1));
        menubarHOME.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        neWelcomeAdmin.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        neWelcomeAdmin.setForeground(new java.awt.Color(255, 51, 51));
        neWelcomeAdmin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        neWelcomeAdmin.setText("Welcome, Admin!");
        neWelcomeAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menubarHOME.add(neWelcomeAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, 20));

        neLogout.setText("logout");
        neLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        neLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neLogoutActionPerformed(evt);
            }
        });
        menubarHOME.add(neLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, 20));

        adminPanel.add(menubarHOME, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 320, 40));

        headTitleHS.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        headTitleHS.setForeground(new java.awt.Color(255, 255, 255));
        headTitleHS.setText("HIGH SCHOOL");
        adminPanel.add(headTitleHS, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        headTitle.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        headTitle.setForeground(new java.awt.Color(255, 255, 255));
        headTitle.setText("JOSIAH CHRISTIAN");
        adminPanel.add(headTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 20));

        neBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enrollmentsystem/Images/newBg.jpg"))); // NOI18N
        neBG.setText("bg");
        neBG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminPanel.add(neBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        filename.setForeground(new java.awt.Color(51, 51, 55));
        filename.setText("jLabel1");
        adminPanel.add(filename, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 10, 20));

        getContentPane().add(adminPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
JFrame frame;
    private void neLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neLogoutActionPerformed
        frame = new JFrame("LogOut!");
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Student Portal",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            LoginForm lf = new LoginForm();
            this.dispose();
            LoginForm.loginPanel.setVisible(true);
            LoginForm.registerPanel.setVisible(false);
            lf.setVisible(true);
            lf.pack();
            lf.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_neLogoutActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        enrollee();
        forms();
        section();
        schedule();
        teachers();
    }//GEN-LAST:event_formWindowActivated

    private void enrolleeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enrolleeTableMouseClicked
        RecordTable = (DefaultTableModel) enrolleeTable.getModel();
        int SelectedRows = enrolleeTable.getSelectedRow();

        IDtxtField.setText(RecordTable.getValueAt(SelectedRows, 0).toString());
        StatsCBox.setSelectedItem(RecordTable.getValueAt(SelectedRows, 1).toString());
        FNametxtField.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
        LNametxtField.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
        MItxtField.setText(RecordTable.getValueAt(SelectedRows, 4).toString());
        sexCBox.setSelectedItem(RecordTable.getValueAt(SelectedRows, 5).toString());
        EmailtxtField.setText(RecordTable.getValueAt(SelectedRows, 6).toString());
        addTxtField.setText(RecordTable.getValueAt(SelectedRows, 7).toString());
        BDatetxtField.setText(RecordTable.getValueAt(SelectedRows, 8).toString());
        ReljTextField10.setText(RecordTable.getValueAt(SelectedRows, 9).toString());
        NatltxtField.setText(RecordTable.getValueAt(SelectedRows, 10).toString());
        cStatCBox.setSelectedItem(RecordTable.getValueAt(SelectedRows, 11).toString());
        NumtxtField.setText(RecordTable.getValueAt(SelectedRows, 12).toString());
        GuardtField.setText(RecordTable.getValueAt(SelectedRows, 13).toString());
        GNumtxtField.setText(RecordTable.getValueAt(SelectedRows, 14).toString());
        secCBox.setSelectedItem(RecordTable.getValueAt(SelectedRows, 15).toString());
        String secCode = (RecordTable.getValueAt(SelectedRows, 15)).toString();
        switch (secCode) {
            case "JON20210601":
                secCBox.setSelectedIndex(1);
                break;
            case "JTW20210602":
                secCBox.setSelectedIndex(2);
                break;
            case "JTH20210603":
                secCBox.setSelectedIndex(3);
                break;
            case "JFO20210604":
                secCBox.setSelectedIndex(4);
                break;
            default:
                secCBox.setSelectedIndex(0);
                break;
        }
    }//GEN-LAST:event_enrolleeTableMouseClicked

    private void updButtonEnrolleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updButtonEnrolleeActionPerformed
        try {
            String status;

            switch (secCBox.getSelectedIndex()) {
                case 1:
                    status = "JON20210601";
                    break;
                case 2:
                    status = "JTW20210602";
                    break;
                case 3:
                    status = "JTH20210603";
                    break;
                case 4:
                    status = "JFO20210604";
                    break;
                default:
                    status = "N/A";
                    break;
            }

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("UPDATE enrollee         "
                    + "SET                     "
                    + "e_stat          = ?,    "
                    + "e_fname         = ?,    "
                    + "e_lname         = ?,    "
                    + "e_minit         = ?,    "
                    + "e_sex           = ?,    "
                    + "e_email         = ?,    "
                    + "e_address       = ?,    "
                    + "e_bdate         = ?,    "
                    + "e_religion      = ?,    "
                    + "e_nationality   = ?,    "
                    + "e_civilstatus   = ?,    "
                    + "e_contactnum    = ?,    "
                    + "e_guardian      = ?,    "
                    + "e_guardiannum   = ?,    "
                    + "sec_code        = ?     "
                    + "WHERE e_num     = ?     ");

            ps.setString(1, StatsCBox.getSelectedItem().toString());
            ps.setString(2, FNametxtField.getText());
            ps.setString(3, LNametxtField.getText());
            ps.setString(4, MItxtField.getText());
            ps.setString(5, sexCBox.getSelectedItem().toString());
            ps.setString(6, EmailtxtField.getText());
            ps.setString(7, addTxtField.getText());
            ps.setString(8, BDatetxtField.getText());
            ps.setString(9, ReljTextField10.getText());
            ps.setString(10, NatltxtField.getText());
            ps.setString(11, cStatCBox.getSelectedItem().toString());
            ps.setString(12, NumtxtField.getText());
            ps.setString(13, GuardtField.getText());
            ps.setString(14, GNumtxtField.getText());
            ps.setString(15, status);
            ps.setString(16, IDtxtField.getText());

            int c = ps.executeUpdate();
            if (c != 0) {
                JOptionPane.showMessageDialog(this, "Enrollee table Updated");
            } else {
                JOptionPane.showMessageDialog(this, "Enrollee table Update failed");
            }
            enrollee();

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_updButtonEnrolleeActionPerformed

    private void delButtonEnrolleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonEnrolleeActionPerformed
        try {

            deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this Enrollee?", "Warning!",
                    JOptionPane.YES_NO_OPTION);
            if (deleteItem == JOptionPane.YES_OPTION) {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                ps = conn.prepareStatement("DELETE FROM account WHERE e_num = ?");

                ps.setString(1, IDtxtField.getText());

                int c1 = ps.executeUpdate();
                if (c1 != 0) {
                    JOptionPane.showMessageDialog(this, "Account has been Removed!");
                    ps = conn.prepareStatement("DELETE FROM requirements WHERE e_num = ?");

                    ps.setString(1, IDtxtField.getText());
                    int c2 = ps.executeUpdate();
                    if (c2 != 0) {
                        JOptionPane.showMessageDialog(this, "Requirements had been Removed!");
                        ps = conn.prepareStatement("DELETE FROM enrollee WHERE e_num = ?");

                        ps.setString(1, IDtxtField.getText());
                        int c3 = ps.executeUpdate();
                        if (c3 != 0) {
                            JOptionPane.showMessageDialog(this, "Enrollee has been Removed!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Enrollee doesn\'t exists!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Requirements doesn\'t exists!");
                        ps = conn.prepareStatement("DELETE FROM enrollee WHERE e_num = ?");

                        ps.setString(1, IDtxtField.getText());
                        int c3 = ps.executeUpdate();
                        if (c3 != 0) {
                            JOptionPane.showMessageDialog(this, "Enrollee has been Removed!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Enrollee doesn\'t exists!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Account doesn\'t exists!");
                    ps = conn.prepareStatement("DELETE FROM requirements WHERE e_num = ?");

                    ps.setString(1, IDtxtField.getText());
                    int c2 = ps.executeUpdate();
                    if (c2 != 0) {
                        JOptionPane.showMessageDialog(this, "Requirements had been Removed!");
                        ps = conn.prepareStatement("DELETE FROM enrollee WHERE e_num = ?");

                        ps.setString(1, IDtxtField.getText());
                        int c3 = ps.executeUpdate();
                        if (c3 != 0) {
                            JOptionPane.showMessageDialog(this, "Enrollee has been Removed!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Enrollee doesn\'t exists!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Requirements doesn\'t exists!");
                        ps = conn.prepareStatement("DELETE FROM enrollee WHERE e_num = ?");

                        ps.setString(1, IDtxtField.getText());
                        int c3 = ps.executeUpdate();
                        if (c3 != 0) {
                            JOptionPane.showMessageDialog(this, "Enrollee has been Removed!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Enrollee doesn\'t exists!");
                        }
                    }
                }
                enrollee();

                IDtxtField.setText("");
                StatsCBox.setSelectedItem("N/A");
                FNametxtField.setText("");
                LNametxtField.setText("");
                MItxtField.setText("");
                sexCBox.setSelectedItem("N/A");
                EmailtxtField.setText("");
                addTxtField.setText("");
                BDatetxtField.setText("");
                ReljTextField10.setText("");
                NatltxtField.setText("");
                cStatCBox.setSelectedItem("N/A");
                NumtxtField.setText("");
                GuardtField.setText("");
                GNumtxtField.setText("");
                secCBox.setSelectedItem("N/A");
            }
        } catch (SQLException e) {
            Logger.getLogger(AdminPortal.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_delButtonEnrolleeActionPerformed

    private void clrButtonEnrolleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrButtonEnrolleeActionPerformed
        IDtxtField.setText("");
        StatsCBox.setSelectedItem("N/A");
        FNametxtField.setText("");
        LNametxtField.setText("");
        MItxtField.setText("");
        sexCBox.setSelectedItem("N/A");
        EmailtxtField.setText("");
        addTxtField.setText("");
        BDatetxtField.setText("");
        ReljTextField10.setText("");
        NatltxtField.setText("");
        cStatCBox.setSelectedItem("N/A");
        NumtxtField.setText("");
        GuardtField.setText("");
        GNumtxtField.setText("");
        secCBox.setSelectedItem("N/A");
    }//GEN-LAST:event_clrButtonEnrolleeActionPerformed

    private void formTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formTableMouseClicked
        RecordTable = (DefaultTableModel) formTable.getModel();
        int SelectedRows = formTable.getSelectedRow();

        txtFormID.setText(RecordTable.getValueAt(SelectedRows, 0).toString());
        txtFormRC.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
        txtFormF137.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
        txtFormBC.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
        txtFormGM.setText(RecordTable.getValueAt(SelectedRows, 4).toString());
        txtFormTOR.setText(RecordTable.getValueAt(SelectedRows, 5).toString());
        txtFormEID.setText(RecordTable.getValueAt(SelectedRows, 6).toString());
    }//GEN-LAST:event_formTableMouseClicked

    private void insButtonFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insButtonFormActionPerformed
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            ps = conn.prepareStatement("INSERT INTO requirements "
                    + "VALUES(?,?,?,?,?,?,?)");

            ps.setString(1, txtFormID.getText());
            ps.setString(2, txtFormRC.getText());
            ps.setString(3, txtFormF137.getText());
            ps.setString(4, txtFormBC.getText());
            ps.setString(5, txtFormGM.getText());
            ps.setString(6, txtFormTOR.getText());
            ps.setString(7, txtFormEID.getText());

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Requirement has been Inserted!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to Insert requirement!");
            }
        } catch (Exception e) {
            Logger.getLogger(AdminPortal.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_insButtonFormActionPerformed

    private void updButtonFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updButtonFormActionPerformed
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("UPDATE requirements         "
                    + "SET                         "
                    + "ReportCard = ?,             "
                    + "Form137 = ?,                "
                    + "BirthCertificate = ?,       "
                    + "GoodMoral = ?,              "
                    + "TranscriptOfRecords = ?,    "
                    + "e_num = ?                   "
                    + "WHERE req_ID = ?            ");

            ps.setString(1, txtFormRC.getText());
            ps.setString(2, txtFormF137.getText());
            ps.setString(3, txtFormBC.getText());
            ps.setString(4, txtFormGM.getText());
            ps.setString(5, txtFormTOR.getText());
            ps.setString(6, txtFormEID.getText());
            ps.setString(7, txtFormID.getText());

            int c = ps.executeUpdate();
            if (c != 0) {
                JOptionPane.showMessageDialog(this, "Requirement Updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Update Enrollee's Requirement!");
            }
            forms();

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_updButtonFormActionPerformed

    private void delButtonFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonFormActionPerformed
        try {
            deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this requirements?", "Warning!",
                    JOptionPane.YES_NO_OPTION);
            if (deleteItem == JOptionPane.YES_OPTION) {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                ps = conn.prepareStatement("DELETE FROM requirements WHERE req_ID = ?");

                ps.setString(1, txtFormID.getText());

                int c = ps.executeUpdate();
                if (c != 0) {
                    JOptionPane.showMessageDialog(this, "Enrollee\'s requirements has been Removed!");
                } else {
                    JOptionPane.showMessageDialog(this, "Enrollee\'s requirements failed to remove!");
                }
                forms();

                txtFormID.setText("");
                txtFormRC.setText("");
                txtFormF137.setText("");
                txtFormBC.setText("");
                txtFormGM.setText("");
                txtFormTOR.setText("");
                txtFormEID.setText("");
                filename.setText("");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }//GEN-LAST:event_delButtonFormActionPerformed

    private void clrButtonFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrButtonFormActionPerformed
        String[] link
                = {"/Images/jcvhsHlLogin.jpg",
                    "/Images/newLogo.png",
                    "/Images/imageInsertHere.jpg"};

        txtFormID.setText("");
        txtFormRC.setText("");
        txtFormF137.setText("");
        txtFormBC.setText("");
        txtFormGM.setText("");
        txtFormTOR.setText("");
        txtFormEID.setText("");
        filename.setText("");
        imageScale(link[2], imageDisplay);
    }//GEN-LAST:event_clrButtonFormActionPerformed

    private void rcSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rcSelActionPerformed
        if (!"".equals(txtFormRC.getText())) {
            imgSetImgForm(txtFormRC);
            filename.setText(txtFormRC.getText());
        } else {
            JOptionPane.showMessageDialog(this, "No File Selected!");
        }
    }//GEN-LAST:event_rcSelActionPerformed

    private void f137SelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f137SelActionPerformed
        if (!"".equals(txtFormF137.getText())) {
            imgSetImgForm(txtFormF137);
            filename.setText(txtFormF137.getText());
        } else {
            JOptionPane.showMessageDialog(this, "No File Selected!");
        }
    }//GEN-LAST:event_f137SelActionPerformed

    private void bcSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcSelActionPerformed
        if (!"".equals(txtFormBC.getText())) {
            imgSetImgForm(txtFormBC);
            filename.setText(txtFormBC.getText());
        } else {
            JOptionPane.showMessageDialog(this, "No File Selected!");
        }
    }//GEN-LAST:event_bcSelActionPerformed

    private void gmSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gmSelActionPerformed
        if (!"".equals(txtFormGM.getText())) {
            imgSetImgForm(txtFormGM);
            filename.setText(txtFormGM.getText());
        } else {
            JOptionPane.showMessageDialog(this, "No File Selected!");
        }
    }//GEN-LAST:event_gmSelActionPerformed

    private void torSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_torSelActionPerformed
        if (!"".equals(txtFormTOR.getText())) {
            imgSetImgForm(txtFormTOR);
            filename.setText(txtFormTOR.getText());
        } else {
            JOptionPane.showMessageDialog(this, "No File Selected!");
        }
    }//GEN-LAST:event_torSelActionPerformed

    private void sectionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sectionTableMouseClicked
        RecordTable = (DefaultTableModel) sectionTable.getModel();
        int SelectedRows = sectionTable.getSelectedRow();

        txtSecCode.setText(RecordTable.getValueAt(SelectedRows, 0).toString());
        txtSecName.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
        txtSecAdv.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
        txtSchYr.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
        txtTID.setText(RecordTable.getValueAt(SelectedRows, 4).toString());
    }//GEN-LAST:event_sectionTableMouseClicked

    private void updButtonSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updButtonSecActionPerformed
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("UPDATE section              "
                    + "SET                         "
                    + "sec_name = ?,               "
                    + "sec_adviser = ?,            "
                    + "sec_schoolyear = ?          "
                    + "WHERE sec_code = ?          ");

            ps.setString(1, txtSecName.getText());
            ps.setString(2, txtSecAdv.getText());
            ps.setString(3, txtSchYr.getText());
            ps.setString(4, txtSecCode.getText());

            int c = ps.executeUpdate();
            if (c != 0) {
                JOptionPane.showMessageDialog(this, "Section Updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Section failed to Update!");
            }
            section();

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_updButtonSecActionPerformed

    private void clrButtonSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrButtonSecActionPerformed
        txtSecCode.setText("");
        txtSecName.setText("");
        txtSecAdv.setText("");
        txtSchYr.setText("");
    }//GEN-LAST:event_clrButtonSecActionPerformed

    private void schedTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedTableMouseClicked
        RecordTable = (DefaultTableModel) schedTable.getModel();
        int SelectedRows = schedTable.getSelectedRow();

        txtSchCode.setText(RecordTable.getValueAt(SelectedRows, 0).toString());
        txtSchSub.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
        txtSchDay.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
        txtSchST.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
        txtSchET.setText(RecordTable.getValueAt(SelectedRows, 4).toString());
        txtSchTch.setText(RecordTable.getValueAt(SelectedRows, 5).toString());
        txtSchSecCode.setText(RecordTable.getValueAt(SelectedRows, 6).toString());
        schedTeachID.setText(RecordTable.getValueAt(SelectedRows, 7).toString());
    }//GEN-LAST:event_schedTableMouseClicked

    private void updButtonSchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updButtonSchActionPerformed
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("UPDATE schedule             "
                    + "SET                         "
                    + "s_subject = ?,              "
                    + "s_day = ?,                  "
                    + "s_starttime = ?,            "
                    + "s_endtime = ?,              "
                    + "s_teacher = ?,              "
                    + "sec_code = ?                "
                    + "WHERE s_code = ?            ");

            ps.setString(1, txtSchSub.getText());
            ps.setString(2, txtSchDay.getText());
            ps.setString(3, txtSchST.getText());
            ps.setString(4, txtSchET.getText());
            ps.setString(5, txtSchTch.getText());
            ps.setString(6, txtSchSecCode.getText());
            ps.setString(7, txtSchCode.getText());

            int c = ps.executeUpdate();
            if (c != 0) {
                JOptionPane.showMessageDialog(this, "Schedule Updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Schedule failed to Update!");
            }
            section();

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_updButtonSchActionPerformed

    private void clrButtonSchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrButtonSchActionPerformed
        txtSchCode.setText("");
        txtSchSub.setText("");
        txtSchDay.setText("");
        txtSchST.setText("");
        txtSchET.setText("");
        txtSchTch.setText("");
        txtSchSecCode.setText("");
    }//GEN-LAST:event_clrButtonSchActionPerformed

    private void openfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openfileActionPerformed
        try {
            String file = filename.getText();
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            desktop.open(new File(file));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No File Selected!");
        }
    }//GEN-LAST:event_openfileActionPerformed

    private void teacherTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherTableMouseClicked
        RecordTable = (DefaultTableModel) teacherTable.getModel();
        int SelectedRows = teacherTable.getSelectedRow();

        tInsID.setText(RecordTable.getValueAt(SelectedRows, 0).toString());
        tInsName.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
        tInsMail.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
        tInsCNum.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
        tInsSex.setSelectedItem(RecordTable.getValueAt(SelectedRows, 4).toString());
        tInsBDate.setText(RecordTable.getValueAt(SelectedRows, 5).toString());
        tInsILink.setText(RecordTable.getValueAt(SelectedRows, 6).toString());

        if (!"".equals(tInsILink) || !"N/A".equals(tInsILink)) {
            imgSetProfile(tInsILink);
            filename.setText(tInsILink.getText());
        } else {
            JOptionPane.showMessageDialog(this, "No Profile yet!");
        }
    }//GEN-LAST:event_teacherTableMouseClicked

    private void imgTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgTMouseClicked
        imgSetProfileScale(tInsILink);
    }//GEN-LAST:event_imgTMouseClicked

    private void updTeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updTeachActionPerformed
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("UPDATE teacher              "
                    + "SET                         "
                    + "t_img = ?,                  "
                    + "t_name = ?,                 "
                    + "t_email = ?,                "
                    + "t_contact = ?,              "
                    + "t_sex = ?,                  "
                    + "t_bdate = ?                 "
                    + "WHERE t_num = ?             ");

            ps.setString(1, tInsILink.getText());
            ps.setString(2, tInsName.getText());
            ps.setString(3, tInsMail.getText());
            ps.setString(4, tInsCNum.getText());
            ps.setString(5, tInsSex.getSelectedItem().toString());
            ps.setString(6, tInsBDate.getText());
            ps.setString(7, tInsID.getText());

            int c = ps.executeUpdate();
            if (c != 0) {
                JOptionPane.showMessageDialog(this, "Teacher Details Updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Teacher Details failed to Update!");
            }
            section();

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_updTeachActionPerformed

    private void clrTeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrTeachActionPerformed
        String[] link
                = {"/Images/jcvhsHlLogin.jpg",
                    "/Images/NA.jpg",
                    "/Images/imageInsertHere.jpg"};

        tInsID.setText("");
        tInsName.setText("");
        tInsMail.setText("");
        tInsBDate.setText("");
        tInsILink.setText("");
        tInsSex.setSelectedItem("N/A");
        tInsCNum.setText("");
        imageScale(link[1], imgT);
    }//GEN-LAST:event_clrTeachActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPortal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BDatetxtField;
    private javax.swing.JTextField EmailtxtField;
    private javax.swing.JTextField FNametxtField;
    private javax.swing.JTextField GNumtxtField;
    private javax.swing.JTextField GuardtField;
    private javax.swing.JTextField IDtxtField;
    private javax.swing.JTextField LNametxtField;
    private javax.swing.JTextField MItxtField;
    private javax.swing.JTextField NatltxtField;
    private javax.swing.JTextField NumtxtField;
    private javax.swing.JTextField ReljTextField10;
    private javax.swing.JComboBox<String> StatsCBox;
    private javax.swing.JTextArea addTxtField;
    private javax.swing.JScrollPane addressScrollPane;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JTabbedPane adminTabbedPane;
    private javax.swing.JButton bcSel;
    private javax.swing.JComboBox<String> cStatCBox;
    private javax.swing.JButton clrButtonEnrollee;
    private javax.swing.JButton clrButtonForm;
    private javax.swing.JButton clrButtonSch;
    private javax.swing.JButton clrButtonSec;
    private javax.swing.JButton clrTeach;
    private javax.swing.JButton delButtonEnrollee;
    private javax.swing.JButton delButtonForm;
    private javax.swing.JButton delTeach;
    private javax.swing.JPanel enrolleeFunc;
    private javax.swing.JScrollPane enrolleeScrollPane;
    private javax.swing.JTable enrolleeTable;
    private javax.swing.JButton f137Sel;
    private javax.swing.JLabel filename;
    private javax.swing.JPanel formEditPanel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JScrollPane formScrollPane;
    private javax.swing.JTable formTable;
    private javax.swing.JButton gmSel;
    private javax.swing.JLabel headTitle;
    private javax.swing.JLabel headTitleHS;
    private javax.swing.JLabel imageDisplay;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JLabel imgT;
    private javax.swing.JButton insButtonForm;
    private javax.swing.JButton insTeach;
    private javax.swing.JLabel lblAddr;
    private javax.swing.JLabel lblBCForm;
    private javax.swing.JLabel lblBDate;
    private javax.swing.JLabel lblCStat;
    private javax.swing.JLabel lblEIDForm;
    private javax.swing.JLabel lblEMail;
    private javax.swing.JLabel lblF137Form;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblGMForm;
    private javax.swing.JLabel lblGNum;
    private javax.swing.JLabel lblGuard;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblIDForm;
    private javax.swing.JLabel lblLName;
    private javax.swing.JLabel lblMI;
    private javax.swing.JLabel lblNat;
    private javax.swing.JLabel lblNum;
    private javax.swing.JLabel lblRCForm;
    private javax.swing.JLabel lblRel;
    private javax.swing.JLabel lblSchCode;
    private javax.swing.JLabel lblSchDay;
    private javax.swing.JLabel lblSchETime;
    private javax.swing.JLabel lblSchSTime;
    private javax.swing.JLabel lblSchSecCode;
    private javax.swing.JLabel lblSchSub;
    private javax.swing.JLabel lblSchTeacher;
    private javax.swing.JLabel lblSchYr;
    private javax.swing.JLabel lblSchYr1;
    private javax.swing.JLabel lblSchYr2;
    private javax.swing.JLabel lblSec;
    private javax.swing.JLabel lblSecAdv;
    private javax.swing.JLabel lblSecCode;
    private javax.swing.JLabel lblSecName;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblStat;
    private javax.swing.JLabel lblTORForm;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel menubarHOME;
    private javax.swing.JLabel neBG;
    private javax.swing.JButton neLogout;
    public static javax.swing.JLabel neWelcomeAdmin;
    private javax.swing.JButton openfile;
    private javax.swing.JButton rcSel;
    private javax.swing.JPanel schedPanel;
    private javax.swing.JScrollPane schedScrollPane;
    private javax.swing.JTable schedTable;
    private javax.swing.JTextField schedTeachID;
    private javax.swing.JComboBox<String> secCBox;
    private javax.swing.JScrollPane secScrollPane;
    private javax.swing.JTable sectionTable;
    private javax.swing.JComboBox<String> sexCBox;
    private javax.swing.JPanel subPanel;
    private javax.swing.JTextField tInsBDate;
    private javax.swing.JTextField tInsCNum;
    private javax.swing.JTextField tInsID;
    private javax.swing.JTextField tInsILink;
    private javax.swing.JTextField tInsMail;
    private javax.swing.JTextField tInsName;
    private javax.swing.JComboBox<String> tInsSex;
    private javax.swing.JScrollPane teachScrollPane;
    private javax.swing.JPanel teacherPanel;
    private javax.swing.JTable teacherTable;
    private javax.swing.JButton torSel;
    private javax.swing.JLabel txtBdate;
    private javax.swing.JLabel txtCNum;
    private javax.swing.JTextField txtFormBC;
    private javax.swing.JTextField txtFormEID;
    private javax.swing.JTextField txtFormF137;
    private javax.swing.JTextField txtFormGM;
    private javax.swing.JTextField txtFormID;
    private javax.swing.JTextField txtFormRC;
    private javax.swing.JTextField txtFormTOR;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtImgLink;
    private javax.swing.JLabel txtMail;
    private javax.swing.JLabel txtName;
    private javax.swing.JTextField txtSchCode;
    private javax.swing.JTextField txtSchDay;
    private javax.swing.JTextField txtSchET;
    private javax.swing.JTextField txtSchST;
    private javax.swing.JTextField txtSchSecCode;
    private javax.swing.JTextField txtSchSub;
    private javax.swing.JTextField txtSchTch;
    private javax.swing.JTextField txtSchYr;
    private javax.swing.JTextField txtSecAdv;
    private javax.swing.JTextField txtSecCode;
    private javax.swing.JTextField txtSecName;
    private javax.swing.JLabel txtSex;
    private javax.swing.JTextField txtTID;
    private javax.swing.JButton updButtonEnrollee;
    private javax.swing.JButton updButtonForm;
    private javax.swing.JButton updButtonSch;
    private javax.swing.JButton updButtonSec;
    private javax.swing.JButton updTeach;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}

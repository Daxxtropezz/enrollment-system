/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollmentsystem;

import com.sun.glass.events.KeyEvent;
import java.awt.*; // uses Color and Component
import java.awt.Image;
import javax.swing.*; // gets JFrame and JOptionPane
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.*; //gets Level and Logger

/**
 *
 * @author sevhe
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    private static final String URL = "jdbc:mysql://localhost:3306/enrollmentsystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "@theHouseof25";
    private static int tries = 5;
    private JFrame frame;
    SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rset = null;

    public LoginForm() {
        initComponents();

        loginPass.setEchoChar('✦');
        regPass.setEchoChar('✦');
        regCPass.setEchoChar('✦');

        this.setSize(900, 490);
        String underline = "<html><u>forgot password?</u></html>";
        forPass.setText(underline);

        ButtonGroup sex = new ButtonGroup();
        sex.add(jrButtonMALE);
        sex.add(jrButtonFEMALE);

        imageCaller();
        registerPanel.setVisible(false);

//        for( Component c : regBDate.getComponents())
//        {
//            ((JComponent)c).setBackground(new Color(140, 0, 1));
//        }
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
                    "/Images/LionsRoarASH.png",
                    "/Images/lionTag.jpg",
                    "/Images/lionWhite.png",
                    "/Images/lion1.png"};

        imageScale(link[1], logo);
        imageScale(link[1], logoREG);
        imageScale(link[4], bgLogoREG);
        imageScale(link[3], headlineLogin);
    }

    public void loginAction() {
        try {
            String username = loginUser.getText();
            String password = String.valueOf(loginPass.getPassword());

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM account "
                    + "WHERE a_user = ? AND BINARY a_pass = ?");

            ps.setString(1, username);
            ps.setString(2, password);

            rset = ps.executeQuery();

            if (rset.next()) {
                String acc_type = rset.getString("a_type");

                if (null == acc_type) {
                    JOptionPane.showMessageDialog(null, "Failed to connect to database!", "LOGIN FAILED!", 2);
                } else {
                    switch (acc_type) {
                        case "TEACHER":

                            ps = conn.prepareStatement("SELECT * FROM teacher WHERE a_user = ?");

                            ps.setString(1, username);
                            rset = ps.executeQuery();

                            if (rset.next()) {
                                AdminPortal ap = new AdminPortal();

                                String uFname = rset.getString("t_name");
                                AdminPortal.neWelcomeAdmin.setText("welcome, " + uFname.toLowerCase() + "!");

                                JOptionPane.showMessageDialog(null, "You've Logged in successfully!", "HI TEACHER!", 1);
                                this.dispose();
                                ap.setLocationRelativeTo(null);
                                ap.setVisible(true);
                                ap.setSize(900, 490);
                                ap.pack();
                                ap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }

                            break;

                        case "STUDENT":
                            ps = conn.prepareStatement("SELECT * FROM enrollee WHERE a_user = ?");

                            ps.setString(1, username);
                            rset = ps.executeQuery();

                            if (rset.next()) {
                                StudentPortal sp = new StudentPortal();
                                String uFname = rset.getString("e_fname");
                                String uStat = rset.getString("e_stat");
                                hiddenLABEL.setText(uStat);
                                String uNum = rset.getString("e_num");
                                hiddenLABELpri.setText(uNum);
                                String sCode = rset.getString("sec_code");
                                hiddenLABELfk.setText(sCode);
                                if ("UNENROLLED".equals(hiddenLABEL.getText())) {
                                    StudentPortal.neWelcomeUser.setText("welcome, " + uFname.toLowerCase() + "!");
                                    StudentPortal.NotEnrolledPanel.setVisible(true);
                                    StudentPortal.EnrolledPanel.setVisible(false);
                                    StudentPortal.hiddenUserPRI.setText(hiddenLABELpri.getText());
                                } else if ("ENROLLED".equals(hiddenLABEL.getText())) {
                                    ps = conn.prepareStatement("SELECT * FROM section WHERE sec_code = ?");
                                    ps.setString(1, sCode);

                                    rset = ps.executeQuery();

                                    if (rset.next()) {
                                        String secName = rset.getString("sec_name");
                                        String secAdv = rset.getString("sec_adviser");
                                        String secSy = rset.getString("sec_schoolyear");

                                        StudentPortal.eWelcomeUser.setText("welcome, " + uFname.toLowerCase() + "!");
                                        StudentPortal.NotEnrolledPanel.setVisible(false);
                                        StudentPortal.EnrolledPanel.setVisible(true);
                                        StudentPortal.hiddenUserPRI.setText(hiddenLABELpri.getText());
                                        StudentPortal.hiddenTable.setText(hiddenLABELfk.getText());

                                        StudentPortal.secName.setText(secName);
                                        StudentPortal.secAdv.setText(secAdv);
                                        StudentPortal.secSY.setText(secSy);
                                    }
                                }

                                JOptionPane.showMessageDialog(null, "You've Logged in successfully!", "HI ENROLLEE!", 1);
                                this.dispose();
                                sp.setLocationRelativeTo(null);
                                sp.setVisible(true);
                                sp.setSize(900, 490);
                                sp.pack();
                                sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                break;
                            }
                        default:
                            JOptionPane.showMessageDialog(null, "Failed to connect to database!", "LOGIN FAILED!", 2);
                            break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username/password!", "LOGIN FAILED!", 2);
                tries--;
                if (tries == 0) {
                    tries = 5;
                    int answer = JOptionPane.showConfirmDialog(null, "Do you have an account?", "Warning!",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Redirecting you to change password!", "LOGIN FAILED!", 2);
                        ForgotPassForm fpf = new ForgotPassForm();
                        fpf.setVisible(true);
                        fpf.setLocationRelativeTo(null);
                        fpf.pack();
                    } else {
                        JOptionPane.showMessageDialog(null, "Redirecting you to registration!", "LOGIN FAILED!", 2);
                        loginPanel.setVisible(false);
                        registerPanel.setVisible(true);
                    }
                }
            }
        } catch (HeadlessException | SQLException e) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, e);
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

        loginPanel = new javax.swing.JPanel();
        loginHomeTitle = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        loginUser = new javax.swing.JTextField();
        showPass = new javax.swing.JCheckBox();
        loginPass = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        forPass = new javax.swing.JLabel();
        hiddenLABELfk = new javax.swing.JLabel();
        hiddenLABELpri = new javax.swing.JLabel();
        hiddenLABEL = new javax.swing.JLabel();
        headTitle = new javax.swing.JLabel();
        headTitleHS = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        headlineLogin = new javax.swing.JLabel();
        menubarLOGIN = new javax.swing.JPanel();
        spacingLabel = new javax.swing.JLabel();
        loginHome = new javax.swing.JLabel();
        loginAboutUs = new javax.swing.JLabel();
        loginContacts = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        loginSU = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        registerPanel = new javax.swing.JPanel();
        regPane = new javax.swing.JScrollPane();
        regPanel = new javax.swing.JPanel();
        regLName = new javax.swing.JTextField();
        signFNamelbl = new javax.swing.JLabel();
        signUserlbl = new javax.swing.JLabel();
        regPassSHOW = new javax.swing.JCheckBox();
        regPass = new javax.swing.JPasswordField();
        regCPassSHOW = new javax.swing.JCheckBox();
        regCPass = new javax.swing.JPasswordField();
        signBDatelbl = new javax.swing.JLabel();
        regFName = new javax.swing.JTextField();
        signReligionlbl = new javax.swing.JLabel();
        regUser = new javax.swing.JTextField();
        regBDate = new com.toedter.calendar.JDateChooser();
        signLNamelbl = new javax.swing.JLabel();
        regMI = new javax.swing.JTextField();
        signMIlbl = new javax.swing.JLabel();
        regReligion = new javax.swing.JTextField();
        signCPasslbl = new javax.swing.JLabel();
        signupButton = new javax.swing.JButton();
        regNationality = new javax.swing.JTextField();
        signNationalitylbl = new javax.swing.JLabel();
        signPasslbl = new javax.swing.JLabel();
        signAddresslbl = new javax.swing.JLabel();
        regCStatus = new javax.swing.JTextField();
        signCStatlbl = new javax.swing.JLabel();
        signCntctlbl = new javax.swing.JLabel();
        regContact = new javax.swing.JTextField();
        regGfname = new javax.swing.JTextField();
        signGFNamelbl = new javax.swing.JLabel();
        signGCntctlbl = new javax.swing.JLabel();
        regGcontact = new javax.swing.JTextField();
        addressScrollPane = new javax.swing.JScrollPane();
        regAddress = new javax.swing.JTextArea();
        endblank = new javax.swing.JLabel();
        regEmail = new javax.swing.JTextField();
        signSexlbl = new javax.swing.JLabel();
        signEmaillbl = new javax.swing.JLabel();
        jrButtonMALE = new javax.swing.JRadioButton();
        jrButtonFEMALE = new javax.swing.JRadioButton();
        regHOMETITLE = new javax.swing.JLabel();
        bgLogoREG = new javax.swing.JLabel();
        logoREG = new javax.swing.JLabel();
        headTitleHS1 = new javax.swing.JLabel();
        headTitle1 = new javax.swing.JLabel();
        menubarLOGIN1 = new javax.swing.JPanel();
        spacingLabel1 = new javax.swing.JLabel();
        regHome = new javax.swing.JLabel();
        regAboutUs = new javax.swing.JLabel();
        regContacts = new javax.swing.JLabel();
        regLogin = new javax.swing.JLabel();
        regSignUp = new javax.swing.JLabel();
        regBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginHomeTitle.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        loginHomeTitle.setForeground(new java.awt.Color(255, 255, 255));
        loginHomeTitle.setText("HOME OF THE RED LIONS");
        loginPanel.add(loginHomeTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        userLabel.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        userLabel.setForeground(new java.awt.Color(255, 255, 255));
        userLabel.setText("USERNAME: ");
        loginPanel.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, 30));

        passLabel.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        passLabel.setForeground(new java.awt.Color(255, 255, 255));
        passLabel.setText("PASSWORD: ");
        loginPanel.add(passLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, -1, -1));

        loginUser.setBackground(new java.awt.Color(255, 255, 255));
        loginUser.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        loginUser.setForeground(new java.awt.Color(0, 0, 0));
        loginUser.setText("enter your username!");
        loginUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginUserFocusLost(evt);
            }
        });
        loginUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginUserKeyPressed(evt);
            }
        });
        loginPanel.add(loginUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 230, 30));

        showPass.setBackground(new java.awt.Color(255, 255, 204));
        showPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassActionPerformed(evt);
            }
        });
        loginPanel.add(showPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, 30));

        loginPass.setBackground(new java.awt.Color(255, 255, 255));
        loginPass.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        loginPass.setForeground(new java.awt.Color(0, 0, 0));
        loginPass.setText("⠀⠀⠀⠀⠀⠀⠀⠀");
        loginPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginPassFocusLost(evt);
            }
        });
        loginPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginPassKeyPressed(evt);
            }
        });
        loginPanel.add(loginPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 230, 30));

        loginButton.setBackground(new java.awt.Color(51, 51, 51));
        loginButton.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("LOG IN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginPanel.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, -1, 30));

        forPass.setForeground(new java.awt.Color(255, 102, 102));
        forPass.setText("forgot password?");
        forPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forPassMouseClicked(evt);
            }
        });
        loginPanel.add(forPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, -1, 30));

        hiddenLABELfk.setForeground(new java.awt.Color(140, 0, 1));
        hiddenLABELfk.setText("hiddentext");
        loginPanel.add(hiddenLABELfk, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        hiddenLABELpri.setForeground(new java.awt.Color(140, 0, 1));
        hiddenLABELpri.setText("hiddentext");
        loginPanel.add(hiddenLABELpri, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        hiddenLABEL.setForeground(new java.awt.Color(140, 0, 1));
        hiddenLABEL.setText("hiddentext");
        loginPanel.add(hiddenLABEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        headTitle.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        headTitle.setForeground(new java.awt.Color(255, 255, 255));
        headTitle.setText("JOSIAH CHRISTIAN");
        loginPanel.add(headTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 20));

        headTitleHS.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        headTitleHS.setForeground(new java.awt.Color(255, 255, 255));
        headTitleHS.setText("HIGH SCHOOL");
        loginPanel.add(headTitleHS, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enrollmentsystem/Images/newLogo.png"))); // NOI18N
        logo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoMouseClicked(evt);
            }
        });
        loginPanel.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 40, 40));

        headlineLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enrollmentsystem/Images/jcvhsHlLogin.jpg"))); // NOI18N
        loginPanel.add(headlineLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 320, 320));

        menubarLOGIN.setBackground(new java.awt.Color(140, 0, 1));
        menubarLOGIN.setForeground(new java.awt.Color(140, 0, 1));
        menubarLOGIN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spacingLabel.setForeground(new java.awt.Color(255, 255, 255));
        spacingLabel.setText("|");
        menubarLOGIN.add(spacingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        loginHome.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        loginHome.setForeground(new java.awt.Color(255, 255, 255));
        loginHome.setText("Home");
        loginHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginHomeMouseClicked(evt);
            }
        });
        menubarLOGIN.add(loginHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        loginAboutUs.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        loginAboutUs.setForeground(new java.awt.Color(255, 255, 255));
        loginAboutUs.setText("About Us");
        loginAboutUs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginAboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginAboutUsMouseClicked(evt);
            }
        });
        menubarLOGIN.add(loginAboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        loginContacts.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        loginContacts.setForeground(new java.awt.Color(255, 255, 255));
        loginContacts.setText("Contacts");
        loginContacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginContacts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginContactsMouseClicked(evt);
            }
        });
        menubarLOGIN.add(loginContacts, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        login.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        login.setForeground(new java.awt.Color(0, 0, 0));
        login.setText("Login");
        login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        menubarLOGIN.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        loginSU.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        loginSU.setForeground(new java.awt.Color(255, 255, 255));
        loginSU.setText("Sign Up");
        loginSU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginSU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginSUMouseClicked(evt);
            }
        });
        menubarLOGIN.add(loginSU, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        loginPanel.add(menubarLOGIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 320, 40));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enrollmentsystem/Images/newBg.jpg"))); // NOI18N
        bg.setText("bg");
        loginPanel.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        getContentPane().add(loginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 490));

        registerPanel.setBackground(new java.awt.Color(255, 255, 255));
        registerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regPane.setBackground(new java.awt.Color(255, 255, 255));
        regPane.setBorder(null);
        regPane.setForeground(new java.awt.Color(255, 255, 255));

        regPanel.setBackground(new java.awt.Color(140, 0, 1));
        regPanel.setForeground(new java.awt.Color(140, 0, 1));
        regPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regLName.setBackground(new java.awt.Color(255, 255, 255));
        regLName.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regLName.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 350, 30));

        signFNamelbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signFNamelbl.setForeground(new java.awt.Color(255, 255, 255));
        signFNamelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signFNamelbl.setText("FIRST NAME:");
        regPanel.add(signFNamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 180, 30));

        signUserlbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signUserlbl.setForeground(new java.awt.Color(255, 255, 255));
        signUserlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signUserlbl.setText("USERNAME:");
        regPanel.add(signUserlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 30));

        regPassSHOW.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regPassSHOW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regPassSHOWActionPerformed(evt);
            }
        });
        regPanel.add(regPassSHOW, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, 30));

        regPass.setBackground(new java.awt.Color(255, 255, 255));
        regPass.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regPass.setForeground(new java.awt.Color(0, 0, 0));
        regPass.setText("⠀⠀⠀⠀⠀⠀⠀⠀");
        regPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                regPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                regPassFocusLost(evt);
            }
        });
        regPanel.add(regPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 350, 30));

        regCPassSHOW.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regCPassSHOW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regCPassSHOWActionPerformed(evt);
            }
        });
        regPanel.add(regCPassSHOW, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, -1, 30));

        regCPass.setBackground(new java.awt.Color(255, 255, 255));
        regCPass.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regCPass.setForeground(new java.awt.Color(0, 0, 0));
        regCPass.setText("⠀⠀⠀⠀⠀⠀⠀⠀");
        regCPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                regCPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                regCPassFocusLost(evt);
            }
        });
        regPanel.add(regCPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 350, 30));

        signBDatelbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signBDatelbl.setForeground(new java.awt.Color(255, 255, 255));
        signBDatelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signBDatelbl.setText("BIRTHDATE:");
        regPanel.add(signBDatelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 180, 30));

        regFName.setBackground(new java.awt.Color(255, 255, 255));
        regFName.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regFName.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regFName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 230, 30));

        signReligionlbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signReligionlbl.setForeground(new java.awt.Color(255, 255, 255));
        signReligionlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signReligionlbl.setText("RELIGION:");
        regPanel.add(signReligionlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 180, 30));

        regUser.setBackground(new java.awt.Color(255, 255, 255));
        regUser.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regUser.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 350, 30));

        regBDate.setBackground(new java.awt.Color(153, 0, 0));
        regBDate.setForeground(new java.awt.Color(255, 255, 255));
        regBDate.setDateFormatString("MMM dd, yyyy");
        regPanel.add(regBDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 350, 30));

        signLNamelbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signLNamelbl.setForeground(new java.awt.Color(255, 255, 255));
        signLNamelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signLNamelbl.setText("SURNAME:");
        regPanel.add(signLNamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 180, 30));

        regMI.setBackground(new java.awt.Color(255, 255, 255));
        regMI.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regMI.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regMI, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 70, 30));

        signMIlbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signMIlbl.setForeground(new java.awt.Color(255, 255, 255));
        signMIlbl.setText("M.I.:");
        regPanel.add(signMIlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, -1, 30));

        regReligion.setBackground(new java.awt.Color(255, 255, 255));
        regReligion.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regReligion.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regReligion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 350, 30));

        signCPasslbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signCPasslbl.setForeground(new java.awt.Color(255, 255, 255));
        signCPasslbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signCPasslbl.setText("CONFIRM PASSWORD:");
        regPanel.add(signCPasslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 180, 30));

        signupButton.setBackground(new java.awt.Color(51, 51, 51));
        signupButton.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signupButton.setForeground(new java.awt.Color(255, 255, 255));
        signupButton.setText("SIGN UP");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });
        regPanel.add(signupButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 680, 350, 30));

        regNationality.setBackground(new java.awt.Color(255, 255, 255));
        regNationality.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regNationality.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regNationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 350, 30));

        signNationalitylbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signNationalitylbl.setForeground(new java.awt.Color(255, 255, 255));
        signNationalitylbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signNationalitylbl.setText("NATIONALITY:");
        regPanel.add(signNationalitylbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 180, 30));

        signPasslbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signPasslbl.setForeground(new java.awt.Color(255, 255, 255));
        signPasslbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signPasslbl.setText("PASSWORD:");
        regPanel.add(signPasslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 180, 30));

        signAddresslbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signAddresslbl.setForeground(new java.awt.Color(255, 255, 255));
        signAddresslbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signAddresslbl.setText("ADDRESS:");
        regPanel.add(signAddresslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 180, 30));

        regCStatus.setBackground(new java.awt.Color(255, 255, 255));
        regCStatus.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regCStatus.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regCStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, 350, 30));

        signCStatlbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signCStatlbl.setForeground(new java.awt.Color(255, 255, 255));
        signCStatlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signCStatlbl.setText("CIVIL STATUS:");
        regPanel.add(signCStatlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 180, 30));

        signCntctlbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signCntctlbl.setForeground(new java.awt.Color(255, 255, 255));
        signCntctlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signCntctlbl.setText("CONTACT#:");
        regPanel.add(signCntctlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 180, 30));

        regContact.setBackground(new java.awt.Color(255, 255, 255));
        regContact.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regContact.setForeground(new java.awt.Color(0, 0, 0));
        regContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regContactKeyTyped(evt);
            }
        });
        regPanel.add(regContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, 350, 30));

        regGfname.setBackground(new java.awt.Color(255, 255, 255));
        regGfname.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regGfname.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regGfname, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 590, 350, 30));

        signGFNamelbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signGFNamelbl.setForeground(new java.awt.Color(255, 255, 255));
        signGFNamelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signGFNamelbl.setText("FULL NAME OF GUARDIAN:");
        regPanel.add(signGFNamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 180, 30));

        signGCntctlbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signGCntctlbl.setForeground(new java.awt.Color(255, 255, 255));
        signGCntctlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signGCntctlbl.setText("GUARDIAN CONTACT#:");
        regPanel.add(signGCntctlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 180, 30));

        regGcontact.setBackground(new java.awt.Color(255, 255, 255));
        regGcontact.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regGcontact.setForeground(new java.awt.Color(0, 0, 0));
        regGcontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regGcontactKeyTyped(evt);
            }
        });
        regPanel.add(regGcontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, 350, 30));

        regAddress.setBackground(new java.awt.Color(255, 255, 255));
        regAddress.setColumns(20);
        regAddress.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regAddress.setForeground(new java.awt.Color(0, 0, 0));
        regAddress.setLineWrap(true);
        regAddress.setRows(1);
        regAddress.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        addressScrollPane.setViewportView(regAddress);

        regPanel.add(addressScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 350, 30));

        endblank.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        endblank.setForeground(new java.awt.Color(255, 51, 51));
        endblank.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        endblank.setText("| | |");
        regPanel.add(endblank, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 730, 350, 30));

        regEmail.setBackground(new java.awt.Color(255, 255, 255));
        regEmail.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        regEmail.setForeground(new java.awt.Color(0, 0, 0));
        regPanel.add(regEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 350, 30));

        signSexlbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signSexlbl.setForeground(new java.awt.Color(255, 255, 255));
        signSexlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signSexlbl.setText("SEX:");
        regPanel.add(signSexlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, 30));

        signEmaillbl.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        signEmaillbl.setForeground(new java.awt.Color(255, 255, 255));
        signEmaillbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signEmaillbl.setText("E-MAIL:");
        regPanel.add(signEmaillbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 180, 30));

        jrButtonMALE.setSelected(true);
        jrButtonMALE.setText("male");
        regPanel.add(jrButtonMALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 60, 30));

        jrButtonFEMALE.setText("female");
        regPanel.add(jrButtonFEMALE, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 60, 30));

        regHOMETITLE.setBackground(new java.awt.Color(0, 0, 0));
        regHOMETITLE.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        regHOMETITLE.setForeground(new java.awt.Color(255, 255, 255));
        regHOMETITLE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regHOMETITLE.setText("HOME OF THE RED LIONS");
        regPanel.add(regHOMETITLE, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 840, -1));
        regPanel.add(bgLogoREG, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 500, 500));

        regPane.setViewportView(regPanel);

        registerPanel.add(regPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 900, 420));

        logoREG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enrollmentsystem/Images/newLogo.png"))); // NOI18N
        logoREG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoREG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoREGMouseClicked(evt);
            }
        });
        registerPanel.add(logoREG, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 40, 40));

        headTitleHS1.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        headTitleHS1.setForeground(new java.awt.Color(255, 255, 255));
        headTitleHS1.setText("HIGH SCHOOL");
        registerPanel.add(headTitleHS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        headTitle1.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        headTitle1.setForeground(new java.awt.Color(255, 255, 255));
        headTitle1.setText("JOSIAH CHRISTIAN");
        registerPanel.add(headTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 20));

        menubarLOGIN1.setBackground(new java.awt.Color(140, 0, 1));
        menubarLOGIN1.setForeground(new java.awt.Color(140, 0, 1));
        menubarLOGIN1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spacingLabel1.setForeground(new java.awt.Color(255, 255, 255));
        spacingLabel1.setText("|");
        menubarLOGIN1.add(spacingLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        regHome.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        regHome.setForeground(new java.awt.Color(255, 255, 255));
        regHome.setText("Home");
        regHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regHomeMouseClicked(evt);
            }
        });
        menubarLOGIN1.add(regHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        regAboutUs.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        regAboutUs.setForeground(new java.awt.Color(255, 255, 255));
        regAboutUs.setText("About Us");
        regAboutUs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regAboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regAboutUsMouseClicked(evt);
            }
        });
        menubarLOGIN1.add(regAboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        regContacts.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        regContacts.setForeground(new java.awt.Color(255, 255, 255));
        regContacts.setText("Contacts");
        regContacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regContacts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regContactsMouseClicked(evt);
            }
        });
        menubarLOGIN1.add(regContacts, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        regLogin.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        regLogin.setForeground(new java.awt.Color(255, 255, 255));
        regLogin.setText("Login");
        regLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regLoginMouseClicked(evt);
            }
        });
        menubarLOGIN1.add(regLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        regSignUp.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        regSignUp.setForeground(new java.awt.Color(0, 0, 0));
        regSignUp.setText("Sign Up");
        regSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regSignUpMouseClicked(evt);
            }
        });
        menubarLOGIN1.add(regSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        registerPanel.add(menubarLOGIN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 320, 40));

        regBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/enrollmentsystem/Images/newBg.jpg"))); // NOI18N
        regBG.setText("bg");
        registerPanel.add(regBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        getContentPane().add(registerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//START OF LOGIN FUNCTION
    private void loginSUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginSUMouseClicked
        loginPanel.setVisible(false);
        registerPanel.setVisible(true);
    }//GEN-LAST:event_loginSUMouseClicked

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        loginPanel.setVisible(true);
        registerPanel.setVisible(false);
    }//GEN-LAST:event_loginMouseClicked

    private void regLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regLoginMouseClicked
        loginPanel.setVisible(true);
        registerPanel.setVisible(false);
    }//GEN-LAST:event_regLoginMouseClicked

    private void regSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regSignUpMouseClicked
        loginPanel.setVisible(false);
        registerPanel.setVisible(true);
    }//GEN-LAST:event_regSignUpMouseClicked

    private void loginHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginHomeMouseClicked
        this.dispose();
        EnrollmentSystem es = new EnrollmentSystem();
        es.setVisible(true);
        EnrollmentSystem.HomePanel.setVisible(true);
        EnrollmentSystem.AboutUsPanel.setVisible(false);
        EnrollmentSystem.ContactsPanel.setVisible(false);
        es.setLocationRelativeTo(null);
        es.setSize(900, 490);
        es.pack();
    }//GEN-LAST:event_loginHomeMouseClicked

    private void loginAboutUsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginAboutUsMouseClicked
        this.dispose();
        EnrollmentSystem es = new EnrollmentSystem();
        es.setVisible(true);
        EnrollmentSystem.HomePanel.setVisible(false);
        EnrollmentSystem.AboutUsPanel.setVisible(true);
        EnrollmentSystem.ContactsPanel.setVisible(false);
        es.setLocationRelativeTo(null);
        es.setSize(900, 490);
        es.pack();
    }//GEN-LAST:event_loginAboutUsMouseClicked

    private void loginContactsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginContactsMouseClicked
        this.dispose();
        EnrollmentSystem es = new EnrollmentSystem();
        es.setVisible(true);
        EnrollmentSystem.HomePanel.setVisible(false);
        EnrollmentSystem.AboutUsPanel.setVisible(false);
        EnrollmentSystem.ContactsPanel.setVisible(true);
        es.setLocationRelativeTo(null);
        es.setSize(900, 490);
        es.pack();
    }//GEN-LAST:event_loginContactsMouseClicked

    private void regHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regHomeMouseClicked
        this.dispose();
        EnrollmentSystem es = new EnrollmentSystem();
        es.setVisible(true);
        EnrollmentSystem.HomePanel.setVisible(true);
        EnrollmentSystem.AboutUsPanel.setVisible(false);
        EnrollmentSystem.ContactsPanel.setVisible(false);
        es.setLocationRelativeTo(null);
        es.setSize(900, 490);
        es.pack();
    }//GEN-LAST:event_regHomeMouseClicked

    private void regAboutUsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regAboutUsMouseClicked
        this.dispose();
        EnrollmentSystem es = new EnrollmentSystem();
        es.setVisible(true);
        EnrollmentSystem.HomePanel.setVisible(false);
        EnrollmentSystem.AboutUsPanel.setVisible(true);
        EnrollmentSystem.ContactsPanel.setVisible(false);
        es.setLocationRelativeTo(null);
        es.setSize(900, 490);
        es.pack();
    }//GEN-LAST:event_regAboutUsMouseClicked

    private void regContactsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regContactsMouseClicked
        this.dispose();
        EnrollmentSystem es = new EnrollmentSystem();
        es.setVisible(true);
        EnrollmentSystem.HomePanel.setVisible(false);
        EnrollmentSystem.AboutUsPanel.setVisible(false);
        EnrollmentSystem.ContactsPanel.setVisible(true);
        es.setLocationRelativeTo(null);
        es.setSize(900, 490);
        es.pack();
    }//GEN-LAST:event_regContactsMouseClicked

    private void showPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassActionPerformed
        if (showPass.isSelected()) {
            loginPass.setEchoChar((char) 0);
        } else {
            loginPass.setEchoChar('✦');
        }
    }//GEN-LAST:event_showPassActionPerformed

    private void loginUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginUserFocusGained

        if (loginUser.getText().trim().toLowerCase().equals("enter your username!")) {
            loginUser.setText("");
        }
    }//GEN-LAST:event_loginUserFocusGained

    private void loginUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginUserFocusLost
        if (loginUser.getText().trim().equals("")
                || loginUser.getText().trim().toLowerCase().equals("enter your username!")) {
            loginUser.setText("enter your username!");
        }
    }//GEN-LAST:event_loginUserFocusLost

    private void loginPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginPassFocusGained
        String password = String.valueOf(loginPass.getPassword());

        if (password.trim().toLowerCase().equals("⠀⠀⠀⠀⠀⠀⠀⠀")) {
            loginPass.setText("");
        }
    }//GEN-LAST:event_loginPassFocusGained

    private void loginPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginPassFocusLost
        String password = String.valueOf(loginPass.getPassword());

        if (password.trim().equals("") || password.trim().toLowerCase().equals("⠀⠀⠀⠀⠀⠀⠀⠀")) {
            loginPass.setText("⠀⠀⠀⠀⠀⠀⠀⠀");
        }
    }//GEN-LAST:event_loginPassFocusLost

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        loginAction();
    }//GEN-LAST:event_loginButtonActionPerformed
//END OF LOGIN FUNCTION

//START OF REGISTRATION FUNCTION
    private void regPassSHOWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regPassSHOWActionPerformed
        if (regPassSHOW.isSelected()) {
            regPass.setEchoChar((char) 0);
        } else {
            regPass.setEchoChar('✦');
        }
    }//GEN-LAST:event_regPassSHOWActionPerformed

    private void regCPassSHOWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regCPassSHOWActionPerformed
        if (regCPassSHOW.isSelected()) {
            regCPass.setEchoChar((char) 0);
        } else {
            regCPass.setEchoChar('✦');
        }
    }//GEN-LAST:event_regCPassSHOWActionPerformed

    private void regPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_regPassFocusGained
        String password = String.valueOf(regPass.getPassword());

        if (password.trim().toLowerCase().equals("⠀⠀⠀⠀⠀⠀⠀⠀")) {
            regPass.setText("");
        }
    }//GEN-LAST:event_regPassFocusGained

    private void regPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_regPassFocusLost
        String password = String.valueOf(regPass.getPassword());

        if (password.trim().equals("") || password.trim().toLowerCase().equals("⠀⠀⠀⠀⠀⠀⠀⠀")) {
            regPass.setText("⠀⠀⠀⠀⠀⠀⠀⠀");
        }
    }//GEN-LAST:event_regPassFocusLost

    private void regCPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_regCPassFocusGained
        String password = String.valueOf(regCPass.getPassword());

        if (password.trim().toLowerCase().equals("⠀⠀⠀⠀⠀⠀⠀⠀")) {
            regCPass.setText("");
        }
    }//GEN-LAST:event_regCPassFocusGained

    private void regCPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_regCPassFocusLost
        String password = String.valueOf(regCPass.getPassword());

        if (password.trim().equals("") || password.trim().toLowerCase().equals("⠀⠀⠀⠀⠀⠀⠀⠀")) {
            regCPass.setText("⠀⠀⠀⠀⠀⠀⠀⠀");
        }
    }//GEN-LAST:event_regCPassFocusLost

    private void regContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regContactKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        } // makes the jtxtField type numbers only
    }//GEN-LAST:event_regContactKeyTyped

    private void regGcontactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regGcontactKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        } // makes the jtxtField type numbers only
    }//GEN-LAST:event_regGcontactKeyTyped

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        String username = regUser.getText();
        String password = String.valueOf(regPass.getPassword());
        String status = "UNENROLLED";
        String fname = regFName.getText();
        String lname = regLName.getText();
        String mInit = regMI.getText();
        String sex = "male";

        if (jrButtonFEMALE.isSelected()) {
            sex = "female";
        }

        String email = regEmail.getText();
        String address = regAddress.getText();
        String birthdate = Date_Format.format(regBDate.getDate());
        String religion = regReligion.getText();
        String nationality = regNationality.getText();
        String civilstat = regCStatus.getText();
        String contactnumber = regContact.getText();
        String guardian = regGfname.getText();
        String guardiannum = regGcontact.getText();
        String fkSecCode = "N/A";
        String accType = "STUDENT";

        if (verifyFields()) {
            if (!checkUsername(username)) {
                try {
                    conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                    ps = conn.prepareStatement("INSERT INTO account "
                            + "VALUES(?,?,?)");

                    ps.setString(1, username.replaceAll(" ", ""));
                    ps.setString(2, password);
                    ps.setString(3, accType);

                    if (ps.executeUpdate() != 0) {

                        ps = conn.prepareStatement("INSERT INTO enrollee "
                                + "(e_stat,e_fname,e_lname,e_minit,e_sex,e_email,"
                                + " e_address,e_bdate,e_religion,e_nationality,e_civilstatus,e_contactnum,e_guardian,"
                                + "e_guardiannum, sec_code, a_user) "
                                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                        ps.setString(1, status);
                        ps.setString(2, fname);
                        ps.setString(3, lname);
                        ps.setString(4, mInit);
                        ps.setString(5, sex);
                        ps.setString(6, email);
                        ps.setString(7, address);
                        ps.setString(8, birthdate);
                        ps.setString(9, religion);
                        ps.setString(10, nationality);
                        ps.setString(11, civilstat);
                        ps.setString(12, contactnumber);
                        ps.setString(13, guardian);
                        ps.setString(14, guardiannum);
                        ps.setString(15, fkSecCode);
                        ps.setString(16, username);

                        if (ps.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(null, "Your account has been created!");
                            regUser.setText("");
                            regPass.setText("");
                            regCPass.setText("");
                            regBDate.setCalendar(null);
                            regNationality.setText("");
                            regEmail.setText("");
                            regAddress.setText("");
                            regFName.setText("");
                            regMI.setText("");
                            regLName.setText("");
                            regReligion.setText("");
                            regCStatus.setText("");
                            regContact.setText("");
                            regGfname.setText("");
                            regGcontact.setText("");

                            loginPanel.setVisible(true);
                            registerPanel.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error! Please check your information!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! Please check your information!");
                    }
                } catch (HeadlessException | SQLException e) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }//GEN-LAST:event_signupButtonActionPerformed

    private void forPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forPassMouseClicked
        ForgotPassForm fpf = new ForgotPassForm();
        fpf.setVisible(true);
        fpf.setLocationRelativeTo(null);
        fpf.pack();
    }//GEN-LAST:event_forPassMouseClicked

    private void loginUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginAction();
        }
    }//GEN-LAST:event_loginUserKeyPressed

    private void loginPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginAction();
        }
    }//GEN-LAST:event_loginPassKeyPressed

    private void logoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoMouseClicked
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Enrollment System",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_logoMouseClicked

    private void logoREGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoREGMouseClicked
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Enrollment System",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_logoREGMouseClicked

    public boolean verifyFields() {
        String username = regUser.getText();
        String password = String.valueOf(regPass.getPassword());
        String confirmpassword = String.valueOf(regCPass.getPassword());
        String fname = regFName.getText();
        String lname = regLName.getText();
        String mInit = regMI.getText();
        String email = regEmail.getText();
        String address = regAddress.getText();
        String birthdate = Date_Format.format(regBDate.getDate());
        String religion = regReligion.getText();
        String nationality = regNationality.getText();
        String civilstat = regCStatus.getText();
        String contactnumber = regContact.getText();
        String guardian = regGfname.getText();
        String guardiannum = regGcontact.getText();

        // checking if the fields are empty
        if (username.trim().equals("")
                || password.trim().equals("⠀⠀⠀⠀⠀⠀⠀⠀")
                || confirmpassword.trim().equals("⠀⠀⠀⠀⠀⠀⠀⠀")
                || fname.trim().equals("")
                || lname.trim().equals("")
                || mInit.trim().equals("")
                || email.trim().equals("")
                || address.trim().equals("")
                || birthdate.trim().equals("")
                || religion.trim().equals("")
                || nationality.trim().equals("")
                || civilstat.trim().equals("")
                || contactnumber.trim().equals("")
                || guardian.trim().equals("")
                || guardiannum.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Empty Fields", 2);
            return false;
        } else if (!password.equals(confirmpassword)) //check if password and password confirmation are equal
        {
            JOptionPane.showMessageDialog(null, "Passwords doesn't match!", "Confirm password!", 2);
            return false;
        } else // if everything is fine
        {
            return true;
        }
    }

    public boolean checkUsername(String username) {

        boolean username_exists = false;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM account WHERE a_user = ?");

            ps.setString(1, username);
            rset = ps.executeQuery();

            if (rset.next()) {
                username_exists = true;
                JOptionPane.showMessageDialog(null, "Sorry, This username already existed! Try another username", "Username exists!", 2);
            }

        } catch (Exception e) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, e);
        }

        return username_exists;
    }

//END OF REGISTRATION FUNCTION
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane addressScrollPane;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bgLogoREG;
    private javax.swing.JLabel endblank;
    private javax.swing.JLabel forPass;
    private javax.swing.JLabel headTitle;
    private javax.swing.JLabel headTitle1;
    private javax.swing.JLabel headTitleHS;
    private javax.swing.JLabel headTitleHS1;
    private javax.swing.JLabel headlineLogin;
    private javax.swing.JLabel hiddenLABEL;
    public static javax.swing.JLabel hiddenLABELfk;
    public static javax.swing.JLabel hiddenLABELpri;
    private javax.swing.JRadioButton jrButtonFEMALE;
    private javax.swing.JRadioButton jrButtonMALE;
    private javax.swing.JLabel login;
    private javax.swing.JLabel loginAboutUs;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginContacts;
    private javax.swing.JLabel loginHome;
    private javax.swing.JLabel loginHomeTitle;
    public static javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField loginPass;
    private javax.swing.JLabel loginSU;
    public static javax.swing.JTextField loginUser;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logoREG;
    private javax.swing.JPanel menubarLOGIN;
    private javax.swing.JPanel menubarLOGIN1;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel regAboutUs;
    private javax.swing.JTextArea regAddress;
    private com.toedter.calendar.JDateChooser regBDate;
    private javax.swing.JLabel regBG;
    private javax.swing.JPasswordField regCPass;
    private javax.swing.JCheckBox regCPassSHOW;
    private javax.swing.JTextField regCStatus;
    private javax.swing.JTextField regContact;
    private javax.swing.JLabel regContacts;
    private javax.swing.JTextField regEmail;
    private javax.swing.JTextField regFName;
    private javax.swing.JTextField regGcontact;
    private javax.swing.JTextField regGfname;
    private javax.swing.JLabel regHOMETITLE;
    private javax.swing.JLabel regHome;
    private javax.swing.JTextField regLName;
    private javax.swing.JLabel regLogin;
    private javax.swing.JTextField regMI;
    private javax.swing.JTextField regNationality;
    private javax.swing.JScrollPane regPane;
    private javax.swing.JPanel regPanel;
    private javax.swing.JPasswordField regPass;
    private javax.swing.JCheckBox regPassSHOW;
    private javax.swing.JTextField regReligion;
    private javax.swing.JLabel regSignUp;
    private javax.swing.JTextField regUser;
    public static javax.swing.JPanel registerPanel;
    private javax.swing.JCheckBox showPass;
    private javax.swing.JLabel signAddresslbl;
    private javax.swing.JLabel signBDatelbl;
    private javax.swing.JLabel signCPasslbl;
    private javax.swing.JLabel signCStatlbl;
    private javax.swing.JLabel signCntctlbl;
    private javax.swing.JLabel signEmaillbl;
    private javax.swing.JLabel signFNamelbl;
    private javax.swing.JLabel signGCntctlbl;
    private javax.swing.JLabel signGFNamelbl;
    private javax.swing.JLabel signLNamelbl;
    private javax.swing.JLabel signMIlbl;
    private javax.swing.JLabel signNationalitylbl;
    private javax.swing.JLabel signPasslbl;
    private javax.swing.JLabel signReligionlbl;
    private javax.swing.JLabel signSexlbl;
    private javax.swing.JLabel signUserlbl;
    private javax.swing.JButton signupButton;
    private javax.swing.JLabel spacingLabel;
    private javax.swing.JLabel spacingLabel1;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}

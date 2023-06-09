/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollmentsystem;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author sevhe
 */
public class subjectDetailsForm extends javax.swing.JFrame {

    /**
     * Creates new form subjectDetailsForm
     */
    public subjectDetailsForm() {
        initComponents();
        descDET.setEditable(false);
        imageCaller();
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
                = {"/Images/lion1.png"};

        imageScale(link[0], bgLogo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subDet = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        subDescScroll = new javax.swing.JScrollPane();
        descDET = new javax.swing.JTextArea();
        dayLBL = new javax.swing.JLabel();
        snLBL = new javax.swing.JLabel();
        ttlDesc = new javax.swing.JLabel();
        teachLBL = new javax.swing.JLabel();
        endDET = new javax.swing.JLabel();
        etLBL = new javax.swing.JLabel();
        stLBL = new javax.swing.JLabel();
        startDET = new javax.swing.JLabel();
        dayDET = new javax.swing.JLabel();
        subNameDET = new javax.swing.JLabel();
        teachDET = new javax.swing.JLabel();
        bgLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subDet.setBackground(new java.awt.Color(153, 153, 153));
        subDet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 177, 0), 2));
        subDet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("CLOSE");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 177, 0), 2));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        subDet.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 80, 30));

        subDescScroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 177, 0), 2));

        descDET.setBackground(new java.awt.Color(153, 153, 153));
        descDET.setColumns(20);
        descDET.setForeground(new java.awt.Color(0, 0, 0));
        descDET.setLineWrap(true);
        descDET.setRows(1);
        descDET.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        subDescScroll.setViewportView(descDET);

        subDet.add(subDescScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 520, 130));

        dayLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dayLBL.setForeground(new java.awt.Color(0, 0, 0));
        dayLBL.setText("Day:");
        subDet.add(dayLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 70, 30));

        snLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        snLBL.setForeground(new java.awt.Color(0, 0, 0));
        snLBL.setText("Subject Name:");
        subDet.add(snLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 30));

        ttlDesc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ttlDesc.setForeground(new java.awt.Color(0, 0, 0));
        ttlDesc.setText("Description:");
        subDet.add(ttlDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, 30));

        teachLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        teachLBL.setForeground(new java.awt.Color(0, 0, 0));
        teachLBL.setText("Teacher:");
        subDet.add(teachLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 70, 30));

        endDET.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        endDET.setForeground(new java.awt.Color(255, 255, 255));
        endDET.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        endDET.setText("DETAILS");
        subDet.add(endDET, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 280, 30));

        etLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        etLBL.setForeground(new java.awt.Color(255, 255, 255));
        etLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etLBL.setText("End Time:");
        subDet.add(etLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 280, 30));

        stLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stLBL.setForeground(new java.awt.Color(0, 0, 0));
        stLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stLBL.setText("Start Time:");
        subDet.add(stLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 280, 30));

        startDET.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        startDET.setForeground(new java.awt.Color(0, 0, 0));
        startDET.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        startDET.setText("DETAILS");
        subDet.add(startDET, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 280, 30));

        dayDET.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dayDET.setForeground(new java.awt.Color(0, 0, 0));
        dayDET.setText("DETAILS");
        subDet.add(dayDET, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 140, 30));

        subNameDET.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        subNameDET.setForeground(new java.awt.Color(0, 0, 0));
        subNameDET.setText("DETAILS");
        subDet.add(subNameDET, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 140, 30));

        teachDET.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        teachDET.setForeground(new java.awt.Color(0, 0, 0));
        teachDET.setText("DETAILS");
        subDet.add(teachDET, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 220, 30));
        subDet.add(bgLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 190, 160));

        getContentPane().add(subDet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(subjectDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(subjectDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(subjectDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(subjectDetailsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new subjectDetailsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgLogo;
    public static javax.swing.JLabel dayDET;
    private javax.swing.JLabel dayLBL;
    public static javax.swing.JTextArea descDET;
    public static javax.swing.JLabel endDET;
    private javax.swing.JLabel etLBL;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel snLBL;
    private javax.swing.JLabel stLBL;
    public static javax.swing.JLabel startDET;
    private javax.swing.JScrollPane subDescScroll;
    private javax.swing.JPanel subDet;
    public static javax.swing.JLabel subNameDET;
    public static javax.swing.JLabel teachDET;
    private javax.swing.JLabel teachLBL;
    private javax.swing.JLabel ttlDesc;
    // End of variables declaration//GEN-END:variables
}

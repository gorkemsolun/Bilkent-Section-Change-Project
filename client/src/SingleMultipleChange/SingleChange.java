/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SingleMultipleChange;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import main.Courses;
import main.Student;

/**
 *
 * @author Pc
 */
public class SingleChange extends javax.swing.JFrame {

    /**
     * Creates new form SingleChange
     */
    private Student student;
    private Courses courses;
    public SingleChange(Student student, Courses courses) {
        this.student = student;
        this.courses = courses;
        this.setLocationRelativeTo(null);
        initComponents2(this);
    }
    
    public void close(){
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void initComponents2(SingleChange singlechange) {
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(new java.awt.Color(220, 172, 146));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 350));
        jPanel1.setLayout(new java.awt.GridLayout(5, 5, 20, 20));
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBackground(new java.awt.Color(220, 172, 146));
        jLabelClose = new javax.swing.JLabel();
        jLabelClose.setFont(new java.awt.Font("Lucida Handwriting", 1, 48)); // NOI18N
        jLabelClose.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClose.setText("X");
        jLabelClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });
        for (int i = 0; i < courses.getSize(); i++) {
            JButton button = new javax.swing.JButton(courses.getCourse(i).getName());
            button.setBackground(new java.awt.Color(220, 172, 146));
            button.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
            button.setForeground(new java.awt.Color(172, 112, 96));
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
            button.setText(courses.getCourse(i).getName()+"-"+courses.getCourse(i).getSection().substring(1));
            button.setToolTipText("");
            button.setBorder(null);
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            button.setMaximumSize(new java.awt.Dimension(340, 117));
            button.setMinimumSize(new java.awt.Dimension(340, 117));
            button.setPreferredSize(new java.awt.Dimension(340, 117));
            button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    singlechange.close();
                }
            });
            jPanel1.add(button);
        }
        jPanel2.setBackground(new java.awt.Color(220, 172, 146));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 990, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelClose)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelClose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(220, 172, 146));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 350));
        jPanel1.setLayout(new java.awt.GridLayout(5, 5, 20, 20));

        button.setBackground(new java.awt.Color(220, 172, 146));
        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
        button.setBorder(null);
        button.setMaximumSize(new java.awt.Dimension(533, 303));
        button.setMinimumSize(new java.awt.Dimension(533, 303));
        button.setPreferredSize(new java.awt.Dimension(533, 303));
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        jPanel1.add(button);

        jToggleButton2.setBackground(new java.awt.Color(220, 172, 146));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
        jToggleButton2.setBorder(null);
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton2);

        jToggleButton4.setBackground(new java.awt.Color(220, 172, 146));
        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
        jToggleButton4.setBorder(null);
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jToggleButton4);

        jToggleButton3.setBackground(new java.awt.Color(220, 172, 146));
        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
        jToggleButton3.setBorder(null);
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jToggleButton3);

        jToggleButton6.setBackground(new java.awt.Color(220, 172, 146));
        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
        jToggleButton6.setBorder(null);
        jToggleButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton6);

        jToggleButton5.setBackground(new java.awt.Color(220, 172, 146));
        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
        jToggleButton5.setBorder(null);
        jToggleButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jToggleButton5);

        jButton1.setBackground(new java.awt.Color(220, 172, 146));
        jButton1.setFont(new java.awt.Font("Baskerville", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(172, 112, 96));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SingleMultipleChange/button2.png"))); // NOI18N
        jButton1.setText("SEND");
        jButton1.setBorder(null);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jButton1);

        jPanel2.setBackground(new java.awt.Color(220, 172, 146));

        jLabelClose.setFont(new java.awt.Font("Lucida Handwriting", 1, 48)); // NOI18N
        jLabelClose.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClose.setText("X");
        jLabelClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelClose))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelClose)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabelCloseMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton button;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    // End of variables declaration//GEN-END:variables
}
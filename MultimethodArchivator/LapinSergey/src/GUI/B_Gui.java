/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * B_Gui.java
 *
 * Created on 17.06.2009, 22:10:39
 */

package GUI;

import Compress.Coder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class B_Gui extends javax.swing.JFrame {
    private File theInputFile;
    private static B_Gui g;

    /** Creates new form B_Gui */
    public B_Gui() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        sfpLabel = new javax.swing.JLabel();
        sfpTextField = new javax.swing.JTextField();
        sfpButton = new javax.swing.JButton();
        mProgressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        rfpLabel = new javax.swing.JLabel();
        rfpTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        acLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        acComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sfpLabel.setText("Выберите файл:");

        sfpButton.setText("Обзор..");
        sfpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sfpButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        rfpLabel.setText("Конечный файл:");

        acLabel.setText("Алгоритм:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Сжать", "Расжать" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        acComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Хаффман", "Арифметическое кодирование" }));
        acComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acComboBoxupdRfp(evt);
            }
        });
        acComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Действие:");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, 0, 180, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(acLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(acComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(acComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(acLabel))
                .add(30, 30, 30)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 431, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(sfpLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(sfpTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 83, Short.MAX_VALUE)
                        .add(sfpButton))
                    .add(mProgressBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(rfpLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(rfpTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sfpTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(sfpLabel))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sfpButton))
                    .add(layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(26, 26, 26)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rfpLabel)
                    .add(rfpTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton)
                    .add(cancelButton))
                .add(18, 18, 18)
                .add(mProgressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sfpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sfpButtonActionPerformed
        int result = jFileChooser1.showOpenDialog(this);
        if(result == JFileChooser.CANCEL_OPTION) return;
        theInputFile = jFileChooser1.getSelectedFile();
        sfpTextField.setText(theInputFile.getPath());
        setRfp();
}//GEN-LAST:event_sfpButtonActionPerformed

    private void acComboBoxupdRfp(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acComboBoxupdRfp
        setRfp();
}//GEN-LAST:event_acComboBoxupdRfp

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        g.setVisible(false);
        g.dispose();
        g.removeAll();
}//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(null!=theInputFile){
            if(jComboBox1.getSelectedIndex() == 0){
                if(acComboBox.getSelectedIndex() == 0){
                    try {
                        Coder input = new Coder();
                        input.CalcFrequency(theInputFile.getAbsolutePath());
                        JOptionPane.showMessageDialog(g, "Выполняем сжатие по Хаффману");
                        try {
                            input.CodeHaffman(theInputFile.getAbsolutePath(), rfpTextField.getText());
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(acComboBox.getSelectedIndex() == 1){
                        try {
                            Coder input = new Coder();
                            input.CalcFrequency(theInputFile.getAbsolutePath());
                            JOptionPane.showMessageDialog(g, "Выполняем сжатие арифметического кодирования");                            
                            input.CodeArith(theInputFile.getAbsolutePath(), rfpTextField.getText());                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                        Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            } else if(jComboBox1.getSelectedIndex() == 1) {
                if(theInputFile.getName().contains(".huff") && acComboBox.getSelectedIndex() == 0){
                    try {
                        Coder input = new Coder();
                        input.CalcFrequency(theInputFile.getAbsolutePath());
                        JOptionPane.showMessageDialog(g, "Выполняем расжатие по Хаффману");
                        try {
                            input.DecodeHaffman(theInputFile.getAbsolutePath(), rfpTextField.getText());
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(theInputFile.getName().contains(".arithm") && acComboBox.getSelectedIndex() == 1){
                    try {
                            Coder input = new Coder();
                            input.CalcFrequency(theInputFile.getAbsolutePath());
                            JOptionPane.showMessageDialog(g, "Выполняем расжатие арифметического кодирования");
                            input.DecodeArith(theInputFile.getAbsolutePath(), rfpTextField.getText());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                        Logger.getLogger(B_Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            }
        }else
            JOptionPane.showMessageDialog(g, "Вначале выберите файл");
}//GEN-LAST:event_okButtonActionPerformed

    private void acComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acComboBoxActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                g = new B_Gui();
				g.setVisible(true);
				g.setTitle("Архиватор студенческий");
            }
        });
    }

    private void setRfp(){
		if(null!=theInputFile){
			String suff = "";
			if(acComboBox.getSelectedIndex() == 0) suff = ".huff";
			if(acComboBox.getSelectedIndex() == 1) suff = ".arithm";
			rfpTextField.setText(theInputFile.getPath()+suff);
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox acComboBox;
    private javax.swing.JLabel acLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar mProgressBar;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel rfpLabel;
    private javax.swing.JTextField rfpTextField;
    private javax.swing.JButton sfpButton;
    private javax.swing.JLabel sfpLabel;
    private javax.swing.JTextField sfpTextField;
    // End of variables declaration//GEN-END:variables

}

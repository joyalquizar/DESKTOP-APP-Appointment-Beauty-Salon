/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package BeautySalon;

        import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
  import java.sql.SQLException;      
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import java.awt.Component;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.Icon;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author effel joy alquizar
 */
public class Payment extends javax.swing.JInternalFrame {
  public void refreshTableServicess() {
      
  }
  
   java.sql.Connection con;
    Statement st;
    PreparedStatement pst;
    /**
     * Creates new form Payment
     */
    public Payment() {
        initComponents();
          this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        
        loadPayment();
    }
public void refreshPaymentFromDatabase() {
    loadPayment(); // Re-query DB and reload table
}
    /**
     * Creates new form Services
     */
public void loadPayment() {
    try {
        DefaultTableModel model = (DefaultTableModel) PaymentTable.getModel();
        model.setRowCount(0); // Clear existing rows

        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
        String sql = "SELECT * FROM tblservices";
        PreparedStatement pst = con.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery();


        int count = 1;
while (rs.next()) {
    int id = rs.getInt("ID"); // Or use String.valueOf(...) if you need it as a String
    String ServiceName = rs.getString("ServiceName");
    String price = rs.getString("Cost");
    String desc = rs.getString("Description");
    byte[] imageBytes = rs.getBytes("Image");

    ImageIcon icon = new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                  String formattedPrice = "₱" + price ;
model.addRow(new Object[]{count++, ServiceName, formattedPrice, desc, icon});

DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
PaymentTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

}

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading services: " + e.getMessage());
    }

PaymentTable.setRowHeight(90);
PaymentTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            return new JLabel((ImageIcon) value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
});
PaymentTable.setRowHeight(90);
PaymentTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public void setValue(Object value) {
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
            setText("");
        } else {
            setIcon(null);
            super.setValue(value);
        }
    }
});

PaymentTable.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent evt) {
        int row = PaymentTable.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            String serviceName = PaymentTable.getValueAt(row, 1).toString(); // assuming 1 is the name column
            String price = PaymentTable.getValueAt(row, 2).toString();
            String description = PaymentTable.getValueAt(row, 3).toString();

            // You can display details or open another window
            JOptionPane.showMessageDialog(null, "Your item selected:\n" + serviceName + "\n" + price + "\n" + description);
        }
    }
});

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
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PaymentTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        timePicker1 = new com.github.lgooddatepicker.components.TimePicker();
        jSpinner1 = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        txtNo = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        txtService = new javax.swing.JTextField();
        txtAbout = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1526, 871));

        jPanel1.setPreferredSize(new java.awt.Dimension(1526, 871));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153), 2));

        jLabel18.setText("POS PAYMENT");
        jLabel18.setFont(new java.awt.Font("Imprint MT Shadow", 1, 48)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 153));

        searchField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("SEARCH");
        jButton1.setBackground(new java.awt.Color(153, 0, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 70));

        PaymentTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153), 2));
        PaymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Service Name", "Cost", "Description", "Image"
            }
        ));
        PaymentTable.setGridColor(new java.awt.Color(255, 153, 153));
        PaymentTable.setShowGrid(true);
        PaymentTable.setSurrendersFocusOnKeystroke(true);
        PaymentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PaymentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PaymentTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1290, 390));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));

        jLabel20.setText("BOOK NOW");
        jLabel20.setFont(new java.awt.Font("Imprint MT Shadow", 1, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153), 2));
        jPanel6.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 240, 40));

        timePicker1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel6.add(timePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 240, 40));

        jSpinner1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel6.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 240, 40));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Co. Alquizar, Effel Joy ", "Co. Dejarlo, Daisy", "Co. Gacusan, Angela", "Co. Joseph Maminta", "Co. Odtojan, Meljean", "Co. Villena, Nicole", "Co. Segovia, John lee" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 240, 40));

        jButton4.setText("CLEAR");
        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 120, 30));

        jButton5.setText("SEE TOTAL");
        jButton5.setBackground(new java.awt.Color(102, 0, 255));
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 110, 30));

        jLabel6.setText("STYLIST :");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("QUANTITY :");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("TIME ;");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("DATE :");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 460, 400, 330));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));

        jLabel19.setText("SELECT PAYMENT");
        jLabel19.setFont(new java.awt.Font("Imprint MT Shadow", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(238, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jPanel3.setForeground(new java.awt.Color(255, 204, 204));

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 220, 180));

        txtNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153), 2));
        jPanel8.add(txtNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 90, 40));

        txtCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel8.add(txtCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 230, 40));

        txtService.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel8.add(txtService, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 230, 40));

        txtAbout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel8.add(txtAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 220, 130));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("NO :");
        jPanel8.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("COST :");
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("SERVICE NAME :");
        jPanel8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("DESCRIPTION :");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, -1, -1));

        jButton2.setText("CLEAR");
        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 282, 120, 30));

        jButton3.setText("SELECT");
        jButton3.setBackground(new java.awt.Color(153, 0, 153));
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 282, 120, 30));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 890, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PaymentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaymentTableMouseClicked
        int selectedRow = PaymentTable.getSelectedRow();
if (selectedRow >= 0) {
    // Get text data
    String id = PaymentTable.getValueAt(selectedRow, 0).toString();
    String service = PaymentTable.getValueAt(selectedRow, 1).toString();
    String cost = PaymentTable.getValueAt(selectedRow, 2).toString();
    String about = PaymentTable.getValueAt(selectedRow, 3).toString();

    // Set to text fields
    txtNo.setText(id);
    txtService.setText(service);
    txtCost.setText(cost);
    txtAbout.setText(about);

    // Handle image
    Object imageObject = PaymentTable.getValueAt(selectedRow, 4);
    if (imageObject instanceof ImageIcon) {
        ImageIcon icon = (ImageIcon) imageObject;
        Image img = icon.getImage();
        int width = lblImage.getWidth() > 0 ? lblImage.getWidth() : 120;
        int height = lblImage.getHeight() > 0 ? lblImage.getHeight() : 120;

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setVerticalAlignment(SwingConstants.CENTER);
        lblImage.setIcon(scaledIcon);
        lblImage.setText(""); // Clear placeholder
    } else {
        lblImage.setIcon(null);
        lblImage.setText("No Image");
    }
}
    }//GEN-LAST:event_PaymentTableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       String id = txtNo.getText();
    String service = txtService.getText();
    String cost =  txtCost.getText();
    String about = txtAbout.getText();

    // Show pop-up with selected service info
    String message = "Selected Services:\n" +
                     "id: " + id + "\n" +
                     "Service: " + service + "\n" +
                     "Cost: " + cost + "\n" +
                     "About: " + about;

    JOptionPane.showMessageDialog(null, message, "Successfully Service Selected", JOptionPane.INFORMATION_MESSAGE);

    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 String id = txtNo.getText();
String service = txtService.getText();
String cost = txtCost.getText();
String about = txtAbout.getText();
Icon image = lblImage.getIcon();

// Booking details
String date = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
String time = timePicker1.getText(); // ✅ use the UI time picker (already placed in form)

int quantity = (Integer) jSpinner1.getValue();
// Calculate total
int total = Integer.parseInt(cost.replace("₱", "").trim()) * quantity;
String specialist = jComboBox1.getSelectedItem().toString();


// Open new summary frame
paymentinformation payment = new paymentinformation(
    id, service, cost, about, image, date, time, quantity,specialist ,total );
payment.setVisible(true);

                           
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int response = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to clear the items?",
        "Confirm Clear",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.WARNING_MESSAGE
);

if (response == JOptionPane.OK_OPTION) {
    txtNo.setText("");
    txtService.setText("");
    txtCost.setText("");
    txtAbout.setText("");
    lblImage.setIcon(null);
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
             String search = searchField.getText(); // replace with your actual search field
    DefaultTableModel model = (DefaultTableModel) PaymentTable.getModel();
    model.setRowCount(0); // clear previous results

    String query = "SELECT * FROM tblservices WHERE id LIKE ? OR ServiceName LIKE ?";
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setString(1, "%" + search + "%");
        pst.setString(2, "%" + search + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String serviceName = rs.getString("ServiceName");
            String cost = rs.getString("Cost");
            String desc = rs.getString("Description");

            // For image (stored as BLOB)
            byte[] imgBytes = rs.getBytes("Image");
            ImageIcon icon = null;
            if (imgBytes != null) {
                ImageIcon originalIcon = new ImageIcon(imgBytes);
                Image scaledImg = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImg);
            }

            model.addRow(new Object[]{id, serviceName, cost, desc, icon});
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Search failed: " + ex.getMessage());
    }



    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PaymentTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField searchField;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    private javax.swing.JTextField txtAbout;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtNo;
    private javax.swing.JTextField txtService;
    // End of variables declaration//GEN-END:variables
}

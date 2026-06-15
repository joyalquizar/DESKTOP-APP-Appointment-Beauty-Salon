/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeautySalon;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.temporal.TemporalQueries.localTime;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;



/**
 *
 * @author effel joy alquizar
 */

public class RecieptsWindow extends javax.swing.JFrame {

Connection con;
    Statement st;
    PreparedStatement pst;

    public RecieptsWindow() {
        con = DBConnection.connect(); // ✅ correct
    }

    public RecieptsWindow(String bookingNumber, String clientName, String numberInfo,
                      String serviceName, String bookingDate, String bookingTime,
                      String unitCost, String quantity, String total,
                      String paymentMethod, String specialist,
                      String cash, String balance) {
        

    initComponents();
    Image img = new ImageIcon(getClass().getResource("/images/logo.jpg")).getImage();
    
    // Set the icon image for the JFrame
    this.setIconImage(img);

    lblBookingNumber.setText(bookingNumber);
    lblClientName.setText(clientName);
    lblNumberInfo.setText(numberInfo);

    lblServiceName.setText(serviceName);
    lblBookingDate.setText(bookingDate);
    lblBookingTime.setText(bookingTime);
    lblUnitCost.setText(unitCost);
    lblQuantity.setText(quantity);
    lblSpecialist.setText(specialist);

    lblPaymentMethod.setText(paymentMethod);
    lblTotalCost.setText(total);
    lblCash.setText(cash);
    lblBalance.setText(balance);
   
}


    java.sql.Connection conn = DBConnection.getConnection();
    
    
     private static final String DbName = "bpmsdb";
    private static final String DbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String DbUrl = "jdbc:mysql://localhost:3306/bpmsdb";
    private static final String DbUsername = "root";
    private static final String DbPassword = "";
  

public java.sql.Connection connectToDatabase() {
    java.sql.Connection con = null;
    try {
        Class.forName(DbDriver);
        con = java.sql.DriverManager.getConnection(DbUrl, DbUsername, DbPassword);
        System.out.println("✅ Database connected.");
    } catch (Exception e) {
        System.out.println("❌ Database connection failed: " + e.getMessage());
        e.printStackTrace();
    }
    return con;
}
    
  private double parseCurrencyLabel(JLabel label) {
    String text = label.getText().replace("₱", "").replace(",", "").trim();
    return Double.parseDouble(text);
}  
public Time convertToSqlTimeFormat(String timeString) throws ParseException {
    // Normalize: remove spaces, make uppercase
    timeString = timeString.replaceAll("\\s+", "").toUpperCase(); // "1:30 am" → "1:30AM"

    SimpleDateFormat sdf = new SimpleDateFormat("h:mma", Locale.ENGLISH); // matching "1:30AM"
    sdf.setLenient(false);
    java.util.Date date = sdf.parse(timeString);
    return new java.sql.Time(date.getTime());
}


    
   

    
    
    
 

   
    public void setBookingNumber(String bookingNo) {
    lblBookingNumber.setText(bookingNo);
}
public void setClientName(String name) {
    lblClientName.setText(name);
}
public void setNumberInfo(String number) {
    lblNumberInfo.setText(number); // assuming number info shares label
}
public void setServiceName(String serviceName) {
    lblServiceName.setText(serviceName);
}
public void setUnitCost(String cost) {
    lblBookingDate.setText(cost);
}
public void setQuantity(String qty) {
    lblBookingTime.setText(qty);
}
public void setSpecialist(String spec) {
    lblSpecialist.setText(spec);
}
public void setPaymentMethod(String method) {
    lblPaymentMethod.setText(method);
}
public void setTotalPay(String total) {
    lblTotalCost.setText(total);
}
public void setCash(String cash) {
    lblCash.setText(cash);
}
public void setBalance(String bal) {
    lblBalance.setText(bal);
}

public void saveReceiptAsPDF(JPanel panel, String filePath, JFrame parent) {
    try {
        // Render JPanel to image
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(
                panel.getWidth(), panel.getHeight(), java.awt.image.BufferedImage.TYPE_INT_RGB);
        panel.paint(image.getGraphics());

        // Save image temporarily
        java.io.File tempImage = new java.io.File("temp_receipt.png");
        javax.imageio.ImageIO.write(image, "png", tempImage);

        // Create PDF
        org.apache.pdfbox.pdmodel.PDDocument document = new org.apache.pdfbox.pdmodel.PDDocument();
        org.apache.pdfbox.pdmodel.PDPage page = new org.apache.pdfbox.pdmodel.PDPage(
                new org.apache.pdfbox.pdmodel.common.PDRectangle(panel.getWidth(), panel.getHeight()));
        document.addPage(page);

        // Add image
        org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject pdImage =
                org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject.createFromFile(
                        tempImage.getAbsolutePath(), document);
        org.apache.pdfbox.pdmodel.PDPageContentStream contentStream =
                new org.apache.pdfbox.pdmodel.PDPageContentStream(document, page);
        contentStream.drawImage(pdImage, 0, 0);
        contentStream.close();

        // Save
        document.save(filePath);
        document.close();

        // Delete temp
        tempImage.delete();

        JOptionPane.showMessageDialog(parent, "PDF saved to: " + filePath);   
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(parent, "PDF saved to: " + filePath);
        this.dispose();
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

        receiptPanel1 = new javax.swing.JPanel();
        Print = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblBookingNumber = new javax.swing.JLabel();
        lblNumberInfo = new javax.swing.JLabel();
        lblServiceName = new javax.swing.JLabel();
        lblBookingDate = new javax.swing.JLabel();
        lblBookingTime = new javax.swing.JLabel();
        lblSpecialist = new javax.swing.JLabel();
        lblPaymentMethod = new javax.swing.JLabel();
        lblTotalCost = new javax.swing.JLabel();
        lblCash = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblClientName = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblUnitCost = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        receiptPanel1.setBackground(new java.awt.Color(255, 255, 255));
        receiptPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Print.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Print.setText("PRINT");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });
        receiptPanel1.add(Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 160, 40));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("OK");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        receiptPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 670, 130, 40));

        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 102));
        jLabel1.setText("BGLAM");
        receiptPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel2.setText("Your beauty, your style");
        receiptPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 140, 20));

        jLabel3.setText("BEAUTY GLAMOROUS SALON & INC. ");
        receiptPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, 20));

        jLabel4.setText("  Bandang Makati JP. Rizal Batumbakal Eyyy");
        receiptPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, 20));

        jLabel5.setText("www.facebook.com @BGLAMBeautySalon");
        receiptPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 240, 20));

        jLabel6.setText("Contact us : 0927 - 8345- 67589");
        receiptPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        jLabel7.setText("-----------------------------------------------------------------------------------------------------");
        receiptPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 510, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("-----------------------------------------------------------------------------------------------------");
        receiptPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 510, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("BEAUTY GLAM SALON RECEIPTS INVOICE");
        receiptPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("NUMBER INFO :");
        receiptPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("BOOKING NO :");
        receiptPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("-----------------------------------------------------------------------------------------------------");
        receiptPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 510, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("BALANCE :");
        receiptPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("SERVICE NAME :");
        receiptPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("UNIT COST :");
        receiptPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("QUANTITY :");
        receiptPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("BOOKING TIME :");
        receiptPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("PAYMENT METHOD :");
        receiptPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("TOTAL PAY :");
        receiptPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("CASH :");
        receiptPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        jLabel21.setText("-----------------------------------------------------------------------------------------------------");
        receiptPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 510, 20));

        jLabel22.setText("-----------------------------------------------------------------------------------------------------");
        receiptPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 510, 20));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText(" THANK YOU COME AGAIN!");
        receiptPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 550, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("-----------------------------------------------------------------------------------------------------");
        receiptPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 510, 20));

        jLabel25.setText("RECEPTIONIST :  NAMI CAT BURGLAR ");
        receiptPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 590, -1, -1));

        jLabel26.setText("CONTACT US : 0927 - 8345 - 6789");
        receiptPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 610, -1, -1));

        jLabel27.setText("EMAIL : BGLAMSalon@gmail.com");
        receiptPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, -1, -1));

        lblBookingNumber.setText("jLabel28");
        receiptPanel1.add(lblBookingNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, -1, -1));
        receiptPanel1.add(lblNumberInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, -1));

        lblServiceName.setText("jLabel30");
        receiptPanel1.add(lblServiceName, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, -1, -1));

        lblBookingDate.setText("jLabel31");
        receiptPanel1.add(lblBookingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, -1, -1));

        lblBookingTime.setText("jLabel32");
        receiptPanel1.add(lblBookingTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, -1, -1));

        lblSpecialist.setText("jLabel33");
        receiptPanel1.add(lblSpecialist, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, -1, -1));

        lblPaymentMethod.setText("jLabel34");
        receiptPanel1.add(lblPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, -1, -1));

        lblTotalCost.setText("jLabel35");
        receiptPanel1.add(lblTotalCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, -1, -1));

        lblCash.setText("jLabel36");
        receiptPanel1.add(lblCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, -1, -1));

        lblBalance.setText("      0");
        receiptPanel1.add(lblBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 520, 40, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText(" X ");
        jLabel38.setOpaque(true);
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });
        receiptPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 40, 30));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setText("CLIENT NAME :");
        receiptPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        lblClientName.setText("jLabel29");
        receiptPanel1.add(lblClientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("STYLIST :");
        receiptPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("BOOKING DATE :");
        receiptPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        lblUnitCost.setText("jLabel33");
        receiptPanel1.add(lblUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, -1, -1));

        lblQuantity.setText("jLabel33");
        receiptPanel1.add(lblQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, -1, -1));

        getContentPane().add(receiptPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
       JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Save Receipt As PDF");
    chooser.setSelectedFile(new File("receipt.pdf"));

    int result = chooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        String filePath = chooser.getSelectedFile().getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            filePath += ".pdf";
        }
        saveReceiptAsPDF(receiptPanel1, filePath,this); // receiptPanel is your JPanel
    }
    
    }//GEN-LAST:event_PrintActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try {
        // Step 1: Parse fields from labels
        String bookingNumber = lblBookingNumber.getText();
        String clientName = lblClientName.getText();
        String numberInfo = lblNumberInfo.getText();
        String serviceName = lblServiceName.getText();
        String bookingDateStr = lblBookingDate.getText().trim();
        String bookingTimeStr = lblBookingTime.getText().trim();
        String paymentMethod = lblPaymentMethod.getText();
        String specialist = lblSpecialist.getText();
        String receptionist = "Admin"; // Hardcoded, change if needed

        // Step 2: Convert to java.sql.Date
        DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        java.util.Date utilDate = df.parse(bookingDateStr);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        // Step 3: Convert to java.sql.Time
        DateFormat tf = new SimpleDateFormat("hh:mma", Locale.ENGLISH); // Format like 4:00AM
        java.util.Date utilTime = tf.parse(bookingTimeStr.toUpperCase());
        java.sql.Time sqlTime = new java.sql.Time(utilTime.getTime());

        // Step 4: Parse currency values
        double unitCost = parseCurrencyLabel(lblUnitCost);
        double quantity = Double.parseDouble(lblQuantity.getText());
        double totalAmount = parseCurrencyLabel(lblTotalCost);
        double cash = parseCurrencyLabel(lblCash);
        double balance = parseCurrencyLabel(lblBalance);

        // Step 5: Save to database
        Connection con = (Connection) connectToDatabase();
        String sql = "INSERT INTO bookinglist (booking_number, client_name, numberinfo, service_name, booking_date, booking_time, unit_cost, quantity,  specialist,payment_method,total_cost, receptionist, cash, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, bookingNumber);
        pst.setString(2, clientName);
        pst.setString(3, numberInfo);
        pst.setString(4, serviceName);
        pst.setDate(5, sqlDate);
        pst.setTime(6, sqlTime);
        pst.setDouble(7, unitCost);
        pst.setDouble(8, quantity);
        pst.setString(9, specialist);
        pst.setString(10, paymentMethod);
        pst.setDouble(11, totalAmount);
        pst.setString(12, receptionist);
        pst.setDouble(13, cash);
        pst.setDouble(14, balance);

        pst.executeUpdate();
        con.close();

        
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "❌ Failed to save booking: " + e.getMessage());
    }
this.dispose();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
     dispose(); 
    }//GEN-LAST:event_jLabel38MouseClicked
private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
;
    
}
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
            java.util.logging.Logger.getLogger(RecieptsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecieptsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecieptsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecieptsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecieptsWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Print;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblBookingDate;
    private javax.swing.JLabel lblBookingNumber;
    private javax.swing.JLabel lblBookingTime;
    private javax.swing.JLabel lblCash;
    private javax.swing.JLabel lblClientName;
    private javax.swing.JLabel lblNumberInfo;
    private javax.swing.JLabel lblPaymentMethod;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblServiceName;
    private javax.swing.JLabel lblSpecialist;
    private javax.swing.JLabel lblTotalCost;
    private javax.swing.JLabel lblUnitCost;
    private javax.swing.JPanel receiptPanel1;
    // End of variables declaration//GEN-END:variables

   

    private static class PDDocument {

        public PDDocument() {
        }

        private void addPage(PDPage page) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void save(String filePath) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void close() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class PDPage {

        public PDPage() {
        }

        private PDPage(PDRectangle pdRectangle) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class PDRectangle {

        public PDRectangle(int width, int height) {
        }
    }

    private static class DriverManager {

        private static Connection getConnection(String jdbcmysqllocalhost3306bpmsdb, String root, String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public DriverManager() {
        }
    }
}

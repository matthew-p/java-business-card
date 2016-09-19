import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Drawing extends JPanel {
    String personalName;
    String businessName;
    String phoneNumber;
    String emailAddress;
    String fontFamily;
    int fontStyle;
    int fontSize;
    Font selectedFont;
    String filePath;
    
    public Drawing(String[] formValues) {
        personalName = formValues[0];
        businessName = formValues[1];
        phoneNumber = formValues[2];
        emailAddress = formValues[3];
        fontFamily = formValues[4];
        if (formValues[5] == "Plain") {
            fontStyle = 0;
        } else if (formValues[5] == "Bold") {
            fontStyle = 1;
        } else if (formValues[5] == "Italic") {
            fontStyle = 2;
        } else {
            fontStyle = 3;
        }
        fontSize = Integer.parseInt(formValues[6]);
        selectedFont = new Font(fontFamily, fontStyle, fontSize);
        filePath = formValues[7];
        
    }
    
    
    public static void main(String[] formValues) {
        JFrame f = new JFrame();
        f.setTitle("Business Card");
        f.setContentPane(new Drawing(formValues));
        f.setSize(600, 400);
        f.setVisible(true);
    }
    
    public void paintComponent (Graphics g) {
        // this statement required to invoke original class' paintComponent
        super.paintComponent(g);
        // variables for method parameters
        // boundary rectangle
        int cardX = 25;
        int cardY = 25;
        int cardWidth = 500;
        int cardHeight = 300;
        int textBlockX = cardX + 25;
        int textBlockY = cardY + 175 - fontSize * 2;
        int logoX = cardX + 350;
        int logoY = cardY + 25;
        int logoWidth = 125;
        int logoHeight = 125;
        
        // draw rectangle & boundary
        g.setColor(Color.WHITE);
        g.fillRect(0,0,600,600);
        g.setColor(Color.BLACK);
        g.drawRect(cardX, cardY, cardWidth, cardHeight);
        
        // draw text block
        g.setFont(selectedFont);
        g.drawString(businessName, textBlockX, textBlockY);
        g.drawString(personalName, textBlockX, textBlockY + fontSize * 3 / 2);
        g.drawString(phoneNumber, textBlockX, textBlockY + fontSize * 6 / 2);
        g.drawString(emailAddress, textBlockX, textBlockY + fontSize * 9 / 2);
        
        // draw logo
        g.setColor(Color.RED);
        g.fillArc(logoX, logoY, logoWidth, logoHeight, 0, 90);
        g.setColor(Color.BLUE);
        g.fillArc(logoX, logoY, logoWidth, logoHeight, 90, 90);
        g.setColor(Color.YELLOW);
        g.fillArc(logoX, logoY, logoWidth, logoHeight, 180, 90);
        g.setColor(Color.GREEN);
        g.fillArc(logoX, logoY, logoWidth, logoHeight, 270, 90);
        g.setColor(Color.WHITE);
        g.fillOval(logoX, logoY + logoHeight / 4, logoWidth, logoHeight / 2);
        g.setColor(Color.BLACK);
        g.drawOval(logoX, logoY + logoHeight / 4, logoWidth, logoHeight / 2);
        g.fillOval(logoX + logoWidth / 4, logoY + logoHeight / 4, logoWidth / 2, logoHeight / 2);
        g.drawOval(logoX, logoY, logoWidth, logoHeight);
        
        // image code
        // see if file path exists
        if (filePath != null) {
            
            BufferedImage photo = null;
            try { 
                File file = new File(filePath);
                photo = ImageIO.read(file);
            } catch (IOException e) {
                g.drawString("Problem reading the file", 100, 100);
            }
            g.drawImage(photo, logoX, logoY + logoHeight + 25, 125, 125, null);
        }
    }
}
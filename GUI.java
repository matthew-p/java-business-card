import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class GUI extends JFrame implements ActionListener {
    // declare instance variables 
    JTextField personalNameField;
    JTextField businessNameField;
    JTextField phoneNumberField;
    JTextField emailAddressField;
    JButton renderButton;
    JComboBox fontFamilyList;
    JComboBox fontStyleList;
    JComboBox fontSizeList;
    JButton fileSelectButton;
    JFileChooser fc;
    String filePath;
    
    // constructor method
    public GUI() {
        setTitle("Business Card Generator");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // make a panel to hold gui elements 
        JPanel guiPanel = new JPanel();
        guiPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        JLabel nameLabel = new JLabel("Your Name: ");
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        guiPanel.add(nameLabel, constraints);
        // JTextField takes number of columns (characters) as argument
        personalNameField = new JTextField(32);
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 0;
        guiPanel.add(personalNameField, constraints);
        
        JLabel businessNameLabel = new JLabel("Business Name: ");
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        guiPanel.add(businessNameLabel, constraints);
        
        businessNameField = new JTextField(32);
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        guiPanel.add(businessNameField, constraints);
        
        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        guiPanel.add(phoneNumberLabel, constraints);
        
        phoneNumberField = new JTextField(32);
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 2;
        guiPanel.add(phoneNumberField, constraints);
        
        JLabel emailAddressLabel = new JLabel("Email Address: ");
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        guiPanel.add(emailAddressLabel, constraints);
        
        emailAddressField = new JTextField(32);
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 3;
        guiPanel.add(emailAddressField, constraints);
        
        String[] fontFamilies = { "Serif", "SansSerif", "Monospaced" };
        fontFamilyList = new JComboBox(fontFamilies);
        fontFamilyList.setSelectedIndex(0);
        constraints.gridwidth = 0;
        constraints.gridx = 0;
        constraints.gridy = 4;
        guiPanel.add(fontFamilyList, constraints);
        
        String[] fontStyles = { "Plain", "Italic", "Bold", "Bold Italic"};
        fontStyleList = new JComboBox(fontStyles);
        fontStyleList.setSelectedIndex(0);
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 4;
        guiPanel.add(fontStyleList, constraints);
        
        String[] fontSizes = { "14", "16", "18", "20", "24", "28", "36"};
        fontSizeList = new JComboBox(fontSizes);
        fontSizeList.setSelectedIndex(4);
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 4;
        guiPanel.add(fontSizeList);
        
      
        fileSelectButton = new JButton("Select Picture");
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 5;
        guiPanel.add(fileSelectButton, constraints);
        fileSelectButton.addActionListener(this);
        
        renderButton = new JButton("Make Card");
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 5;
        guiPanel.add(renderButton, constraints);
        renderButton.addActionListener(this);
        
        // add the panel and set frame visible
        add(guiPanel);        
        setVisible(true);
    }
    
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == renderButton) {
            String[] formValues = {
                personalNameField.getText(),
                businessNameField.getText(),
                phoneNumberField.getText(),
                emailAddressField.getText(),
                (String)fontFamilyList.getSelectedItem(),
                (String)fontStyleList.getSelectedItem(),
                (String)fontSizeList.getSelectedItem(),
                filePath
            };
            Drawing.main(formValues);
        } else if (e.getSource() == fileSelectButton) {
                    // make a file chooser
            fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
            fc.setFileFilter(filter);
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                filePath = fc.getSelectedFile().getAbsolutePath();
                
            }
        }
    }
}
package com.timone.setup.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.timone.menu.dashboard.MainAdmin;
import com.timone.setup.manager.FormsManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Activation extends JPanel {

    public static MainAdmin menu;
    public static Activation logIn;

    public Activation() {
        init();
        cmdActivate.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Register());
        });
    }

    private boolean isValidLicense(String name, String productKey) {
        String[] licenseInfo = verifyLicense(productKey);
        if (licenseInfo != null && licenseInfo.length == 2) {
            return name.equals(licenseInfo[0]);
        }
        return false;
    }

    private String getLicenseDetails(String productKey) {
        String[] licenseInfo = verifyLicense(productKey);
        if (licenseInfo != null && licenseInfo.length == 2) {
            return "License Details\nName: " + licenseInfo[0] + "\nPurchase Date: " + licenseInfo[1];
        } else {
            return "Invalid License";
        }
    }

    private String[] verifyLicense(String licenseKey) {
        // Splitting license key into data and key
        String[] parts = new String(Base64.getDecoder().decode(licenseKey)).split("\\|");
        if (parts.length != 3) {
            return null; // Invalid format
        }
        String name = parts[0];
        String dateTime = parts[1];
        String key = parts[2];

        // Implement your verification logic here
        // For demonstration, let's check if the name is not empty
        // and if the date is not in the future
        if (name.isEmpty()) {
            return null;
        }

        try {
            Date parsedDateTime = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime);
            Date currentDate = new Date();
            if (parsedDateTime.after(currentDate)) {
                return null; // Date is in the future
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return new String[]{name, dateTime};
    }

    private void init() {
        UIManager.put("TextComponent.arc", 10);
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtName = new JTextField();
        txtPD = new JTextField();
        cmdActivate = new JButton("Activate");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 45 30 45 30", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        cmdActivate.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");


        txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your name");
        txtPD.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your product key");

        JLabel lbTitle = new JLabel("Activation");
        JLabel description = new JLabel("Enter your product key to continue");
        JLabel description2 = new JLabel("<html><a href=\"#\" style=\"text-decoration: none;\">here</a></html> for more information");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        description2.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Name"), "gapy 11");
        panel.add(txtName);
        panel.add(new JLabel("Product key"), "gapy 15");
        panel.add(txtPD);
        //panel.add(chRememberMe, "grow 0");
        panel.add(cmdActivate, "gapy 25");
        panel.add(createSignupLabel(), "gapy 8");
        add(panel);
    }



    private Component createSignupLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JButton cmdRfid = new JButton("<html><a href=\"#\" style=\"text-decoration: none;\">here</a></html>");
        cmdRfid.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:3,3,3,3");
        cmdRfid.setContentAreaFilled(false);
        cmdRfid.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdRfid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ganti URL_berikut dengan URL yang ingin Anda buka
                    String url = "https://github.com/naaeell/Pharmastan";
                    openWebpage(url);
                } catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel label = new JLabel("for more information");
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdRfid);
        return panel;
    }

    public static void openWebpage(String urlString) throws URISyntaxException, IOException {
        URI uri = new URI(urlString);
        Desktop.getDesktop().browse(uri);
    }

    private JTextField txtName;
    private JTextField txtPD;
    private JCheckBox chRememberMe;
    private JButton cmdActivate;
}

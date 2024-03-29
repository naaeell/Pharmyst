/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.print.component;

/**
 *
 * @author Fadel
 */
import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrinterExample implements Printable {

    // Data for the receipt
    private String[] items;
    private double[] prices;
    private int[] quantities;
    private int itemCount;
    private String cashierName;

    // Receipt information
    private String minimarketName = "Apotek Dewata";
    private String address = "Gebang kota jember";
    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private String paymentType = "Cash";
    private double paymentAmount = 500.00;

    public PrinterExample(String[] items, double[] prices, int[] quantities, int itemCount, String cashierName) {
        this.items = items;
        this.prices = prices;
        this.quantities = quantities;
        this.itemCount = itemCount;
        this.cashierName = cashierName;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Font setting for the receipt
        Font font = new Font("Monospaced", Font.PLAIN, 8); // Set the font size to the smallest
        g.setFont(font);

        int x = 10; // starting x coordinate
        int y = 20; // starting y coordinate

        // Draw separator line
        String separator = "----------------------------------------\n"; // Length of separator matches the width of the paper
        g.drawString(separator, x, y);
        y += 10;

        // Print minimarket name, address, date, time, and cashier name
        g.drawString(minimarketName, x, y);
        y += 10;
        g.drawString(address, x, y);
        y += 10;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        g.drawString("Date & Time: " + dateFormat.format(currentDate), x, y);
        y += 10;
        g.drawString("Cashier: " + cashierName, x, y);
        y += 20; // Extra space between header and items

        // Print items, quantities, and prices
        for (int i = 0; i < itemCount; i++) {
            String itemName = items[i];
            // Trim item name to fit the width of the receipt
            if (itemName.length() > 16) {
                itemName = itemName.substring(0, 16);
            }
            g.drawString(itemName, x, y);
            g.drawString(String.format("%7.2f", prices[i]), x + 100, y); // Original price
            g.drawString("x" + quantities[i], x + 140, y);
            g.drawString(String.format("%7.2f", prices[i] * quantities[i]), x + 160, y); // Total price
            y += 10;
            // If the item name was trimmed, print the remaining part on the next line
            if (items[i].length() > 16) {
                g.drawString(items[i].substring(16), x, y);
                y += 10;
            }
        }

        // Print the footer
        g.drawString(separator, x, y);
        y += 10;
        g.drawString("Total: ", x, y);
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += prices[i] * quantities[i];
        }
        g.drawString(String.format("%7.2f", total), x + 160, y);
        y += 20;
        g.drawString("Payment Type: ", x, y);
        g.drawString(paymentType, x + 160, y);
        y += 10;
        g.drawString("Payment Amount: ", x, y);
        g.drawString(String.format("%7.2f", paymentAmount), x + 160, y);
        y += 10;
        g.drawString("Change: ", x, y);
        g.drawString(String.format("%7.2f", paymentAmount - total), x + 160, y);
        y += 35;
        g.drawString(separator, x, y);

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        // Example data
        String[] items = {"Item 1", "Teh javana asli jawa tengah rumah ganjar", "Item 3", "Item 4", "Item 5", "Item 6"};
        double[] prices = {10.00, 20000.00, 15.50, 12.75, 8.90, 6.25};
        int[] quantities = {2, 1, 3, 1, 2, 1};
        int itemCount = 6;
        String cashierName = "Fadel";

        PrinterExample receipt = new PrinterExample(items, prices, quantities, itemCount, cashierName);

        // Create a PrinterJob
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pf = job.defaultPage();
        Paper paper = new Paper();
        paper.setSize(80 * 72 / 25.4, 1000); // 80mm width, arbitrary long height
        paper.setImageableArea(0, 0, 80 * 72 / 25.4, 1000);
        pf.setPaper(paper);

        job.setPrintable(receipt, pf);

        // Show print dialog
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                System.out.println("Printing failed: " + e.getMessage());
            }
        }
    }
}
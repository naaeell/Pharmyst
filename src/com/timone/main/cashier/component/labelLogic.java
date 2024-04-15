/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.main.cashier.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 *
 * @author fadel
 */
public class labelLogic {
    
    //logic buat generate kode transaksi
    public static String generateTransactionCode() {
        String prefix = "TR";
        StringBuilder sb = new StringBuilder(prefix);

        // generate 10 karakter numerik
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            // buat limit biar bisa generate hanya dari 0 dan 9
            char digit = (char) (random.nextInt(10) + '0');
            sb.append(digit);
        }
        return sb.toString();
    }
    
    
    //buat nampilin tanggal
    public static String getCurrentDateTime() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return now.format(formatter);
    }
    
}

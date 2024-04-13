/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.timone.theme.component;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDarkFuchsiaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialOceanicIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialPalenightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
/**
 *
 * @author Fadel
 */
public class ThemeManager {
   private static final String[] themes = {
        "FlatLaf mac Dark", "FlatLaf mac Light", "Arc Orange",
        "Arc Dark Orange", "Carbon", "Cobalt", "Cyan Light",
        "Dark Flat", "Dark Purple", "Dracula", "Gradianto Dark Fuchsia",
        "Gradianto Deep Ocean", "Gradianto Midnight Blue", "Gradianto Nature Green",
        "Nord", "Atom One Dark", "Atom One Light", "Dracula", "Github", "Github Dark",
        "Light Owl", "Material Darker", "Materila Deep Ocean", "Material Lighter",
        "Material Oceanic", "Material Palenight", "Monokai Pro", "Moonlight",
        "Night Owl", "Solarized Dark", "Solarized Light"
    };

    private int currentThemeIndex = 0;
    private String currentTheme = themes[currentThemeIndex];
    
    public String getNextTheme() {
        currentThemeIndex++;
        if (currentThemeIndex >= themes.length) {
            currentThemeIndex = 0; // Kembali ke tema pertama jika mencapai tema terakhir
        }
        return themes[currentThemeIndex];
    }
    
    public String getFirstTheme() {
        return themes[0]; // Mengembalikan tema pertama dari array themes
    }
    
    public String getCurrentTheme() {
        return currentTheme;
    }
    
    public void setupTheme(String theme) {
        switch (theme) {
            case "FlatLaf mac Dark" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMacDarkLaf.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "FlatLaf mac Light" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMacLightLaf.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Arc" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatArcIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Arc Orange" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatArcOrangeIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Arc Dark" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatArcDarkIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Arc Dark Orange" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatArcDarkOrangeIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Carbon" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatCarbonIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Cobalt" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatCobalt2IJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Cyan Light" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatCyanLightIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Dark Flat" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatDarkFlatIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Dark Purple" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatDarkPurpleIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Gradianto Dark Fuchsia" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGradiantoDarkFuchsiaIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Gradianto Deep Ocean" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGradiantoDeepOceanIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Gradianto Midnight Blue" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGradiantoMidnightBlueIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Gradianto Nature Green" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGradiantoNatureGreenIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Nord" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatNordIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Atom One Dark" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatAtomOneDarkIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Atom One Light" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatAtomOneLightIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Dracula" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatDraculaIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Github" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGitHubIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Github Dark" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatGitHubDarkIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Light Owl" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatLightOwlIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Material Darker" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMaterialDarkerIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Material Deep Ocean" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMaterialDeepOceanIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Material Lighter" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMaterialLighterIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Material Oceanic" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMaterialOceanicIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Material Palenight" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMaterialPalenightIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Monokai Pro" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMonokaiProIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Moonlight" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMoonlightIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Night Owl" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatNightOwlIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Solarized Dark" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatSolarizedDarkIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }
            case "Solarized Light" -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatSolarizedLightIJTheme.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
           }  
        }
    }
}

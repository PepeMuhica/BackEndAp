/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.portfolio.cmgm.Dto;

import javax.validation.constraints.NotBlank;

public class dtoAcercade {
    @NotBlank
    private String tituloA;
    @NotBlank
    private String descripcionA;
    
    // Constructor
    public dtoAcercade() {
    }

    public dtoAcercade(String tituloA, String descripcionA) {
        this.tituloA = tituloA;
        this.descripcionA = descripcionA;
    }
    
    // Getter and Setter
    public String getTituloA() {
        return tituloA;
    }

    public void setTituloA(String tituloA) {
        this.tituloA = tituloA;
    }
        
    public String getDescripcionA() {
        return descripcionA;
    }

    public void setDescripcionA(String descripcionA) {
        this.descripcionA = descripcionA;
    }
    
}

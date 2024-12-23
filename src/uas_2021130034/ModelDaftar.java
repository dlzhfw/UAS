/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_2021130034;

import java.sql.Date;

/**
 *
 * @author user
 */
public class ModelDaftar {
    private String no_daftar, nisn, id_pegawai, nama;
    private Date tgl_daftar;

    public String getNo_daftar() {
        return no_daftar;
    }

    public void setNo_daftar(String no_daftar) {
        this.no_daftar = no_daftar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    
    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public Date getTgl_daftar() {
        return tgl_daftar;
    }

    public void setTgl_daftar(Date tgl_lahir) {
        this.tgl_daftar = tgl_daftar;
    }

   
    
}

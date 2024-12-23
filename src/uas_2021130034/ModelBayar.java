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
public class ModelBayar {
    private String no_bayar, nisn, id_pegawai, nama, status_bayar;
    private Date tgl_bayar;

    public String getNo_bayar() {
        return no_bayar;
    }

    public void setNo_bayar(String no_bayar) {
        this.no_bayar = no_bayar;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus_bayar() {
        return status_bayar;
    }

    public void setStatus_bayar(String status_bayar) {
        this.status_bayar = status_bayar;
    }

    public Date getTgl_bayar() {
        return tgl_bayar;
    }

    public void setTgl_bayar(Date tgl_bayar) {
        this.tgl_bayar = tgl_bayar;
    }

    

    public ModelBayar() {
    }

    public ModelBayar(String no_bayar, String nisn, String status_bayar, Date tgl_bayar) {
        this.no_bayar = no_bayar;
        this.nisn = nisn;
        this.status_bayar = status_bayar;
        this.tgl_bayar = tgl_bayar;
    }
    
    
}

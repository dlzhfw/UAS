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
public class ModelSiswa {
    
    private String nisn, nama, alamat, gender, sekolah_asal, status_siswa;
    private Date tgl_lahir;

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSekolah_asal() {
        return sekolah_asal;
    }

    public void setSekolah_asal(String sekolah_asal) {
        this.sekolah_asal = sekolah_asal;
    }

    public String getStatus_siswa() {
        return status_siswa;
    }

    public void setStatus_siswa(String status_siswa) {
        this.status_siswa = status_siswa;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
    
    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_2021130034;

/**
 *
 * @author user
 */
public class ModelScore {

    private String no_daftar, id_mapel, nama_mapel, semester;
    private double nilai, total;

    public String getNo_daftar() {
        return no_daftar;
    }

    public void setNo_daftar(String no_daftar) {
        this.no_daftar = no_daftar;
    }

    public String getId_mapel() {
        return id_mapel;
    }

    public void setId_mapel(String id_mapel) {
        this.id_mapel = id_mapel;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public ModelScore() {
    }

    public ModelScore(String no_daftar, String id_mapel, String semester, double nilai) {
        this.id_mapel = id_mapel;
    }

    public String getNama_mapel() {
        return nama_mapel;
    }

    public void setNama_mapel(String nama_mapel) {
        this.nama_mapel = nama_mapel;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}

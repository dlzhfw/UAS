/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_2021130034;

/**
 *
 * @author user
 */
public class ModelDetilBayar {

    private String no_bayar, id_tagihan, nama_tagihan;
    private int jumlah;

    public String getNo_bayar() {
        return no_bayar;
    }

    public void setNo_bayar(String no_bayar) {
        this.no_bayar = no_bayar;
    }

    public String getId_tagihan() {
        return id_tagihan;
    }

    public void setId_tagihan(String id_tagihan) {
        this.id_tagihan = id_tagihan;
    }

    public String getNama_tagihan() {
        return nama_tagihan;
    }

    public void setNama_tagihan(String nama_tagihan) {
        this.nama_tagihan = nama_tagihan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public ModelDetilBayar(){
        
    }
    
    public ModelDetilBayar(String no_bayar, String id_tagihan, String nama_tagihan, int jumlah){
        this.id_tagihan= id_tagihan;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_2021130034;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author user
 */
public class DBDetilBayar {
    private ModelDetilBayar dt = new ModelDetilBayar();
    
    public ModelDetilBayar getModelDetilBayar() {
        return (dt);
    }

    public void setModelDetilBayar(ModelDetilBayar s) {
        dt = s;
    }
    
    public ObservableList<ModelDetilBayar> Load() {
        try {
            ObservableList<ModelDetilBayar> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from tagihan t inner join detil_bayar d on t.id_tagihan=d.id_tagihan");
            int i = 1;
            while (rs.next()) {
                ModelDetilBayar d = new ModelDetilBayar();
                d.setNo_bayar(rs.getString("no_bayar"));
                d.setId_tagihan(rs.getString("id_tagihan"));
                d.setNama_tagihan(rs.getString("nama_tagihan"));
                d.setJumlah(rs.getInt("jumlah"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public int validasi(String nomor, String kode) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from detil_bayar where no_bayar= '" + nomor
                    + "' and id_tagihan='" + kode + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "insert into detil_bayar (no_bayar,id_tagihan,jumlah) values (?,?,?)");
            con.preparedStatement.setString(1, getModelDetilBayar().getNo_bayar());
            con.preparedStatement.setString(2, getModelDetilBayar().getId_tagihan());
            con.preparedStatement.setInt(3, getModelDetilBayar().getJumlah());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor, String kode) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detil_bayar where no_bayar  = ? and id_tagihan = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "update detil_bayar set jumlah = ? where  no_bayar = ? and id_tagihan = ? ");
            con.preparedStatement.setInt(1, getModelDetilBayar().getJumlah());
            con.preparedStatement.setString(2, getModelDetilBayar().getNo_bayar());
            con.preparedStatement.setString(3, getModelDetilBayar().getId_tagihan());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
}

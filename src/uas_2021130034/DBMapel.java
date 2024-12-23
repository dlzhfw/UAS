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
 * @author User
 */
public class DBMapel {
    private ModelMapel dt = new ModelMapel();

    public ModelMapel getModelMapel() {
        return (dt);
    }

    public void setModelMapel (ModelMapel m) {
        dt = m;
    }
    public ObservableList<ModelMapel> Load() {
        try {
            ObservableList<ModelMapel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select id_mapel, nama_mapel from mapel");
            int i = 1;
            while (rs.next()) {
                ModelMapel d = new ModelMapel();
                d.setId_mapel(rs.getString("id_mapel"));
                d.setNama_mapel(rs.getString("nama_mapel"));

                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from mapel where id_mapel = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into mapel (id_mapel, nama_mapel) values (?,?)");
            con.preparedStatement.setString(1, getModelMapel().getId_mapel());
            con.preparedStatement.setString(2, getModelMapel().getNama_mapel());
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

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from mapel where id_mapel  = ? ");
            con.preparedStatement.setString(1, nomor);
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
                    "update mapel set nama_mapel = ? where id_mapel = ? ; ");
            con.preparedStatement.setString(1, getModelMapel().getNama_mapel());
            con.preparedStatement.setString(2, getModelMapel().getId_mapel());
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
    
    public ObservableList<ModelMapel> LookUp(String fld, String dt) {
        try {
            ObservableList<ModelMapel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select id_mapel, nama_mapel from mapel where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelMapel d = new ModelMapel();
                d.setId_mapel(rs.getString("id_mapel"));
                d.setNama_mapel(rs.getString("nama_mapel"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}


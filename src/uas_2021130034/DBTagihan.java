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
public class DBTagihan {
     private ModelTagihan dt = new ModelTagihan();

    public ModelTagihan getModelTagihan() {
        return (dt);
    }

    public void setModelTagihan(ModelTagihan s) {
        dt = s;
    }

    public ObservableList<ModelTagihan> Load() {
        try {
            ObservableList<ModelTagihan> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select id_tagihan, nama_tagihan_tagihan, jumlah from tagihan");
            int i = 1;
            while (rs.next()) {
                ModelTagihan d = new ModelTagihan();
                d.setId_tagihan(rs.getString("id_tagihan"));
                d.setNama_tagihan(rs.getString("nama_tagihan"));
                d.setJumlah(rs.getInt("jumlah"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from siswa where id_tagihan = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into tagihan (id_tagihan, nama_tagihan, jumlah) values (?,?,?)");
            con.preparedStatement.setString(1, getModelTagihan().getId_tagihan());
            con.preparedStatement.setString(2, getModelTagihan().getNama_tagihan());
            con.preparedStatement.setInt(3, getModelTagihan().getJumlah());
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
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from siswa where id_tagihan  = ? ");
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
                    "update tagihan set nama_tagihan = ?,jumlah = ? where  id_tagihan = ? ; ");
            con.preparedStatement.setString(1, getModelTagihan().getNama_tagihan());
            con.preparedStatement.setInt(2, getModelTagihan().getJumlah());
            con.preparedStatement.setString(7, getModelTagihan().getId_tagihan());
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

    public ObservableList<ModelTagihan> LookUp(String fld, String dt) {
        try {
            ObservableList<ModelTagihan> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select id_tagihan, nama_tagihan, jumlah, alamat, gender, sekolah_asal, status_siswa from siswa where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelTagihan d = new ModelTagihan();
                d.setId_tagihan(rs.getString("id_tagihan"));
                d.setNama_tagihan(rs.getString("nama_tagihan"));
                d.setJumlah(rs.getInt("jumlah"));

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

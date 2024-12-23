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
public class DBScore {
    private ModelScore dt = new ModelScore();

    public ModelScore getModelScore() {
        return (dt);
    }

    public void setModelScore(ModelScore s) {
        dt = s;
    }

    public ObservableList<ModelScore> Load() {
        try {
            ObservableList<ModelScore> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from mapel m inner join score s on m.id_mapel=s.id_mapel");
            int i = 1;
            while (rs.next()) {
                ModelScore d = new ModelScore();
                d.setNo_daftar(rs.getString("no_daftar"));
                d.setId_mapel(rs.getString("id_mapel"));
                d.setNama_mapel(rs.getString("nama_mapel"));
                d.setNilai(rs.getDouble("nilai"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from score where no_daftar= '" + nomor
                    + "' and id_mapel='" + kode + "'");
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
                    "insert into score (no_daftar,id_mapel) values (?,?,?)");
            con.preparedStatement.setString(1, getModelScore().getNo_daftar());
            con.preparedStatement.setString(2, getModelScore().getId_mapel());
            con.preparedStatement.setDouble(3, getModelScore().getNilai());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from score where no_daftar  = ? and id_mapel = ?");
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
                    "update score set nilai = ? where  no_daftar = ? and id_mapel = ? ");
            con.preparedStatement.setDouble(1, getModelScore().getNilai());
            con.preparedStatement.setString(2, getModelScore().getNo_daftar());
            con.preparedStatement.setString(3, getModelScore().getId_mapel());
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

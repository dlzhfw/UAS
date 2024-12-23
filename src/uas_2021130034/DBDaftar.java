/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_2021130034;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class DBDaftar {

    private ModelDaftar dt = new ModelDaftar();

    public ModelDaftar getModelDaftar() {
        return (dt);
    }

    public void setModelDaftar(ModelDaftar d) {
        dt = d;
    }

    private HashMap<String, ModelScore> dt2 = new HashMap<String, ModelScore>();

    public HashMap<String, ModelScore> getModelScore() {
        return (dt2);
    }

    public void setModelScore(ModelScore s) {
        dt2.put(s.getId_mapel(), s);
    }

    public ObservableList<ModelScore> LoadDetil() {
        try {
            ObservableList<ModelScore> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            dt2.clear();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select * from score s inner join mapel m on s.id_mapel=m.id_mapel where no_daftar = '"
                    + getModelDaftar().getNo_daftar() + "'");
            int i = 1;
            while (rs.next()) {
                ModelScore d = new ModelScore();
                d.setNo_daftar(rs.getString("no_daftar"));
                d.setId_mapel(rs.getString("id_mapel"));
                d.setSemester(rs.getString("semester"));
                d.setNilai(rs.getInt("nilai"));
                tableData.add(d);
                setModelScore(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveall() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.dbKoneksi.setAutoCommit(false); // membuat semua perintah menjadi 1 transaksi
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from pendaftar where no_daftar=?");
            con.preparedStatement.setString(1, getModelDaftar().getNo_daftar());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual (no_daftar, nisn, id_pegawai, tgl_daftar) values (?,?,?, ?)");
            con.preparedStatement.setString(1, getModelDaftar().getNo_daftar());
            con.preparedStatement.setString(2, getModelDaftar().getNisn());
            con.preparedStatement.setString(3, getModelDaftar().getId_pegawai());
            con.preparedStatement.setDate(4, getModelDaftar().getTgl_daftar());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from score where no_daftar=?");
            con.preparedStatement.setString(1, getModelDaftar().getNo_daftar());
            con.preparedStatement.executeUpdate();
            for (ModelScore sm : dt2.values()) {
                con.preparedStatement = con.dbKoneksi.prepareStatement("insert into subjual (no_daftar,id_mapel,Jumlah) values (?,?,?)");
                con.preparedStatement.setString(1, sm.getNo_daftar());
                con.preparedStatement.setString(2, sm.getId_mapel());
                con.preparedStatement.setDouble(3, sm.getNilai());
                con.preparedStatement.executeUpdate();
            }
            con.dbKoneksi.commit(); //semua perintah di transaksi dikerjakan
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public ModelDaftar getdata(String nomor) {

        ModelDaftar tmp = new ModelDaftar();
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "select * from pendaftar d where no_daftar = '"
                    + nomor + "'");
            while (rs.next()) {
                tmp.setNo_daftar(rs.getString("no_daftar"));
                tmp.setNisn(rs.getString("nisn"));
                tmp.setId_pegawai(rs.getString("id_pegawai"));
                tmp.setTgl_daftar(rs.getDate("tgl_daftar"));
                
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public ObservableList<ModelDaftar> Load() {
        try {
            ObservableList<ModelDaftar> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select no_daftar, nisn, id_pegawai, tgl_daftar from pendaftar");
            int i = 1;
            while (rs.next()) {
                ModelDaftar d = new ModelDaftar();
                d.setNo_daftar(rs.getString("no_daftar"));
                d.setNisn(rs.getString("nisn"));
                d.setId_pegawai(rs.getString("id_pegawai"));
                d.setTgl_daftar(rs.getDate("tgl_daftar"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pendaftar where no_daftar = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual (no_daftar, nisn,id_pegawai, tgl_lahir) values (?,?,?,?)");
            con.preparedStatement.setString(1, getModelDaftar().getNo_daftar());
            con.preparedStatement.setString(2, getModelDaftar().getNisn());
            con.preparedStatement.setString(1, getModelDaftar().getId_pegawai());
            con.preparedStatement.setDate(2, getModelDaftar().getTgl_daftar());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pendaftar where no_daftar  = ? ");
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
                    "update pendaftar set nisn= ?, id_pegawai = ?,  tgl_daftar = ?,   where  no_daftar = ? ; ");
            con.preparedStatement.setString(1, getModelDaftar().getNisn());
            con.preparedStatement.setString(2, getModelDaftar().getId_pegawai());
            con.preparedStatement.setDate(3, getModelDaftar().getTgl_daftar());
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

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
public class DBSiswa {

    private ModelSiswa dt = new ModelSiswa();

    public ModelSiswa getModelSiswa() {
        return (dt);
    }

    public void setModelSiswa(ModelSiswa s) {
        dt = s;
    }

    public ObservableList<ModelSiswa> Load() {
        try {
            ObservableList<ModelSiswa> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select nisn, nama, tgl_lahir, alamat, gender, sekolah_asal, status_siswa from siswa");
            int i = 1;
            while (rs.next()) {
                ModelSiswa d = new ModelSiswa();
                d.setNisn(rs.getString("nisn"));
                d.setNama(rs.getString("nama"));
                d.setTgl_lahir(rs.getDate("tgl_lahir"));
                d.setAlamat(rs.getString("alamat"));
                d.setGender(rs.getString("gender"));
                d.setSekolah_asal(rs.getString("sekolah_asal"));
                d.setStatus_siswa(rs.getString("status_siswa"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from siswa where nisn = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into siswa (nisn, nama, tgl_lahir, alamat, gender, sekolah_asal, status_siswa) values (?,?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getModelSiswa().getNisn());
            con.preparedStatement.setString(2, getModelSiswa().getNama());
            con.preparedStatement.setDate(3, getModelSiswa().getTgl_lahir());
            con.preparedStatement.setString(4, getModelSiswa().getAlamat());
            con.preparedStatement.setString(5, getModelSiswa().getGender());
            con.preparedStatement.setString(6, getModelSiswa().getSekolah_asal());
            con.preparedStatement.setString(7, getModelSiswa().getStatus_siswa());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from siswa where nisn  = ? ");
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
                    "update siswa set Nama = ?,Tgl_lahir = ?, Alamat = ?, Gender = ?, Sekolah_asal = ?, Status_siswa = ? where  nisn = ? ; ");
            con.preparedStatement.setString(1, getModelSiswa().getNama());
            con.preparedStatement.setDate(2, getModelSiswa().getTgl_lahir());
            con.preparedStatement.setString(3, getModelSiswa().getAlamat());
            con.preparedStatement.setString(4, getModelSiswa().getGender());
            con.preparedStatement.setString(5, getModelSiswa().getSekolah_asal());
            con.preparedStatement.setString(6, getModelSiswa().getStatus_siswa());
            con.preparedStatement.setString(7, getModelSiswa().getNisn());
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

    public ObservableList<ModelSiswa> LookUp(String fld, String dt) {
        try {
            ObservableList<ModelSiswa> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select nisn, nama, tgl_lahir, alamat, gender, sekolah_asal, status_siswa from siswa where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ModelSiswa d = new ModelSiswa();
                d.setNisn(rs.getString("nisn"));
                d.setNama(rs.getString("nama"));
                d.setTgl_lahir(rs.getDate("TglLahir"));
                d.setAlamat(rs.getString("Alamat"));
                d.setGender(rs.getString("Gender"));
                d.setSekolah_asal(rs.getString("sekolah_asal"));
                d.setStatus_siswa(rs.getString("status_siswa"));

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

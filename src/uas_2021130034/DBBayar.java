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
public class DBBayar {
    private ModelBayar dt = new ModelBayar();

    public ModelBayar getModelBayar() {
        return (dt);
    }

    public void setModelBayar(ModelBayar j) {
        dt = j;
    }

    private HashMap<String, ModelDetilBayar> dt2 = new HashMap<String, ModelDetilBayar>();

    public HashMap<String, ModelDetilBayar> getModelDetilBayar() {
        return (dt2);
    }

    public void setModelDetilBayar(ModelDetilBayar d) {
        dt2.put(d.getId_tagihan(), d);
    }

    public ObservableList<ModelDetilBayar> LoadDetil() {
        try {
            ObservableList<ModelDetilBayar> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            dt2.clear();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select * from detil_bayar s inner join tagihan b on s.id_tagihan=b.id_tagihan where no_bayar = '"
                    + getModelBayar().getNo_bayar() + "'");
            int i = 1;
            while (rs.next()) {
                ModelDetilBayar d = new ModelDetilBayar();
                d.setNo_bayar(rs.getString("no_bayar"));
                d.setId_tagihan(rs.getString("id_tagihan"));
                d.setJumlah(rs.getInt("jumlah"));
                d.setNama_tagihan(rs.getString("nama_tagihan"));
                tableData.add(d);
                setModelDetilBayar(d);
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
                    "delete from pembayaran where no_bayar=?");
            con.preparedStatement.setString(1, getModelBayar().getNo_bayar());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pembayaran (no_bayar, tgl_bayar, nisn) values (?,?,?)");
            con.preparedStatement.setString(1, getModelBayar().getNo_bayar());
            con.preparedStatement.setDate(2, getModelBayar().getTgl_bayar());
            con.preparedStatement.setString(3, getModelBayar().getNisn());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from detil_bayar where no_bayar=?");
            con.preparedStatement.setString(1, getModelBayar().getNo_bayar());
            con.preparedStatement.executeUpdate();
            for (ModelDetilBayar sm : dt2.values()) {
                con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detil_bayar (no_bayar,id_tagihan,jumlah) values (?,?,?)");
                con.preparedStatement.setString(1, sm.getNo_bayar());
                con.preparedStatement.setString(2, sm.getId_tagihan());
                con.preparedStatement.setInt(3, sm.getJumlah());
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

    public ModelBayar getdata(String nomor) {

        ModelBayar tmp = new ModelBayar();
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();

            ResultSet rs = con.statement.executeQuery(
                    "select * from pembayaran j inner join pelanggan p on j.nisn=p.nisn where no_bayar = '"
                    + nomor + "'");
            while (rs.next()) {
                tmp.setNo_bayar(rs.getString("no_bayar"));
                tmp.setTgl_bayar(rs.getDate("tgl_bayar"));
                tmp.setNisn(rs.getString("nisn"));
                tmp.setNama(rs.getString("nama"));
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public ObservableList<ModelBayar> Load() {
        try {
            ObservableList<ModelBayar> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select no_bayar, tgl_bayar, nisn from pembayaran");
            int i = 1;
            while (rs.next()) {
                ModelBayar d = new ModelBayar();
                d.setNo_bayar(rs.getString("no_bayar"));
                d.setTgl_bayar(rs.getDate("tgl_bayar"));
                d.setNisn(rs.getString("nisn"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pembayaran where no_bayar = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pembayaran (no_bayar, tgl_bayar, nisn) values (?,?,?)");
            con.preparedStatement.setString(1, getModelBayar().getNo_bayar());
            con.preparedStatement.setDate(2, getModelBayar().getTgl_bayar());
            con.preparedStatement.setString(3, getModelBayar().getNisn());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pembayaran where no_bayar  = ? ");
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
                    "update pembayaran set tgl_bayar = ?, nisn = ?  where  no_bayar = ? ; ");
            con.preparedStatement.setDate(1, getModelBayar().getTgl_bayar());
            con.preparedStatement.setString(2, getModelBayar().getNisn());
            con.preparedStatement.setString(3, getModelBayar().getNo_bayar());
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

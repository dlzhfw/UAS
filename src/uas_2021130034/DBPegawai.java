/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_2021130034;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class DBPegawai {
    ModelPegawai dt = new ModelPegawai();

    public ModelPegawai getDt() {
        return dt;
    }

    public void setDt(ModelPegawai dt) {
        this.dt = dt;
    }
    
    public void getData(String name){
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select * from pegawai where id_pegawai= '"+name + "'" );
            
            while (rs.next()) {
                dt.setId_pegawai(rs.getString("id_pegawai"));
                dt.setPassword(rs.getString("pw"));
                dt.setDivisi(rs.getString("divisi"));
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


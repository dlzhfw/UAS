/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas_2021130034;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_PilihSiswaController implements Initializable {

    @FXML
    private TableView<ModelSiswa> tbvsiswa;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btncari;
    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private TextField txtisi;

    private int hasil = 0;
    private String nisnhasil = "";

    public int getHasil() {
        return (hasil);
    }

    public String getNisnhasil() {
        return (nisnhasil);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbfield.setItems(FXCollections.observableArrayList(
                "nisn", "nama", "alamat"));
        cmbfield.getSelectionModel().select(0);
        showdata("nisn", "");
    }

    public void showdata(String f, String i) {
        ObservableList<ModelSiswa> data = FXML_MenuController.dtsiswa.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXML_MenuController.dtsiswa.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbvsiswa.getColumns().clear();
            tbvsiswa.getItems().clear();
            TableColumn col = new TableColumn("nisn");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("nisn"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("nama"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Tanggal Lahir");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("Tgl_lahir"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("alamat"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Jenis Kelamin");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("gender"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Sekolah Asal");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("sekolah_asal"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Status Siswa");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("status_siswa"));
            tbvsiswa.getColumns().addAll(col);
            tbvsiswa.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvsiswa.getScene().getWindow().hide();
        }
        awalklik(null);
        txtisi.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectFirst();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectAboveCell();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectBelowCell();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectLast();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvsiswa.getSelectionModel().getSelectedCells().get(0).getRow();
        nisnhasil = tbvsiswa.getItems().get(pilihan).getNisn();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showdata(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }

}

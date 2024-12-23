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
 * @author user
 */
public class FXML_PilihTagihanController implements Initializable {

    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private TextField txtisi;
    @FXML
    private Button btncari;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private TableView<ModelTagihan> tbvtagihan;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;

    private int hasil = 0;
    private String kodehasil = "";

    public int getHasil() {
        return (hasil);
    }

    public String getKodehasil() {
        return (kodehasil);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbfield.setItems(FXCollections.observableArrayList(
                "id_tagihan", "nama_tagihan"));
        cmbfield.getSelectionModel().select(0);
        showdata("id_tagihan", "");
    }

    public void showdata(String f, String i) {
        ObservableList<ModelTagihan> data = FXML_MenuController.dttagihan.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXML_MenuController.dttagihan.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbvtagihan.getColumns().clear();
            tbvtagihan.getItems().clear();
            TableColumn col = new TableColumn("id_tagihan");
            col.setCellValueFactory(new PropertyValueFactory<ModelTagihan, String>("id_tagihan"));
            tbvtagihan.getColumns().addAll(col);
            col = new TableColumn("nama_tagihan");
            col.setCellValueFactory(new PropertyValueFactory<ModelTagihan, String>("nama_tagihan"));
            tbvtagihan.getColumns().addAll(col);
            col = new TableColumn("jumlah");
            col.setCellValueFactory(new PropertyValueFactory<ModelTagihan, String>("jumlah"));

            tbvtagihan.getColumns().addAll(col);
            tbvtagihan.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvtagihan.getScene().getWindow().hide();
        }
        awalklik(null);
        txtisi.requestFocus();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showdata(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvtagihan.getSelectionModel().getSelectedCells().get(0).getRow();
        kodehasil = tbvtagihan.getItems().get(pilihan).getId_tagihan();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvtagihan.getSelectionModel().selectFirst();
        tbvtagihan.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvtagihan.getSelectionModel().selectAboveCell();
        tbvtagihan.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvtagihan.getSelectionModel().selectBelowCell();
        tbvtagihan.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvtagihan.getSelectionModel().selectLast();
        tbvtagihan.requestFocus();
    }
}
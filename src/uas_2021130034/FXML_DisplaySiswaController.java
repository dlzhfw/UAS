/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas_2021130034;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXML_DisplaySiswaController implements Initializable {

    @FXML
    private TableView<ModelSiswa> tbvsiswa;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsetelah;
    @FXML
    private Button btnakhir;

    private DBSiswa dt = new DBSiswa();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    private void showdata() {
        ObservableList<ModelSiswa> data = dt.Load();
        if (data != null) {
            tbvsiswa.getColumns().clear();
            tbvsiswa.getItems().clear();
            TableColumn col = new TableColumn("NISN");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("nisn"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("nama"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("TanggalLahir");
            col.setCellValueFactory(new PropertyValueFactory<ModelSiswa, String>("tgl_lahir"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Alamat");
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
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputSiswa.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);
    }

    @FXML
    private void editklik(ActionEvent event) {

        ModelSiswa s = new ModelSiswa();
        s = tbvsiswa.getSelectionModel().getSelectedItem();
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputSiswa.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputSiswaController isidt = (FXML_InputSiswaController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        ModelSiswa s = new ModelSiswa();
        s = tbvsiswa.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXML_MenuController.dtsiswa.delete(s.getNisn())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            awalklik(event);
        }
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
    private void setelahklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectBelowCell();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectLast();
        tbvsiswa.requestFocus();
    }

}

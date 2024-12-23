/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas_2021130034;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author user
 */
public class FXML_DisplayPendaftaranController implements Initializable {

    @FXML
    private TableView<ModelDaftar> tbvdaftar;
    @FXML
    private TableView<ModelScore> tbvscore;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnedit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbvdaftar.getColumns().clear();
        tbvdaftar.getItems().clear();
        TableColumn col = new TableColumn("No Daftar");
        col.setCellValueFactory(new PropertyValueFactory<ModelDaftar, String>("no_daftar"));
        tbvdaftar.getColumns().addAll(col);
        col = new TableColumn("tgl_daftar");
        col.setCellValueFactory(new PropertyValueFactory<ModelDaftar, String>("tgl_daftar"));
        tbvdaftar.getColumns().addAll(col);
        col = new TableColumn("NISN");
        col.setCellValueFactory(new PropertyValueFactory<ModelDaftar, String>("nisn"));
        tbvdaftar.getColumns().addAll(col);
        col = new TableColumn("Nama");
        col.setCellValueFactory(new PropertyValueFactory<ModelDaftar, String>("nama"));
        tbvdaftar.getColumns().addAll(col);
        col = new TableColumn("alamat");
        col.setCellValueFactory(new PropertyValueFactory<ModelDaftar, String>("alamat"));
        tbvdaftar.getColumns().addAll(col);

        tbvscore.getColumns().clear();
        tbvscore.getItems().clear();
        col = new TableColumn("No Daftar");
        col.setCellValueFactory(new PropertyValueFactory<ModelScore, String>("no_daftar"));
        tbvscore.getColumns().addAll(col);
        col = new TableColumn("Id Tagihan");
        col.setCellValueFactory(new PropertyValueFactory<ModelScore, String>("id_tagihan"));
        tbvscore.getColumns().addAll(col);
        col = new TableColumn("Nama tagihan");
        col.setCellValueFactory(new PropertyValueFactory<ModelScore, String>("nama_tagihan"));
        tbvscore.getColumns().addAll(col);
        col = new TableColumn("jumlah");
        col.setCellValueFactory(new PropertyValueFactory<ModelScore, String>("jumlah"));
        tbvscore.getColumns().addAll(col);
        showdata();
    }    
    
    private void showdata() {
        ObservableList<ModelDaftar> data = FXML_MenuController.dtdaftar.Load();
        if (data != null) {
            tbvdaftar.setItems(data);
//            awalklik(null);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvdaftar.getScene().getWindow().hide();;
        }
    }
      private void showdetil() {
        FXML_MenuController.dtdaftar.getModelDaftar().setNo_daftar(
                tbvdaftar.getSelectionModel().getSelectedItem().getNo_daftar());
        ObservableList<ModelScore> data = FXML_MenuController.dtdaftar.LoadDetil();
        if (data != null) {
            tbvscore.setItems(data);
            tbvscore.getSelectionModel().selectFirst();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvdaftar.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_TampilanJual.fxml"));
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
//        awalklik(event);
        
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        ModelDaftar s = new ModelDaftar();
        s = tbvdaftar.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?",
                ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXML_MenuController.dtdaftar.delete(s.getNo_daftar())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION,
                        "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR,
                        "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
//            awalklik(event);
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void editklik(ActionEvent event) {
        ModelDaftar s = new ModelDaftar();
        s = tbvdaftar.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_TampilanJual.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputPendaftaranController isidt = (FXML_InputPendaftaranController) loader.getController();
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
    }
    
}

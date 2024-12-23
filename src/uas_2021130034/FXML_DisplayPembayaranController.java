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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXML_DisplayPembayaranController implements Initializable {

    @FXML
    private TableView<ModelBayar> tbvbayar;
    @FXML
    private TableView<ModelDetilBayar> tbvdetil;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnedit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          tbvbayar.getColumns().clear();
        tbvbayar.getItems().clear();
        TableColumn col = new TableColumn("No bayar");
        col.setCellValueFactory(new PropertyValueFactory<ModelBayar, String>("no_bayar"));
        tbvbayar.getColumns().addAll(col);
        col = new TableColumn("tgl_bayar");
        col.setCellValueFactory(new PropertyValueFactory<ModelBayar, String>("tgl_bayar"));
        tbvbayar.getColumns().addAll(col);
        col = new TableColumn("nisn");
        col.setCellValueFactory(new PropertyValueFactory<ModelBayar, String>("nisn"));
        tbvbayar.getColumns().addAll(col);
        col = new TableColumn("nama siswa");
        col.setCellValueFactory(new PropertyValueFactory<ModelBayar, String>("nama"));
        tbvbayar.getColumns().addAll(col);
        col = new TableColumn("alamat");
        col.setCellValueFactory(new PropertyValueFactory<ModelBayar, String>("alamat"));
        tbvbayar.getColumns().addAll(col);

        tbvdetil.getColumns().clear();
        tbvdetil.getItems().clear();
        col = new TableColumn("No bayar");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("no_bayar"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Id Tagihan");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("id_tagihan"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Jumlah");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("Jumlah"));
        tbvdetil.getColumns().addAll(col);
        showdata();
    }    

    
    private void showdata() {
        ObservableList<ModelBayar> data = FXML_MenuController.dtbayar.Load();
        if (data != null) {
            tbvbayar.setItems(data);
//            awalklik(null);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvbayar.getScene().getWindow().hide();;
        }
    }
    
    private void showdetil() {
        FXML_MenuController.dtbayar.getModelBayar().setNo_bayar(
                tbvbayar.getSelectionModel().getSelectedItem().getNo_bayar());
        ObservableList<ModelDetilBayar> data = FXML_MenuController.dtbayar.LoadDetil();
        if (data != null) {
            tbvdetil.setItems(data);
            tbvdetil.getSelectionModel().selectFirst();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvbayar.getScene().getWindow().hide();;
        }
    }
    
    
    @FXML
    private void keluarklik(ActionEvent event) {
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
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        ModelBayar s = new ModelBayar();
        s = tbvbayar.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?",
                ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXML_MenuController.dtbayar.delete(s.getNo_bayar())) {
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
    private void editklik(ActionEvent event) {
        ModelBayar s = new ModelBayar();
        s = tbvbayar.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_TampilanJual.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PembayaranController isidt = (FXML_PembayaranController) loader.getController();
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

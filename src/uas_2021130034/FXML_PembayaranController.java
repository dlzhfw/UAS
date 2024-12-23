/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas_2021130034;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user
 */
public class FXML_PembayaranController implements Initializable {

    @FXML
    private TextField txtnobayar;
    @FXML
    private TextField txtnisn;
    @FXML
    private TextField txtidpegawai;
    @FXML
    private TextField txttagihan;
    @FXML
    private TextField txtjmlbayar;
    @FXML
    private TextField txtstatbayar;
    @FXML
    private DatePicker dtptglbayar;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnclear;
    @FXML
    private TableView<ModelDetilBayar> tbvdetil;
    @FXML
    private Button btnsimpan;

    private boolean editmode = false;
    private DBBayar dt = new DBBayar();
    @FXML
    private Button btnbatal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dt.getModelDetilBayar().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dtptglbayar.setValue(LocalDate.parse(formatter.format(LocalDate.now()), formatter));
        tbvdetil.getColumns().clear();
        tbvdetil.getItems().clear();
        TableColumn col = new TableColumn("No Bayar");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("no_bayar"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Kode Barang");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("KodeBrg"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Jumlah");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, Integer>("Jumlah"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Nama Barang");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("NamaBrg"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Harga");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("Harga"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("total");
        col.setCellValueFactory(new PropertyValueFactory<ModelDetilBayar, String>("Total"));
        tbvdetil.getColumns().addAll(col);
    }    

     public void execute(ModelBayar d) {
        if (!d.getNo_bayar().isEmpty()) {
            FXML_MenuController.dtbayar.getModelBayar().setNo_bayar(d.getNo_bayar());
            if (FXML_MenuController.dtbayar.validasi(d.getNo_bayar()) >= 1) {
                ModelBayar tmp = FXML_MenuController.dtbayar.getdata(d.getNo_bayar());
                editmode = true;
                FXML_MenuController.dtbayar.setModelBayar(d);
                txtnobayar.setText(d.getNo_bayar());
                if (d.getTgl_bayar() != null) {
                    dtptglbayar.setValue(d.getTgl_bayar().toLocalDate());
                }
                txtnisn.setText(d.getNisn());
                ObservableList<ModelDetilBayar> data
                        = FXML_MenuController.dtbayar.LoadDetil();
                if (data != null) {
                    tbvdetil.setItems(data);

                }
                txtnobayar.setEditable(false);
                txtnisn.requestFocus();
            }
        }
    }
     
    @FXML
    private void hapusklik(ActionEvent event) {
        ModelDetilBayar tmp = tbvdetil.getSelectionModel().getSelectedItem();
        if (tmp != null) {
            tbvdetil.getItems().remove(tmp);
            dt.getModelDetilBayar().remove(tmp.getId_tagihan());
            clearklik(event);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Pilih data dulu", ButtonType.OK);
            a.showAndWait();
            tbvdetil.requestFocus();
        }
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        ModelDetilBayar tmp = new ModelDetilBayar();
        tmp.setNo_bayar(txtnobayar.getText());
        tmp.setId_tagihan(txttagihan.getText());
        tmp.setJumlah(Integer.parseInt(txtjmlbayar.getText()));
        if (dt.getModelDetilBayar().get(txttagihan.getText()) == null) {
            dt.setModelDetilBayar(tmp);
            tbvdetil.getItems().add(tmp);
        } else {
            int p = -1;
            for (int i = 0; i < tbvdetil.getItems().size(); i++) {
                if (tbvdetil.getItems().get(i).getId_tagihan().equalsIgnoreCase(
                        txttagihan.getText())) {
                    p = i;
                }
            }
            if (p >= 0) {
                tbvdetil.getItems().set(p, tmp);
            }
            dt.getModelDetilBayar().remove(txttagihan.getText());
            dt.setModelDetilBayar(tmp);
        }
        clearklik(event);
    }

    @FXML
    private void clearklik(ActionEvent event) {
        txttagihan.setText("");
        txtnisn.setText("");
        txtjmlbayar.setText("");
        txttagihan.requestFocus();
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        dt.getModelBayar().setNo_bayar(txtnobayar.getText());
        dt.getModelBayar().setTgl_bayar(Date.valueOf(dtptglbayar.getValue().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        dt.getModelBayar().setNisn(txtnisn.getText());
        if (dt.saveall()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan ", ButtonType.OK);
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan ", ButtonType.OK);
            a.showAndWait();
        }
        batalklik(event);
        
        
    }

    @FXML
    private void batalklik(ActionEvent event) {
        clearklik(event);
        txtnobayar.setText("");
        txtnisn.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dtptglbayar.setValue(LocalDate.parse(formatter.format(LocalDate.now()), formatter));
        tbvdetil.getItems().clear();
        dt.getModelDetilBayar().clear();
        txtnobayar.setEditable(true);
        editmode = false;
        txtnobayar.requestFocus();
    }
    
}

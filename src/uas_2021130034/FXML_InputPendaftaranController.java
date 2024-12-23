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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class FXML_InputPendaftaranController implements Initializable {

    @FXML
    private TextField txtnisn;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txtasal;
    @FXML
    private TextField txtstatsiswa;
    @FXML
    private DatePicker dtptgllahir;
    @FXML
    private RadioButton rbl;
    @FXML
    private ToggleGroup tggender;
    @FXML
    private RadioButton rbp;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private DatePicker dtptgldaftar;
    @FXML
    private TextField txtidpegawai;
    @FXML
    private TableView<ModelScore> tbvscore;

    private boolean editmode = false;
    private DBDaftar dt = new DBDaftar();
    @FXML
    private TextField txtnodaftar;
    @FXML
    private Button btnpilih;
    @FXML
    private TextField txtidmapel;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private TextField txtnilai;
    @FXML
    private Button txtclear;
    @FXML
    private TextField txtscore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dt.getModelScore().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dtptgldaftar.setValue(LocalDate.parse(formatter.format(LocalDate.now()), formatter));
        tbvscore.getColumns().clear();
        tbvscore.getItems().clear();
        TableColumn col = new TableColumn("No Daftar");
        col.setCellValueFactory(new PropertyValueFactory<ModelScore, String>("no_daftar"));
        tbvscore.getColumns().addAll(col);
        col = new TableColumn("ID Mapel");
        col.setCellValueFactory(new PropertyValueFactory<ModelScore, String>("id_mapel"));
        tbvscore.getColumns().addAll(col);
        col = new TableColumn("nilai");
        col.setCellValueFactory(new PropertyValueFactory<ModelScore, Integer>("nilai"));
        tbvscore.getColumns().addAll(col);

    }

    private void hitungtotal() {
        int total = 0;
        for (int i = 0; i < tbvscore.getItems().size(); i++) {
            ModelScore dtl = tbvscore.getItems().get(i);
            total += dtl.getTotal();
        }
        txtscore.setText(String.valueOf(total));
    }

    public void execute(ModelDaftar d) {
        if (!d.getNo_daftar().isEmpty()) {
            FXML_MenuController.dtdaftar.getModelDaftar().setNo_daftar(d.getNo_daftar());
            if (FXML_MenuController.dtdaftar.validasi(d.getNo_daftar()) >= 1) {
                ModelDaftar tmp = FXML_MenuController.dtdaftar.getdata(d.getNo_daftar());
                editmode = true;
                FXML_MenuController.dtdaftar.setModelDaftar(d);
                txtnodaftar.setText(d.getNo_daftar());
                if (d.getTgl_daftar() != null) {
                    dtptgldaftar.setValue(d.getTgl_daftar().toLocalDate());
                }
                txtnisn.setText(d.getNisn());
//                txtnama.setText(d.getNama());
//                txtalamat.setText(d.getAlamat());
                ObservableList<ModelScore> data
                        = FXML_MenuController.dtdaftar.LoadDetil();
                if (data != null) {
                    tbvscore.setItems(data);
                    hitungtotal();
                }
                txtnodaftar.setEditable(false);
                txtidpegawai.requestFocus();
            }
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        ModelSiswa s = new ModelSiswa();
        s.setNisn(txtnisn.getText());
        s.setNama(txtnama.getText());
        s.setTgl_lahir(Date.valueOf(dtptgllahir.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        s.setAlamat(txtalamat.getText());
        if (rbp.isSelected()) {
            s.setGender(rbp.getText());
        } else if (rbl.isSelected()) {
            s.setGender(rbl.getText());
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Data harus diisi", ButtonType.OK);
            a.showAndWait();
            txtnisn.requestFocus();
        }
        s.setSekolah_asal(txtasal.getText());
        s.setStatus_siswa(txtstatsiswa.getText());
        
        
        dt.getModelDaftar().setNo_daftar(txtnodaftar.getText());
        dt.getModelDaftar().setTgl_daftar(Date.valueOf(dtptgldaftar.getValue().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        dt.getModelDaftar().setNisn(txtnisn.getText());
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
        txtnodaftar.setText("");
        txtnisn.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dtptgldaftar.setValue(LocalDate.parse(formatter.format(LocalDate.now()), formatter));
        tbvscore.getItems().clear();
        dt.getModelScore().clear();
        txtnodaftar.setEditable(true);
        editmode = false;
        txtnodaftar.requestFocus();

    }

    @FXML
    private void txtmapel(ActionEvent event) {
    }

    @FXML
    private void plihmapel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihMapel.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PilihMapelController isidt = (FXML_PilihMapelController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtidmapel.setText(isidt.getId_mapelhasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        ModelScore tmp = new ModelScore();
        tmp.setNo_daftar(txtnodaftar.getText());
        tmp.setId_mapel(txtidmapel.getText());
        tmp.setNilai(Double.parseDouble(txtnilai.getText()));
        if (dt.getModelScore().get(txtidmapel.getText()) == null) {
            dt.setModelScore(tmp);
            tbvscore.getItems().add(tmp);
        } else {
            int p = -1;
            for (int i = 0; i < tbvscore.getItems().size(); i++) {
                if (tbvscore.getItems().get(i).getId_mapel().equalsIgnoreCase(
                        txtidmapel.getText())) {
                    p = i;
                }
            }
            if (p >= 0) {
                tbvscore.getItems().set(p, tmp);
            }
            dt.getModelScore().remove(txtidmapel.getText());
            dt.setModelScore(tmp);
        }
        hitungtotal();
        clearklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        ModelScore tmp = tbvscore.getSelectionModel().getSelectedItem();
        if (tmp != null) {
            tbvscore.getItems().remove(tmp);
            dt.getModelScore().remove(tmp.getId_mapel());
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Pilih data dulu", ButtonType.OK);
            a.showAndWait();
            tbvscore.requestFocus();
        }
    }

    @FXML
    private void clearklik(ActionEvent event) {
    }

}

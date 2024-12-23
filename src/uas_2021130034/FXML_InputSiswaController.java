/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas_2021130034;

import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXML_InputSiswaController implements Initializable {

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

    boolean editdata = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
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
        
        FXML_MenuController.dtsiswa.setModelSiswa(s);
        if (editdata) {
            if (FXML_MenuController.dtsiswa.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtnisn.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_MenuController.dtsiswa.validasi(s.getNisn()) <= 0) {
            if (FXML_MenuController.dtsiswa.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtnisn.requestFocus();
        }

    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtnisn.setText("");
        txtnama.setText("");
        dtptgllahir.getEditor().clear();
        txtalamat.setText("");
        rbp.setSelected(false);
        rbl.setSelected(false);
        txtasal.setText("");
        txtnisn.requestFocus();
    }

    public void execute(ModelSiswa d) {
        if (!d.getNisn().isEmpty()) {
            editdata = true;
            txtnisn.setText(d.getNisn());
            txtnama.setText(d.getNama());
            dtptgllahir.setValue(d.getTgl_lahir().toLocalDate());
            txtalamat.setText(d.getAlamat());
            txtasal.setText(d.getSekolah_asal());
            if (d.getGender().equalsIgnoreCase("Perempuan")) {
                rbp.setSelected(true);
            } else if (d.getGender().equalsIgnoreCase("Laki-Laki")) {
                rbl.setSelected(true);
            }
            txtnisn.setEditable(false);
            txtnama.requestFocus();
        }
    }
}

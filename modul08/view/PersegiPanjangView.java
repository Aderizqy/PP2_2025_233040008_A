package modul08.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    public JTextField txtPanjang = new JTextField(10);
    public JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-");
    private JLabel lblHasilKeliling = new JLabel("-");
    private JButton btnHitung  = new JButton("Hitung");
    public JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 250);
        this.setLayout(new GridLayout(6, 2, 10, 10)); // Grid 6 baris
        this.setTitle("MVC Kalkulator");

        this.add(new JLabel("Panjang"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar"));
        this.add(txtLebar);
        this.add(new JLabel("Hasil Luas"));
        this.add(lblHasilLuas);
        this.add(new JLabel("Hasil keliling"));
        this.add(lblHasilKeliling);
        this.add(new JLabel("")); // Spacer kosong
        this.add(new JLabel("")); // Spacer kosong
        this.add(btnHitung);
        this.add(btnReset);
    }

    // Mengambil nilai panjang dari Textfield
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai lebar dari Textfield
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Menampilkan hasil ke Label
    public void setHasilLuas(double hasil) {
        lblHasilLuas.setText(String.valueOf(hasil));
    }

    public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }

    public void reset() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
    }

    // Menampilkan pesan error (jika input bukan angka)
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Mendaftarkan Listener untuk tombol (Controller yang akan memberikan aksinya)
    public void addHitungListener (ActionListener listener) {
        btnHitung.addActionListener(listener);
    }

    // Mendaftarkan Listener untuk tombol Reset
    public void addResetListener (ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}
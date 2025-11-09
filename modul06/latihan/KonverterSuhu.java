package modul06.latihan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KonverterSuhu extends JFrame {
    private JLabel labelCelcius;
    private JTextField fieldCelcius;
    private JButton tombolKonversi;
    private JLabel labelFahrenheit;
    private JLabel labelHasil;

    public KonverterSuhu() {
        // --- 1. Pengaturan Window (JFrame) ---
        setTitle("Konverter Suhu");
        setSize(450, 100); // Atur ukuran window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        // --- 2. Inisialisasi Komponen ---
        labelCelcius = new JLabel("Celcius:");
        fieldCelcius = new JTextField(10); // Lebar 10 kolom
        tombolKonversi = new JButton("Konversi");
        labelFahrenheit = new JLabel("Fahrenheit:");
        labelHasil = new JLabel(""); // Awalnya kosong

        // Atur font hasil agar sedikit lebih tebal
        labelHasil.setFont(new Font("Arial", Font.BOLD, 14));

        // --- 3. Menambahkan Komponen ke Frame ---
        add(labelCelcius);
        add(fieldCelcius);
        add(tombolKonversi);
        add(labelFahrenheit);
        add(labelHasil);

        // --- 4. Menambahkan ActionListener ke Tombol ---
        tombolKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Panggil metode konversi saat tombol diklik
                konversiSuhu();
            }
        });
    }

    private void konversiSuhu() {
        try {
            // 1. Ambil teks dari JTextField
            String inputText = fieldCelcius.getText();

            // 2. Ubah teks ke double
            double celcius = Double.parseDouble(inputText);

            // 3. Hitung Fahrenheit
            double fahrenheit = (celcius * 9.0 / 5.0) + 32;

            // 4. Atur teks JLabel hasil
            labelHasil.setText(String.format("%.2f", fahrenheit));

            // Ubah warna teks hasil menjadi hitam jika sebelumnya error
            labelHasil.setForeground(Color.BLACK);

        } catch (NumberFormatException ex) {
            // 5. Penanganan jika input bukan angka
            labelHasil.setText("Input salah!");
            labelHasil.setForeground(Color.RED); // Beri warna merah untuk error
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                KonverterSuhu app = new KonverterSuhu();
                app.setLocationRelativeTo(null); // Posisikan di tengah layar
                app.setVisible(true);
            }
        });
    }
}

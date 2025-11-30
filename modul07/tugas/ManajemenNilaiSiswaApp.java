package modul07.tugas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManajemenNilaiSiswaApp extends JFrame {

    private JTextField txtName;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    // Method untuk membuat desain Tab Input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa"));
        txtName = new JTextField();
        panel.add(txtName);

        // Komponen Mata Pelajaran (ComboBox)
        panel.add(new JLabel("Mata Pelajaran"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia", "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100) :"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Panel untuk tombol Simpan dan Reset
        JPanel panelTombol = new JPanel(new GridLayout(1, 2, 10, 0));

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panelTombol.add(btnSimpan);

        // 4. Tambahkan tombol reset pada Tab Input Data
        JButton btnReset = new JButton("Reset Input");
        panelTombol.add(btnReset);

        panel.add(new JLabel("")); // Placeholder kosong
        panel.add(panelTombol);

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        // Event Handling Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Panggil method untuk membersihkan inputan
                resetForm();
            }
        });

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk tombol Hapus (di bawah tabel)
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // 2. Tambahkan tombol hapus di panel bagian bawah Tab Daftar Nilai
        JButton btnHapus = new JButton("Hapus Data Terpilih");
        panelBawah.add(btnHapus);

        panel.add(panelBawah, BorderLayout.SOUTH);

        // Event Handling Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesHapus();
            }
        });

        return panel;
    }

    // Method untuk membersihkan inputan
    private void resetForm() {
        txtName.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
    }

    // Method untuk memproses penghapusan data
    private void prosesHapus() {
        // Mendapatkan indeks baris yang dipilih
        int selectedRow = tableData.getSelectedRow();

        // Cek apakah ada baris yang dipilih
        if (selectedRow > -1) {
            // Konfirmasi penghapusan
            int konfirmasi = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin menghapus data ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);

            if (konfirmasi == JOptionPane.YES_OPTION) {
                // Hapus baris dari model tabel
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Pilih baris data yang ingin dihapus terlebih dahulu.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void prosesSimpan() {
        // 1. Ambil data dari input
        String nama = txtName.getText().trim();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // 2. VALIDASI INPUT

        // Validasi 1: Cek apakah nama kosong
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Tambahkan validasi untuk memastikan nama siswa minimal terdiri dari 3 karakter
        if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama siswa minimal harus 3 karakter!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validasi 2: Cek apakah nilai berupa angka dan dalam range valid
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!", "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Logika Bisnis (Menentukan Grade)
        String grade;

        // 1. Ubah logika untuk menentukan grade menjadi menggunakan switch case
        int rentang = nilai / 10;

        switch (rentang) {
            case 10: // 100
            case 9:  // 90-99
            case 8:  // 80-89
                grade = "A";
                break;
            case 7:  // 70-79
                grade = "AB";
                break;
            case 6:  // 60-69
                grade = "B";
                break;
            case 5:  // 50-59
                grade = "BC";
                break;
            case 4:  // 40-49
                grade = "C";
                break;
            case 3:  // 30-39
                grade = "D";
                break;
            default: // 0-29
                grade = "E";
                break;
        }

        // 4. Masukkan ke Tabel (Update Model)
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 5. Reset Form dan Pindah Tab
        resetForm();

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1); // Otomatis pindah ke tab tabel
    }

    public ManajemenNilaiSiswaApp() {
        // 1. Konfigurasi Frame Utama
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar

        // 2. Inisialisasi Tabbed Pane
        tabbedPane = new JTabbedPane();

        // 3. Membuat Panel untuk Tab 1 (Form Input)
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        // 4. Membuat Panel untuk Tab 2 (Tabel Data)
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        // Menambahkan TabbedPane ke Frame
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}
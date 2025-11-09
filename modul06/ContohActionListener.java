package modul06;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ContohActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ContohActionListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        // 1. BUat komponen
        JLabel label = new JLabel("Halo, Klik tombol di samping!");
        JButton button = new JButton("Klik Saya!");

        // 2. Membuat EventListener
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 3. Logika yang di eksekusi saat event terjadi
                label.setText("Tombol telah diklik!");
            }
        };

        // 4. Daftarkan listener ke source
        button.addActionListener(listener);

        // Tambahkan Komponen ke frame
        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
    }
}

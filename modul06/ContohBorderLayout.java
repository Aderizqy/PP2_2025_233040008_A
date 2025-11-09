package modul06;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ContohBorderLayout {
    public static void main(String[] args) {
        // 1. Buat frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 2. Buat dan tambahkan komponen ke lima wilayah
        frame.add(new JButton("NORTH"), BorderLayout.NORTH);
        frame.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        frame.add(new JButton("EAST"), BorderLayout.EAST);
        frame.add(new JButton("WEST"), BorderLayout.WEST);
        frame.add(new JButton("CENTER"), BorderLayout.CENTER);

        // 3. Tampilkan frame
        frame.setVisible(true);
    }
}

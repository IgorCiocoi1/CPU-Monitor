import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    // Instanță pentru monitorizarea CPU și RAM
    private static CPUMonitor monitor;
    // Timer pentru executarea sarcinilor repetitiv
    private static Timer timer;

    public static void main(String[] args) {
        // Inițializează monitorul de sistem
        monitor = new CPUMonitor();

        // Creează o fereastră principală
        JFrame frame = new JFrame("CPU and RAM MONITORING");
        frame.setSize(500, 400); // Dimensiunea ferestrei
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Închide aplicația la închiderea ferestrei

        // Titlul aplicației
        JLabel titleLabel = new JLabel("CPU and RAM Monitor");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Font mare și îngroșat
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Aliniere centrală
        titleLabel.setForeground(Color.BLUE); // Text albastru

        // Etichete pentru afișarea datelor CPU și RAM
        JLabel cpuLabel = new JLabel("CPU LOAD: ");
        JLabel ramLabel = new JLabel("RAM FREE: ");
        JLabel totalRamLabel = new JLabel("TOTAL RAM: ");

        // Stilizare pentru etichete
        cpuLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        cpuLabel.setForeground(Color.DARK_GRAY);

        ramLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        ramLabel.setForeground(Color.DARK_GRAY);

        totalRamLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        totalRamLabel.setForeground(Color.DARK_GRAY);

        // Crearea unui panou principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Layout cu zone
        panel.setBackground(Color.LIGHT_GRAY); // Fundal gri deschis

        // Panou pentru etichete
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Grid cu 3 rânduri, 1 coloană și spațiu între elemente
        infoPanel.setBackground(Color.LIGHT_GRAY); // Fundal gri deschis
        infoPanel.add(cpuLabel);
        infoPanel.add(ramLabel);
        infoPanel.add(totalRamLabel);

        // Adaugă componentele în panoul principal
        panel.add(titleLabel, BorderLayout.NORTH); // Titlul în partea de sus
        panel.add(infoPanel, BorderLayout.CENTER); // Etichetele în centru

        // Adaugă panoul în fereastră
        frame.add(panel);
        frame.setVisible(true); // Face fereastra vizibilă

        // Pornește monitorizarea cu actualizări la fiecare 5 secunde
        startMonitoring(5000, cpuLabel, ramLabel, totalRamLabel);
    }

    private static void startMonitoring(int interval, JLabel cpuLabel, JLabel ramLabel, JLabel totalRamLabel) {
        timer = new Timer(); // Creează un Timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Obține datele curente despre CPU și RAM
                double cpuLoad = monitor.getCpuLoad();
                double freeMemory = monitor.getFreeMemory();
                double totalMemory = monitor.getTotalMemory();

                // Actualizează interfața grafică
                SwingUtilities.invokeLater(() -> {
                    cpuLabel.setText("CPU Load: " + String.format("%.2f", cpuLoad) + " %");
                    ramLabel.setText("RAM FREE: " + String.format("%.2f", freeMemory) + " GiB");
                    totalRamLabel.setText("TOTAL RAM: " + String.format("%.2f", totalMemory) + " GiB");
                });
            }
        }, 0, interval); // Rulează imediat și repetitiv la fiecare interval
    }
}
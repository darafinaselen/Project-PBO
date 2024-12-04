import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WorkoutAppWithVideo extends Application {

    @Override
    public void start(javafx.stage.Stage primaryStage) {
        System.out.println("JavaFX application started."); // Debug log
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Application starting..."); // Debug log

                // Frame Utama
                JFrame frame = new JFrame("Workout App");
                frame.setSize(390, 700);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                // Panel Utama
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                frame.add(mainPanel, BorderLayout.CENTER);

                // Panel Video Menggunakan JavaFX
                JFXPanel videoPanel = new JFXPanel();
                videoPanel.setPreferredSize(new Dimension(350, 400));
                mainPanel.add(videoPanel);

                // Menginisialisasi Media Player di JavaFX
                SwingUtilities.invokeLater(() -> initFX(videoPanel));

                // Timer Panel
                JPanel timerPanel = new JPanel(new FlowLayout());
                JLabel timerLabel = new JLabel("Durasi Workout: 20 detik");
                timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
                timerPanel.add(timerLabel);
                mainPanel.add(timerPanel);

                // Tombol untuk memulai workout
                JPanel buttonPanel = new JPanel();
                JButton startButton = new JButton("Mulai Workout");
                buttonPanel.add(startButton);
                mainPanel.add(buttonPanel);

                // Timer Countdown Logic
                javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
                    int seconds = 20;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (seconds > 0) {
                            timerLabel.setText("Durasi Workout: " + seconds + " detik");
                            seconds--;
                        } else {
                            timerLabel.setText("Workout Selesai!");
                            ((javax.swing.Timer) e.getSource()).stop();
                        }
                    }
                });

                startButton.addActionListener(e -> timer.start());

                frame.setVisible(true);
                System.out.println("Application running."); // Debug log
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void initFX(JFXPanel panel) {
        try {
            String videoPath = "path_to_your_video.mp4"; // Masukkan path video Anda di sini
            File videoFile = new File(videoPath);

            if (!videoFile.exists()) {
                System.err.println("Video file tidak ditemukan di: " + videoPath);
                return;
            }

            Media media = new Media(videoFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            // Atur ukuran video
            mediaView.setFitWidth(350);
            mediaView.setFitHeight(400);

            // Membuat root untuk JavaFX Scene
            StackPane root = new StackPane();
            root.getChildren().add(mediaView);

            // Menambahkan scene JavaFX ke JFXPanel
            Scene scene = new Scene(root, 350, 400);
            panel.setScene(scene);

            // Mulai pemutaran video
            mediaPlayer.play();
            System.out.println("Video started."); // Debug log
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

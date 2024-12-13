// import java.awt.*;
// import java.util.List;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.SwingUtilities;

// public class WorkoutInfoUpdaterThread extends Thread {
// private List<WorkoutVideoList> videoList;
// private JPanel durasiPanel, latihanPanel, kaloriPanel;

// public WorkoutInfoUpdaterThread(List<WorkoutVideoList> videoList, JPanel
// durasiPanel, JPanel latihanPanel,
// JPanel kaloriPanel) {
// this.videoList = videoList;
// this.durasiPanel = durasiPanel;
// this.latihanPanel = latihanPanel;
// this.kaloriPanel = kaloriPanel;
// }

// public void run() {
// int totalDurationInSeconds = 0;
// int totalCalories = 0;
// int totalWorkouts = videoList.size();

// // Menghitung total durasi dan kalori
// for (WorkoutVideoList video : videoList) {
// totalDurationInSeconds += video.getDuration();
// totalCalories += video.getCalories();
// }

// // Mengonversi durasi ke format menit:detik
// String formattedDuration = formatDuration(totalDurationInSeconds);

// // Gunakan SwingUtilities.invokeLater untuk memperbarui UI di thread utama
// SwingUtilities.invokeLater(() -> {
// // Durasi Panel
// durasiPanel.removeAll();
// JLabel durasiLabel = new JLabel("Durasi: " + formattedDuration);
// durasiLabel.setFont(new Font("Lato", Font.PLAIN, 12));
// durasiLabel.setForeground(Color.BLACK);
// durasiPanel.add(durasiLabel);

// // Latihan Panel
// latihanPanel.removeAll();
// JLabel latihanLabel = new JLabel("Latihan: " + totalWorkouts);
// latihanLabel.setFont(new Font("Lato", Font.PLAIN, 12));
// latihanLabel.setForeground(Color.BLACK);
// latihanPanel.add(latihanLabel);

// // Kalori Panel
// kaloriPanel.removeAll();
// JLabel kaloriLabel = new JLabel("Kalori: " + totalCalories + " kkal");
// kaloriLabel.setFont(new Font("Lato", Font.PLAIN, 12));
// kaloriLabel.setForeground(Color.BLACK);
// kaloriPanel.add(kaloriLabel);

// // Revalidate dan repaint untuk memperbarui tampilan
// durasiPanel.revalidate();
// latihanPanel.revalidate();
// kaloriPanel.revalidate();
// durasiPanel.repaint();
// latihanPanel.repaint();
// kaloriPanel.repaint();
// });
// }

// private String formatDuration(int totalSeconds) {
// int minutes = totalSeconds / 60;
// int seconds = totalSeconds % 60;
// return String.format("%d:%02d", minutes, seconds);
// }
// }

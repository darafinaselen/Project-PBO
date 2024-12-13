import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
// import java.util.ArrayList;
import java.util.List;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class WorkoutApp {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WorkoutApp().createAndShowGUI());

        WorkoutVideoList newVideo = new WorkoutVideoList("Stright Leg Sit Up", 25,
                "assets\\gambar\\Stright_Leg_Sit_Up.png",
                "-");
        WorkoutVideoList newVideo2 = new WorkoutVideoList("Elevated Heels Slide", 25,
                "assets\\gambar\\ELEVATED.png", "-");
        WorkoutVideoList newVideo3 = new WorkoutVideoList("Superman", 25,
                "assets\\gambar\\Superman.png", "-");
        WorkoutVidepDAO.save(newVideo);
        WorkoutVidepDAO.save(newVideo2);
        WorkoutVidepDAO.save(newVideo3);
    }

    public void createAndShowGUI() {
        // Membuat JFrame
        JFrame frame = new JFrame("Workout App");
        frame.setSize(390, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel descPanel = createDescPanel();
        mainPanel.add(descPanel, "Deskripsi");

        JPanel workoutListPanel = createWorkoutListPanel();
        mainPanel.add(workoutListPanel, "WorkoutList");

        // Tambahkan panel utama ke frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createDescPanel() {
        // JPanel descPanel = new JPanel();
        // descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        // descPanel.setBackground(Color.WHITE);

        // Panel utama (Panel 1)
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));

        // Panel 1.1: Indikator HP
        JPanel panel11 = new JPanel();
        panel11.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel11.setMaximumSize(new Dimension(390, 47));
        panel11.setMinimumSize(new Dimension(390, 47));
        panel11.setPreferredSize(new Dimension(390, 47));
        panel11.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel11.setBackground(Color.WHITE);

        ImageIcon indicatorIcon = new ImageIcon("assets/icon/indikator_iphone.png");
        Image img = indicatorIcon.getImage();
        Image resizedImage = img.getScaledInstance(390, 47, Image.SCALE_SMOOTH);
        indicatorIcon = new ImageIcon(resizedImage);
        JLabel indicatorLabel = new JLabel(indicatorIcon);

        // panel11.add(indicatorLabel, BorderLayout.CENTER);
        panel11.add(indicatorLabel);
        descPanel.add(panel11);
        // Menambahkan panel11 ke mainPanel di bagian NORTH
        // mainPanel.add(panel11, BorderLayout.NORTH);

        // Panel 1.2: Panel konten utama
        // JPanel panel12 = new JPanel();
        // panel12.setLayout(new BoxLayout(panel12, BoxLayout.Y_AXIS));

        // Panel 1.2: Header dengan ikon back dan teks "ABS"
        JPanel panel12 = new JPanel();
        // panel12.setLayout(new BorderLayout());
        panel12.setLayout(new BoxLayout(panel12, BoxLayout.Y_AXIS));
        panel12.setPreferredSize(new Dimension(330, 35));
        panel12.setBackground(Color.WHITE);

        // panel121.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
        // Color.decode("#8A9C60")));
        // panel121.setBorder(new EmptyBorder(30, 0, 0, 0));
        // // Garis bawah

        JPanel headerPanel = new JPanel();
        // headerPanel.setLayout(new BorderLayout());
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBackground(Color.WHITE);

        Border matteBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#8A9C60"));
        Border emptyBorder = new EmptyBorder(15, 20, 0, 20);
        Border combinedBorder = BorderFactory.createCompoundBorder(emptyBorder, matteBorder);
        headerPanel.setBorder(combinedBorder);

        ImageIcon backIcon = new ImageIcon("assets/icon/icon_button_back.png");
        Image img2 = backIcon.getImage();
        Image resizedImage2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(resizedImage2);

        JButton backButton = new JButton(backIcon);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        // panel121.add(backButton, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("ABS");
        headerLabel.setFont(new Font("Lato", Font.BOLD, 20));
        headerLabel.setForeground(Color.decode("#8A9C60"));
        // headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // panel121.add(headerLabel, BorderLayout.CENTER);

        // headerPanel.add(backButton, BorderLayout.WEST);
        // headerPanel.add(headerLabel, BorderLayout.CENTER);

        headerPanel.add(backButton);
        headerPanel.add(Box.createHorizontalStrut(90));
        headerPanel.add(headerLabel);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.setMaximumSize(new Dimension(390, 50));
        headerPanel.setMinimumSize(new Dimension(390, 50));
        headerPanel.setPreferredSize(new Dimension(390, 50));

        panel12.add(headerPanel, BorderLayout.NORTH);

        // // Panel 1.2.2: Gambar utama
        JPanel panelGambar = new JPanel();
        ImageIcon gambar = new ImageIcon("assets/gambar/ABS_frame.png");
        Image gbr = gambar.getImage();
        Image resgbr = gbr.getScaledInstance(329, 273, Image.SCALE_SMOOTH);
        gambar = new ImageIcon(resgbr);

        JLabel mainImageLabel = new JLabel(gambar);
        panelGambar.add(mainImageLabel);
        panelGambar.setBackground(Color.WHITE);
        panelGambar.setMaximumSize(new Dimension(390, 280));
        panelGambar.setMinimumSize(new Dimension(390, 280));
        panelGambar.setPreferredSize(new Dimension(390, 280));

        panel12.add(panelGambar);

        // Panel 1.2.3: Sub-header dan deskripsi
        JPanel panelSubDes = new JPanel();
        panelSubDes.setLayout(new BoxLayout(panelSubDes, BoxLayout.Y_AXIS));
        panelSubDes.setPreferredSize(new Dimension(390, 150));
        panelSubDes.setMinimumSize(new Dimension(390, 150));
        panelSubDes.setMaximumSize(new Dimension(390, 150));
        panelSubDes.setBackground(Color.WHITE);

        // Panel 1.2.3.1: Sub-header
        JPanel panelSub = new JPanel();
        JLabel subHeaderLabel = new JLabel("ABS (Abdominal Muscles)");
        subHeaderLabel.setFont(new Font("Lato", Font.BOLD, 16));
        subHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelSub.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSub.setBackground(Color.WHITE);
        panelSub.setPreferredSize(new Dimension(330, 40));
        panelSub.setMinimumSize(new Dimension(330, 40));
        panelSub.setMaximumSize(new Dimension(330, 40));

        subHeaderLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
        panelSub.setBorder(bottomBorder);

        panelSub.add(subHeaderLabel);
        panelSubDes.add(panelSub);

        // Panel 1.2.3.2: Kata-kata
        JPanel panelDes = new JPanel();
        panelDes.setLayout(new BorderLayout());
        panelDes.setPreferredSize(new Dimension(330, 80));
        panelDes.setMinimumSize(new Dimension(330, 80));
        panelDes.setMaximumSize(new Dimension(330, 80));

        JLabel descriptionLabel = new JLabel(
                "<html><div style='width: 100%;'>Waktu terbatas? Kamu tetap bisa melatih otot perut secara efektif hanya dalam 7 menit! Perkuat abs, tingkatkan stabilitas, dan rasakan hasilnya—cocok untuk jadwal padatmu!</div></html>");
        descriptionLabel.setFont(new Font("Lato", Font.PLAIN, 12));
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panelDes.add(descriptionLabel, BorderLayout.CENTER);
        panelDes.setBackground(Color.WHITE);

        panelSubDes.add(panelDes);
        panel12.add(panelSubDes);

        // Panel 1.2.4: Tombol "Mulai Workout"
        JPanel panelButton = new JPanel();
        RoundedButton startWorkoutButton = new RoundedButton("Mulai Workout", 50);
        startWorkoutButton.setFont(new Font("Lato", Font.PLAIN, 16));
        startWorkoutButton.setBackground(Color.decode("#8A9C60"));
        startWorkoutButton.setForeground(Color.WHITE);
        startWorkoutButton.setFocusPainted(false);

        startWorkoutButton.addActionListener(e -> cardLayout.show(mainPanel, "WorkoutList"));
        panelButton.add(startWorkoutButton);

        startWorkoutButton.setPreferredSize(new Dimension(330, 50));
        panelButton.setBorder(new EmptyBorder(30, 0, 0, 0));
        panelButton.setBackground(Color.WHITE);
        panelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel12.add(Box.createVerticalGlue());
        panel12.add(panelButton);

        // // Tambahkan panel 1.1 dan 1.2 ke panel utama
        descPanel.add(panel12);
        return descPanel;
    }

    private JPanel createInfoPanel(String labelText, String valueText, String iconPath) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        infoPanel.setPreferredSize(new Dimension(infoPanel.getPreferredSize().width, 50)); // Lebar otomatis, tinggi 15
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Batasi tinggi maksimum
        infoPanel.setMinimumSize(new Dimension(infoPanel.getMinimumSize().width, 50)); // Tinggi minimum 15

        // Label atas (Durasi, Latihan, Kalori)
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Lato", Font.PLAIN, 12));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel bawah (Icon dan nilai)
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        subPanel.setBackground(Color.WHITE);

        subPanel.setPreferredSize(new Dimension(subPanel.getPreferredSize().width, 15));
        subPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizedImg);
        JLabel iconLabel = new JLabel(icon);

        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setFont(new Font("Lato", Font.PLAIN, 12));
        valueLabel.setForeground(Color.BLACK);

        subPanel.add(iconLabel);
        subPanel.add(valueLabel);

        infoPanel.add(label);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(subPanel);

        return infoPanel;
    }

    private JPanel createWorkoutListPanel() {
        JPanel workoutListPanel = new JPanel();
        workoutListPanel.setLayout(new BoxLayout(workoutListPanel,
                BoxLayout.Y_AXIS));
        workoutListPanel.setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel();
        // headerPanel.setLayout(new BorderLayout());
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBackground(Color.WHITE);

        Border matteBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#8A9C60"));
        Border emptyBorder = new EmptyBorder(15, 20, 0, 20);
        Border combinedBorder = BorderFactory.createCompoundBorder(emptyBorder, matteBorder);
        headerPanel.setBorder(combinedBorder);

        ImageIcon backIcon = new ImageIcon("assets/icon/icon_button_back.png");
        Image img2 = backIcon.getImage();
        Image resizedImage2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(resizedImage2);

        JButton backButton = new JButton(backIcon);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Deskripsi"));

        JLabel headerLabel = new JLabel("ABS");
        headerLabel.setFont(new Font("Lato", Font.BOLD, 20));
        headerLabel.setForeground(Color.decode("#8A9C60"));

        headerPanel.add(backButton);
        headerPanel.add(Box.createHorizontalStrut(90));
        headerPanel.add(headerLabel);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.setMaximumSize(new Dimension(390, 50));
        headerPanel.setMinimumSize(new Dimension(390, 50));
        headerPanel.setPreferredSize(new Dimension(390, 50));

        JPanel awalPanel = new JPanel();
        awalPanel.setLayout(new BoxLayout(awalPanel, BoxLayout.Y_AXIS));
        awalPanel.setBackground(Color.WHITE);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.setBackground(Color.WHITE);
        labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));
        labelPanel.setPreferredSize(new Dimension(labelPanel.getPreferredSize().width, 30));

        JLabel tanpaAlatLabel = new JLabel("Tanpa Alat");
        tanpaAlatLabel.setFont(new Font("Lato", Font.BOLD, 16));
        tanpaAlatLabel.setForeground(Color.BLACK);
        tanpaAlatLabel.setHorizontalAlignment(SwingConstants.LEFT);
        labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        labelPanel.add(tanpaAlatLabel);
        awalPanel.add(labelPanel);
        awalPanel.add(Box.createVerticalStrut(5));

        JPanel infoRowPanel = new JPanel();
        infoRowPanel.setLayout(new BoxLayout(infoRowPanel, BoxLayout.X_AXIS));
        infoRowPanel.setBackground(Color.WHITE);
        infoRowPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JPanel durasiPanel = createInfoPanel("Durasi", "7:24", "assets/icon/icon_durasi.png");
        JPanel latihanPanel = createInfoPanel("Latihan", "12", "assets/icon/icon_latihan.png");
        JPanel kaloriPanel = createInfoPanel("Kalori", "50 kkal", "assets/icon/icon_api_kalori.png");

        infoRowPanel.add(durasiPanel);
        infoRowPanel.add(Box.createVerticalStrut(5));
        infoRowPanel.add(latihanPanel);
        infoRowPanel.add(Box.createVerticalStrut(5));
        infoRowPanel.add(kaloriPanel);

        awalPanel.add(infoRowPanel);

        workoutListPanel.add(headerPanel, BorderLayout.NORTH);

        // workoutListPanel.add(infoPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        List<WorkoutVideoList> videoList = WorkoutVidepDAO.fetchAll();

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        // listPanel.setBackground(Color.GREEN);
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        for (WorkoutVideoList video : videoList) {

            RoundedButton videoPanel = new RoundedButton(10);
            videoPanel.setLayout(new BoxLayout(videoPanel, BoxLayout.X_AXIS));
            videoPanel.setBackground(Color.WHITE);
            videoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            videoPanel.setPreferredSize(new Dimension(330, 70));
            videoPanel.setMaximumSize(new Dimension(330, 70));

            videoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            JPanel imagePanel = new JPanel();
            // imagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            imagePanel.setLayout(new GridBagLayout());
            imagePanel.setPreferredSize(new Dimension(70, 50));
            imagePanel.setMaximumSize(new Dimension(70, 50));
            imagePanel.setBackground(Color.WHITE);

            ImageIcon resizedIcon = resizeImageWithCanvas(video.getImagePath(), 50, 50);
            JLabel imageLabel = new JLabel(resizedIcon);
            imagePanel.add(imageLabel);
            imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel(video.getTitle());
            titleLabel.setFont(new Font("Lato", Font.BOLD, 14));
            titleLabel.setForeground(Color.BLACK);
            textPanel.add(titleLabel);

            // Panel untuk durasi
            JLabel durationLabel = new JLabel(video.getDuration() + " detik");
            durationLabel.setFont(new Font("Lato", Font.PLAIN, 12));
            durationLabel.setForeground(Color.BLACK);
            textPanel.add(durationLabel);

            videoPanel.add(imagePanel);
            videoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            videoPanel.add(textPanel);
            videoPanel.setOpaque(false);

            listPanel.add(videoPanel);
            listPanel.add(Box.createVerticalStrut(10));
        }

        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        listPanel.setBackground(Color.WHITE);
        listPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        contentPanel.add(awalPanel);
        contentPanel.add(scrollPane);

        listPanel.revalidate();
        listPanel.repaint();

        workoutListPanel.add(contentPanel, BorderLayout.CENTER);
        return workoutListPanel;
    }

    class RoundedButton extends JButton {
        private int radius;

        public RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        public RoundedButton(int radius) {
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));

            // g2.fill(new RoundRectangle2D.Double(3, 3, getWidth() - 6, getHeight() - 6,
            // radius, radius));

            for (int i = 1; i <= 2; i++) { // Draw multiple layers
                g2.setColor(new Color(128, 128, 128, 35 - (i * 10))); // Adjust alpha for transparency
                g2.draw(new RoundRectangle2D.Double(i, i, getWidth() - i * 2, getHeight() - i * 2, radius, radius));
            }

            // g2.setColor(Color.GRAY);
            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, radius, radius));
            super.paintComponent(g);
        }

    }

    public ImageIcon resizeImageWithCanvas(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();

        // Membuat canvas 50x50
        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = canvas.createGraphics();

        // Gambar ulang gambar pada canvas dengan ukuran baru
        int radius = 1;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // g2d.drawImage(img, x, y, scaledWidth, scaledHeight, null);
        g2d.setClip(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(canvas);
    }
}

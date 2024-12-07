import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutApp {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WorkoutApp().createAndShowGUI());
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
        awalPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JLabel tanpaAlatLabel = new JLabel("Tanpa Alat");
        tanpaAlatLabel.setFont(new Font("Lato", Font.BOLD, 16));
        tanpaAlatLabel.setForeground(Color.BLACK);
        tanpaAlatLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        awalPanel.add(tanpaAlatLabel);
        awalPanel.add(Box.createVerticalStrut(5));

        // Durasi, latihan dan kalori
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        infoPanel.setBackground(Color.WHITE);

        ImageIcon durationIcon = new ImageIcon("assets/icon/icon_durasi.png");
        Image dur = durationIcon.getImage();
        Image resize = dur.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        durationIcon = new ImageIcon(resize);

        JLabel durationIconLabel = new JLabel(durationIcon);
        JLabel durationLabel = new JLabel("7:24");
        durationLabel.setFont(new Font("Lato", Font.PLAIN, 14));
        durationLabel.setForeground(Color.BLACK);

        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new BoxLayout(durationPanel, BoxLayout.X_AXIS));
        durationPanel.setBackground(Color.WHITE);
        durationPanel.add(durationIconLabel);
        durationPanel.add(Box.createHorizontalStrut(5));
        durationPanel.add(durationLabel);

        infoPanel.add(durationPanel);

        workoutListPanel.add(headerPanel, BorderLayout.NORTH);
        workoutListPanel.add(awalPanel);
        workoutListPanel.add(infoPanel);
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

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
            super.paintComponent(g);
        }

    }
}

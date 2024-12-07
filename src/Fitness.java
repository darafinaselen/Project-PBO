
// untuk mengimport library yang ada di javax swing untuk membuat tampilan GUI
import javax.swing.*;
// untuk mrngimport library yang ada di javax swing border EmptyBorder untuk menetapkan padding disekitar tampilan GUI
import javax.swing.border.EmptyBorder;
// untuk mengimport library yang ada di java.awt untuk membuat tampilan GUI
import java.awt.*;
// untuk mengimport library yang ada di jMouseAdapter untuk membuat event pada GUI
import java.awt.event.*;
// library yang digunakan untuk mengelola array list
import java.util.ArrayList;

public class Fitness {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public static void main(String[] args) {
        Fitness app = new Fitness();
        app.launchApp();
    }

    // Method to launch the app
    public void launchApp() {
        frame = new JFrame("Fitness App");
        frame.setSize(600, 1400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // CardLayout untuk navigasi antar halaman
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Tambahkan halaman ke CardLayout
        mainPanel.add(createMainContentPanel(), "For You");
        mainPanel.add(createWorkoutPanel(), "Workout");
        mainPanel.add(createMealsPanel(), "Meals");
        // mainPanel.setBackground(Color.cyan);

        // Tambahkan navigasi header
        frame.add(createHeaderPanel(), BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(createNavBar(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    // Navigari Baris pada bagian bawah
    JPanel createNavBar(){
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new GridLayout(1, 4));  // 1 baris, 4 kolom
        navbarPanel.setBackground(new Color(50, 50, 50));

        // Menambahkan tombol untuk setiap fitur navbar
        JButton homeButton = createNavButton("Home");
        JButton chatButton = createNavButton("Chat");
        JButton jadwalButton = createNavButton("Jadwal");
        JButton profilButton = createNavButton("Profil");

        // Menambahkan tombol ke dalam panel navbar
        navbarPanel.add(homeButton);
        navbarPanel.add(chatButton);
        navbarPanel.add(jadwalButton);
        navbarPanel.add(profilButton);

        return navbarPanel;
    }

    // Header navigasi
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 3, 15, 0));
        headerPanel.setBackground(Color.lightGray);
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15)); 
        // Tombol navigasi
        JButton forYouButton = createNavButton("For You");
        JButton workoutButton = createNavButton("Workout");
        JButton mealsButton = createNavButton("Meals");

        forYouButton.addActionListener(e -> cardLayout.show(mainPanel, "For You"));
        workoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Workout"));
        mealsButton.addActionListener(e -> cardLayout.show(mainPanel, "Meals"));
        forYouButton.setBackground(Color.white);
        workoutButton.setBackground(Color.white);
        mealsButton.setBackground(Color.white);

        headerPanel.add(forYouButton);
        headerPanel.add(workoutButton);
        headerPanel.add(mealsButton);

        JPanel searchFieldContainer = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText(""); // Clear placeholder when focus is gained
                    searchField.setForeground(Color.BLACK); // Set text color to black
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search..."); // Set placeholder back if no text is entered
                    searchField.setForeground(Color.GRAY); // Set placeholder text color
                }
            }
        });
        searchFieldContainer.setBorder(new EmptyBorder(15, 15, 15, 15));
        searchFieldContainer.add(searchField, BorderLayout.CENTER);
        
        // searchField.setBorder(new EmptyBorder(15, 15, 15, 15)); 

        JPanel containerTemp = new JPanel(new GridLayout(2, 0, 0, 0));
        containerTemp.add(searchFieldContainer);
        containerTemp.add(headerPanel);

        return containerTemp;
    }

    // Tombol navigasi
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    // Halaman konten utama (Workout dan Stress-Relief)
    private JPanel createMainContentPanel() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.BLACK);

        // Tambahkan kategori Workout
        ArrayList<Workout> workout = new ArrayList<>();
        workout.add(new Workout("ABS", "assets\\Workout\\ABS_frame.png",
                "ABS adalah singkatan dari abdominal muscles atau otot perut, yang merujuk pada kelompok otot yang terletak di area perut bagian depan. Otot-otot ini memiliki beberapa bagian, yang masing-masing memiliki fungsi yang berbeda.\n"
                        +
                        "\nBerikut adalah bagian utama dari otot perut: \n" +
                        "1. Rectus Abdominis: Ini adalah otot yang paling dikenal, yang membentang dari tulang dada hingga panggul. Otot ini bertanggung jawab untuk memberikan bentuk perut yang lebih ramping dan terdefinisi, serta berfungsi dalam gerakan seperti membungkuk atau menarik tubuh ke depan.\n"
                        +
                        "2. Obliques (Otot Serong): Ada dua jenis otot obliques—obliques eksternal dan internal—yang terletak di sisi tubuh. Otot ini berfungsi untuk memutar tubuh dan membantu gerakan samping.\n"
                        +
                        "3. Transverse Abdominis: Otot ini adalah lapisan terdalam dari otot perut dan berfungsi untuk memberikan stabilitas pada tubuh, menjaga postur tubuh, dan melindungi organ internal.\n"));
        workout.add(new Workout("Upper Body", "assets\\Workout\\Upper_body_frame.png",
                "Upper body merujuk pada bagian tubuh manusia yang terletak di bagian atas, mulai dari leher hingga pinggang, yang mencakup kepala, leher, dada, punggung atas, bahu, dan lengan atas. Latihan upper body bertujuan untuk memperkuat dan membentuk otot-otot di bagian tubuh ini, yang meliputi otot dada (pectoralis), otot punggung (latissimus dorsi dan trapezius), otot bahu (deltoid), serta otot lengan (bisep dan trisep).\n"
                        +
                        "\nBeberapa latihan yang efektif untuk melatih upper body antara lain: \n" +
                        "1. push-up, yang melatih dada, trisep, dan bahu \n" +
                        "2. bench press untuk otot dada dan trisep \n" +
                        "3. pull-up yang fokus pada punggung dan bisep \n" +
                        "4. lat pulldown untuk memperkuat otot punggung atas \n" +
                        "5. dumbbell shoulder press dapat melatih bahu \n" +
                        "6. bicep curl dan triceps dips fokus pada otot bisep dan trisep \n"));
        workout.add(new Workout("Lower Body", "assets\\Workout\\Lower Body.jpeg",
                "Lower body merujuk pada bagian tubuh manusia yang terletak di bawah pinggang, mencakup otot-otot dan struktur tubuh seperti paha, lutut, betis, dan bokong. Latihan lower body bertujuan untuk memperkuat otot-otot besar di bagian bawah tubuh, seperti otot quadriceps (paha depan), hamstring (paha belakang), gluteus (bokong), dan otot betis (gastrocnemius dan soleus). \n"
                        +
                        "\nBeberapa latihan yang efektif untuk melatih lower body antara lain: \n" +
                        "1. Squat, yang melibatkan hampir seluruh otot bagian bawah tubuh \n" +
                        "2. Lunges, yang mengaktifkan otot paha dan bokong; deadlift, yang berfokus pada hamstring, gluteus \n"
                        +
                        "3. Punggung bawah; serta calf raise untuk melatih otot betis \n"));
        workout.add(new Workout("Side Plank", "assets\\Workout\\Side Plank.jpg",
                "Side plank adalah latihan tubuh bagian inti yang dilakukan dengan posisi tubuh miring, dengan satu tangan dan satu kaki sebagai tumpuan, sementara tubuh lainnya terangkat dan lurus. Latihan ini fokus pada penguatan otot-otot inti, khususnya otot obliques yang terletak di sisi tubuh. Selain itu, side plank juga melibatkan otot-otot lainnya seperti punggung, bahu, dan pinggul. \n"
                        +
                        "\nUntuk melakukan side plank, ikuti langkah-langkah berikut: \n" +
                        "1. Berbaring miring di lantai dengan kaki lurus dan tumpukan salah satu kaki di atas kaki lainnya. \n"
                        +
                        "2. Letakkan tangan bawah di lantai dengan lengan lurus, atau bisa juga dengan tangan ditekuk, tergantung pada kenyamanan. \n"
                        +
                        "3. Angkat pinggul dari lantai sehingga tubuh membentuk garis lurus dari kepala hingga kaki. \n"
                        +
                        "4. Tahan posisi ini selama beberapa detik hingga beberapa menit, lalu turunkan dan ulangi di sisi lainnya. \n"));
        workout.add(new Workout("Squat", "assets\\Workout\\Squat.jpg",
                "Squat adalah latihan fisik yang melibatkan gerakan menurunkan tubuh ke posisi jongkok, kemudian kembali berdiri dengan posisi tubuh tegak. Latihan ini terutama menargetkan otot-otot bagian bawah tubuh, seperti quadriceps (paha depan), hamstring (paha belakang), gluteus (bokong), dan otot inti (core)."
                        +
                        "\nSelain itu, squat juga dapat meningkatkan postur tubuh dan membantu memperkuat otot-otot yang digunakan dalam aktivitas sehari-hari, seperti berjalan, berlari, atau mengangkat barang. Variasi squat, seperti goblet squat, front squat, dan barbell squat, dapat dilakukan dengan tambahan beban untuk meningkatkan intensitas latihan. "));
        workout.add(new Workout("Push Up", "assets\\Workout\\Push Up.jpg",
                "Push-up adalah latihan tubuh bagian atas yang dilakukan dengan posisi tubuh tengkurap, kemudian menekan tubuh ke atas dan ke bawah menggunakan tangan sebagai tumpuan. Latihan ini terutama menargetkan otot dada (pectoralis), otot trisep (otot lengan belakang), dan otot bahu (deltoid), serta melibatkan otot inti (core) dan otot punggung atas sebagai stabilisator. \n"
                        +
                        "\nUntuk melakukan push up, ikuti langkah-langkah berikut: \n" +
                        "1. Letakkan tangan sedikit lebih lebar dari bahu \n" +
                        "2. Turunkan tubuh hingga dada hampir menyentuh lantai \n" +
                        "3. Dorong tubuh kembali ke posisi semula dengan menggunakan kekuatan tangan dan dada \n"));

        mainContentPanel.add(createCategoryPanel("Workout", workout));

        // Tambahkan kategori Stress-Relief
        ArrayList<Workout> stressRelief = new ArrayList<>();
        stressRelief.add(new Workout("Relaksasi", "assets\\Workout\\relaksasi_frame.png","Relaksasi adalah proses mengurangi ketegangan fisik dan mental dengan tujuan untuk mencapai keadaan tenang, nyaman, dan seimbang. Teknik relaksasi dapat melibatkan berbagai metode, seperti pernapasan dalam, meditasi, yoga, atau mendengarkan musik yang menenangkan, yang membantu menurunkan tingkat stres dan kecemasan, serta meningkatkan kesehatan mental dan fisik secara keseluruhan." +
        "\n Saat melakukan relaksasi, tubuh dan pikiran diberikan kesempatan untuk melepaskan ketegangan yang telah terakumulasi akibat tekanan sehari-hari, memungkinkan sistem saraf untuk kembali ke keadaan lebih stabil. Relaksasi secara teratur dapat meningkatkan kualitas tidur, mengurangi tekanan darah, dan meningkatkan konsentrasi serta kesejahteraan emosional."));
        stressRelief.add(new Workout("Yoga", "assets\\Workout\\yoga_frame.png","Yoga adalah olahraga yang menggabungkan gerakan tubuh, pernapasan, meditasi, dan relaksasi untuk meningkatkan kesehatan dan kesejahteraan. Yoga merupakan latihan pikiran-tubuh yang dapat membantu meningkatkan kekuatan, fleksibilitas, dan kontrol pikiran." +
        "\nYoga memiliki beberapa manfaat untuk kesehatan yaitu: " +
        "\n1. Membantu mengurangi setres\n" +
        "2. Meningkatkan kebugaran tubuh\n" +
        "3. Mengatasi berbagai masalah kesehatan\n" +
        "4. Meringankan sakit punggung\n" +
        "5. Membantu membakar lemak tubuh\n" ));
        stressRelief.add(new Workout("Meditasi", "assets\\Workout\\Mediasi.png", "Meditasi adalah teknik menjernihkan pikiran untuk mempertajam fokus dan perhatian seseorang.\n" +
        "Praktik ini dilakukan dengan memejamkan mata sembari mengatur pernapasan untuk membuang seluruh emosi negatif.\n" +
        "\nManfaat Meditasi sebagai berikut: " +
        "\n1. Mengurangi setres\n" +
        "2. Meningkatkan kualitas tidur\n" +
        "3. Mengatur emosi lebih baik\n" +
        "4. Meningkatkan fokus\n" +
        "5. Mengelola suasana hati\n"));

        mainContentPanel.add(createCategoryPanel("Stress-Relief", stressRelief));

        return mainContentPanel;
    }

    private JPanel createWorkoutPanel() {
        JPanel workoutPanel = new JPanel();
        workoutPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));
        workoutPanel.setBackground(Color.BLACK);

        // Tambahkan kategori Pilihan Workout
        ArrayList<Workout> workout = new ArrayList<>();
        workout.add(new Workout("ABS", "assets\\Workout\\ABS_frame.png",
                "ABS adalah singkatan dari abdominal muscles atau otot perut, yang merujuk pada kelompok otot yang terletak di area perut bagian depan. Otot-otot ini memiliki beberapa bagian, yang masing-masing memiliki fungsi yang berbeda.\n"
                        +
                        "\nBerikut adalah bagian utama dari otot perut: \n" +
                        "1. Rectus Abdominis: Ini adalah otot yang paling dikenal, yang membentang dari tulang dada hingga panggul. Otot ini bertanggung jawab untuk memberikan bentuk perut yang lebih ramping dan terdefinisi, serta berfungsi dalam gerakan seperti membungkuk atau menarik tubuh ke depan.\n"
                        +
                        "2. Obliques (Otot Serong): Ada dua jenis otot obliques—obliques eksternal dan internal—yang terletak di sisi tubuh. Otot ini berfungsi untuk memutar tubuh dan membantu gerakan samping.\n"
                        +
                        "3. Transverse Abdominis: Otot ini adalah lapisan terdalam dari otot perut dan berfungsi untuk memberikan stabilitas pada tubuh, menjaga postur tubuh, dan melindungi organ internal.\n"));
        workout.add(new Workout("Upper Body", "assets\\Workout\\Upper_body_frame.png",
                "Upper body merujuk pada bagian tubuh manusia yang terletak di bagian atas, mulai dari leher hingga pinggang, yang mencakup kepala, leher, dada, punggung atas, bahu, dan lengan atas. Latihan upper body bertujuan untuk memperkuat dan membentuk otot-otot di bagian tubuh ini, yang meliputi otot dada (pectoralis), otot punggung (latissimus dorsi dan trapezius), otot bahu (deltoid), serta otot lengan (bisep dan trisep).\n"
                        +
                        "\nBeberapa latihan yang efektif untuk melatih upper body antara lain: \n" +
                        "1. push-up, yang melatih dada, trisep, dan bahu \n" +
                        "2. bench press untuk otot dada dan trisep \n" +
                        "3. pull-up yang fokus pada punggung dan bisep \n" +
                        "4. lat pulldown untuk memperkuat otot punggung atas \n" +
                        "5. dumbbell shoulder press dapat melatih bahu \n" +
                        "6. bicep curl dan triceps dips fokus pada otot bisep dan trisep \n"));
        workout.add(new Workout("Lower Body", "assets\\Workout\\Lower Body.jpeg",
                "Lower body merujuk pada bagian tubuh manusia yang terletak di bawah pinggang, mencakup otot-otot dan struktur tubuh seperti paha, lutut, betis, dan bokong. Latihan lower body bertujuan untuk memperkuat otot-otot besar di bagian bawah tubuh, seperti otot quadriceps (paha depan), hamstring (paha belakang), gluteus (bokong), dan otot betis (gastrocnemius dan soleus).\n"
                        +
                        "\nBeberapa latihan yang efektif untuk melatih lower body antara lain: \n" +
                        "1. Squat, yang melibatkan hampir seluruh otot bagian bawah tubuh \n" +
                        "2. Lunges, yang mengaktifkan otot paha dan bokong; deadlift, yang berfokus pada hamstring, gluteus \n"
                        +
                        "3. Punggung bawah; serta calf raise untuk melatih otot betis \n"));
        workout.add(new Workout("Side Plank", "assets\\Workout\\Side Plank.jpg",
                "Side plank adalah latihan tubuh bagian inti yang dilakukan dengan posisi tubuh miring, dengan satu tangan dan satu kaki sebagai tumpuan, sementara tubuh lainnya terangkat dan lurus. Latihan ini fokus pada penguatan otot-otot inti, khususnya otot obliques yang terletak di sisi tubuh. Selain itu, side plank juga melibatkan otot-otot lainnya seperti punggung, bahu, dan pinggul. \n"
                        +
                        "\nUntuk melakukan side plank, ikuti langkah-langkah berikut: \n" +
                        "1. Berbaring miring di lantai dengan kaki lurus dan tumpukan salah satu kaki di atas kaki lainnya. \n"
                        +
                        "2. Letakkan tangan bawah di lantai dengan lengan lurus, atau bisa juga dengan tangan ditekuk, tergantung pada kenyamanan. \n"
                        +
                        "3. Angkat pinggul dari lantai sehingga tubuh membentuk garis lurus dari kepala hingga kaki. \n"
                        +
                        "4. Tahan posisi ini selama beberapa detik hingga beberapa menit, lalu turunkan dan ulangi di sisi lainnya. \n"));
        workout.add(new Workout("Squat", "assets\\Workout\\Squat.jpg",
                "Squat adalah latihan fisik yang melibatkan gerakan menurunkan tubuh ke posisi jongkok, kemudian kembali berdiri dengan posisi tubuh tegak. Latihan ini terutama menargetkan otot-otot bagian bawah tubuh, seperti quadriceps (paha depan), hamstring (paha belakang), gluteus (bokong), dan otot inti (core)."
                        +
                        "\nSelain itu, squat juga dapat meningkatkan postur tubuh dan membantu memperkuat otot-otot yang digunakan dalam aktivitas sehari-hari, seperti berjalan, berlari, atau mengangkat barang. Variasi squat, seperti goblet squat, front squat, dan barbell squat, dapat dilakukan dengan tambahan beban untuk meningkatkan intensitas latihan. "));
        workout.add(new Workout("Push Up", "assets\\Workout\\Push Up.jpg",
                "Push-up adalah latihan tubuh bagian atas yang dilakukan dengan posisi tubuh tengkurap, kemudian menekan tubuh ke atas dan ke bawah menggunakan tangan sebagai tumpuan. Latihan ini terutama menargetkan otot dada (pectoralis), otot trisep (otot lengan belakang), dan otot bahu (deltoid), serta melibatkan otot inti (core) dan otot punggung atas sebagai stabilisator.\n"
                        +
                        "\nUntuk melakukan push up, ikuti langkah-langkah berikut: \n" +
                        "1. Letakkan tangan sedikit lebih lebar dari bahu \n" +
                        "2. Turunkan tubuh hingga dada hampir menyentuh lantai \n" +
                        "3. Dorong tubuh kembali ke posisi semula dengan menggunakan kekuatan tangan dan dada \n"));

        workoutPanel.add(createCategoryPanel("Workout", workout));

        return workoutPanel;
    }


    private JPanel createMealsPanel() {
        JPanel mealsPanel = new JPanel();
        mealsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mealsPanel.setLayout(new BoxLayout(mealsPanel, BoxLayout.Y_AXIS));
        mealsPanel.setBackground(Color.BLACK);

        // Tambahkan kategori makanan
        ArrayList<Workout> kategori = new ArrayList<>();
        kategori.add(new Workout("Salad", "assets\\Meals\\Salad.jpeg", "Salad sehat dengan banyak sayuran."));
        kategori.add(new Workout("Daging", "assets\\Meals\\Daging.jpeg", "Makanan kaya protein dari daging."));
        kategori.add(
                new Workout("Sayuran", "assets\\Meals\\Sayuran.jpg", "Makanan sehat yang mengandung banyak serat."));
        kategori.add(new Workout("Buah", "assets\\Meals\\Buah.jpg", "Makanan segar yang kaya vitamin dan mineral."));
        kategori.add(new Workout("Dada Ayam", "assets\\Meals\\Ayam.jpg", "Sumber protein yang baik untuk tubuh."));

        mealsPanel.add(createCategoryPanel("Kategori", kategori));

        // Tambahkan kategori Rekomendasi untuk Diet
        ArrayList<Workout> rekomendasi = new ArrayList<>();
        rekomendasi.add(new Workout("Telur Rebus", "assets\\Meals\\Telur.jpeg", ""));
        rekomendasi.add(new Workout("Sereal Oat", "assets\\Meals\\Sereal.jpeg", ""));
        rekomendasi.add(new Workout("Pisang", "assets\\Meals\\Pisang.jpg", ""));
        rekomendasi.add(new Workout("Susu Rendah Lemak", "assets\\Meals\\Susu.jpg", ""));
        rekomendasi.add(new Workout("Nasi Merah", "assets\\Meals\\Nasi Merah.jpeg", ""));
        rekomendasi.add(new Workout("Brokoli", "assets\\Meals\\Brokoli.jpeg", ""));

        mealsPanel.add(createCategoryPanel("Rekomendasi untuk Diet", rekomendasi));

        return mealsPanel;
    }

    // Panel kategori
    private JPanel createCategoryPanel(String categoryTitle, ArrayList<Workout> workoutList) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setBackground(Color.BLACK);
        categoryPanel.setLayout(new BorderLayout());

        // Label judul kategori
        JLabel categoryLabel = new JLabel(categoryTitle);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 30));
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryPanel.add(categoryLabel, BorderLayout.NORTH);

        // Model data untuk daftar
        DefaultListModel<Workout> workoutListModel = new DefaultListModel<>();
        for (Workout workout : workoutList) {
            workoutListModel.addElement(workout);
        }

        // JList untuk menampilkan daftar
        JList<Workout> workoutJList = new JList<>(workoutListModel);
        workoutJList.setCellRenderer(new WorkoutListRenderer());
        workoutJList.setBackground(Color.BLACK);
        workoutJList.setBorder(null);
        workoutJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        workoutJList.setVisibleRowCount(1);

        // Scroll pane untuk daftar
        JScrollPane scrollPane = new JScrollPane(workoutJList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setEnabled(false);
        
        // Untuk menyembunyikan JScrollPane nya tidak terlihat
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0)); //Biar transparan
    
        // Sembunyikan scrollbar (baik horizontal maupun vertical)
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        // Sembunyikan JScrollPane dari tampilan
        scrollPane.setOpaque(false);  // Membuat scroll pane transparan
        scrollPane.getViewport().setOpaque(false); // Membuat viewport transparan

                // Tambahkan scroll pane ke panel kategori
        categoryPanel.add(scrollPane, BorderLayout.CENTER);

        // Event klik pada item di JList
        workoutJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = workoutJList.locationToIndex(e.getPoint());
                if (index != -1) {
                    // Menampilkan detail item yang dipilih
                    Workout selectedDesc = workoutList.get(index);
                    showDetailWindow(selectedDesc);
                }
            }
        });

        return categoryPanel;
    }

    // Menampilkan jendela detail untuk item yang dipilih
    private void showDetailWindow(Workout selectedWorkout) {
        JFrame detailFrame = new JFrame("Fitness App");
        detailFrame.setSize(390, 700);
        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel(selectedWorkout.getnama());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailPanel.add(titleLabel);

        JLabel imageLabel = new JLabel(new ImageIcon(
                new ImageIcon(selectedWorkout.getgambar()).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailPanel.add(imageLabel);

        // Menambahkan penjelasan detail
        JTextArea descriptionTextArea = new JTextArea(selectedWorkout.getDeskripsi());
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionTextArea.setForeground(Color.WHITE);
        descriptionTextArea.setBackground(Color.BLACK);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        detailPanel.add(scrollPane);
        detailFrame.add(detailPanel);
        detailFrame.setVisible(true);
    }

    

    // Renderer untuk menampilkan daftar workout
    

}

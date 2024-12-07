import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.ListCellRenderer;

public class WorkoutListRenderer extends JPanel implements ListCellRenderer<Workout> {
    private JLabel imageLabel;
    private JLabel titleLabel;

    public WorkoutListRenderer() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 310));
        setBackground(Color.BLACK);

        imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(imageLabel);
        add(Box.createVerticalStrut(5));
        add(titleLabel);

        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Workout> list, Workout workout, int index,
            boolean isSelected, boolean cellHasFocus) {
        imageLabel.setIcon(new ImageIcon(
                new ImageIcon(workout.getgambar()).getImage().getScaledInstance(165, 236, Image.SCALE_SMOOTH)));
        titleLabel.setText(workout.getnama());

        setBackground(isSelected ? Color.DARK_GRAY : Color.BLACK);

        return this;
    }
}
package en.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import en.Function.Cleaner;
import en.Function.Find;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class More extends JDialog {
    public More(MainUI UI){
        super(UI,"More apps (click the icon to run)",true);
        FlatLaf.setup(new FlatIntelliJLaf());
        FlatDarkFlatIJTheme.setup();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screen.width-600)/2,(screen.height-500)/2,600,500);
        this.setResizable(false);
        this.setLayout(new GridLayout(5,3,10,10));
        JPanel Find = new JPanel();
        Find.setLayout(new GridLayout(1,2,1,1));
        ImageIcon ImageFind = new ImageIcon("icon/more/find.png");
        JButton ButtonFind = new JButton(ImageFind);
        ButtonFind.setContentAreaFilled(false);
        ButtonFind.setBorderPainted(false);
        ButtonFind.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                en.Function.Find find = new Find(null);
                find.setVisible(true);
            }
        });
        JLabel LabelFind = new JLabel("Find the file");
        Find.add(ButtonFind);
        Find.add(LabelFind);
        this.add(Find);
        JPanel Clean = new JPanel();
        Clean.setLayout(new GridLayout(1,2,1,1));
        ImageIcon ImageClean = new ImageIcon("icon/more/clean.png");
        JButton ButtonClean = new JButton(ImageClean);
        ButtonClean.setContentAreaFilled(false);
        ButtonClean.setBorderPainted(false);
        ButtonClean.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cleaner clean = new Cleaner();
                clean.start();
            }
        });
        JLabel LabelClean = new JLabel("Clean up temporary garbage");
        Clean.add(ButtonClean);
        Clean.add(LabelClean);
        this.add(Clean);
        JPanel PiFu = new JPanel();
        PiFu.setLayout(new GridLayout(1,2,1,1));
        ImageIcon ImagePiFu = new ImageIcon("icon/more/pifu.png");
        JButton ButtonPiFu = new JButton(ImagePiFu);
        ButtonPiFu.setContentAreaFilled(false);
        ButtonPiFu.setBorderPainted(false);
        ButtonPiFu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                en.UI.PiFu PiFu = new PiFu(null);
                PiFu.setVisible(true);
            }
        });
        JLabel LabelPiFu = new JLabel("Modify the skin");
        PiFu.add(ButtonPiFu);
        PiFu.add(LabelPiFu);
        this.add(PiFu);
        JPanel Setting = new JPanel();
        Setting.setLayout(new GridLayout(1,2,1,1));
        ImageIcon ImageSetting = new ImageIcon("icon/more/setting.png");
        JButton ButtonSetting = new JButton(ImageSetting);
        ButtonSetting.setContentAreaFilled(false);
        ButtonSetting.setBorderPainted(false);
        ButtonSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                en.UI.Setting setting = new Setting(null);
                setting.setVisible(true);
            }
        });
        JLabel LabelSetting = new JLabel("Set up");
        Setting.add(ButtonSetting);
        Setting.add(LabelSetting);
        this.add(Setting);
    }
}
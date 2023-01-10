package en.UI;

import en.Function.CollationUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Collation extends JDialog {
    static String data;
    static void readAppointedLineNumber(File sourceFile, int lineNumber)
            throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = "";
        if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {
            JOptionPane.showMessageDialog (null, "Please select a desktop path", "An error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
            if((lines - lineNumber) == 0) {
                data = s;
            }
        }
        reader.close();
        in.close();
    }
    static int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }
    public  Collation(MainUI UI) {
        super(UI, "", true);
                     Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        this.setType(Type.UTILITY);
        this.setBounds((screen.width - 500) / 2, (screen.height - 300) / 2, 500, 300);
        this.setLayout(new GridLayout(9, 1, 1, 1));
        JLabel YON = new JLabel("Select the desktop path:");
        ButtonGroup Group = new ButtonGroup();
        JRadioButton DeskTop = new JRadioButton("C:\\Users\\" + en.Function.Collation.userName + "\\Desktop");
        JRadioButton OneDrive = new JRadioButton("C:\\Users\\" + en.Function.Collation.userName + "\\OneDrive\\Desktop");
        JTextField jt = new JTextField();
        JRadioButton qt = new JRadioButton("other");
        Group.add(DeskTop);
        Group.add(OneDrive);
        Group.add(qt);
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(qt,BorderLayout.WEST);
        jp.add(jt,BorderLayout.CENTER);
        JRadioButton mr = new JRadioButton("Use the default type");
        JRadioButton DIY = new JRadioButton("Use custom types");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(mr);
        buttonGroup.add(DIY);

        JButton setting = new JButton("disposition");
        setting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CollationUI cu;
                try {
                    cu = new CollationUI(null);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog (null, "The custom format is:\nFile suffix [space] folder name\nSuch as: ppt pptFile", "Welcome to custom features", JOptionPane.INFORMATION_MESSAGE);

                cu.setVisible(true);

            }
        });
        JPanel DIYs = new JPanel();
        DIYs.setLayout(new GridLayout(1, 2, 1, 1));
        DIYs.add(DIY);
        DIYs.add(setting);
        JLabel Info = new JLabel("Note: The former is not using OneDrive and the latter is using OneDrive");
        JCheckBox YesOrNo = new JCheckBox("Whether to turn on automatic organizing");
        File file = new File("data/collation/collation.data");
        int lineNumber;
        if (file.exists()) {
            lineNumber = 3;
            File sourceFile = new File("data/collation/collation.data");
            try {
                readAppointedLineNumber(sourceFile, lineNumber);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mr.setSelected(data.equals("false"));
            DIY.setSelected(data.equals("true"));
            lineNumber = 1;
            try {
                readAppointedLineNumber(sourceFile, lineNumber);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (data.equals("C:\\Users\\" + en.Function.Collation.userName + "\\Desktop")) {
                DeskTop.setSelected(true);
            } else if (data.equals("C:\\Users\\" + en.Function.Collation.userName + "\\OneDrive\\Desktop")) {
                OneDrive.setSelected(true);
            }else{
                qt.setSelected(true);
                jt.setText(data);
            }
            lineNumber = 2;
            try {
                readAppointedLineNumber(sourceFile, lineNumber);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            YesOrNo.setSelected(data.equals("true"));
        }
        JPanel Button = new JPanel();
        Button.setLayout(new GridLayout(1, 2, 1, 1));
        JButton Yes = new JButton("Sure");
        JButton No = new JButton("Cancel");
        No.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Collation.this.setVisible(false);
            }
        });
        Yes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String path = null;
                String yn;
                String ifDIY;
                if (DeskTop.isSelected()) {
                    path = DeskTop.getText();
                } else if (OneDrive.isSelected()) {
                    path = OneDrive.getText();
                }else if(qt.isSelected()){
                    path = jt.getText();
                }
                yn = String.valueOf(YesOrNo.isSelected());
                if (mr.isSelected()) {
                    ifDIY = "false";
                } else if (DIY.isSelected()) {
                    ifDIY = "true";
                } else {
                    ifDIY = "false";
                }
                if (!DeskTop.isSelected() && !OneDrive.isSelected() && !qt.isSelected()) {
                    JOptionPane.showMessageDialog(null, "请选择桌面路径", "出现了一个错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    String filePath = "data/collation";
                    File dir = new File(filePath);
                    if (!dir.exists()) {
                        //noinspection ResultOfMethodCallIgnored
                        dir.mkdirs();
                    }
                    File checkFile = new File(filePath + "/collation.data");
                    FileWriter writer = null;
                    try {
                        if (!checkFile.exists()) {
                            //noinspection ResultOfMethodCallIgnored
                            checkFile.createNewFile();
                        }
                        writer = new FileWriter(checkFile, false);
                        writer.append(path);
                        writer.append("\n");
                        writer.append(yn);
                        writer.append("\n");
                        writer.append(ifDIY);
                        writer.flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally {
                        if (null != writer) {
                            try {
                                writer.close();
                            } catch (IOException ex) {
                                //noinspection ThrowFromFinallyBlock
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    Collation.this.setVisible(false);
                }
            }
        });
        Button.add(Yes);
        Button.add(No);
        this.add(YON);

        this.add(DeskTop);
        this.add(OneDrive);
        this.add(Info);
        this.add(jp);
        this.add(mr);
        this.add(DIYs);
        this.add(YesOrNo);
        this.add(Button);
    }
}

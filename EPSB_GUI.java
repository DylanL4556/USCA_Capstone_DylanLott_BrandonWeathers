// Author(s): Dylan Lott & Brandon Weathers
// Last updated: 10/20/2025 10:21 PM

// This is a very rough GUI version of the EPSB algorithm.
// After entering a password at the top, the sitting submit, the variables will
// update accordingly.

// Small visual updated have been implemented.

// Updated program to show stats for all letters and length of passwords.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;

public class EPSB_GUI extends JPanel{
    private JTextField newPasswordInput = new JTextField();
    private JTextField
        capitalsMin        = new JTextField(),
        capitalsMax        = new JTextField(),
        capitalsMean       = new JTextField(),
        capitalsMedian     = new JTextField(),
        capitalsMode       = new JTextField(),
        lowerCaseMin       = new JTextField(),
        lowerCaseMax       = new JTextField(),
        lowerCaseMean      = new JTextField(),
        lowerCaseMedian    = new JTextField(),
        lowerCaseMode      = new JTextField(),
        lettersMin         = new JTextField(),
        lettersMax         = new JTextField(),
        lettersMean        = new JTextField(),
        lettersMedian      = new JTextField(),
        lettersMode        = new JTextField(),
        numbersMin         = new JTextField(),
        numbersMax         = new JTextField(),
        numbersMean        = new JTextField(),
        numbersMedian      = new JTextField(),
        numbersMode        = new JTextField(),
        specialCharsMin    = new JTextField(),
        specialCharsMax    = new JTextField(),
        specialCharsMean   = new JTextField(),
        specialCharsMedian = new JTextField(),
        specialCharsMode   = new JTextField(),
        lengthMin          = new JTextField(),
        lengthMax          = new JTextField(),
        lengthMean         = new JTextField(),
        lengthMedian       = new JTextField(),
        lengthMode         = new JTextField();

    private JButton addPassword = new JButton("Submit password");
    EPSB EPSB1 = new EPSB();

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key = keys.nextElement();
          Object value = UIManager.get (key);
          if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
          }
    }

    public void init(){
        setUIFont (new javax.swing.plaf.FontUIResource("Ariel", Font.PLAIN, 20));
        setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridLayout(43, 2));
        p1.add(new JLabel("<html><u>Password</u></html>", SwingConstants.CENTER));
        p1.add(newPasswordInput);

        p1.add(new JLabel(""));
        p1.add(addPassword);

        p1.add(new JLabel("<html><u>Capitals</html></u>", SwingConstants.CENTER));
                p1.add(new JLabel(""));
                p1.add(new JLabel("├─Min", SwingConstants.CENTER));
                p1.add(capitalsMin);
                p1.add(new JLabel(" ├─Max", SwingConstants.CENTER));
                p1.add(capitalsMax);
                p1.add(new JLabel("   ├─Mean", SwingConstants.CENTER));
                p1.add(capitalsMean);
                p1.add(new JLabel("      ├─Median", SwingConstants.CENTER));
                p1.add(capitalsMedian);
                p1.add(new JLabel("   └─Mode", SwingConstants.CENTER));
                p1.add(capitalsMode);

        p1.add(new JLabel(""));
        p1.add(new JLabel(""));

        p1.add(new JLabel("<html><u>Lower Case</html></u>", SwingConstants.CENTER));
                p1.add(new JLabel(""));
                p1.add(new JLabel("├─Min", SwingConstants.CENTER));
                p1.add(lowerCaseMin);
                p1.add(new JLabel(" ├─Max", SwingConstants.CENTER));
                p1.add(lowerCaseMax);
                p1.add(new JLabel("   ├─Mean", SwingConstants.CENTER));
                p1.add(lowerCaseMean);
                p1.add(new JLabel("      ├─Median", SwingConstants.CENTER));
                p1.add(lowerCaseMedian);
                p1.add(new JLabel("   └─Mode", SwingConstants.CENTER));
                p1.add(lowerCaseMode);

        p1.add(new JLabel(""));
        p1.add(new JLabel(""));

        p1.add(new JLabel("<html><u>All Letters</html></u>", SwingConstants.CENTER));
                p1.add(new JLabel(""));
                p1.add(new JLabel("├─Min", SwingConstants.CENTER));
                p1.add(lettersMin);
                p1.add(new JLabel(" ├─Max", SwingConstants.CENTER));
                p1.add(lettersMax);
                p1.add(new JLabel("   ├─Mean", SwingConstants.CENTER));
                p1.add(lettersMean);
                p1.add(new JLabel("      ├─Median", SwingConstants.CENTER));
                p1.add(lettersMedian);
                p1.add(new JLabel("   └─Mode", SwingConstants.CENTER));
                p1.add(lettersMode);

        p1.add(new JLabel(""));
        p1.add(new JLabel(""));

        p1.add(new JLabel("<html><u>Numbers</html></u>", SwingConstants.CENTER));
                p1.add(new JLabel(""));
                p1.add(new JLabel("├─Min", SwingConstants.CENTER));
                p1.add(numbersMin);
                p1.add(new JLabel(" ├─Max", SwingConstants.CENTER));
                p1.add(numbersMax);
                p1.add(new JLabel("   ├─Mean", SwingConstants.CENTER));
                p1.add(numbersMean);
                p1.add(new JLabel("      ├─Median", SwingConstants.CENTER));
                p1.add(numbersMedian);
                p1.add(new JLabel("   └─Mode", SwingConstants.CENTER));
                p1.add(numbersMode);

        p1.add(new JLabel(""));
        p1.add(new JLabel(""));

        p1.add(new JLabel("<html><u>Special Characters</html></u>", SwingConstants.CENTER));
                p1.add(new JLabel(""));
                p1.add(new JLabel("├─Min", SwingConstants.CENTER));
                p1.add(specialCharsMin);
                p1.add(new JLabel(" ├─Max", SwingConstants.CENTER));
                p1.add(specialCharsMax);
                p1.add(new JLabel("   ├─Mean", SwingConstants.CENTER));
                p1.add(specialCharsMean);
                p1.add(new JLabel("      ├─Median", SwingConstants.CENTER));
                p1.add(specialCharsMedian);
                p1.add(new JLabel("   └─Mode", SwingConstants.CENTER));
                p1.add(specialCharsMode);

        p1.add(new JLabel(""));
        p1.add(new JLabel(""));

        p1.add(new JLabel("<html><u>Length</html></u>", SwingConstants.CENTER));
                p1.add(new JLabel(""));
                p1.add(new JLabel("├─Min", SwingConstants.CENTER));
                p1.add(lengthMin);
                p1.add(new JLabel(" ├─Max", SwingConstants.CENTER));
                p1.add(lengthMax);
                p1.add(new JLabel("   ├─Mean", SwingConstants.CENTER));
                p1.add(lengthMean);
                p1.add(new JLabel("      ├─Median", SwingConstants.CENTER));
                p1.add(lengthMedian);
                p1.add(new JLabel("   └─Mode", SwingConstants.CENTER));
                p1.add(lengthMode);

        // p1.setBorder(new EmptyBorder(100, 100, 100, 100));
        add(p1, BorderLayout.CENTER);

        addPassword.addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
                String currentPassword;
                currentPassword = newPasswordInput.getText();
                System.out.println(currentPassword);
                EPSB1.addNewPassword(currentPassword);

                capitalsMin   .setText(EPSB1.getMin(     EPSB1.capitals) + "");
                capitalsMax   .setText(EPSB1.getMax(     EPSB1.capitals) + "");
                capitalsMean  .setText(EPSB1.getAverage( EPSB1.capitals) + "");
                capitalsMedian.setText(EPSB1.getMedian(  EPSB1.capitals) + "");
                capitalsMode  .setText(EPSB1.getMode(    EPSB1.capitals) + "");

                lowerCaseMin   .setText(EPSB1.getMin(     EPSB1.lowerCase) + "");
                lowerCaseMax   .setText(EPSB1.getMax(     EPSB1.lowerCase) + "");
                lowerCaseMean  .setText(EPSB1.getAverage( EPSB1.lowerCase) + "");
                lowerCaseMedian.setText(EPSB1.getMedian(  EPSB1.lowerCase) + "");
                lowerCaseMode  .setText(EPSB1.getMode(    EPSB1.lowerCase) + "");

                lettersMin   .setText(EPSB1.getMin(     EPSB1.letters) + "");
                lettersMax   .setText(EPSB1.getMax(     EPSB1.letters) + "");
                lettersMean  .setText(EPSB1.getAverage( EPSB1.letters) + "");
                lettersMedian.setText(EPSB1.getMedian(  EPSB1.letters) + "");
                lettersMode  .setText(EPSB1.getMode(    EPSB1.letters) + "");

                numbersMin   .setText(EPSB1.getMin(     EPSB1.numbers) + "");
                numbersMax   .setText(EPSB1.getMax(     EPSB1.numbers) + "");
                numbersMean  .setText(EPSB1.getAverage( EPSB1.numbers) + "");
                numbersMedian.setText(EPSB1.getMedian(  EPSB1.numbers) + "");
                numbersMode  .setText(EPSB1.getMode(    EPSB1.numbers) + "");

                specialCharsMin   .setText(EPSB1.getMin(     EPSB1.symbols) + "");
                specialCharsMax   .setText(EPSB1.getMax(     EPSB1.symbols) + "");
                specialCharsMean  .setText(EPSB1.getAverage( EPSB1.symbols) + "");
                specialCharsMedian.setText(EPSB1.getMedian(  EPSB1.symbols) + "");
                specialCharsMode  .setText(EPSB1.getMode(    EPSB1.symbols) + "");

                lengthMin   .setText(EPSB1.getMin(     EPSB1.length) + "");
                lengthMax   .setText(EPSB1.getMax(     EPSB1.length) + "");
                lengthMean  .setText(EPSB1.getAverage( EPSB1.length) + "");
                lengthMedian.setText(EPSB1.getMedian(  EPSB1.length) + "");
                lengthMode  .setText(EPSB1.getMode(    EPSB1.length) + "");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI implementation of the EPSB algorithm");
        EPSB_GUI applet = new EPSB_GUI();
        frame.add(applet, BorderLayout.CENTER);
        applet.init();

        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}

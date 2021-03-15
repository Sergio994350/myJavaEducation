import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class MainForm {

        private JPanel mainPanel;
        private JButton expandButton;
        private JButton collapseButton;
        private JPanel buttonsPanel;
        private JTextField surnameField;
        private JTextField nameField;
        private JTextField secondNameField;
        private JLabel surname;
        private JLabel name;
        private JLabel secondName;
        private JLabel fullname;
        private JTextField fullNameField;
        private JPanel textPanel;
        private JLabel errorMessage;
        private String fullNameText;

        public MainForm()
        {
            expandButton.setVisible(false);
            fullNameField.setVisible(false);
            fullname.setVisible(false);
            errorMessage.setVisible(false);

            collapseButton.addActionListener(e -> {
                if (surnameField.getText().matches("\\S+") && nameField.getText().matches("\\S+") && secondNameField.getText().matches("\\S+"))
                {
                    //  Set Invisible
                    errorMessage.setVisible(false);
                    surnameField.setVisible(false);
                    nameField.setVisible(false);
                    secondNameField.setVisible(false);
                    surname.setVisible(false);
                    name.setVisible(false);
                    secondName.setVisible(false);
                    collapseButton.setVisible(false);

                    //  Set Visible
                    fullNameText = surnameField.getText() + " " + nameField.getText()
                            + " " + secondNameField.getText();
                    secondNameField.setText("");
                    fullname.setVisible(true);
                    fullNameField.setText(fullNameText);
                    fullNameField.setVisible(true);
                    expandButton.setVisible(true);
                }
                else {
                    errorMessage.setText("Ошибка ввода данных. Заполните все поля ");
                    errorMessage.setVisible(true);
                }
            });

            expandButton.addActionListener(e -> {
                fullNameText = fullNameField.getText();

                if (fullNameField.getText().split("\\s+").length > 1 && fullNameField.getText().split("\\s+").length < 4 && fullNameField.getText().matches("\\S?.+"))
                {
                    //  Set Invisible
                    errorMessage.setVisible(false);
                    fullname.setVisible(false);
                    fullNameField.setVisible(false);
                    expandButton.setVisible(false);

                    // Set Visible
                    String secName = (fullNameText.split("\\s+").length > 2) ?
                            fullNameText.split("\\s+")[2] : "";
                    surnameField.setVisible(true);
                    surnameField.setText(fullNameText.split("\\s+")[0]);
                    nameField.setVisible(true);
                    nameField.setText(fullNameText.split("\\s+")[1]);
                    secondNameField.setVisible(true);
                    secondNameField.setText(secName);
                    surname.setVisible(true);
                    name.setVisible(true);
                    secondName.setVisible(true);
                    collapseButton.setVisible(true);
                    fullNameText = "";
                }
                else {
                    errorMessage.setText("Ошибка ввода данных. Введите через пробел Ф.И.О.");
                    errorMessage.setVisible(true);
                }
            });
        }
        public JPanel getMainPanel()
        {
            return mainPanel;
        }


}

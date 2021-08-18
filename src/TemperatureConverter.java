import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TemperatureConverter extends JFrame implements ActionListener {

    JTextArea textArea;
    JTextArea newTextArea;
    int d = 400;

    JComboBox<String> tempUnits;
    String[] temperatureTypes = new String[] {"Celsius", "Kelvin", "Fahrenheit"};

    JComboBox<String> newTempUnits;
    JButton Convert;

    JLabel startUnit;
    JLabel endUnit;

    int optionOne = 1;
    int optionTwo = 3;


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tempUnits) {
            optionOne = getOption((String) Objects.requireNonNull(tempUnits.getSelectedItem()));
        }
        if(e.getSource() == newTempUnits) {
            optionTwo = getOption((String) Objects.requireNonNull(newTempUnits.getSelectedItem()));
        }
        if(e.getSource() == Convert) {
            String textAreaText = textArea.getText();
            try{
                double userTemp = Double.parseDouble(textAreaText);
                textArea.setText(textArea.getText() + " Degrees ");
                newTextArea.setText(convertTemp(optionOne, optionTwo, userTemp) + " Degrees");
            } catch (NumberFormatException a) {
                newTextArea.setText("Please type a valid number");
            }
        }
    }

    TemperatureConverter() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Temperature Converter");
        this.setSize(d+200,d);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        startUnit = new JLabel("Starting Temperature and Unit");
        this.add(startUnit);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color (255,255,255));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setForeground(Color.black);
        textArea.setFont(new Font ("Trebuchet MS", Font.PLAIN, 20));
        textArea.setPreferredSize(new Dimension(d -150, d- 300));
        textArea.setText("");
        this.add(textArea);

        tempUnits = new JComboBox<>(temperatureTypes);
        tempUnits.setPreferredSize(new Dimension(100,50 ));
        tempUnits.setSelectedItem("Celsius");
        tempUnits.addActionListener(this);
        this.add(tempUnits);

        endUnit = new JLabel("Ending Temperature and Unit");
        this.add(endUnit);

        newTextArea = new JTextArea();
        newTextArea.setLineWrap(true);
        newTextArea.setWrapStyleWord(true);
        newTextArea.setBackground(new Color (255,255,255));
        newTextArea.setBorder(BorderFactory.createBevelBorder(1));
        newTextArea.setForeground(Color.black);
        newTextArea.setFont(new Font ("Trebuchet MS", Font.PLAIN, 20));
        newTextArea.setPreferredSize(new Dimension(d -150, d- 300));
        newTextArea.setText("");
        this.add(newTextArea);

        newTempUnits = new JComboBox<>(temperatureTypes);
        newTempUnits.setPreferredSize(new Dimension(100,50));
        newTempUnits.setSelectedItem("Fahrenheit");
        newTempUnits.addActionListener(this);
        this.add(newTempUnits);

        Convert = new JButton("Convert!");
        Convert.addActionListener(this);
        Convert.setPreferredSize(new Dimension(200,50));
        this.add(Convert);

        this.setVisible(true);

    }
    public int getOption(String selection) {
        switch(selection) {
            case "Celsius":
                return 1;
            case "Kelvin":
                return 2;
            case "Fahrenheit":
                return 3;
        }
        return -1;
    }
    public String convertTemp(int startUnit, int endUnit, double temperature) {
        switch(startUnit + "_" + endUnit) {
            case "2_1":
                temperature = temperature - 273.15; //Kelvin to Celsius
                break;
            case "2_3":
                temperature = ((temperature -273.15)*((double)9/5)) + 32.0; //Kelvin to Fahrenheit
                break;
            case "1_2":
                temperature = temperature + 273.15; //Celsius to Kelvin
                break;
            case "1_3":
                temperature = ((temperature)*((double)9/5)) + 32.0; //Celsius to Fahrenheit
                break;
            case "3_2":
                temperature = (temperature-32.0)*((double)5/9) + 273.15; //Fahrenheit to Kelvin
                break;
            case "3_1":
                temperature = (temperature-32.0)*((double)5/9); //Fahrenheit to Celsius
                break;
            default:
                return String.valueOf(temperature);
        }

        return String.valueOf(temperature);
    }
}

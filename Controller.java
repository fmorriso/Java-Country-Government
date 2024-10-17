import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Runnable
{

    private static final int MAX_COLUMNS = 5;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel controlPanel;
    private List<CountryButton> countries;
    private EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);
    private int numCountries;

    public Controller(Dimension frameSize)
    {
        System.out.println("DEBUG: running Controller constructor");
        String frameTitle = String.format("Country Government using java version %s", getJavaVersion());
        frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(frameSize);
        frame.setSize(frameSize);

        this.run();
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run()
    {
        System.out.println("DEBUG: made it to Controller.run()");
        start();
    }

    private static String getJavaVersion()
    {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }

    private void start()
    {
        System.out.println("DEBUG: Running Controller.start()");
        numCountries = getNumberOfCountries();
        String title = "Country Government chooser";
        String message;
        if (numCountries == Integer.MIN_VALUE) {
            message = "No country count chosen.  Program stopped.";
            int messageType = JOptionPane.PLAIN_MESSAGE;
            JOptionPane.showMessageDialog(null, message, title, messageType);
            return;
        }

        frame.setLayout(new GridLayout(0, 1, 10, 10));

        mainPanel = createMainPanel();

        populateCountryGridPanel();

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        controlPanel = createControlPanel();
        frame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        frame.pack();

        // put the JFrame in the middle of the physical screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void populateCountryGridPanel()
    {
        this.countries = new ArrayList<CountryButton>();
        for (int i = 0; i < this.numCountries; i++) {

            boolean needUniqueCountryName = true;
            String name = null;
            do {
                name = CountryNameGenerator.getRandomCountryName();
                if (isUniqueCountryName(name)) {
                    needUniqueCountryName = false;
                }
            } while (needUniqueCountryName);

            CountryButton cb = new CountryButton(name, Government.Unspecified, this);
            this.countries.add(cb);
            mainPanel.add(cb);

        }
    }

    // make sure we haven't already used a country name
    private boolean isUniqueCountryName(String name)
    {

        if (this.countries.size() == 0)
            return true;

        boolean isUnique = true;
        for (CountryButton cb : this.countries) {
            if (cb.getName().equals(name))
                return false;
        }
        return isUnique;
    }

    private JPanel createMainPanel()
    {

        int numRows = 4; // start by assuming we have 4 rows of MAX_COLUMNS
        if (this.numCountries <= MAX_COLUMNS)
            numRows = 1;
        else if (this.numCountries <= MAX_COLUMNS * 2)
            numRows = 2;
        else if (this.numCountries <= MAX_COLUMNS * 3)
            numRows = 3;

        GridLayout layout = new GridLayout(numRows, MAX_COLUMNS, 20, 20);

        JPanel pnl = new JPanel(layout);
        pnl.setBorder(emptyBorder);

        return pnl;
    }

    private JPanel createControlPanel()
    {
        System.out.format("Frame: width=%d, height=%d%n", this.frame.getWidth(), this.frame.getHeight());
        Dimension size = new Dimension(this.frame.getWidth() / 8, this.frame.getHeight() / 16);
        Dimension maxButtonSize = size;
        Dimension minButtonSize = size;
        System.out.format("maxButtonSize = (%d x %d)%n", maxButtonSize.width, maxButtonSize.height);

        // allow for one row, with three components (button, separator, button)
        GridLayout layout = new GridLayout(1, 3, 20, 20);

        JPanel controlPanel = new JPanel(layout);
        controlPanel.setSize(size);
        controlPanel.setBorder(emptyBorder);
        controlPanel.setPreferredSize(size);

        // trick JAVA GridLayout into NOT auto-resizing our buttons by wrapping them with a JPanel
        JPanel resetButtonWrapper = new JPanel();
        resetButtonWrapper.setSize(maxButtonSize);
        //resetButtonWrapper.setPreferredSize(maxButtonSize);
        JButton resetButton = new JButton("Reset");
        resetButton.setMinimumSize(minButtonSize);
        resetButton.setMaximumSize(maxButtonSize);
        resetButton.addActionListener(ae -> resetButtons());
        resetButtonWrapper.add(resetButton);

        controlPanel.add(resetButtonWrapper);

        JPanel separatorWrapper = new JPanel();
        separatorWrapper.setSize(maxButtonSize);
        //separatorWrapper.setPreferredSize(maxButtonSize);
        JSeparator separator = new JSeparator();
        separatorWrapper.add(separator);
        controlPanel.add(separatorWrapper);

        JPanel exitButtonWrapper = new JPanel();
        exitButtonWrapper.setSize(maxButtonSize);
        JButton exitButton = new JButton("Exit");
        exitButton.setMinimumSize(minButtonSize);
        exitButton.setMaximumSize(maxButtonSize);
        exitButton.addActionListener(this::exitProgram);
        exitButtonWrapper.add(exitButton);
        controlPanel.add(exitButtonWrapper);

        return controlPanel;
    }

    private void exitProgram(ActionEvent ae)
    {
        JOptionPane.showMessageDialog(null, "stopping program");
        System.exit(0);
    }

    private void resetButtons()
    {

        for (CountryButton cb : this.countries) {
            cb.reset();
        }


    }

    private int getNumberOfCountries()
    {

        int count = 0;
        final int min = 5, max = 20;
        String dialogTitle = "Number of countries";
        String prompt = String.format("Enter number of countries that is between %d and %d", min, max);
        final String errorMessageOutsideRange = String.format("Invalid value - outside of allowable range of %d to %d. Try again.", min, max);
        // keep asking for a number until the number is within the valid range.

        boolean keepAsking = true;
        do {
            try {

                Object rawResponse = JOptionPane.showInputDialog(null, prompt, dialogTitle, JOptionPane.QUESTION_MESSAGE);

                // check for Cancel button click:
                if (rawResponse == null) {
                    keepAsking = false;
                    count = Integer.MIN_VALUE; // call should check this value as the indicator that Cancel was clicked
                    continue;
                }

                count = Integer.parseInt((String) rawResponse);

                if (count < min || count > max) {
                    JOptionPane.showMessageDialog(null, errorMessageOutsideRange, "Range Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    keepAsking = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid value. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (keepAsking);

        return count;
    }

    /*
    Improve (left mouse click) or degrade (right mouse click) the country's government.
    */
    public void reactToCountryMouseClickEvent(CountryButton countryButton, MouseButton mouseButton)
    {
        // System.out.format("Button %s was clicked by the %s mouse button%n", countryButton.getName(), mouseButton);
        switch (mouseButton) {
            case Left:
                improveGovernment(countryButton);
                break;

            case Right:
                degradeGovernment(countryButton);
                break;

            default:
                break;
        }
    }

    /** Degrade the specified country's form of government.
     * @apiNote - if the country's current form of government is Capitalist, change it to Socialist.
     *            if the country's current form of government is Unspecified, change it to Capitalist.
     *            For any other type of government, do not change it.
     * @param countryButton - the button for the country being degraded.
     */
    private void degradeGovernment(CountryButton countryButton)
    {
        switch (countryButton.getGovernment()) {

            case Unspecified:
                countryButton.setGovernment(Government.Capitalist);
                break;

            case Capitalist:
                countryButton.setGovernment(Government.Socialist);
                break;

            default:
                break;
        }

    }

    private void improveGovernment(CountryButton countryButton)
    {

        switch (countryButton.getGovernment()) {

            case Unspecified:
                countryButton.setGovernment(Government.Socialist);
                break;

            case Socialist:
                countryButton.setGovernment(Government.Capitalist);
                break;

            default:
                break;
        }
    }


}

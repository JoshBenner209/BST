import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class DictionairyFrame {
    private Dictionairy dictionairy;
    private DefaultTableModel model;
    private JTable table;
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField searchField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton ClearFieldButton;


    public DictionairyFrame() {
      dictionairy= new Dictionairy();
        createUI();
    }

    private void createUI() {
        // Create the main frame
        JFrame frame = new JFrame("DataBase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Text Area
        JTextArea output= new JTextArea();
        // Create the table model and table
        model = new DefaultTableModel(new Object[]{"ID","First Name", "Last Name", "Email", "Phone", "Address", "City", "State", "Zip"}, 0);
        table = new JTable(model);
    
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
       
        // Create the input fields and buttons
        idField= new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        emailField = new JTextField(20);
        phoneField = new JTextField(10);
        addressField = new JTextField(20);
        cityField = new JTextField(10);
        stateField = new JTextField(2);
        zipField = new JTextField(5);
        searchField = new JTextField(20);
    
        addButton = new JButton("Add");
        addButton.setToolTipText("Fill in all fields and click \"add\" to add Employee");
        addButton.addActionListener( e -> {
           
                String fName= firstNameField.getText();
                String lName= lastNameField.getText();
                String id = idField.getText();
                Integer idInteger= Integer.valueOf(id);
                String email=emailField.getText();
                String phone=phoneField.getText();
                String address=addressField.getText();
                String state=stateField.getText();
                String city=cityField.getText();
                String zip=zipField.getText();
                dictionairy.addEntry(idInteger, fName, lName, email,phone,address,city,state,zip);
                updateTable();
                clearField();
                //output.append(dictionairy.printInOrder());

            });
            ClearFieldButton= new JButton("Clear");
            ClearFieldButton.setToolTipText("clear all fields");
            ClearFieldButton.addActionListener(e -> {
                firstNameField.setText(""); 
                lastNameField.setText("");
               idField.setText("");
               emailField.setText("");
               phoneField.setText("");
               addressField.setText("");
               stateField.setText("");
               cityField.setText("");
               zipField.setText("");
            });
    
        editButton = new JButton("Edit");
        editButton.setToolTipText("Must enter ID and Type changes into corresponding fields.");
        editButton.addActionListener(e -> {
            Integer id = Integer.valueOf(idField.getText());
            PersonInfo person = dictionairy.getPerson(id);
            if (!firstNameField.getText().isEmpty()) {
                person.setFirstName(firstNameField.getText());
            }
            if (!lastNameField.getText().isEmpty()) {
                person.setLastName(lastNameField.getText());
            }
            if (!emailField.getText().isEmpty()) {
                person.setEmail(emailField.getText());
            }
            if (!phoneField.getText().isEmpty()) {
                person.setPhoneNumb(phoneField.getText());
            }
            if (!stateField.getText().isEmpty()) {
                person.setState(stateField.getText());
            }
            if (!addressField.getText().isEmpty()) {
                person.setAddress(addressField.getText());
            }
            if (!cityField.getText().isEmpty()) {
                person.setCity(cityField.getText());
            }
            if (!zipField.getText().isEmpty()) {
                person.setZip(zipField.getText());
            }
        
            // Update the table and display a message to the user
            updateTable();
            JOptionPane.showMessageDialog(null, "Entry with ID " + id + " has been updated.");
        });
        
    
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deleteEntry();
            }
        });
    
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // searchEntries();
            }
        });
    
        // Create the input fields and labels
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);
        inputPanel.add(new JLabel("State:"));
        inputPanel.add(stateField);
        inputPanel.add(new JLabel("Zip:"));
        inputPanel.add(zipField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(searchButton);
        inputPanel.add(deleteButton);
        inputPanel.add(ClearFieldButton);
        //inputPanel.add()
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(inputPanel, BorderLayout.WEST);
            //mainPanel.add(table,BorderLayout.SO);

            frame.setContentPane(mainPanel);
            frame.setSize(800, 600);
            frame.setVisible(true);
    }
    private void clearField(){
        firstNameField.setText(""); 
        lastNameField.setText("");
        idField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        stateField.setText("");
        cityField.setText("");
        zipField.setText("");
    }
    private void updateTable() {
        // Clear the table
        model.setRowCount(0);
    
        // Add the updated entries to the table
        for (PersonInfo person : dictionairy.getEntries()) {
            Object[] row = new Object[]{person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(), person.getPhoneNumb(), person.getAddress(), person.getCity(), person.getState(), person.getZip()};
            model.addRow(row);
        }
    }
    
}
    
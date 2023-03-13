import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton ClearFieldButton;
    private JButton preOrderButton;
    private JButton postOrderButton;
    private JButton inOrderButton;


    public DictionairyFrame() {
      dictionairy= new Dictionairy();
        createUI();
    }

    private void createUI() {
        // Create the main frame
        JFrame frame = new JFrame("DataBase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
     
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
        new JTextField(20);
    
        addButton = new JButton("Add");
        addButton.setToolTipText("Fill in all fields and click \"add\" to add Employee");
        addButton.addActionListener( e -> {
            add();
            updateTableInorder();
            clearField();
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
           edit();
           updateTableInorder();
        });
        
    
        deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Enter Id to delet");
        deleteButton.addActionListener(e -> {
                deleteEntry();
                updateTableInorder();
        
        });
    
        searchButton = new JButton("Search");
        searchButton.setToolTipText("enter Id to search");
        searchButton.addActionListener(e-> {
            searchEntries();
        });
        preOrderButton = new JButton("Display PreOrder");
        preOrderButton.addActionListener(e-> {
            updateTablePreorder();
        });  
        postOrderButton = new JButton("Display PostOrder");
        postOrderButton.addActionListener(e-> {
            updateTablePostorder();
        });  
       inOrderButton = new JButton("Display InOrder");
        inOrderButton.addActionListener(e-> {
            updateTableInorder();
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
        inputPanel.add(postOrderButton);
        inputPanel.add(preOrderButton);
        inputPanel.add(inOrderButton);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(inputPanel, BorderLayout.WEST);
            //mainPanel.add(table,BorderLayout.SO);

            frame.setContentPane(mainPanel);
            frame.setSize(800, 600);
            frame.setVisible(true);
    }
    private void searchEntries() {
        Integer id= Integer.valueOf(idField.getText());
        if (dictionairy.verify(id)==false){
            JOptionPane.showMessageDialog(null, "No results found for ID: "+id);
        } else{
        JOptionPane.showMessageDialog(null, dictionairy.search(id));
        }
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
    private void updateTableInorder() {
        // Clear the table
        model.setRowCount(0);
    
        // Add the updated entries to the table
        for (PersonInfo person : dictionairy.getEntriesInOrder()) {
            Object[] row = new Object[]{person.getId(), person.getFirstName(), person.getLastName(),
                 person.getEmail(), person.getPhoneNumb(), person.getAddress(), person.getCity(), person.getState(), 
                 person.getZip()};
            model.addRow(row);
        }
    }
    private void updateTablePreorder() {
        // Clear the table
        model.setRowCount(0);
    
        // Add the updated entries to the table
        for (PersonInfo person : dictionairy.getEntriesPreOrder()) {
            Object[] row = new Object[]{person.getId(), person.getFirstName(), person.getLastName(),
                 person.getEmail(), person.getPhoneNumb(), person.getAddress(), person.getCity(), person.getState(), 
                 person.getZip()};
            model.addRow(row);
        }
    }
    private void updateTablePostorder() {
        // Clear the table
        model.setRowCount(0);
    
        // Add the updated entries to the table
        for (PersonInfo person : dictionairy.getEntriesPostOrder()) {
            Object[] row = new Object[]{person.getId(), person.getFirstName(), person.getLastName(),
                 person.getEmail(), person.getPhoneNumb(), person.getAddress(), person.getCity(), person.getState(), 
                 person.getZip()};
            model.addRow(row);
        }
    }
    private void edit(){
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
    
        // display a message to the user
        JOptionPane.showMessageDialog(null, "Entry with ID " + id + " has been updated.");
        }
        private void add(){
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
            
            //output.append(dictionairy.printInOrder());
            JOptionPane.showMessageDialog(null, "Entry with ID " + id + " has been added.");
    }
    private void deleteEntry(){
        Integer id= Integer.valueOf(idField.getText());
        if(!idField.getText().isEmpty()){
            dictionairy.remove(id);
            JOptionPane.showMessageDialog(null, "Entry with ID " + id + " has been deleted");
        }else{
            JOptionPane.showMessageDialog(null, "Entry with ID " + id + " Not found.");
        }
    }
}
    
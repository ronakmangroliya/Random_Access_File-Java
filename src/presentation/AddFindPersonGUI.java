package presentation;

import business.RandomIO;
import data.Person;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddFindPersonGUI extends JFrame implements ActionListener {
    private JTextField recordInput,firstNameInput,lastNameInput,ageInput,phoneInput;
    private JButton btnAdd,btnFind;
    public AddFindPersonGUI(){
        setMainFrame();
        setScreenLayout();
    }
    /*
     * @ Function Name      : setMainFrame
     * @ Function Params    : None
     * @ Function Purpose   : This method is used to set main frame.
     */
    private void setMainFrame() {
        setTitle("Random File Processing");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(350, 450);
        setLocationRelativeTo(null);
    }

    /*
     * @ Function Name      : setScreenLayout
     * @ Function Params    : None
     * @ Function Purpose   : This method is used set GUI components in frame
     */
    private void setScreenLayout() {
        JPanel recordPanel =new JPanel();
        JLabel recordLabel = new JLabel("Record #    ");
        recordInput = new JTextField(10);
        recordPanel.add(recordLabel);
        recordPanel.add(recordInput);

        JPanel fNamePanel =new JPanel();
        JLabel firstNameLabel = new JLabel("First Name  ");
        firstNameInput = new JTextField(10);
        fNamePanel.add(firstNameLabel);
        fNamePanel.add(firstNameInput);

        JPanel lNamePanel =new JPanel();
        JLabel lastNameLabel = new JLabel("Last Name  ");
        lastNameInput = new JTextField(10);
        lNamePanel.add(lastNameLabel);
        lNamePanel.add(lastNameInput);

        JPanel agePanel =new JPanel();
        JLabel ageLabel = new JLabel("Age             ");
        ageInput = new JTextField(10);
        agePanel.add(ageLabel);
        agePanel.add(ageInput);

        JPanel phonePanel =new JPanel();
        JLabel phoneLabel = new JLabel("Phone          ");
        phoneInput = new JTextField(10);
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneInput);

        JPanel btnPanel = new JPanel();
        btnAdd = new JButton("Add");
        btnFind=new JButton("Find");
        btnPanel.add(btnAdd);
        btnPanel.add(btnFind);
        btnAdd.addActionListener(this);
        btnFind.addActionListener(this);
        btnAdd.setPreferredSize(new Dimension(138, 38));
        btnFind.setPreferredSize(new Dimension(138, 38));

        JPanel mainPanel = new JPanel(new GridLayout(6, 1));
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        mainPanel.add(recordPanel);
        mainPanel.add(fNamePanel);
        mainPanel.add(lNamePanel);
        mainPanel.add(agePanel);
        mainPanel.add(phonePanel);
        mainPanel.add(btnPanel);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){
            addPersonData();
        }else if(e.getSource()==btnFind){
                findPersonData();
        }
    }

    /*
     * @ Function Name      : addPersonData
     * @ Function Params    : None
     * @ Function Purpose   : This method is used validate and add person data into file
     */
    private void addPersonData() {
        //get field input from JTextField
        int record = Integer.parseInt(recordInput.getText().trim());
        String firstName = firstNameInput.getText().trim();
        String lastName = lastNameInput.getText().trim();
        int age = Integer.parseInt(ageInput.getText().trim());
        String phone= phoneInput.getText().trim();

        //validate input data
        if(validateData(record,firstName,lastName,age,phone)){
            Person person = new Person(record,firstName,lastName,age,phone);
            try {
                //call method from RandomIo class to find the data is already in the file or not
                // if it found in the file then show message popup
                //else data will add into file
                RandomIO randomIO= new RandomIO();
                if(randomIO.findperson(record)==null){
                    randomIO.addPerson(person);
                    clearFieldData();
                }else {
                    JOptionPane.showMessageDialog(this, "Sorry! Record already in the file with Record ID");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
     * @ Function Name      : findPersonData
     * @ Function Params    : None
     * @ Function Purpose   : This method is used find person data from file
     */
    private void findPersonData() {
        //get field input from JTextField
        int record= Integer.parseInt(recordInput.getText());
        // if record id enter by the user is >0 then it going to check the data is available into the file or not.
        if(record>0){
            try {
                RandomIO  randomIO = new RandomIO();
                if(randomIO.findperson(record)!=null){
                    // this method is used to set data into JTextField after getting from file.
                    setDataIntoFiled(randomIO.findperson(record));
                }else {
                    //This method is used to clear the input filed
                    clearFieldData();
                    JOptionPane.showMessageDialog(this, "Not any record found belongs to this record id.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Please enter valid Record ID #.");
        }
    }

    /*
     * @ Function Name      : validateData
     * @ Function Params    : String firstName,String lastName, String age, String phone
     * @ Function Purpose   : This method is used validate person data
     */
    public boolean validateData(int record,String firstName,String lastName, int age, String phone ) {

        boolean isValid = false;
        if (record<=0) {
            JOptionPane.showMessageDialog(this, "Please enter valid RecordID");
        }
        else if (firstName.isEmpty() || firstName.length()>20) {
            JOptionPane.showMessageDialog(this, "Please enter valid First Name.");
        }
        else if (lastName.isEmpty() ||lastName.length()>25) {
            JOptionPane.showMessageDialog(this, "Please enter valid Last Name.");
        }
        else if (age<=0) {
            JOptionPane.showMessageDialog(this, "Please enter valid Age");
        } else if (!RandomIO.isValidNumber(phone) || phone.isEmpty() || phone.length()>10 ) {
            JOptionPane.showMessageDialog(this, "Please enter valid Phone");
        } else {
            isValid = true;
        }
        return isValid;
    }

    /*
     * @ Function Name      : setDataIntoFiled
     * @ Function Params    : Person person
     * @ Function Purpose   : This method is used set person data into JTextFiled
     */
    private void setDataIntoFiled(Person person) {
        firstNameInput.setText(person.getFirstName());
        lastNameInput.setText(person.getLastName());
        ageInput.setText(String.valueOf(person.getAge()));
        phoneInput.setText(person.getPhone());
    }

    /*
     * @ Function Name      : clearFieldData
     * @ Function Params    : None
     * @ Function Purpose   : This method is used clear person data from JTextFiled
     */
    private void clearFieldData() {
        firstNameInput.setText("");
        lastNameInput.setText("");
        ageInput.setText("");
        phoneInput.setText("");
        recordInput.setText("");
    }

    /*
     * @ Function Name      : main
     * @ Function Params    : String[] args
     * @ Function Purpose   : This method is start our project
     */
    public static void main(String[] args) {
        new AddFindPersonGUI();
    }

}

package business;

import data.Person;

import java.io.*;
public class RandomIO {
    private RandomAccessFile personFile;

    /*
     * @ Function Name      : addPerson
     * @ Function Params    : Person person
     * @ Function Purpose   : This method is used to add person record into the file.
     */
    public void addPerson(Person person) throws IOException {
        personFile = new RandomAccessFile("src/data/personInfo.dat", "rw");;
        personFile.seek(personFile.length()); // go to end of personFile
        personFile.writeInt(person.getRecord());
        //This method is used to convert string into char and then added char to the file
        writeString(person.getFirstName(), Person.FIRST_NAME_SIZE);
        writeString(person.getLastName(), Person.LAST_NAME_SIZE);
        writeString(person.getPhone(), Person.PHONE_SIZE);
        personFile.writeInt(person.getAge());
    }

    /*
     * @ Function Name      : findPerson
     * @ Function Params    : int recordNumber
     * @ Function Purpose   : This method is used to find record from the file.
     */
    public Person findperson(int record) throws IOException {
        personFile = new RandomAccessFile("src/data/personInfo.dat", "rw");;
        while (personFile.getFilePointer() < personFile.length()) {
            int crrRecord = personFile.readInt();
            //This method is used to get char data from file and converted itn the string based on the given size.
            String firstName = readString(Person.FIRST_NAME_SIZE);
            String lastName = readString(Person.LAST_NAME_SIZE);
            String phone = readString(Person.PHONE_SIZE);
            int age = personFile.readInt();
            if (crrRecord == record) {
                personFile.close();
                return new Person(record, firstName.trim(), lastName.trim(), age, phone.trim());
            }
        }
        personFile.close();
        return null;
    }

    /*
     * @ Function Name      : writeString
     * @ Function Params    : String write, int charSize
     * @ Function Purpose   : This method is used to convert string data into char based on the given string size
     */
    private void writeString(String write, int charSize){
        try {
            for (int i = 0; i < charSize; i++) {
                if (i < write.length()) {
                    personFile.writeChar(write.charAt(i));
                } else {
                    personFile.writeChar(' ');
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /*
     * @ Function Name      : readString
     * @ Function Params    : int charSize
     * @ Function Purpose   : This method is used to read char data and covert into string
     */
    private String readString(int charSize){
        StringBuilder sb = new StringBuilder();
        try{
            for (int i = 0; i < charSize; i++) {
                char c = personFile.readChar();
                if (c != ' ') {
                    sb.append(c);
                }
            }
            return sb.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }

    /*
     * @ Function Name      : isValidNumber
     * @ Function Params    : String text
     * @ Function Purpose   : This method is used to validate number.
     */
    public static boolean isValidNumber(String text) {
        try {
            double number = Double.parseDouble(text);
            return !(number <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

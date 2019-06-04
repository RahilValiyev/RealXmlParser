package xmlparser.saxparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlparser.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class ContentHandler extends DefaultHandler {

    private List<Employee> employeeList = new ArrayList<>();
    private Employee employeeTemp;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    private boolean isEmployee = false;
    private boolean isId = false;
    private boolean isFullName = false;
    private boolean isAddress = false;
    private boolean isPhoneNumber = false;
    private boolean isActvityName = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("Employee")) {
            isEmployee = true;
            employeeTemp = new Employee();
        } else if (isEmployee && qName.equals("id")) {
            isId = true;
        } else if (isEmployee && qName.equals("fullName")) {
            isFullName = true;
        } else if (isEmployee && qName.equals("addressDescription")) {
            isAddress = true;
        } else if (isEmployee && qName.equals("phone1")) {
            isPhoneNumber = true;
        } else if (isEmployee && qName.equals("activityname")) {
            isActvityName = true;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        if (isEmployee && isId) {
            employeeTemp.setId(Integer.parseInt(value));
        } else if (isEmployee && isFullName) {
            employeeTemp.setFullName(value);
        } else if (isEmployee && isAddress) {
            employeeTemp.setAddress(value);
        } else if (isEmployee && isPhoneNumber) {
            employeeTemp.setPhoneNumber(value);
        } else if (isEmployee && isActvityName) {
            employeeTemp.setActvityName(value);
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Employee")) {
            employeeList.add(employeeTemp);
            isEmployee = false;
            employeeTemp = null;
        } else if (isEmployee && qName.equals("id")) {
            isId = false;
        } else if (isEmployee && qName.equals("fullName")) {
            isFullName = false;

        } else if (isEmployee && qName.equals("addressDescription")) {
            isAddress = false;

        } else if (isEmployee && qName.equals("phone1")) {
            isPhoneNumber = false;

        } else if (isEmployee && qName.equals("activityname")) {
            isActvityName = false;
        }
    }


}

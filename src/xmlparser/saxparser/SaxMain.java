package xmlparser.saxparser;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import validator.XsdValidator;
import xmlparser.dao.EmployeImpl;
import xmlparser.model.Employee;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxMain {

    public static void main(String[] args) {

        Logger logger= Logger.getLogger(SaxMain.class);

        System.out.println("Hello World!");

        try {
            logger.info("saxparser start");
            SAXParserFactory factory=SAXParserFactory.newInstance();
            SAXParser parser= factory.newSAXParser();
            ContentHandler content = new ContentHandler();
            parser.parse("xml/mutexesis.xml",content);
            //parser.parse("https://sc.opendata.az/file/478",content);

            List<Employee> employeeList=content.getEmployeeList();
            System.out.println(employeeList);
            EmployeImpl employeImpl = new EmployeImpl();
            employeImpl.addEmploye(employeeList);



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (SAXException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
}

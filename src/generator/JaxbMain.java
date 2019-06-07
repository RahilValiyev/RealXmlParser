package generator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import validator.XsdValidator;
import xmlparser.dao.EmployeImpl;
import xmlparser.model.Employee;
import xmlparser.model.Employees;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class JaxbMain {

    public static void main(String[] args) {

        PropertyConfigurator.configure("log4j.properties");

        Logger logger= Logger.getLogger(JaxbMain.class);

        EmployeImpl emp = new EmployeImpl();
        List<Employee> list = emp.getEmployeList();


        try {
            logger.info("jaxb generator start");

            Employees employees = new Employees();
            employees.setEmployeeList(list);
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employees, new File("employees_jaxb.xml"));

            System.out.println(list);

        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error("error ",e);
        }
        logger.info("jaxbgenerator end process");

    }
}

package validator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XsdValidator {

    public static void main(String[] args) {

        PropertyConfigurator.configure("log4j.properties");

        Logger logger= Logger.getLogger(XsdValidator.class);
        try {


           logger.info("XsdValidator for mutexesis start");

            String xml = "xml/mutexesis.xml";
            String xsd = "xml/mutexesis.xsd";

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            System.out.println("xml is valid according to xsd");
            logger.info("xml is valid to xsd");


        } catch (Exception e) {
            System.out.println("xml is invalid");
            e.printStackTrace();
            logger.error("xml is invalid ",e);

        }
        logger.info("Xsdvalidator is end");

    }


}

package ee.mihkel.veebipood.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "root")
public class JohnDoeXML {

        public String city;
        public String firstName;
        public String lastName;
        public String state;
}

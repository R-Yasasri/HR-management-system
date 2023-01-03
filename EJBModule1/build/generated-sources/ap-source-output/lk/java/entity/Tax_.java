package lk.java.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(Tax.class)
public class Tax_ { 

    public static volatile SingularAttribute<Tax, String> incomeTaxNo;
    public static volatile SingularAttribute<Tax, Integer> idtax;
    public static volatile SingularAttribute<Tax, Employee> employeeIdemployee;
    public static volatile SingularAttribute<Tax, String> officeRegistered;

}
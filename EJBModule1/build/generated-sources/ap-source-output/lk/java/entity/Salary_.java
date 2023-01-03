package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(Salary.class)
public class Salary_ { 

    public static volatile SingularAttribute<Salary, Integer> idsalary;
    public static volatile SingularAttribute<Salary, Date> month;
    public static volatile SingularAttribute<Salary, Employee> employeeIdemployee;
    public static volatile SingularAttribute<Salary, Double> salary;
    public static volatile SingularAttribute<Salary, String> status;

}
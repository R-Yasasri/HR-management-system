package lk.java.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(Bank.class)
public class Bank_ { 

    public static volatile SingularAttribute<Bank, String> address;
    public static volatile SingularAttribute<Bank, String> name;
    public static volatile SingularAttribute<Bank, Integer> idbank;
    public static volatile SingularAttribute<Bank, Employee> employeeIdemployee;
    public static volatile SingularAttribute<Bank, String> account;

}
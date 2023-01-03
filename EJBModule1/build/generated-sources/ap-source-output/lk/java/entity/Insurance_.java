package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(Insurance.class)
public class Insurance_ { 

    public static volatile SingularAttribute<Insurance, String> providerAddress;
    public static volatile SingularAttribute<Insurance, String> provider;
    public static volatile SingularAttribute<Insurance, Date> endDate;
    public static volatile SingularAttribute<Insurance, Integer> idinsurance;
    public static volatile SingularAttribute<Insurance, Employee> employeeIdemployee;
    public static volatile SingularAttribute<Insurance, String> plan;
    public static volatile SingularAttribute<Insurance, Date> startingDate;

}
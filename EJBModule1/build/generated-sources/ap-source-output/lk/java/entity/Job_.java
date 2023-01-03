package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile SingularAttribute<Job, Integer> idjob;
    public static volatile SingularAttribute<Job, Date> endDate;
    public static volatile SingularAttribute<Job, Employee> employeeIdemployee;
    public static volatile SingularAttribute<Job, String> title;
    public static volatile SingularAttribute<Job, Date> startDate;

}
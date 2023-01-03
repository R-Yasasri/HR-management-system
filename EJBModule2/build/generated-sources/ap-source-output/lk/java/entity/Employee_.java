package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Bank;
import lk.java.entity.DisciplinaryHistory;
import lk.java.entity.Insurance;
import lk.java.entity.Job;
import lk.java.entity.PerformanceFeedback;
import lk.java.entity.Salary;
import lk.java.entity.Shift;
import lk.java.entity.Tax;
import lk.java.entity.TimeOffRequest;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-19T00:06:51")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, Date> birthday;
    public static volatile SingularAttribute<Employee, String> address;
    public static volatile ListAttribute<Employee, Insurance> insuranceList;
    public static volatile ListAttribute<Employee, DisciplinaryHistory> disciplinaryHistoryList;
    public static volatile SingularAttribute<Employee, String> mobile;
    public static volatile SingularAttribute<Employee, String> nic;
    public static volatile ListAttribute<Employee, Tax> taxList;
    public static volatile SingularAttribute<Employee, Integer> idemployee;
    public static volatile ListAttribute<Employee, Salary> salaryList;
    public static volatile ListAttribute<Employee, PerformanceFeedback> performanceFeedbackList;
    public static volatile ListAttribute<Employee, Bank> bankList;
    public static volatile SingularAttribute<Employee, String> name;
    public static volatile ListAttribute<Employee, TimeOffRequest> timeOffRequestList;
    public static volatile ListAttribute<Employee, Shift> shiftList;
    public static volatile ListAttribute<Employee, Job> jobList;
    public static volatile SingularAttribute<Employee, String> email;

}
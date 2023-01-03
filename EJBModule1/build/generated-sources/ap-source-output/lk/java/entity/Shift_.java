package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Attendance;
import lk.java.entity.Department;
import lk.java.entity.Employee;
import lk.java.entity.Leave;
import lk.java.entity.Location;
import lk.java.entity.Project;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(Shift.class)
public class Shift_ { 

    public static volatile ListAttribute<Shift, Leave> leaveList;
    public static volatile SingularAttribute<Shift, Location> locationIdlocation;
    public static volatile SingularAttribute<Shift, Integer> allocatedLeaveDays;
    public static volatile SingularAttribute<Shift, Double> paymentPerHour;
    public static volatile SingularAttribute<Shift, Department> departmentIddepartment;
    public static volatile ListAttribute<Shift, Attendance> attendanceList;
    public static volatile SingularAttribute<Shift, Date> finishDate;
    public static volatile SingularAttribute<Shift, Employee> employeeIdemployee;
    public static volatile SingularAttribute<Shift, Project> projectIdproject;
    public static volatile SingularAttribute<Shift, Date> startDate;
    public static volatile SingularAttribute<Shift, Integer> idshift;

}
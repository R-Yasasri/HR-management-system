package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-19T00:06:51")
@StaticMetamodel(TimeOffRequest.class)
public class TimeOffRequest_ { 

    public static volatile SingularAttribute<TimeOffRequest, String> reason;
    public static volatile SingularAttribute<TimeOffRequest, Integer> idtimeOffRequest;
    public static volatile SingularAttribute<TimeOffRequest, Date> beginTime;
    public static volatile SingularAttribute<TimeOffRequest, Date> endTime;
    public static volatile SingularAttribute<TimeOffRequest, Employee> employeeIdemployee;

}
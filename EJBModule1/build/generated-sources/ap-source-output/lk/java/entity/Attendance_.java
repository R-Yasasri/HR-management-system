package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Shift;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(Attendance.class)
public class Attendance_ { 

    public static volatile SingularAttribute<Attendance, Date> clockOut;
    public static volatile SingularAttribute<Attendance, Shift> shiftIdshift;
    public static volatile SingularAttribute<Attendance, Date> clockIn;
    public static volatile SingularAttribute<Attendance, Integer> idattendance;

}
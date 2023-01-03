package lk.java.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Shift;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-19T00:06:51")
@StaticMetamodel(Leave.class)
public class Leave_ { 

    public static volatile SingularAttribute<Leave, Date> beginDate;
    public static volatile SingularAttribute<Leave, Shift> shiftIdshift;
    public static volatile SingularAttribute<Leave, Integer> idleave;
    public static volatile SingularAttribute<Leave, Date> finishDate;
    public static volatile SingularAttribute<Leave, String> type;

}
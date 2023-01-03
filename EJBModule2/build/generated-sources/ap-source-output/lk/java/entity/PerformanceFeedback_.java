package lk.java.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-19T00:06:51")
@StaticMetamodel(PerformanceFeedback.class)
public class PerformanceFeedback_ { 

    public static volatile SingularAttribute<PerformanceFeedback, Integer> idperformanceFeedback;
    public static volatile SingularAttribute<PerformanceFeedback, String> description;
    public static volatile SingularAttribute<PerformanceFeedback, Employee> employeeIdemployee;
    public static volatile SingularAttribute<PerformanceFeedback, String> personGivenBy;

}
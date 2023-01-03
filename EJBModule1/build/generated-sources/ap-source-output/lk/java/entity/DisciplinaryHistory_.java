package lk.java.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Employee;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-18T20:30:50")
@StaticMetamodel(DisciplinaryHistory.class)
public class DisciplinaryHistory_ { 

    public static volatile SingularAttribute<DisciplinaryHistory, String> actionTaken;
    public static volatile SingularAttribute<DisciplinaryHistory, Integer> iddisciplinaryHistory;
    public static volatile SingularAttribute<DisciplinaryHistory, String> description;
    public static volatile SingularAttribute<DisciplinaryHistory, Employee> employeeIdemployee;

}
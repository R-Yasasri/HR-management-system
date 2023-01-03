package lk.java.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Shift;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-19T00:06:51")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, String> name;
    public static volatile SingularAttribute<Project, Integer> idproject;
    public static volatile SingularAttribute<Project, String> description;
    public static volatile ListAttribute<Project, Shift> shiftList;

}
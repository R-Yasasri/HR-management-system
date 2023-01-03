package lk.java.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lk.java.entity.Shift;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-19T00:06:51")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, String> number;
    public static volatile SingularAttribute<Location, String> city;
    public static volatile SingularAttribute<Location, String> street;
    public static volatile SingularAttribute<Location, String> latitude;
    public static volatile SingularAttribute<Location, Integer> idlocation;
    public static volatile ListAttribute<Location, Shift> shiftList;
    public static volatile SingularAttribute<Location, String> longitude;

}
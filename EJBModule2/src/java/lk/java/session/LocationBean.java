/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.LocationJpaController;
import lk.java.entity.Location;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class LocationBean implements LocationBeanRemote, LocationBeanLocal {

    @Resource
    UserTransaction utx;

    @Override
    public void create(String city, String street, String number, String lat, String lon) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        try {

            Location l = new Location();

            l.setCity(city);
            l.setStreet(street);
            l.setLatitude(lat);
            l.setLongitude(lon);
            l.setNumber(number);

            LocationJpaController ljc = new LocationJpaController(utx, emf);
            ljc.create(l);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String city, String street, String number, String lat, String lon) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        try {

            Integer i = Integer.parseInt(id);

            LocationJpaController ljc = new LocationJpaController(utx, emf);

            Location location = ljc.findLocation(i);

            location.setCity(city);
            location.setStreet(street);
            location.setLatitude(lat);
            location.setLongitude(lon);
            location.setNumber(number);

            ljc.edit(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        String msg = "not found";
        LocationJpaController ljc = new LocationJpaController(utx, emf);

        try {

            List<Location> locations = ljc.findLocationEntities();

            Gson gson = new Gson();

            List list = new ArrayList();

            for (Location location : locations) {
                location.setShiftList(null);
                list.add(location);
            }

            msg = gson.toJson(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public void delete(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Integer i = Integer.parseInt(id);

        try {
            LocationJpaController ljc = new LocationJpaController(utx, emf);

            ljc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Integer i = Integer.parseInt(id);

        String msg = "not found";

        try {
            LocationJpaController ljc = new LocationJpaController(utx, emf);

            Location findLocation = ljc.findLocation(i);

            Gson gson = new Gson();

            findLocation.setShiftList(null);
            msg = gson.toJson(findLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByCity(String city) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        String msg = "not found";
        try {
            LocationJpaController ljc = new LocationJpaController(utx, emf);
            List<Location> locations = ljc.findLocationByCity(city);

            Gson gson = new Gson();

            List list = new ArrayList();

            for (Location location : locations) {
                location.setShiftList(null);
                list.add(location);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public Location searchLocationByIdLocal(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        LocationJpaController ljc = new LocationJpaController(utx, emf);

        Location loc = null;
        try {

            Integer i = Integer.parseInt(id);
            loc = ljc.findLocation(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loc;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.interceptors;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import lk.java.model.Validator;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@LocalBean
public class DepartmentInterceptor {

    @AroundInvoke
    public Object validator(InvocationContext ic) throws Exception {

        System.out.println("validator");
        String name = ic.getMethod().getName();
        if (name.equals("create")) {
            Object[] parameters = ic.getParameters();
            //name
            String nm = parameters[0].toString();

            if (!nm.isEmpty()) {
                System.out.println("successful create validation(DepartmentInterceptor.class)");
                return ic.proceed();
            } else {
                System.out.println("invalid values passed to create(DepartmentInterceptor.class)");
                return null;
            }
        } else if (name.equals("edit")) {

            Object[] parameters = ic.getParameters();
            //id,name
            String id = parameters[0].toString();
            String nm = parameters[1].toString();

            if (Validator.isValidNumber(id) && !nm.isEmpty()) {
                System.out.println("successful edit validation(DepartmentInterceptor.class)");
                return ic.proceed();
            } else {
                System.out.println("invalid values passed to edit(DepartmentInterceptor.class)");
                return null;
            }
        } else {
            return ic.proceed();
        }

    }
}

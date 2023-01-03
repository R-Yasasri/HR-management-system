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

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@LocalBean
public class ShiftInterceptor{

   @AroundInvoke
   public Object validator(InvocationContext ic)throws Exception{
       
       System.out.println(ic.getMethod().getName()); 
       return ic.proceed();
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import javax.ejb.Local;
import lk.java.entity.Shift;

/**
 *
 * @author Rajitha Yasasri
 */
@Local
public interface ShiftBeanLocal {

    Shift searchShiftByIdLocal(String id);

}

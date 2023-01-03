package lk.java.session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.ejb.Remote;

/**
 *
 * @author Rajitha Yasasri
 */
@Remote
public interface TaxBeanRemote {

    void create(String tax_no, String office, String emp_id);

    void edit(String tax_no, String office, String emp_id);

    String load();

    void delete(String id);

    String searchByTaxNo(String tax_no);

    String searchById(String id);

}

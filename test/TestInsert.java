/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import base.EmployeDao;
import domaine.*;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jojo
 */

public class TestInsert {
    
    private domaine.Employe emp;
    private domaine.Fonction fonc;
    private metier.ListeFonctions lstFonc;
    private final int INCDEC = 5;
    private final int IDSEM = 13;
    
    
    @BeforeMethod
    public void createEmploye() throws Exception{
        lstFonc = new metier.ListeFonctions();
        emp = new Employe("PrenomTest","NomTest",lstFonc.get(1),"BureauTest");
        EmployeDao.insert(emp);
    }
    
    @Test
    public void testIncDecCafe(){
        int totInit = EmployeDao.getCafes(emp.getIdEmpl(), IDSEM);
        EmployeDao.incDecCafes(emp.getIdEmpl(), IDSEM, INCDEC);
        assertTrue(totInit + INCDEC == EmployeDao.getCafes(emp.getIdEmpl(), IDSEM));
    }
    
    @Test
    public void testTotCafe(){
        int totInit = EmployeDao.getTotalCafes(emp.getIdEmpl());
        EmployeDao.incDecCafes(emp.getIdEmpl(), IDSEM, INCDEC);
        assertTrue(totInit + INCDEC == EmployeDao.getTotalCafes(emp.getIdEmpl()));
    }
    
    
    
}

/*******************************************************/
/**** CETTE CLASSE PEUT ÊTRE MODIFIÉE SI NÉCESSAIRE ****/
/*******************************************************/
package metier;

import base.EmployeDao;
import domaine.Employe;

/**
 * 634.1-Programmation / TP P05
 * 
 * Liste des employés (dans l'ordre des nom et prénom) avec une position courante
 *
 * @author Peter DAEHNE - HEG Genève
 * @version 1.0
 */
public class ListeEmployes extends ListeObjects {

  /** Constructeur */
  public ListeEmployes () {liste = EmployeDao.getListeEmployes();}

  /** Retourne l'employé courant, null si la position courante est NO_POS */
  public Employe getCourant () {return (Employe)super.getCourant();}

  /** Retourne l'employé d'indice k, null si k n'est pas correctement défini */
  public Employe get (int k) {return (Employe)super.get(k);}
  
} // ListeEmployes

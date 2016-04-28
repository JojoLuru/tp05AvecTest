/*******************************************************/
/**** CETTE CLASSE PEUT ÊTRE MODIFIÉE SI NÉCESSAIRE ****/
/*******************************************************/
package metier;

import base.FonctionDao;
import domaine.Fonction;

/**
 * 634.1-Programmation / TP P05
 * 
 * Liste des fonctions (dans l'ordre des libellés) avec une position courante
 *
 * @author Peter DAEHNE - HEG Genève
 * @version 1.0
 */
public class ListeFonctions extends ListeObjects {

  /** Constructeur */
  public ListeFonctions () {liste = FonctionDao.getListeFonctions();}

  /** Retourne la fonction courante, null si la position courante est NO_POS */
  public Fonction getCourant () {return (Fonction)super.getCourant();}

  /** Retourne la fonction d'indice k, null si k n'est pas correctement défini */
  public Fonction get (int k) {return (Fonction)super.get(k);}

} // ListeFonctions

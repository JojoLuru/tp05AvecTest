/************************************************/
/**** CETTE CLASSE NE DOIT PAS ÊTRE MODIFIÉE ****/
/************************************************/
package domaine;

import base.EmployeDao;

/**
 * 634.1-Programmation / TP P05
 * 
 * Modélisation d'un employé
 *
 * @author Peter DAEHNE - HEG Genève
 * @version 1.1
 */
public class Employe implements Comparable {

  private int idEmpl;        /* Numéro de l'employé: identifiant */
  private String nom;        /* Nom */
  private String prenom;     /* Prénom */
  private Fonction fonction; /* Fonction de l'employé */
  private String bureau;     /* Bureau de l'employé */

  /** Constructeurs */
  public Employe (int idEmpl, String nom, String prenom, Fonction fonction, String bureau) {
    this(nom, prenom, fonction, bureau);
    this.idEmpl = idEmpl;
  } // Constructeur

  public Employe (String nom, String prenom, Fonction fonction, String bureau) {
    this.nom = nom; this.prenom = prenom;
    this.fonction = fonction; this.bureau = bureau;
  } // Constructeur

  /** Accesseurs */
  public int getIdEmpl () {return idEmpl;}
  public void setIdEmpl (int idEmpl) {this.idEmpl = idEmpl;}
  public String getNom () {return nom;}
  public String getPrenom () {return prenom;}
  public Fonction getFonction () {return fonction;}
  public String getBureau () {return bureau;}

  public boolean equals (Object obj) {return ((Employe)obj).idEmpl == idEmpl;}
  
  public String toString () {return nom + " " + prenom;}
  
  /*** Interface Comparable ***/
  
  /** L'ordre défini par cette méthode est indépendant de la casse */
  public int compareTo (Object obj) {
    Employe e = (Employe)obj;
    int res = nom.compareToIgnoreCase(e.nom);
    if (res != 0) {return res;}
    res = prenom.compareToIgnoreCase(e.prenom);
    return (res != 0) ? res : idEmpl - e.idEmpl;
  } // compareTo

} // Employe

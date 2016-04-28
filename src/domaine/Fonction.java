/************************************************/
/**** CETTE CLASSE NE DOIT PAS ÊTRE MODIFIÉE ****/
/************************************************/
package domaine;

/**
 * 634.1-Programmation / TP P05
 * 
 * Modélisation d'une fonction
 *
 * @author Peter DAEHNE - HEG Genève
 * @version 1.0
 */
public class Fonction {

  private int idFonction; /* Numéro de la fonction: identifiant */
  private String libelle; /* Libellé de la fonction */

  /** Constructeur */
  public Fonction (int idFonction, String libelle) {
    this.idFonction = idFonction;
    this.libelle = libelle;
  } // Constructeur

  /** Accesseurs */
  public int getIdFonction () {return idFonction;}
  public String getLibelle () {return libelle;}

  public boolean equals (Object obj) {return ((Fonction)obj).idFonction == idFonction;}

  public String toString () {return libelle;}
  
} // Fonction

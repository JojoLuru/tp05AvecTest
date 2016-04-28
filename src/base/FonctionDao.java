package base;

import domaine.Fonction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 634.1-Programmation / TP P05
 * 
 * Gestion des accès à la base de données pour l'entité Fonction.
 *
 * @author Raillard Jonathan
*/
public class FonctionDao {
  
  /** Retourne la liste des fonctions, dans l'ordre des libellés. */
  public static ArrayList getListeFonctions () {
    ArrayList lstFonc = new ArrayList();
    Connection con = ConnexionBase.get();
    try {
        Statement stmt = con.createStatement();
        ResultSet st = stmt.executeQuery("SELECT * FROM Fonction ORDER BY Libelle");
        while(st.next()){
            Fonction fon = new Fonction(st.getInt("IdFonc"), st.getString("Libelle"));
            lstFonc.add(fon);
        }
        stmt.close();
    } catch (Exception e) {e.getMessage();e.printStackTrace();}
    
    return lstFonc;
  } // getListeFonctions
  

  /**** À COMPLÉTER SI NÉCESSAIRE ****/


} // FonctionDao

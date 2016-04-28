package base;

import domaine.Employe;
import domaine.Fonction;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import metier.*;

/**
 * 634.1-Programmation / TP P05
 * 
 * Gestion des accès à la base de données pour l'entité Employe.
 *
 * @author Raillard Jonathan
*/
public class EmployeDao {
  
  /** Retourne la liste des employés, dans l'ordre des nom et prénom. */
  public static ArrayList getListeEmployes () {
    ArrayList emps = new ArrayList();
    Connection con = ConnexionBase.get();
      try {
          Statement stmtEmp = con.createStatement();
          ResultSet st = stmtEmp.executeQuery("SELECT IdEmpl, Nom, Prenom, Employe.IdFonc, Fonction.Libelle, Bureau FROM Employe JOIN Fonction on Employe.IdFonc = Fonction.IdFonc ORDER BY Nom, Prenom");
          while(st.next()){
              Fonction fonc = new Fonction(st.getInt("IdFonc"), st.getString("Libelle"));
              Employe emp = new Employe(st.getInt("IdEmpl"), st.getString("Nom"), st.getString("Prenom"),fonc,st.getString("Bureau"));
              emps.add(emp);
          }
          stmtEmp.close();
      } catch (Exception e) {}  
    return emps;
  } // getListeEmployes
  
  /** Retourne le nombre total de cafés pour l'employé d'identifiant id. */
  public static int getTotalCafes (int id) {
    int nbCafes = 0;
    Connection con = ConnexionBase.get();
      try {
          Statement stmtCafe = con.createStatement();
          ResultSet rs = stmtCafe.executeQuery("SELECT SUM(NbCafes) FROM Consommation WHERE IdEmpl = " + id );
          rs.next(); nbCafes = rs.getInt("SUM(NbCafes)");
          stmtCafe.close();
      } catch (SQLException e) {e.getMessage();}
    return nbCafes;
  } // getTotalCafes

  /** Retourne le nombre de cafés pour l'employé d'identifiant id et la semaine n°k. */
  public static int getCafes (int id, int k) {
     int nbCafes = -1;
     Connection con = ConnexionBase.get();
      try {        
        Statement stmtTot = con.createStatement();
        ResultSet rs = stmtTot.executeQuery("SELECT SUM(NbCafes) FROM Consommation WHERE IdEmpl = " + id + " AND NoSem = " + k); 
        rs.next(); nbCafes = rs.getInt("SUM(NbCafes)");
        stmtTot.close();
        if(nbCafes == 0){
            PreparedStatement stmtConso = con.prepareStatement("INSERT INTO Consommation (IdEmpl,NoSem,NbCafes) VALUES (?,?,?)");
            stmtConso.setInt(1, id); 
            stmtConso.setInt(2, k); 
            stmtConso.setInt(3,0);
            stmtConso.executeUpdate();
            stmtConso.close();
        }  else {
            Statement stmtCafe = con.createStatement();
            ResultSet rsCafesSemaine = stmtCafe.executeQuery("SELECT NbCafes FROM Consommation WHERE IdEmpl = " + id + " AND NoSem = " + k);
            rsCafesSemaine.next(); nbCafes = rsCafesSemaine.getInt("NbCafes");
            stmtCafe.close();
        }
      } catch (SQLException e) {e.getMessage();}
    return nbCafes;
  } // getCafes
  
  /** Insère l'employé empl dans la base et retourne l'identifiant qui lui a été attribué */
  public static int insert (Employe empl) {
    int id = -1;
    Connection con = ConnexionBase.get();
      try {
          PreparedStatement stmt = con.prepareStatement("INSERT INTO `Employe`(`Nom`, `Prenom`, `IdFonc`, `Bureau`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          stmt.setString(1, empl.getNom());
          stmt.setString(2, empl.getPrenom());
          stmt.setInt(3,((empl.getFonction()).getIdFonction()));
          stmt.setString(4, empl.getBureau());
          stmt.executeUpdate();
          ResultSet rs = stmt.getGeneratedKeys();
          rs.next(); 
          id = rs.getInt("IdEmpl");
          stmt.close();
      } catch (SQLException e) {e.getMessage();}
    return id;
  } // insert
  
  /** Ajoute (incr=+1) / enlève (incr=-1) une unité au nombre de cafés de l'employé d'identifiant id pour la semaine n°k.
      Si il n'y a pas encore d'enregistrement pour la semaine n°k, celui-ci est créé avec la valeur 1 pour le nombre de cafés. */
  public static void incDecCafes (int id, int k, int incr) {
    Connection con = ConnexionBase.get();
      try {
        int nbCafes = getCafes(id,k);
        nbCafes = nbCafes+incr;
        PreparedStatement stmtUpdate = con.prepareStatement("UPDATE Consommation set NbCafes = ? WHERE IdEmpl = ? AND NoSem = ?");
        stmtUpdate.setInt(1, nbCafes); 
        stmtUpdate.setInt(2,id); 
        stmtUpdate.setInt(3,k);
        stmtUpdate.executeUpdate();
        stmtUpdate.close(); 
      } catch (Exception e) {e.getMessage();}
  } // incDecCafes
  
} // EmployeDao

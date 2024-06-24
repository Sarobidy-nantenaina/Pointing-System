package mg.nantenaina.Pointing_System;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Employe {

  private String nom;
  private String prenom;
  private String matricule;
  private LocalDate dateNaissance;
  private LocalDate dateEmbauche;
  private LocalDate dateFinContrat;
  private double salaireBrut;
  private double salaireNet;
  private Categorie categorie;

  public Employe(String nom, String prenom, String matricule, LocalDate dateNaissance, LocalDate dateEmbauche,
                 LocalDate dateFinContrat, double salaireBrut, Categorie categorie) {
    this.nom = nom;
    this.prenom = prenom;
    this.matricule = matricule;
    this.dateNaissance = dateNaissance;
    this.dateEmbauche = dateEmbauche;
    this.dateFinContrat = dateFinContrat;
    this.salaireBrut = salaireBrut;
    this.salaireNet = salaireBrut * 0.8;
    this.categorie = categorie;
  }

}

package mg.nantenaina.Pointing_System;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Categorie {

  private String nom;
  private int heuresNormalesParSemaine;
  private double salaireParSemaine;
  private double indemnites;

  public double getTauxHoraire() {
    return this.salaireParSemaine / this.heuresNormalesParSemaine;
  }

}

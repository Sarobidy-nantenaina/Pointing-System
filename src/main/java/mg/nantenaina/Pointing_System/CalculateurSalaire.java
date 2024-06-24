package mg.nantenaina.Pointing_System;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class CalculateurSalaire {

  public static double calculerSalaireBrut(Pointage pointage, Employe employe, Calendrier calendrier) {
    double salaireBrut = 0;
    double tauxHoraire = employe.getCategorie().getTauxHoraire();

    for (Map.Entry<LocalDateTime, Integer> entree : pointage.getHeuresParJour().entrySet()) {
      LocalDateTime dateTime = entree.getKey();
      int heures = entree.getValue();
      LocalDate date = dateTime.toLocalDate();
      DayOfWeek jour = date.getDayOfWeek();

      if (calendrier.estJourFerie(date)) {
        salaireBrut += heures * tauxHoraire * 1.5; // HM50
      } else if (jour == DayOfWeek.SUNDAY) {
        salaireBrut += heures * tauxHoraire * 1.4; // HM40
      } else if (dateTime.getHour() >= 22 || dateTime.getHour() < 6) {
        salaireBrut += heures * tauxHoraire * 1.3; // HM30
      } else {
        salaireBrut += heures * tauxHoraire;
      }
    }

    // Calcul des heures supplémentaires
    int totalHeures = pointage.calculerTotalHeures();
    int heuresNormales = employe.getCategorie().getHeuresNormalesParSemaine();
    if (totalHeures > heuresNormales) {
      int heuresSupplementaires = totalHeures - heuresNormales;
      int hs30 = Math.min(heuresSupplementaires, 8);
      int hs50 = Math.min(Math.max(heuresSupplementaires - 8, 0), 12);
      salaireBrut += hs30 * tauxHoraire * 1.3;
      salaireBrut += hs50 * tauxHoraire * 1.5;
    }

    return salaireBrut;
  }

  public static double calculerSalaireNet(double salaireBrut) {
    return salaireBrut * 0.8;
  }

}

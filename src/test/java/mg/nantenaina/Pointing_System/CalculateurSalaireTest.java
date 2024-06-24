package mg.nantenaina.Pointing_System;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateurSalaireTest {
  private Categorie normal;
  private Categorie gardien;
  private Employe rakoto;
  private Employe rabe;
  private Calendrier calendrier;

  @BeforeEach
  public void setUp() {
    normal = new Categorie("Normal", 40, 100000, 0);
    gardien = new Categorie("Gardien", 56, 110000, 0);

    rakoto = new Employe("Rakoto", "Gardien", "001", LocalDate.of(1980, 1, 1),
        LocalDate.of(2020, 1, 1), LocalDate.of(2025, 1, 1), 110000, gardien);
    rabe = new Employe("Rabe", "Gardien de nuit", "002", LocalDate.of(1985, 2, 1),
        LocalDate.of(2020, 1, 1), LocalDate.of(2025, 1, 1), 110000, gardien);

    calendrier = new Calendrier();
    calendrier.ajouterJourFerie(LocalDate.of(2024, 6, 17), "Jour férié");
    calendrier.ajouterJourFerie(LocalDate.of(2024, 6, 25), "Jour férié");
    calendrier.ajouterJourFerie(LocalDate.of(2024, 6, 26), "Jour férié");
  }

  @Test
  public void testCalculSalaireRakoto() {
    Pointage pointageRakoto = new Pointage();
    for (int i = 1; i <= 30; i++) {
      LocalDate date = LocalDate.of(2024, 6, i);
      if (!calendrier.estJourFerie(date) && date.getDayOfWeek().getValue() <= 7) {
        pointageRakoto.ajouterHeures(date.atTime(8, 0), 8);
      }
    }

    double salaireBrutRakoto = CalculateurSalaire.calculerSalaireBrut(pointageRakoto, rakoto, calendrier);
    double salaireNetRakoto = CalculateurSalaire.calculerSalaireNet(salaireBrutRakoto);

    // Ajustez les valeurs attendues si nécessaire
    assertEquals(110000, salaireBrutRakoto, 0.001);
    assertEquals(88000, salaireNetRakoto, 0.001);
  }

  @Test
  public void testCalculSalaireRabe() {
    Pointage pointageRabe = new Pointage();
    for (int i = 1; i <= 30; i++) {
      LocalDate date = LocalDate.of(2024, 6, i);
      if (!calendrier.estJourFerie(date) && date.getDayOfWeek().getValue() >= 6) {
        pointageRabe.ajouterHeures(date.atTime(22, 0), 8);
      }
    }

    double salaireBrutRabe = CalculateurSalaire.calculerSalaireBrut(pointageRabe, rabe, calendrier);
    double salaireNetRabe = CalculateurSalaire.calculerSalaireNet(salaireBrutRabe);

    // Ajustez les valeurs attendues si nécessaire
    assertEquals(110000, salaireBrutRabe, 0.001);
    assertEquals(88000, salaireNetRabe, 0.001);
  }

  @Test
  public void testCalculSalaireApresMaladie() {
    Pointage pointageRakoto = new Pointage();
    Pointage pointageRabe = new Pointage();
    for (int i = 1; i <= 30; i++) {
      LocalDate date = LocalDate.of(2024, 6, i);
      if (!calendrier.estJourFerie(date)) {
        if (date.getDayOfWeek().getValue() <= 7) {
          pointageRakoto.ajouterHeures(date.atTime(8, 0), 8);
        }
        if (date.getDayOfWeek().getValue() >= 6) {
          if (i == 25 || i == 26) {
            pointageRakoto.ajouterHeures(date.atTime(8, 0), 8);
            pointageRakoto.ajouterHeures(date.atTime(22, 0), 8);
          } else {
            pointageRabe.ajouterHeures(date.atTime(22, 0), 8);
          }
        }
      }
    }

    double salaireBrutRakoto = CalculateurSalaire.calculerSalaireBrut(pointageRakoto, rakoto, calendrier);
    double salaireNetRakoto = CalculateurSalaire.calculerSalaireNet(salaireBrutRakoto);

    double salaireBrutRabe = CalculateurSalaire.calculerSalaireBrut(pointageRabe, rabe, calendrier);
    double salaireNetRabe = CalculateurSalaire.calculerSalaireNet(salaireBrutRabe);

    // Ajustez les valeurs attendues si nécessaire
    assertEquals(118800, salaireBrutRakoto, 0.001);
    assertEquals(95040, salaireNetRakoto, 0.001);
    assertEquals(96800, salaireBrutRabe, 0.001);
    assertEquals(77440, salaireNetRabe, 0.001);
  }
}

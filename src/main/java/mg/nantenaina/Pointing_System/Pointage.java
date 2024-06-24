package mg.nantenaina.Pointing_System;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pointage {

  private Map<LocalDate, Integer> heuresParJour = new HashMap<>();

  public void ajouterHeures(LocalDate date, int heures) {
    this.heuresParJour.put(date, heures);
  }

  public int calculerTotalHeures() {
    return this.heuresParJour.values().stream().mapToInt(Integer::intValue).sum();
  }

}

package mg.nantenaina.Pointing_System;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pointage {

  private Map<LocalDateTime, Integer> heuresParJour = new HashMap<>();

  public void ajouterHeures(LocalDateTime dateTime, int heures) {
    this.heuresParJour.put(dateTime, heures);
  }

  public int calculerTotalHeures() {
    return this.heuresParJour.values().stream().mapToInt(Integer::intValue).sum();
  }

}

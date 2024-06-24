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
public class Calendrier {

  private Map<LocalDate, String> joursFeries = new HashMap<>();

  public void ajouterJourFerie(LocalDate date, String description) {
    this.joursFeries.put(date, description);
  }

  public boolean estJourFerie(LocalDate date) {
    return this.joursFeries.containsKey(date);
  }

}

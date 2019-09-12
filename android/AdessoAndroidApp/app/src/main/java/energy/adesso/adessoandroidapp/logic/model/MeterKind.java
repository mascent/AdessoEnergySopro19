package energy.adesso.adessoandroidapp.logic.model;

import java.io.Serializable;

public enum MeterKind implements Serializable {

  WATER("water", 8, 3),
  ELECTRIC("electric", 7, 1),
  GAS("gas", 8, 3);

  String kind;
  int commaPosition;
  int lengthOfReading;


  MeterKind(String kind, int commaPosition, int lengthOfReading) {
    this.kind = kind;
    this.commaPosition = commaPosition;
    this.lengthOfReading = lengthOfReading;
  }
}

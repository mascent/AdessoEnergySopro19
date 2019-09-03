package energy.adesso.adessoandroidapp.logic.model.internal;

public enum MeterKind {

  WATER("water", 8, 3),
  ELECTRIC("electric", 7, 1),
  GAS("gas", 8,3);

  String kind;
  int commaPosition;
  int lengthOfReading;


  MeterKind(String kind, int commaPosition, int lengthOfReading) {
    this.kind = kind;
    this.commaPosition = commaPosition;
    this.lengthOfReading = lengthOfReading;
  }
  }

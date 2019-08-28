package energy.adesso.app.logic.model.transfer;


public abstract class DTO {
  String id;
  String updatedAt;
  String createdAt;
  String deletedAt;

  protected abstract boolean validateSpecifics();

  public boolean validate() {

    if (!stringCheck(id, createdAt))
      return false;

    if (!validateSpecifics())
      return false;

    return true;
  }

  protected boolean stringCheck(String... strings) {
    for (String s : strings) {
      if (s == null || s.contentEquals(""))
        return false;
    }
    return true;
  }
}

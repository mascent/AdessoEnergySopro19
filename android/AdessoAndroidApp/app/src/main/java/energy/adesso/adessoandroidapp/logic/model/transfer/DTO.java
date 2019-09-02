package energy.adesso.adessoandroidapp.logic.model.transfer;


public abstract class DTO {
  public long id;
  public String updatedAt;
  public String createdAt;
  public String deletedAt;

  protected abstract boolean validateSpecifics();

  public boolean validate() {
    //Todo: id check

    if (!stringCheck(createdAt))
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

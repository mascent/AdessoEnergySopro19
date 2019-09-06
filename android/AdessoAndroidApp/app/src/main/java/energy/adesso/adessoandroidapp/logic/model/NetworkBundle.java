package energy.adesso.adessoandroidapp.logic.model;

public class NetworkBundle {
  private String payload;
  private boolean error;

  public NetworkBundle(String payload, boolean error) {
    this.payload = payload;
    this.error = error;
  }

  public boolean isError() {
    return error;
  }

  public String getPayload() {
    return payload;
  }
}

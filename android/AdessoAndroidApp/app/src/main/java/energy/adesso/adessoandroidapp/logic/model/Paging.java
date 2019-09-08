package energy.adesso.adessoandroidapp.logic.model;

public class Paging<T> {
  // from JSON
  private T[] content;
  private int size;
  private int page;
  private int totalPages;
  private int total;
  private boolean isFirst;
  private boolean isLast;
  // own
  private transient String baseRequest;

  public void setBaseRequest(String baseRequest) {
    this.baseRequest = this.baseRequest;
  }

  public String getBaseRequest() {
    return baseRequest;
  }

  public T[] getContent() {
    return content;
  }

  public int getSize() {
    return size;
  }

  public int getPage() {
    return page;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public int getTotalEntries() {
    return total;
  }

  public boolean isFirst() {
    return isFirst;
  }

  public boolean isLast() {
    return isLast;
  }
}

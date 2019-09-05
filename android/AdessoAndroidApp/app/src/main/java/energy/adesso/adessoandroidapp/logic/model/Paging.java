package energy.adesso.adessoandroidapp.logic.model;

public class Paging<T> {
  // from JSON

  // own
  String baseRequest;

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
    return totalEntries;
  }
  public T[] content;
  public int size;
  public int page;
  public int totalPages;
  public int totalEntries;
  public boolean isFirst;
  public boolean isLast;



}

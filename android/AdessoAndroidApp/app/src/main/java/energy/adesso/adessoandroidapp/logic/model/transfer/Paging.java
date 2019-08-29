package energy.adesso.adessoandroidapp.logic.model.transfer;

public class Paging<T> {
  // from JSON
  T[] content;
  int size;
  int page;
  int totalPages;
  int totalEntries;
  boolean isFirst;
  boolean isLast;

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

}

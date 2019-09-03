package energy.adesso.adessoandroidapp.logic.model.transfer;

public class Paging<T> {
  // from JSON
  public final T[] content;
  public final int size;
  public final int page;
  public final int totalPages;
  public final int totalEntries;
  public final boolean isFirst;
  public final boolean isLast;

  public Paging(T[] content, int size, int page, int totalPages, int totalEntries, boolean isFirst, boolean isLast) {
    this.content = content;
    this.size = size;
    this.page = page;
    this.totalPages = totalPages;
    this.totalEntries = totalEntries;
    this.isFirst = isFirst;
    this.isLast = isLast;
  }

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

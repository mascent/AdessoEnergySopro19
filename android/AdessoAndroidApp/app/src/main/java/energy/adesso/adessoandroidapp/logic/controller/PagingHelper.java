package energy.adesso.adessoandroidapp.logic.controller;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.Either;
import energy.adesso.adessoandroidapp.logic.model.Paging;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.NetworkBundle;

public class PagingHelper<T> {

  public Either<String, List<T>> getAll(String request) throws NetworkException, CredentialException {
    ArrayList<T> list = new ArrayList<T>();
    int pageNumber = 0;
    while (true) {
      // get current page
      String url = "/api/users/me/meters/" + "?" + pageNumber++;
      NetworkBundle bundle = NetworkController.get(url, true);
      // Netzwerkfehlerfall
      if (bundle.isError())
        return new Either<String, List<T>>(bundle.getPayload(), null);
      Type pagingType = new Paging<T>() {
      }.getClass();
      Paging<T> paging = new Gson().fromJson(pagingString, pagingType);
      List<T> content = Arrays.asList(paging.getContent());
      list.addAll(content);
      if (paging.isLast) break;
    }
    // Erfolgsfall
    return new Either<String, List<T>>(null, list);
  }
}

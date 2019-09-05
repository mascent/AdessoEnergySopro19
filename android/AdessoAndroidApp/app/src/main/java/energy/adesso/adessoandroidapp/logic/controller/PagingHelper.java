package energy.adesso.adessoandroidapp.logic.controller;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.Paging;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.logic.model.Token;

public class PagingHelper<T> {

  public List<T> getAll(String request, Token token) throws NetworkException, CredentialException {
    ArrayList<T> list = new ArrayList<T>();
    int pageNumber = 0;
    while (true) {
      // get current page
      String url = "/api/users/me/meters/" + "?" + pageNumber++;
      String pagingString = NetworkController.get(url, token.getToken());
      Type pagingType = new Paging<T>() {}.getClass();
      Paging<T> paging = new Gson().fromJson(pagingString, pagingType);
      List<T> content = Arrays.asList(paging.getContent());
      list.addAll(content);
      if (paging.isLast) break;
    }
    return list;
  }
}

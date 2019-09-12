package energy.adesso.adessoandroidapp.ui.mock;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;

public class MockIssue extends Issue {
  public MockIssue(String id) {
    super(id);
  }

  @Override
  public void send() throws NetworkException {
  }
}

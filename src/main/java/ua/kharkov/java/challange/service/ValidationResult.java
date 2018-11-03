package ua.kharkov.java.challange.service;

import com.google.common.base.Joiner;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;


public class ValidationResult {

  private static final Joiner ERRORS_JOINER = Joiner.on(";" + System.lineSeparator());

  private boolean valid = true;
  private List<String> errors = new ArrayList<>(0);

  private ValidationResult() {
  }

  public static ValidationResult getValidInstance() {
    return new ValidationResult();
  }

  public void addError(String error) {
    valid = false;
    errors.add(error);
  }

  private String getErrorsString() {
    return ERRORS_JOINER.join(errors);
  }

  private boolean isValid() {
    return valid;
  }

  public void assertValid() {
    if (!isValid()) {
      throw new ValidationException(getErrorsString());
    }
  }

}

package org.example.utils;

/**
 * Ошибка при вводе данных в неверном формате. Тестовая ошибка
 */
public class ValidationError extends RuntimeException {
  String place;

  public ValidationError(String place) {
    super();
    this.place = place;
  }

  @Override
  public String getMessage() {
    return "Ошибка при загрузке данных из файла. Объект класса " + place + " введен неверно.";
  }
}
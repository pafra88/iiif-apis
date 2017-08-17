package de.digitalcollections.iiif.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;
import java.net.URI;

public class ViewingHint {
  public enum Type {
    INDIVIDUALS,
    PAGED,
    CONTINUOUS,
    MULTI_PART,
    NON_PAGED,
    TOP,
    FACING_PAGES,
    OTHER
  }

  public static ViewingHint INDIVIDUALS = new ViewingHint(Type.INDIVIDUALS);
  public static ViewingHint PAGED = new ViewingHint(Type.PAGED);
  public static ViewingHint CONTINUOUS = new ViewingHint(Type.CONTINUOUS);
  public static ViewingHint MULTI_PART = new ViewingHint(Type.MULTI_PART);
  public static ViewingHint NON_PAGED = new ViewingHint(Type.NON_PAGED);
  public static ViewingHint TOP = new ViewingHint(Type.TOP);
  public static ViewingHint FACING_PAGES = new ViewingHint(Type.FACING_PAGES);

  private Type type;
  private URI uri;

  @JsonCreator
  public ViewingHint(String value) {
    String typeName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, value);
    try {
      this.type = Type.valueOf(typeName);
    } catch (IllegalArgumentException e) {
      this.type = Type.OTHER;
      this.uri = URI.create(value);
    }
  }

  public ViewingHint(Type type) {
    this.type = type;
  }

  public ViewingHint(URI uri) {
    this.type = Type.OTHER;
    this.uri = uri;
  }

  @JsonIgnore
  public Type getType() {
    return type;
  }

  @JsonIgnore
  public URI getUri() {
    return uri;
  }



  @Override
  @JsonValue
  public String toString() {
    if (this.type == Type.OTHER) {
      return this.uri.toString();
    } else {
      return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, this.type.name());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewingHint that = (ViewingHint) o;
    return this.type == that.type &&
           (uri != null ? uri.equals(that.uri) : that.uri == null);
  }

  @Override
  public int hashCode() {
    int result = type.hashCode();
    result = 31 * result + (uri != null ? uri.hashCode() : 0);
    return result;
  }
}
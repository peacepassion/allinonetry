package me.ele.base.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionsUtils {

  private CollectionsUtils() {

  }

  public static boolean isEmpty(Collection<?> collection) {
    if (collection == null || collection.isEmpty()) {
      return true;
    }
    return false;
  }

  public static boolean isEmpty(Map<?, ?> map) {
    if (map == null || map.isEmpty()) {
      return true;
    }
    return false;
  }

  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }

  public static boolean isNotEmpty(Map<?, ?> map) {
    return !isEmpty(map);
  }

  public static int size(Collection<?> collection) {
    if (isEmpty(collection)) {
      return 0;
    }
    return collection.size();
  }

  public static int size(Map<?, ?> map) {
    if (isEmpty(map)) {
      return 0;
    }
    return map.size();
  }

  public static void clear(Collection<?> collection) {
    if (isEmpty(collection)) {
      return;
    }
    collection.clear();
  }

  public static void clear(Map<?, ?> map) {
    if (isEmpty(map)) {
      return;
    }
    map.clear();
  }
}

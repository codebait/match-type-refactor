package com.codebait;

import java.util.Set;
import java.util.regex.Pattern;

enum MatchType implements Filter {
  ANY_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      for (String pattern : patterns) {
        if (Pattern.compile(pattern).matcher(text).find()) {
          return true;
        }
      }
      return false;
    }
  },
  ALL_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      for (String pattern : patterns) {
        if (!Pattern.compile(pattern).matcher(text).find()) {
          return false;
        }
      }
      return true;
    }
  },
  ALL_NOT_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      for (String pattern : patterns) {
        if (Pattern.compile(pattern).matcher(text).find()) {
          return false;
        }
      }
      return true;
    }
  },
  ANY_NOT_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      for (String pattern : patterns) {
        if (!Pattern.compile(pattern).matcher(text).find()) {
          return true;
        }
      }
      return false;
    }
  }
}

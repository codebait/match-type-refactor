package com.codebait;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum MatchType implements Filter {
  ANY_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      return patterns.stream()
          .map(Pattern::compile)
          .map(pattern -> pattern.matcher(text))
          .anyMatch(Matcher::find);
    }
  },
  ALL_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      return patterns.stream()
          .map(Pattern::compile)
          .map(pattern -> pattern.matcher(text))
          .allMatch(Matcher::find);
    }
  },
  ALL_NOT_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      return patterns.stream()
          .map(Pattern::compile)
          .map(pattern -> pattern.matcher(text))
          .noneMatch(Matcher::find);
    }
  },
  ANY_NOT_MATCH {
    @Override
    public boolean filter(String text, Set<String> patterns) {
      return patterns.stream()
          .map(Pattern::compile)
          .map(pattern -> pattern.matcher(text))
          .anyMatch(matcher -> !matcher.find());
    }
  }
}

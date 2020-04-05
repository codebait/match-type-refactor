package com.codebait;

import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

enum MatchType implements Filter {
  ANY_MATCH(stream -> stream.anyMatch(Matcher::find)),
  ALL_MATCH(stream -> stream.allMatch(Matcher::find)),
  ALL_NOT_MATCH(stream -> stream.noneMatch(Matcher::find)),
  ANY_NOT_MATCH(stream -> stream.anyMatch(matcher -> !matcher.find()));
  private final Predicate<Stream<Matcher>> matchPredicate;

  MatchType(Predicate<Stream<Matcher>> matchPredicate) {
    this.matchPredicate = matchPredicate;
  }

  @Override
  public boolean filter(String text, Set<String> patterns) {
    return matchPredicate.test(generateMatcherStream(text, patterns));
  }

  private Stream<Matcher> generateMatcherStream(String text, Set<String> patterns) {
    return patterns.stream()
        .map(Pattern::compile)
        .map(pattern -> pattern.matcher(text));
  }
}

package com.codebait;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.Test;

class MatchTypeTest {

  public static final String EMAIL_PATTERN = "\\S+@\\w+\\.\\w+";
  public static final String PHONE_NUMBER_PATTERN = "(\\d\\s*-*){9}";
  public static final String TEXT_WITH_EMAIL_AND_NUMBER = "Hej oto mój email: przemek.codebait@gmail.com a to mój numer telefonu : 999-495-394";
  public static final String TEXT_WITH_NUMBER = "Hej oto mój numer telefonu : 999-495-394";
  public static final String TEXT_WITHOUT_EMAIL_AND_NUMBER = "Brak info";

  @Test
  void shouldAllMatchReturnTrueIfAllPatternMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ALL_MATCH.filter(TEXT_WITH_EMAIL_AND_NUMBER, patterns);
    // then
    assertTrue(filter);
  }

  @Test
  void shouldAllMatchReturnFalseIfOnePatternNotMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ALL_MATCH.filter(TEXT_WITH_NUMBER, patterns);
    // then
    assertFalse(filter);
  }

  @Test
  void shouldAnyMatchReturnTrueIfOnePatternMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ANY_MATCH.filter(TEXT_WITH_NUMBER, patterns);
    // then
    assertTrue(filter);
  }

  @Test
  void shouldAnyMatchReturnFalseIfNoOnePatternMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ANY_MATCH.filter(TEXT_WITHOUT_EMAIL_AND_NUMBER, patterns);
    // then
    assertFalse(filter);
  }

  @Test
  void shouldAllNotMatchReturnTrueIfNoOnePatternMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ALL_NOT_MATCH.filter(TEXT_WITHOUT_EMAIL_AND_NUMBER, patterns);
    // then
    assertTrue(filter);
  }

  @Test
  void shouldAllNotMatchReturnFalseIfAllPatternMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ALL_NOT_MATCH.filter(TEXT_WITH_EMAIL_AND_NUMBER, patterns);
    // then
    assertFalse(filter);
  }

  @Test
  void shouldAllNotMatchReturnFalseIfOnePatternMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ALL_NOT_MATCH.filter(TEXT_WITH_NUMBER, patterns);
    // then
    assertFalse(filter);
  }

  @Test
  void shouldAnyNotMatchReturnTrueIfOnePatternNotMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ANY_NOT_MATCH.filter(TEXT_WITH_NUMBER, patterns);
    // then
    assertTrue(filter);
  }

  @Test
  void shouldAnyNotMatchReturnFalseIfAllPatternMatch() {
    // given
    Set<String> patterns = Set.of(EMAIL_PATTERN, PHONE_NUMBER_PATTERN);
    // when
    boolean filter = MatchType.ANY_NOT_MATCH.filter(TEXT_WITH_EMAIL_AND_NUMBER, patterns);
    // then
    assertFalse(filter);
  }


}

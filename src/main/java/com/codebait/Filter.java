package com.codebait;

import java.util.Set;

interface Filter {

  boolean filter(String text, Set<String> patterns);

}

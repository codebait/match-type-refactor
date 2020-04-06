package com.codebait;

import java.util.Collection;

interface Filter {

  boolean filter(String text, Collection<String> patterns);

}

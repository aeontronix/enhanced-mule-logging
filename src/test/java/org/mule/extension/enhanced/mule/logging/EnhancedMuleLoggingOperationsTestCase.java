package org.mule.extension.enhanced.mule.logging;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mule.extension.enhanced.mule.logging.internal.Flattener.flatten;

import org.junit.Assert;
import org.mule.extension.enhanced.mule.logging.internal.EnhancedMuleLoggingOperations;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnhancedMuleLoggingOperationsTestCase {
  @Test
  public void testFlatten() {
    final Map<String, Object> map1 = new HashMap<>();
    map1.put("k1","hello");
    final HashMap<Object, Object> subMap1 = new HashMap<>();
    subMap1.put("s1","k2s1val");
    map1.put("k2", subMap1);
    final ArrayList<Object> ar1 = new ArrayList<>();
    final HashMap<Object, Object> subMap2 = new HashMap<>();
    subMap2.put("s2","s2.0.aval");
    ar1.add(subMap2);
    ar1.add("s2.1.val");
    map1.put("k3",ar1);
    final Map<String, Object> flatMap = flatten(null, map1);
    final Map<String, Object> expected = new HashMap<>();
    expected.put("k1","hello");
    expected.put("k2.s1","k2s1val");
    expected.put("k3.[0].s2","s2.0.aval");
    expected.put("k3.[1]","s2.1.val");
    Assert.assertEquals(expected,flatMap);
  }
}


package com.dataSwitch.ds.functionTypeRules;

import java.util.HashMap;

public class HashMapCaseInsensitive extends HashMap<String, String>{
 
 @Override
 public String put(String key, String value) {
  return super.put(key.toLowerCase(), value);
 }

 @Override
 public String get(Object key) {
  return super.get(key.toString().toLowerCase());
 }
}
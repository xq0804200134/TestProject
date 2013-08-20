package com.test3;

import java.io.Serializable;
import java.security.Principal;
//
// This class defines the principle object, which is just an encapsulated
// String name
public class PrincipalImpl implements Principal, Serializable {
	
  private String name;
	
  public PrincipalImpl(String n) {
    name = n;
  }
	
  public boolean equals(Object obj) {
    if (!(obj instanceof PrincipalImpl)) {
      return false;
    }
    PrincipalImpl pobj = (PrincipalImpl)obj;
    if (name.equals(pobj.getName())) {
      return true;
    }
    return false;
  }
	
  public String getName() {
    return name;
  }
	
  public int hashCode() {
    return name.hashCode();
  }
	
  public String toString() {
    return getName();
  }
	
}

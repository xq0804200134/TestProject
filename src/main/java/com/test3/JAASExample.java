package com.test3;

import java.security.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
//
// This is the main program in the JAAS Example.  It creates a Login Context,
// logs the user in based on the settings in the Login Configuration file,
// and calls two sensitive pieces of code, the first using programmatic
// authorization, and the second using declaritive authorization.
public class JAASExample {

  static LoginContext lc = null;

  public static void main( String[] args) {
    //
    // Create a login context
	System.out.println(JAASExample.class.getResource("/com/test3/")+"login.config");
    System.setProperty("java.security.auth.login.config", JAASExample.class.getResource("/com/test3/")+"login.config");
    System.setProperty("java.security.policy", JAASExample.class.getResource("/com/test3/")+"jaas.policy");

    try {
      lc = new LoginContext("JAASExample",
         new UsernamePasswordCallbackHandler());
    } catch (LoginException le) {
      System.out.println( "Login Context Creation Error" );
      System.exit(1);
    }
    //
    // Login
    try {
      lc.login();
    } catch (LoginException le) {
    	le.printStackTrace();
      System.out.println( "\nOVERALL AUTHENTICATION FAILED\n" );
      System.exit(1);
    }
    System.out.println( "\nOVERALL AUTHENTICATION SUCCEEDED\n" );
    System.out.println( lc.getSubject() );
    //
    // Call the sensitive PayrollAction code, which uses programmatic
    // authorization.
    try {
      Subject.doAs( lc.getSubject(), new PayrollAction() );
    } catch (AccessControlException e) {
      System.out.println( "Payroll Access DENIED" );
    }
    //
    // Call the sensitive PersonnelAction code, which uses declarative
    // authorization.
    try {
      Subject.doAsPrivileged( lc.getSubject(), new PersonnelAction(), null );
    } catch (AccessControlException e) {
      System.out.println( "Personnel Access DENIED" );
    }
    try {
      lc.logout();
    } catch (LoginException le) {
      System.out.println( "Logout FAILED" );
      System.exit(1);
    }
    System.exit(0);
  }
}


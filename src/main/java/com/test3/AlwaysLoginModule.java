package com.test3;

import java.security.*;
import javax.security.auth.*;
import javax.security.auth.spi.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import java.io.*;
import java.util.*;

// This is a JAAS Login Module that always succeeds.  While not realistic,
// it is designed to illustrate the bare bones structure of a Login Module
// and is used in examples that show the login configuration file operation.
public class AlwaysLoginModule implements LoginModule {
	
  private Subject subject;
  private Principal principal;
  private CallbackHandler callbackHandler;
  private String username;
  private boolean loginSuccess;
  //
  // Initialize sets up the login module.  sharedState and options are
  // advanced features not used here
  public void initialize(Subject sub, CallbackHandler cbh,
    Map sharedState, Map options) {

    subject = sub;
    callbackHandler = cbh;
    loginSuccess = false;
    System.out.println("AlwaysLoginModule.initialize");
  }
  //
  // The login phase gets the userid from the user
  public boolean login() throws LoginException {
    //
    // Since we need input from a user, we need a callback handler
	 // System.out.println("AlwaysLoginModule.login");
    if (callbackHandler == null) {
      throw new LoginException( "No CallbackHandler defined");
    }
    Callback[] callbacks = new Callback[1];
    callbacks[0] = new NameCallback("Username");
    //
    // Call the callback handler to get the username
    try {
      System.out.println( "\nAlwaysLoginModule Login" );
      callbackHandler.handle(callbacks);
      username = ((NameCallback)callbacks[0]).getName();
    } catch (IOException ioe) {
      throw new LoginException(ioe.toString());
    } catch (UnsupportedCallbackException uce) {
      throw new LoginException(uce.toString());
    }
    loginSuccess = true;
    System.out.println();
    System.out.println( "Login: AlwaysLoginModule SUCCESS" );   
    return true;
  }
  //
  // The commit phase adds the principal if both the overall authentication
  // succeeds (which is why commit was called) as well as this particular
  // login module
  public boolean commit() throws LoginException {
    //
    // Check to see if this login module succeeded (which it always will
    // in this examp  System.setProperty("java.security.auth.login.config", "D:/workspace_WLE/701_IC83170/_AAA/src/com/jaas.conf");  System.setProperty("java.security.auth.login.config", "D:/workspace_WLE/701_IC83170/_AAA/src/com/jaas.conf");  System.setProperty("java.security.auth.login.config", "D:/workspace_WLE/701_IC83170/_AAA/src/com/jaas.conf");le)
    if (loginSuccess == false) {
      System.out.println( "Commit: AlwaysLoginModule FAIL" );
      return false;
    }
    //
    // If this login module succeeded too, then add the new principal
    // to the subject (if it does not already exist)
    principal = new PrincipalImpl(username);
    if (!(subject.getPrincipals().contains(principal))) {
      subject.getPrincipals().add(principal);
    }
    System.out.println( "Commit: AlwaysLoginModule SUCCESS" );
    return true;
  }
  //
  // The abort phase is called if the overall authentication fails, so
  // we have to clean up the internal state
  public boolean abort() throws LoginException {

    if (loginSuccess == false) {
      System.out.println( "Abort: AlwaysLoginModule FAIL" );
      principal = null;
      return false;
    }
    System.out.println( "Abort: AlwaysLoginModule SUCCESS" );
    logout();
    return true;
  }
  //
  // The logout phase cleans up the state
  public boolean logout() throws LoginException {

    subject.getPrincipals().remove(principal);
    loginSuccess = false;
    principal = null;
    System.out.println( "Logout: AlwaysLoginModule SUCCESS" );
    return true;
   }
}

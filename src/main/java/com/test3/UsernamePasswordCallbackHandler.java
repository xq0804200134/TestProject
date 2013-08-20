package com.test3;

import java.io.*;
import java.security.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
//
// This class implements a username/password callback handler that gets
// information from the user
public class UsernamePasswordCallbackHandler implements CallbackHandler {
  //
  // The handle method does all the work and iterates through the array
  // of callbacks, examines the type, and takes the appropriate user
  // interaction action.
  public void handle(Callback[] callbacks) throws
      UnsupportedCallbackException, IOException {

    for(int i=0;i<callbacks.length;i++) {
      Callback cb = callbacks[i];
      //
      // Handle username aquisition
      if (cb instanceof NameCallback) {
        NameCallback nameCallback = (NameCallback)cb;
        System.out.print( nameCallback.getPrompt() + "? ");
        System.out.flush();
        String username = new BufferedReader(
            new InputStreamReader(System.in)).readLine();
        nameCallback.setName(username);
        //
        // Handle password aquisition
      } else if (cb instanceof PasswordCallback) {
        PasswordCallback passwordCallback = (PasswordCallback)cb;
        System.out.print( passwordCallback.getPrompt() + "? ");
        System.out.flush();
        String password = new BufferedReader(
            new InputStreamReader(System.in)).readLine();
        passwordCallback.setPassword(password.toCharArray());
        password = null;
        //
        // Other callback types are not handled here
      } else {
        throw new UnsupportedCallbackException(cb, "Unsupported Callback Type");
      }
    }
  }
}

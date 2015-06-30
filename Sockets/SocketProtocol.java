package test;

import java.net.*;
import java.io.*;

public class SocketProtocol {
    private static final int WAITING = 0;
    private static final int SENTCOMMAND = 1;

    private int state = WAITING;

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Enter your details";
            state = SENTCOMMAND;
        } else if (state == SENTCOMMAND) {
            if (theInput.contains("hello this is")) {
                theOutput = "ACK";
                state = WAITING;
            } else {
                theOutput = "You're supposed to give your details";
            }
        }
        return theOutput;
    }
}
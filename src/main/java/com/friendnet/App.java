package com.friendnet;

import java.util.List;
import java.util.Scanner;

/**
 *
 *
 */
public class App {
    public static void main( String[] args ) {
        FriendGraph graph = new FriendGraph();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("=== FriendNet CLI ===");
        System.out.println("Commands: adduser <name>, addfriend <user1> <user2>, check <user1> <user2>, exit");

        while (true){
            System.out.print("> ");
            command = scanner.nextLine(); //parts = "Adduser Anna".split -> parts= ["addUser", "Anna"]
            String[] parts = command.split("\\s+"); //"\\s+": use whitespace as a splitter
            if (parts.length == 0) continue;

            switch(parts[0].toLowerCase()){
                case "adduser":
                    if(parts.length < 2){
                        System.out.println("Usage: adduser <name>");
                        break;
                    }
                    graph.addUser(parts[1]);
                    System.out.println("Added user: " + parts[1]);
                    break;
                case "addfriend":
                    if (parts.length < 3) {
                        System.out.println("Usage: addfriend <user1> <user2>");
                        break;
                    }
                    // ensure users exist; addUser already prevent Overwriting existing users.
                    graph.addUser(parts[1]);
                    graph.addUser(parts[2]);
                    graph.addFriendship(parts[1], parts[2]);
                    System.out.println("Created friendship between " + parts[1] + " and " + parts[2]);
                    break;
                case "check":
                    if (parts.length < 3) {
                        System.out.println("Usage: check <user1> <user2>");
                        break;
                    }
                    boolean connected = graph.areConnected(parts[1], parts[2]);
                    String message = connected
                            ? parts[1] + " and " + parts[2] + " are connected"
                            : parts[1] + " and " + parts[2] + " are not connected";
                    System.out.println(message);
                    //System.out.println(parts[1] + " is connected to " + parts[2] + "? " + connected);
                    break;
                case "exit":
                    System.out.println("Exiting FriendNet.");
                    return;
                default:
                    System.out.println("Unknown command.");




            }
        }

    }
}
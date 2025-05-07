package com.friendnet;


import java.util.*;

/**
 * Represents a simple graph to model a social network.
 * Each node is a user and edges represent friendship.
 */
public class FriendGraph {
    // Adjacency List
    private final Map<String, List<String>> adjacencyList;

    public FriendGraph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a user to the network.
     * @param name Name of the user.
     */
    public void addUser(String name){
        // putIfAbsent: prevents Overwriting existing users (and their list of friends).
        this.adjacencyList.putIfAbsent(name, new ArrayList<>());
    }

    /**
     * Creates a friendship (undirected edge) between two users.
     * @param user1 First user.
     * @param user2 Second user.
     */
    public void addFriendship(String user1, String user2){
        this.adjacencyList.get(user1).add(user2);
        this.adjacencyList.get(user2).add(user1);

    }
    /**
     * Gets friends of a user.
     * @param user Name of user.
     * @return List of direct friends.
     */
    public List<String> getFriends(String user){
        return  this.adjacencyList.getOrDefault(user, Collections.emptyList());
        // return  this.adjacencyList.get(user);
        /**
         * "Alice" -> ["Bob", "Charlie"]
         * "Bob" -> ["Alice"]
         *
         * getFriends("Bob") : ["Alice"]
         * getFriends("Dave") : [] : not null
         * */

    }

    public boolean areConnected(String source, String target){
        Set<String> visited = new HashSet<>(); // Initialize Visited Set

        Queue<String> queue = new LinkedList<>(); // Initialize 'BFS' Search Queue
        queue.add(source); // Add the source user to start the search

        // Process Until Queue is Empty
        // Typical BFS loop — continue until all connected nodes are visited or target is found.
        while (!queue.isEmpty()){
            // Step1: Dequeue and Check
            String current = queue.poll();
            if(current.equals(target)) return true; // If this is the target, we found a connection → return true.
            // Mark as Visited: Avoid visiting this user again in future iterations.
            visited.add(current);

            // Explore Neighbors
            for(String neighbor: getFriends(current)){
                if(!visited.contains(neighbor)){
                    queue.add(neighbor);
                }
            }


        }


        // If We Exhaust All Options
        return false;
    }
}
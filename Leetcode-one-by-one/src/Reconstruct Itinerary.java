//Approach no 1 : 

import java.util.*;

public class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build graph
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        // Step 2: DFS from JFK
        dfs("JFK");

        // Step 3: Itinerary is built in reverse
        return itinerary;
    }

    private void dfs(String airport) {
        PriorityQueue<String> dests = graph.get(airport);
        while (dests != null && !dests.isEmpty()) {
            dfs(dests.poll());  // visit smallest lex destination
        }
        itinerary.addFirst(airport); // add after exploring all edges
    }
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------

//Approach no 2 :

import java.util.*;

public class Solution {
    Map<String, List<String>> graph = new HashMap<>();
    List<String> result;
    int ticketCount;

    public List<String> findItinerary(List<List<String>> tickets) {
        // Build graph
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), x -> new ArrayList<>()).add(ticket.get(1));
        }

        // Sort each destination list in lexical order (smallest first)
        for (List<String> dests : graph.values()) {
            Collections.sort(dests);  // small lists → fast
        }

        result = new ArrayList<>();
        ticketCount = tickets.size();

        dfs("JFK");

        Collections.reverse(result);  // itinerary built in reverse
        return result;
    }

    private void dfs(String airport) {
        List<String> dests = graph.get(airport);
        while (dests != null && !dests.isEmpty()) {
            String next = dests.remove(0);  // remove smallest lex
            dfs(next);
        }
        result.add(airport);  // post-order
    }
}

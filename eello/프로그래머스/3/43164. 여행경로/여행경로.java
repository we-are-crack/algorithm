import java.util.*;

class Solution {
    
    private static class Ticket {
        private String dest;
        private boolean used = false;
        
        public Ticket(String dest) {
            this.dest = dest;
        }
    }
    
    public String[] solution(String[][] tickets) {
        Map<String, List<Ticket>> ticket = new HashMap<>();
        for (String[] t : tickets) {
            String dep = t[0];
            String dest = t[1];
            
            if (!ticket.containsKey(dep)) {
                ticket.put(dep, new ArrayList<>());
            }
            ticket.get(dep).add(new Ticket(dest));
         }
        
        for (Map.Entry<String, List<Ticket>> entry : ticket.entrySet()) {
            Collections.sort(entry.getValue(), (t1, t2) -> t1.dest.compareTo(t2.dest));
        }
        
        String[] route = new String[tickets.length + 1];
        route[0] = "ICN";
        return setRoute(ticket, route, 1);
    }
    
    private String[] setRoute(Map<String, List<Ticket>> ticket, String[] route, int idx) {
        if (idx == route.length) {
            return route;
        }
        
        String current = route[idx - 1];
        if (!ticket.containsKey(current)) {
            return null;
        }
        
        String[] ret = null;
        for (Ticket tk : ticket.get(current)) {
            if (!tk.used) {
                tk.used = true;
                route[idx] = tk.dest;
                
                ret = setRoute(ticket, route, idx + 1);
                if (ret != null) {
                    break;
                }
                
                tk.used = false;
            }
        }
        
        return ret;
    }
}
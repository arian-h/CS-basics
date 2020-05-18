package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostOccurringPattern {

    private final Map<Pattern, Integer> freq = new HashMap<>();
    private final Map<String, List<String>> userVisits = new HashMap<>();

    /**
     * Having a history of web page views, including the name of the user who visited the page, and time
     * this algorithm finds the view path of length 3 that was most frequently visited by unique users.
     * (in other words, if one user viewed a single path of length three several times, it's only counted once.
     *
     * First create a list of views sorted by time for each user.
     * For each user, select all three subsequence of web pages, and increase frequency for that path.
     * Finally find the path with highest view. If there are multiple paths with equal frequency, find the one
     * that is lexicographically smallest.
     *
     * @param username list of users associated with each page view.
     * @param time list of visit times, associated with each page view.
     * @param website list of page names, associated with each page view.
     * @return the most visited page visits path of length three.
     */
    public List<String> mostVisitedPattern(String[] username, int[] time, String[] website) {
        separateUserVisits(username, time, website);
        for (String user: userVisits.keySet()) {
            traverse(userVisits.get(user));
        }
        return getMostVisitedPattern().sites;
    }

    private void separateUserVisits(String[] username, int[] time, String[] website) {
        List<Visit> visits = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            visits.add(new Visit(username[i], time[i], website[i]));
        }
        visits.sort((v1, v2) -> {
            if (v1.user.compareTo(v2.user) != 0) {
                return v1.user.compareTo(v2.user);
            }
            if (v1.time != v2.time) {
                return v1.time - v2.time;
            }
            if (v1.website.compareTo(v2.website) != 0) {
                return v1.website.compareTo(v2.website);
            }
            return 0;
        });
        for (Visit visit: visits) {
            userVisits.putIfAbsent(visit.user, new ArrayList<>());
            userVisits.get(visit.user).add(visit.website);
        }
    }

    private Pattern getMostVisitedPattern() {
        int max = freq.values().stream().max(Comparator.comparingInt(a -> a)).get();
        return freq.entrySet().stream().filter(e -> e.getValue() == max).map(Map.Entry::getKey).min((p1, p2) -> {
            for (int i = 0; i < 3; i++) {
                if (p1.sites.get(i).compareTo(p2.sites.get(i)) != 0) {
                    return p1.sites.get(i).compareTo(p2.sites.get(i));
                }
            }
            return 0;
        }).get();
    }
    private void traverse(List<String> websites) {
        Set<Pattern> visited = new HashSet<>();
        for (int i = 0; i < websites.size(); i++) {
            for (int j = i + 1; j < websites.size(); j++) {
                for (int k = j + 1; k < websites.size(); k++) {
                    Pattern pattern = new Pattern(Arrays.asList(websites.get(i), websites.get(j), websites.get(k)));
                    if (!visited.contains(pattern)) {
                        visited.add(pattern);
                        freq.put(pattern, freq.getOrDefault(pattern, 0) + 1);
                    }
                }
            }
        }
    }

    private static class Pattern {
        private final List<String> sites;
        public Pattern(List<String> sites) {
            this.sites = new ArrayList<>(sites);
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pattern)) {
                return false;
            }
            Pattern p = (Pattern) o;
            for (int i = 0; i < 3; i++) {
                if (!p.sites.get(i).equals(sites.get(i))) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return 13 * sites.get(0).hashCode() + 31 * sites.get(1).hashCode() + 7 * sites.get(2).hashCode();
        }
    }

    private static class Visit {
        private final String website;
        private final int time;
        private final String user;
        public Visit(String user, int time, String website) {
            this.website = website;
            this.time = time;
            this.user = user;
        }
    }
}

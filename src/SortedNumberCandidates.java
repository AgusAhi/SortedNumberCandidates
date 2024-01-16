import java.io.*;
import java.util.*;

public class SortedNumberCandidates {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = readCSV("Colfuturo-Seleccionados.csv");
        List<Pair> list = convertMapToList(map);
        Collections.sort(list);
        print(list);
    }

    private static void print(List<Pair> list) {
        for (Pair pair: list) {
            System.out.println(pair);
        }
    }

    private static Map<String, Integer> readCSV(String filename) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = input.readLine()) != null) {
                String[] items = line.split(",");
                String country = items[6];
                Integer freq = map.get(country);
                map.put(country, map.get(country) == null ? 1 : freq + 1);
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return map;
    }

    private static List<Pair> convertMapToList(Map<String, Integer> map) {
        List<Pair> list = new ArrayList<Pair>();
        Set<String> keySet = map.keySet();
        for (String key: keySet) {
            Integer value = map.get(key);
            Pair pair = new Pair(key, value);
            list.add(pair);
        }
        return list;
    }
}

class Pair implements Comparable<Pair> {
    private String country;
    private Integer freq;
    public Pair(String country, Integer freq) {
        this.country = country;
        this.freq = freq;
    }

    @Override
    public int compareTo(Pair pair) {
        int intComparation = freq.compareTo(pair.freq);
        if (intComparation == 0) {
            return country.compareToIgnoreCase(pair.country);
        }
        return intComparation;
    }

    @Override
    public String toString() {
        return country + ": " + freq;
    }

}

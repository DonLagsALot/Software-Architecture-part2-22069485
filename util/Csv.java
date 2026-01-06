package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class Csv {
    private Csv() {}

    public static List<Map<String,String>> read(Path path) throws Exception {
        if (!Files.exists(path)) return new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String headerLine = br.readLine();
            if (headerLine == null) return new ArrayList<>();
            List<String> headers = parse(headerLine);

            List<Map<String,String>> rows = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                List<String> cells = parse(line);
                Map<String,String> row = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    row.put(headers.get(i), i < cells.size() ? cells.get(i) : "");
                }
                rows.add(row);
            }
            return rows;
        }
    }

    public static void write(Path path, List<String> headers, List<List<String>> rows) throws Exception {
        Files.createDirectories(path.getParent());
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bw.write(String.join(",", escAll(headers)));
            bw.newLine();
            for (List<String> r : rows) {
                bw.write(String.join(",", escAll(r)));
                bw.newLine();
            }
        }
    }

    private static List<String> parse(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean q = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (q && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    cur.append('"'); i++;
                } else q = !q;
            } else if (c == ',' && !q) {
                out.add(cur.toString()); cur.setLength(0);
            } else cur.append(c);
        }
        out.add(cur.toString());
        return out;
    }

    private static List<String> escAll(List<String> vals) {
        List<String> out = new ArrayList<>(vals.size());
        for (String v : vals) out.add(esc(v));
        return out;
    }

    private static String esc(String v) {
        if (v == null) return "";
        // CSV escaping: if value contains comma, quote, or newline, wrap in quotes and double any quotes.
        boolean needs = v.contains(",") || v.contains("\"") || v.contains("\n") || v.contains("\r");
        String s = v.replace("\"", "\"\"");
        return needs ? "\"" + s + "\"" : s;
    }
}

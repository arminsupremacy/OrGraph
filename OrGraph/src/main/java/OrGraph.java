import java.util.*;

public class OrGraph {
    private Map<String, Vertex> vertices = new HashMap<>();
    private Map<Vertex, Map<Vertex, Edge>> edges = new HashMap<>();

    class Vertex {
        String name;

        public Vertex(String name) {
            this.name = name;
        }


    }

    class Edge {
        int weight;
        Vertex start, end;

        public Edge(Vertex start, Vertex end, int weight) {
            if (weight < 1) throw new IllegalArgumentException("Weight must be greater than 0");
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }


    public void addVertex(String name) {
        vertices.put(name, new Vertex(name));
    }

    public Edge getEdge(String nameStart, String nameEnd) {
        if (vertices.get(nameStart) == null | vertices.get(nameEnd) == null)
            throw new IllegalArgumentException("Vertices with given names must exist");
        if (edges.get(vertices.get(nameStart)).get(vertices.get(nameEnd)) == null)
            throw new IllegalArgumentException("Edge between vertices with given names must exist");
        return edges.get(vertices.get(nameStart)).get(vertices.get(nameEnd));
    }

    public Set<Edge> getEdges() {
        Set<Edge> result = null;
        for (Vertex start : edges.keySet()) {
            for (Vertex end :edges.get(start).keySet()) {
                result.add(edges.get(start).get(end));
            }
        }
        return result;
    }

    public Vertex getVertex(String name) {
        if (vertices.get(name) == null)
            throw new IllegalArgumentException("Vertex with given name must exist");
        return vertices.get(name);
    }

    public void changeName(String oldName, String newName) {
        vertices.get(oldName).name = newName;
        vertices.put(newName, vertices.get(oldName));
        vertices.remove(oldName);
    }

    public void addEdge(String nameStart, String nameEnd, int weight) {
        if (vertices.get(nameStart) == null || vertices.get(nameEnd) == null)
            throw new IllegalArgumentException("Vertices with given names must exist");
        Edge newEdge = new Edge(vertices.get(nameStart), vertices.get(nameEnd), weight);
        Map <Vertex, Edge> oldMap = edges.get(vertices.get(nameStart));
        if (oldMap == null) oldMap = new HashMap<>();
        oldMap.put(vertices.get(nameEnd), newEdge);
        edges.put(vertices.get(nameStart), oldMap);
    }

    public void changeWeight(String nameStart, String nameEnd, int newWeight) {
        if (vertices.get(nameEnd) == null | vertices.get(nameStart) == null)
            throw new IllegalArgumentException("Vertices with given names must exist");
        if (newWeight < 1) throw new IllegalArgumentException("Weight must be greater than 0");
        if (edges.get(vertices.get(nameStart)).get(vertices.get(nameEnd)) == null)
            throw new IllegalArgumentException("Edge between vertices with given names must exist");
        edges.get(vertices.get(nameStart)).get(vertices.get(nameEnd)).weight = newWeight;
    }

    public void deleteVertex(String name) {
        if (vertices.get(name) == null) throw new IllegalArgumentException("Vertex with given name must exist");
        edges.remove(vertices.get(name));
        for (Vertex key : edges.keySet()) {
            if (edges.get(key).containsKey(vertices.get(name))) {
                Map<Vertex, Edge> oldMap = edges.get(key);
                oldMap.remove(vertices.get(name));
                edges.put(key, oldMap);
            }
        }
        vertices.remove(name);
    }

    public void deleteEdge(String nameStart, String nameEnd) {
        if (vertices.get(nameEnd) == null | vertices.get(nameStart) == null)
            throw new IllegalArgumentException("Vertices with given names must exist");
        Map<Vertex, Edge> oldMap = edges.get(vertices.get(nameStart));
        if (oldMap == null) throw new IllegalArgumentException("Edge between vertices with given names must exist");
        oldMap.remove(vertices.get(nameEnd));
        edges.put(vertices.get(nameStart), oldMap);
    }

    public Collection<Edge> getOutgoingEdges(String name) {
        if (vertices.get(name) == null) throw new IllegalArgumentException("Vertex with given name must exist");
        return edges.get(vertices.get(name)).values();
    }

    public Set<Edge> getIncomingEdges(String name) {
        if (vertices.get(name) == null) throw new IllegalArgumentException("Vertex with given name must exist");
        Set<Edge> result = new HashSet<>();
        for (Map<Vertex, Edge> value : edges.values()) {
            if (value.containsKey(vertices.get(name))) {
                result.add(value.get(vertices.get(name)));
            }
        }
        return result;
    }
}

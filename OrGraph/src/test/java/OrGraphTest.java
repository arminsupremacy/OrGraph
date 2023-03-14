import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrGraphTest {
    @Test
    public void testAddVertex() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        assertEquals("A", orGraph.getVertex("A").name);
    }

    @Test
    public void testChangeName() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        orGraph.addVertex("C");
        orGraph.addEdge("A", "C", 3);
        orGraph.changeName("A", "B");
        assertEquals("B", orGraph.getVertex("B").name);
        assertEquals("B", orGraph.getEdge("B", "C").start.name);
    }

    @Test
    public void testAddEdge() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        orGraph.addVertex("B");
        orGraph.addEdge("A", "B", 3);
        assertEquals(3, orGraph.getEdge("A", "B").weight);
    }

    @Test
    public void testChangeWeight() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        orGraph.addVertex("B");
        orGraph.addEdge("A", "B", 3);
        orGraph.changeWeight("A", "B", 5);
        assertEquals(5, orGraph.getEdge("A", "B").weight);
    }

    @Test
    public void testDeleteEdge() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        orGraph.addVertex("B");
        orGraph.addEdge("A", "B", 3);
        orGraph.deleteEdge("A", "B");
        assertThrows(IllegalArgumentException.class, () -> orGraph.getEdge("A", "B"));
    }

    @Test
    public void testDeleteVertex() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        orGraph.addVertex("B");
        orGraph.addEdge("A", "B", 3);
        orGraph.deleteVertex("A");
        assertThrows(IllegalArgumentException.class, () -> orGraph.getVertex("A"));
        assertNull(orGraph.getEdges());
    }

    @Test
    public void testGetOutgoingEdges() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        orGraph.addVertex("B");
        orGraph.addEdge("A", "B", 3);
        assertEquals(
                orGraph.getEdge("A", "B"),
                orGraph.getOutgoingEdges("A").toArray()[0]
        );
    }

    @Test
    public void testGetIncomingEdges() {
        OrGraph orGraph = new OrGraph();
        orGraph.addVertex("A");
        orGraph.addVertex("B");
        orGraph.addEdge("A", "B", 3);
        assertEquals(
                orGraph.getEdge("A", "B"),
                orGraph.getIncomingEdges("B").toArray()[0]
        );
    }
}
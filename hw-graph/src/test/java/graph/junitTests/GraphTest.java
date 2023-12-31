package graph.junitTests;

import graph.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class GraphTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test
    public void testChangeNode() {
        Graph<String, String> G = new Graph<>();
        Set<String> expected = new HashSet<>();

        // Add 100 nodes
        for (int i = 1; i < 100; i++) {
            String old = String.valueOf(i);
            expected.add(old);
            G.addNode(old);
        }

        // Change each node one by one and test
        for (int i = 1; i < 100; i++) {
            String old = String.valueOf(i);
            expected.remove(old);
            expected.add(old + "-new");
            G.changeNode(old, old + "-new");
            assertEquals(G.getNodes(), expected);
        }
    }

    @Test
    public void testGetEdges() {
        Graph<String, String> G = new Graph<>();
        Set<SimpleEntry<String, String>> expected = new HashSet<>();

        // Add 100 nodes
        for (int i = 1; i < 100; i++) {
            G.addNode(String.valueOf(i));
        }

        // Add 100 edges one by one and test
        String parent = String.valueOf(1);
        for (int i = 1; i < 100; i++) {
            String edge = String.valueOf(i);
            expected.add(new SimpleEntry<>(edge, edge));
            G.addEdge(parent, edge, edge);
            assertEquals(expected, G.getEdges(parent));
        }
    }

    @Test
    public void testChangeEdge() {
        Graph<String, String> G = new Graph<>();
        Set<SimpleEntry<String, String>> expected = new HashSet<>();

        // Add 100 nodes
        for (int i = 1; i < 100; i++) {
            G.addNode(String.valueOf(i));
        }

        // Add 100 edges
        String parent = String.valueOf(1);
        for (int i = 1; i < 100; i++) {
            String edge = String.valueOf(i);
            expected.add(new SimpleEntry<>(edge, edge));
            G.addEdge(parent, edge, edge);
        }

        // Change each edge one by one and test
        for (int i = 1; i < 100; i++) {
            String old = String.valueOf(i);
            expected.remove(new SimpleEntry<>(old, old));
            expected.add(new SimpleEntry<>(old + "-new", old));
            G.changeEdge(parent, old, old, old + "-new");
            assertEquals(expected, G.getEdges(parent));
        }
    }

    @Test
    public void testIsEmpty() {
        // Should be true if empty
        Graph<String, String> G = new Graph<>();
        assertTrue(G.isEmpty());

        // Should be false if not empty
        G.addNode("new");
        assertFalse(G.isEmpty());
    }

    @Test
    public void testSize() {
        Graph<String, String> G = new Graph<>();
        assertEquals(G.size(), 0);

        // Add 100 nodes one by one and test size
        for (int i = 1; i < 100; i++) {
            G.addNode(String.valueOf(i));
            assertEquals(G.size(), i);
        }
    }

    @Test
    public void testAddNodeExceptions() {
        Graph<String, String> G = new Graph<>();
        G.addNode("n1");

        // Should throw exception if node value already exists in the graph
        assertThrows(IllegalArgumentException.class, () -> {G.addNode("n1");});
    }

    @Test
    public void testAddEdgeExceptions() {
        Graph<String, String> G = new Graph<>();
        G.addNode("n1");
        G.addNode("n2");
        G.addEdge("n1", "n2", "e12");

        // Should throw exception if edge label already exists between parent/child
        assertThrows(IllegalArgumentException.class, () -> {G.addEdge("n1", "n2", "e12");});

        // Should throw exception if parent/child doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {G.addEdge("n3", "n2", "e12");});
        assertThrows(IllegalArgumentException.class, () -> {G.addEdge("n1", "n3", "e12");});
    }

    @Test
    public void testChangeNodeExceptions() {
        Graph<String, String> G = new Graph<>();
        G.addNode("n1");
        G.addNode("n2");

        // Should throw exception if new node value already exists in the graph
        assertThrows(IllegalArgumentException.class, () -> {G.changeNode("n1", "n2");});

        // Should throw exception if old node value doesn't exist in the graph
        assertThrows(IllegalArgumentException.class, () -> {G.changeNode("n4", "n-new");});
    }

    @Test
    public void testChangeEdgeExceptions() {
        Graph<String, String> G = new Graph<>();
        G.addNode("n1");
        G.addNode("n2");
        G.addEdge("n1", "n2", "e12");
        G.addEdge("n1", "n2", "e122");

        // Should throw exception if edge label already exists between parent and child
        assertThrows(IllegalArgumentException.class, () -> {G.changeEdge("n1", "n2", "e12", "e122");});

        // Should throw exception if old value doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {G.changeEdge("n1", "n2", "e13", "e14");});

        // Should throw exception if parent or child doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {G.changeEdge("n3", "n2", "e12", "e13");});
        assertThrows(IllegalArgumentException.class, () -> {G.changeEdge("n1", "n3", "e12", "e13");});
    }

    @Test
    public void testGetChildrenExceptions() {
        Graph<String, String> G = new Graph<>();

        // Should throw exception if given node doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {G.getChildren("n1");});
    }

    @Test
    public void testGetEdgesExceptions() {
        Graph<String, String> G = new Graph<>();

        // Should throw exception if given node doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {G.getEdges("n1");});
    }
}

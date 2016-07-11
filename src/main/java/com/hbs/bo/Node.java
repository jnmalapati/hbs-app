package com.hbs.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Tree structure
 * of Nodes
 * Every Node has a reference to 1 or 0 parent
 * Every Node has 0 or n children
 * Created by jyo on 07/07/2016.
 */
public class Node {
    /**
     * Node Name
     */
    private String name;

    /**
     * Children
     */
    private List<Node> children = new ArrayList<>();

    /**
     * Reference to Parent Node
     */
    private Node parent;

    /**
     * Constructor
     *
     * @param name
     */
    public Node(String name) {
        this.name = name;
    }

    /**
     * This method returns the Root Node
     * [Root Node: is a Node which does not have a parent]
     *
     * @return Root Node
     */
    public Node getRoot() {
        if (parent == null) {
            return this;
        }
        return parent.getRoot();
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    /***
     * This method outprints a tree-like view of
     * the relationship structure of Parent and Children
     * Nodes
     *
     * @param prefix (Indentation)
     * @param isTail (boolean value )
     */
    public void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + name);

        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1).print(prefix + (isTail ? "    " : "│   "), true);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}

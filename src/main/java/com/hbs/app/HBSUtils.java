package com.hbs.app;

import com.hbs.bo.Node;
import com.hbs.bo.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility Class
 * Created by jyo on 07/07/2016.
 */
public class HBSUtils {

    /**
     * This method prints a tree-like view of the relationship structure
     * of reviewer and reviewee.
     *
     * @param fileName
     */
    public static void printHierarchicalView(final String fileName) throws Exception {

        final Path filePath = Paths.get(fileName);
        //1. Build the List of Pairs <Reviewer, Reviewee>
        final List<Pair> pairList = buildPairsListFromFile(filePath.toUri());

        //2. Build the hierarchical Tree model and Identify the Root Node
        final Node rootNode = buildTreeAndIdentifyRootNode(pairList);

        //3. Print Hierarchy tree structure starting from the Root Node
        rootNode.print("", false);
    }

    /**
     * This method builds the tree structure of reviewer (Parent) -reviewee (List of childrens)
     * and returns the Root node (Reviewer who doesn't have a parent i.e.,
     * Reviewer who not a reviewee for someone else)
     *
     * @param pairsList
     * @return Node returns a root node
     */
    public static Node buildTreeAndIdentifyRootNode(final List<Pair> pairsList) {
        Node root = null;
        //Temporary HashMap to build the Tree Model
        Map<String, Node> temp = new HashMap<>();
        for (Pair pair : pairsList) {
            /*
             * Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
             */
            Node parent = temp.getOrDefault(pair.getReviewer(), new Node(pair.getReviewer()));
            Node child = temp.getOrDefault(pair.getReviewee(), new Node(pair.getReviewee()));

            //We now have a parent(Reviewer) and child (Reviewee). Let's relate them
            parent.getChildren().add(child);
            child.setParent(parent);

            temp.put(parent.getName(), parent);
            temp.put(child.getName(), child);
        }
        for (Node n : temp.values()) {
            root = n.getRoot();
            break;
        }
        return root;
    }

    /**
     * This method readds pairs [ Reviewer-reviewee] details from the given file
     * and builds a List<Pair>.  Each line in a the given must have
     * the in the format of "X reviews Y". Where X is a reviewer and Y is a reviewee.
     * Any incorrectly formatted lines will be ignored.
     * NOTE:
     * 1. Every reviewer has one or more reviewee.
     * 2. Every Reviewee must have one reviewer
     *
     * @param fileName
     * @return list of pairs
     * @throws IOException
     */
    public static List<Pair> buildPairsListFromFile(java.net.URI fileName) throws IOException {

        final String TOKEN = "reviews";

        List<Pair> pairsList = new ArrayList<>();
        //Read the File, get all the entries and build the Model
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        // Identify the reviewer and reviewee relationship
        for (String line : lines) {
            String[] pairs = line.split(TOKEN);

            //Quick validation to make sure we have reviewer and reviewee relationship defined correctly
            //i.e., we have two persons( 1-reviewer, 2 - reviewee otherwise we ignore the invalid ones.
            if (pairs.length == 2) {
                pairsList.add(new Pair(pairs[0].trim(), pairs[1].trim()));
            }
        }
        return pairsList;
    }
}

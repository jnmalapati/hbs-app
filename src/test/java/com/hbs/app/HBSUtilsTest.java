package com.hbs.app;

import com.hbs.bo.Node;
import com.hbs.bo.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jyo on 07/07/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(HBSUtils.class)
public class HBSUtilsTest {
    @Test(expected = NoSuchFileException.class)
    public void buildPairsListFromFileShouldThrowNoSuchFileException() throws IOException {
        final Path filePath = Paths.get("src/test/resources/reviewer-and-reviewees-nosuchfile.txt");
        List<Pair> pairList = HBSUtils.buildPairsListFromFile(filePath.toUri());
    }

    @Test
    public void buildPairsListFromFileWithBadlyformattedDataWithOneValidPair() throws IOException {
        final Path filePath = Paths.get("src/test/resources/reviewers-and-reviewees-badlyformatted.txt");
        List<Pair> pairList = HBSUtils.buildPairsListFromFile(filePath.toUri());
        assertFalse(pairList.isEmpty());
        assertEquals(1, pairList.size());
    }

    @Test
    public void buidlPairsListFromFileContainingThreeValidPairs() throws IOException {
        final Path filePath = Paths.get("src/test/resources/reviewers-and-reviewees.txt");
        List<Pair> pairList = HBSUtils.buildPairsListFromFile(filePath.toUri());
        assertFalse(pairList.isEmpty());
        assertEquals(3, pairList.size());
    }

    @Test
    public void buidlPairsListFromFileValidFileEmptyLines() throws IOException {
        final Path filePath = Paths.get("src/test/resources/reviewers-and-reviewees-empty.txt");
        List<Pair> pairList = HBSUtils.buildPairsListFromFile(filePath.toUri());
        assertTrue(pairList.isEmpty());
    }

    @Test
    public void buildTreeAndIdentifyRootNode_ShouldReturnNullForEmptyPairsList() {
        Node rootNode = HBSUtils.buildTreeAndIdentifyRootNode(new ArrayList<Pair>());
        assertNull(rootNode);
    }

    @Test
    public void buildTreeAndIdentifyRootNode_ShouldReturnRootNodeAndChildren() {
        Pair pair1 = new Pair("James", "Tony");
        List<Pair> pairList = new ArrayList<>();
        pairList.add(pair1);

        String expectedTreeStructure = "Node{name='James', children=[Node{name='Tony', children=[]}]}";

        Node rootNode = HBSUtils.buildTreeAndIdentifyRootNode(pairList);

        //Check the Root node is the correct Reviewer
        assertEquals(rootNode.getName(), pair1.getReviewer());

        //Check Right no. of childrens (reviewees)
        assertEquals(rootNode.getChildren().size(), 1);

        Node child1 = rootNode.getChildren().get(0);
        //Reviewee is not a reviewer for some one else(i.e., no children)
        assertTrue(child1.getChildren().isEmpty());

        //Check the Reviewee node.
        assertEquals(child1.getName(), pair1.getReviewee());
        assertEquals(expectedTreeStructure, rootNode.toString());
    }

    @Test
    public void buildTreeAndIdentifyRootNode_ShouldReturnRoooNodeAndTwoChildren() {
        Pair pair1 = new Pair("James", "Tony");
        Pair pair2 = new Pair("Tony", "Jyo");
        Pair pair3 = new Pair("James", "Andy");
        String expectedTreeStructure = "Node{name='James', children=[Node{name='Tony', " +
                "children=[Node{name='Jyo', children=[]}]}, Node{name='Andy', children=[]}]}";

        List<Pair> pairList = new ArrayList<>();
        pairList.add(pair1);
        pairList.add(pair2);
        pairList.add(pair3);

        Node rootNode = HBSUtils.buildTreeAndIdentifyRootNode(pairList);

        //Check the reviewer is - James
        assertEquals(rootNode.getName(), pair1.getReviewer());

        //Check the childrens size is 2 (Tony, Andy)
        assertEquals(rootNode.getChildren().size(), 2);

        assertEquals(expectedTreeStructure, rootNode.toString());
    }
}


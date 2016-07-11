package com.hbs.app;

/**
 * HBS : Hierarchical Buddy System
 * manages the relationship details of reviewer-reviewee
 * Created by jyo on 07/07/2016.
 */
public class HBSApp {
    public static void main(String[] args) throws Exception {
        if ((args == null) || (args.length != 1)) {
            throw new Exception("Invalid arguments: Please pass the <<reviewers-and-reviewees.txt>> file!");
        }
        HBSUtils.printHierarchicalView(args[0]);
    }

}
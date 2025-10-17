package com.example.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            String csvPath = "data/ratings.csv"; // Use relative path
            if (args.length > 0) {
                csvPath = args[0];
            }

            System.out.println("=== Movie Recommendation System ===");
            System.out.println("Data file: " + csvPath);
            System.out.println();

            // Load data model
            DataModel model = new FileDataModel(new File(csvPath));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            int neighborhoodSize = 2;
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(neighborhoodSize, similarity, model);
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Test multiple users to get better results
            long[] testUsers = {1L, 2L, 3L, 4L, 5L};
            int howMany = 3;

            for (long userID : testUsers) {
                System.out.println("=== Recommendations for User " + userID + " ===");
                List<RecommendedItem> recommendations = recommender.recommend(userID, howMany);

                if (recommendations.isEmpty()) {
                    System.out.println("No new recommendations found.");
                    System.out.println("(User may have already rated all available items)");
                } else {
                    for (RecommendedItem item : recommendations) {
                        System.out.printf("Item ID: %d | Recommendation Strength: %.4f%n",
                                item.getItemID(), item.getValue());
                    }
                }

                // Show estimated preferences for some items
                System.out.println("Estimated preferences:");
                long[] itemsToCheck = {101L, 102L, 103L, 104L, 105L};
                for (long itemID : itemsToCheck) {
                    try {
                        if (!Float.isNaN(recommender.estimatePreference(userID, itemID))) {
                            float pref = recommender.estimatePreference(userID, itemID);
                            System.out.printf("  Item %d: %.4f%n", itemID, pref);
                        }
                    } catch (TasteException te) {
                        // Skip items that can't be estimated
                    }
                }
                System.out.println();
            }

            System.out.println("=== Analysis Complete ===");

        } catch (IOException ioe) {
            System.err.println("Error: " + ioe.getMessage());
        } catch (TasteException te) {
            System.err.println("Mahout Taste error: " + te.getMessage());
        }
    }
}
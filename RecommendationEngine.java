import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.UserSimilarity;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.File;
import java.util.List;

public class RecommendationEngine {

    public static void main(String[] args) throws Exception {
        // Load the user-item ratings data model from a CSV file
        File file = new File("ratings.csv");  // Path to the ratings.csv file
        DataModel model = new FileDataModel(file);

        // Compute user similarity using Tanimoto coefficient (similarity metric)
        UserSimilarity similarity = new TanimotoCoefficientSimilarity(model);

        // Create a recommender based on user similarity
        Recommender recommender = new GenericUserBasedRecommender(model, similarity);

        // Recommend 3 items for user 1
        List<RecommendedItem> recommendations = recommender.recommend(1, 3);

        // Print the recommendations
        System.out.println("Recommendations for user 1:");
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Item ID: " + recommendation.getItemID() + ", Predicted Rating: " + recommendation.getValue());
        }
    }
}

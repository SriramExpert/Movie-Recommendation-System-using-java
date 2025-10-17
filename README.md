# Movie Recommendation System

A collaborative filtering-based movie recommendation system built using Apache Mahout. This system provides personalized movie recommendations based on user ratings using user-based collaborative filtering.

---

## 📁 Project Structure
```bash
Task3/
│
├── pom.xml # Maven configuration file
├── output.txt # Program output (generated)
├── data/
│   └── ratings.csv # Sample rating data
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── recommender/
                        └── Main.java # Main application

```
## 🚀 Features

- **User-Based Collaborative Filtering**: Recommends items based on similar users' preferences
- **Pearson Correlation Similarity**: Measures user similarity using Pearson correlation coefficient
- **Personalized Recommendations**: Generates top-N recommendations for each user
- **Preference Estimation**: Estimates user ratings for unrated items
- **Multiple User Support**: Analyzes and provides recommendations for all users in the dataset

## 🛠️ Technologies Used

- **Java 8**
- **Apache Mahout 0.9** - Machine learning library for collaborative filtering
- **Maven** - Dependency management and build tool
- **SLF4J** - Simple logging facade

## 📊 Dataset

The system uses a sample movie rating dataset with the following format:
```csv
userID,itemID,rating
1,101,5.0
1,102,3.0
1,103,2.5
2,101,2.0
2,102,2.5
2,103,5.0
...
```
**Dataset Details:**
- 5 users (User IDs: 1, 2, 3, 4, 5)
- 5 movies (Item IDs: 101, 102, 103, 104, 105)
- Ratings scale: 1.0 to 5.0

## ⚙️ Installation & Setup

### Prerequisites
- Java 8 or higher
- Maven 3.6 or higher

### Steps to Run

1. **Clone or download the project**
   ```bash
   # Navigate to project directory
   cd Task3
   ```


2. **Compile and run the application**
   ```bash
   # Method 1: Run with console output
    mvn compile exec:java

    # Method 2: Run quietly and save output to file
    mvn -q compile exec:java > output.txt
   ```
   
3. **Check Results**
   ```bash
   # View the generated output
    cat output.txt
    # Or in PowerShell:
    Get-Content output.txt
   ```

   ## 🎯 How It Works

### Algorithm
1. **Data Loading**: Reads user-item ratings from CSV file
2. **Similarity Calculation**: Computes Pearson correlation between users
3. **Neighborhood Formation**: Finds nearest N similar users (N=2 in this implementation)
4. **Recommendation Generation**: Predicts ratings for unrated items and recommends top items

### Key Components
- **FileDataModel**: Loads and manages rating data
- **PearsonCorrelationSimilarity**: Calculates user similarity
- **NearestNUserNeighborhood**: Finds similar users
- **GenericUserBasedRecommender**: Generates recommendations

## 📈 Output Format

The program generates recommendations for all users in this format:
```bash
=== Movie Recommendation System ===
Data file: data/ratings.csv

=== Recommendations for User 1 ===
No new recommendations found.
(User may have already rated all available items)
Estimated preferences:
Item 101: 5.0000
Item 102: 3.0000
Item 103: 2.5000
Item 104: 3.5000
Item 105: 3.5000

=== Recommendations for User 2 ===
Item ID: 104 | Recommendation Strength: 4.2500
Item ID: 105 | Recommendation Strength: 4.2500
Estimated preferences:
Item 101: 2.0000
Item 102: 2.5000
Item 103: 5.0000
Item 104: 4.2500
Item 105: 4.2500
...

```
## 🔧 Configuration

You can modify these parameters in `Main.java`:

| Parameter | Default | Description |
|-----------|---------|-------------|
| `neighborhoodSize` | 2 | Number of similar users to consider |
| `howMany` | 3 | Number of recommendations to generate |
| `testUsers` | [1,2,3,4,5] | User IDs to analyze |
| `csvPath` | data/ratings.csv | Path to rating data file |

## 📝 Customization

### Using Your Own Data
1. Prepare your CSV file with format: `userID,itemID,rating`
2. Update the file path in `Main.java`
 ```bash
  String csvPath = "path/to/your/data.csv";
   ```
   
3. Adjust user IDs in the `testUsers` array


## 📝 Algorithm Tuning

- **Increase neighborhoodSize** for more diverse recommendations
- **Modify howMany** to change number of recommendations
- **Try different similarity measures** (e.g., EuclideanDistanceSimilarity)

## 🐛 Troubleshooting

### Common Issues

**FileNotFoundException**
- Ensure `ratings.csv` exists in the `data` folder
- Check file path in `Main.java`

**No recommendations found**
- This is normal for small datasets
- Users may have rated all available items

**Dependency download issues**
- Run `mvn clean compile` to refresh dependencies
- Check internet connection

**File access errors**
- Close any open editors viewing `output.txt`
- Use `mvn -q compile exec:java > new_output.txt` with different filename

## 📚 Learning Resources

- Apache Mahout Documentation
- Collaborative Filtering Explained
- Maven Getting Started Guide

## 👨‍💻 Developer

- **Sriram** - Initial development

## 📄 License

This project is for educational purposes as part of an internship program.


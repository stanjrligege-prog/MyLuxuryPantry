package com.rf402503222.myluxurypantry.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.rf402503222.myluxurypantry.models.Recipe;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.rf402503222.myluxurypantry.models.Ingredient;

@Database(entities = {Ingredient.class, Recipe.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract IngredientDao ingredientDao();

    public abstract RecipeDao recipeDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "pantry_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            new Thread(() -> {
                                seedRecipes(instance.recipeDao());
                            }).start();
                        }
                    })
                    .build();
        }
        return instance;
    }

    private static void seedRecipes(RecipeDao dao) {
        if (dao.getCount() > 0) return;

        // Recipe 1
        dao.insert(new Recipe("Tomato Pasta",
                "pasta, tomato, garlic, olive oil",
                "1. Boil pasta.\n2. Sauté garlic in olive oil.\n3. Add tomatoes and simmer.\n4. Mix with pasta."));

        // Recipe 2
        dao.insert(new Recipe("Garlic Bread",
                "bread, garlic, butter",
                "1. Mix crushed garlic with soft butter.\n2. Spread on sliced bread.\n3. Bake at 200C for 10 mins."));

        // Recipe 3
        dao.insert(new Recipe("Veggie Omelette",
                "eggs, onion, pepper, cheese",
                "1. Whisk eggs.\n2. Chop onions and peppers.\n3. Pour eggs over veggies, add cheese, and fold."));

        // Recipe 4
        dao.insert(new Recipe("Grilled Cheese",
                "bread, cheese, butter",
                "1. Butter the outside of the bread.\n2. Put cheese between the slices.\n3. Grill in a pan until golden brown."));

        // Recipe 5
        dao.insert(new Recipe("Chicken Stir-fry",
                "chicken, rice, soy sauce, broccoli",
                "1. Cook rice.\n2. Stir-fry chicken until cooked.\n3. Add broccoli and soy sauce, cook until tender.\n4. Serve over rice."));

        // Recipe 6
        dao.insert(new Recipe("Pancakes",
                "flour, milk, eggs, sugar",
                "1. Whisk all ingredients into a batter.\n2. Pour small amounts onto a hot pan.\n3. Flip when bubbles appear."));

        // Recipe 7
        dao.insert(new Recipe("Scrambled Eggs",
                "eggs, butter, salt",
                "1. Whisk eggs with salt.\n2. Melt butter in a pan.\n3. Pour in eggs and stir gently until cooked."));

        // Recipe 8
        dao.insert(new Recipe("Tuna Sandwich",
                "bread, tuna, mayonnaise",
                "1. Mix tuna with mayonnaise.\n2. Spread on bread.\n3. Top with another slice of bread."));

        // Recipe 9
        dao.insert(new Recipe("Bean Salad",
                "beans, onion, tomato, olive oil",
                "1. Chop onion and tomato.\n2. Mix with beans.\n3. Drizzle with olive oil and toss."));

        // Recipe 10
        dao.insert(new Recipe("Cheese Quesadilla",
                "tortilla, cheese, butter",
                "1. Put cheese on half the tortilla.\n2. Fold over.\n3. Fry in butter until crispy."));

        // Recipe 11
        dao.insert(new Recipe("Fruit Smoothie",
                "banana, milk, yogurt",
                "1. Slice banana.\n2. Blend with milk and yogurt until smooth."));

        // Recipe 12
        dao.insert(new Recipe("Baked Potato",
                "potato, butter, cheese",
                "1. Bake potato at 200C for 45 mins.\n2. Slice open and add butter and cheese."));

        // Recipe 13
        dao.insert(new Recipe("Chicken Salad",
                "chicken, lettuce, cucumber, mayonnaise",
                "1. Cook and slice chicken.\n2. Chop lettuce and cucumber.\n3. Mix all with mayonnaise."));

        // Recipe 14
        dao.insert(new Recipe("Pasta with Garlic",
                "pasta, garlic, olive oil, cheese",
                "1. Boil pasta.\n2. Sauté garlic in olive oil.\n3. Toss pasta in the oil and top with cheese."));

        // Recipe 15
        dao.insert(new Recipe("Egg Fried Rice",
                "rice, eggs, soy sauce, onion",
                "1. Cook rice (or use leftover rice).\n2. Fry chopped onion, add eggs and scramble.\n3. Add rice and soy sauce, fry for 3 mins."));
    }
}
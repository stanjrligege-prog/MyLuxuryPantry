package com.rf402503222.myluxurypantry.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rf402503222.myluxurypantry.R;
import com.rf402503222.myluxurypantry.models.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder> {

    private List<Ingredient> ingredients = new ArrayList<>();
    private OnItemClickListener listener;

    // Interface so the Activity can handle clicks (for editing/deleting later)
    public interface OnItemClickListener {
        void onItemClick(Ingredient ingredient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // This is how we update the list from the database
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged(); // Tells the RecyclerView to redraw
    }

    @NonNull
    @Override
    public IngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new IngredientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientHolder holder, int position) {
        Ingredient currentIngredient = ingredients.get(position);
        holder.tvName.setText(currentIngredient.getName());

        // Format the quantity and unit (e.g., "2.0 kg")
        String qtyString = currentIngredient.getQuantity() + " " + currentIngredient.getUnit();
        holder.tvQuantity.setText(qtyString);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvQuantity;

        public IngredientHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvIngredientName);
            tvQuantity = itemView.findViewById(R.id.tvIngredientQuantity);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(ingredients.get(position));
                }
            });
        }
    }
}
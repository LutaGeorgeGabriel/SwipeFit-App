package eu.swipefit.app.favorites;
/**
 * FILE DESCRIPTION
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import eu.swipefit.app.Product;
import eu.swipefit.app.R;

/** ADD COMMENTS */
public class ProductAdapter extends ArrayAdapter<Product>{

    public ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context,0,products);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.favorite_item,parent,false);
        }

        Product currentProduct = getItem(position);

        ImageView imageView = view.findViewById(R.id.product_image);
        Picasso.with(getContext()).load(currentProduct.getImageUrl()).into(imageView);

        TextView textView = view.findViewById(R.id.product_name);
        textView.setText(currentProduct.getName());

        return view;
    }
}

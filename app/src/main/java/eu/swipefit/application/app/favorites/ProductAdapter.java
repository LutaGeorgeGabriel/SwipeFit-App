package eu.swipefit.application.app.favorites;
/**
 * FILE DESCRIPTION
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import java.util.List;

import eu.swipefit.app.R;
import eu.swipefit.application.Product;

/** ADD COMMENTS */
public class ProductAdapter extends ArrayAdapter<Product>{

    public ProductAdapter(Context context, int resource, List<Product> products) {
        super(context,0,products);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.favorite_item,parent,false);
        }

        final Product currentProduct = getItem(position);

        ImageView imageView = view.findViewById(R.id.product_image);
        //Picasso.with(getContext()).load(currentProduct.getUrl()).into(imageView);
        Picasso.with(getContext()).load(currentProduct.getUrl()).resize(180,250).into(imageView);

        TextView name = view.findViewById(R.id.nameTxt);
        name.setText(currentProduct.getName());

        TextView retailer = view.findViewById(R.id.retailerTxt);
        retailer.setText(currentProduct.getRetailer());

        TextView price = view.findViewById(R.id.priceTxt);
        price.setText(currentProduct.getPrice() + " RON ");

        ImageView buy = view.findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(currentProduct.getSite()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        return view;
    }
}

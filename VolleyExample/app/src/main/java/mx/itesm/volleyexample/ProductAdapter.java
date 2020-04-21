package mx.itesm.volleyexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.recyclerview.widget.*;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> product_list;
    private Context context;
    private RecyclerView myrecyclerview;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textview_name;
        public TextView textview_brand;
        public TextView textview_price;
        public ImageView imageview_image;


        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            textview_name = (TextView) view.findViewById(R.id.name);
            textview_brand = (TextView) view.findViewById(R.id.brand);
            textview_price = (TextView) view.findViewById(R.id.price);
            imageview_image = (ImageView) view.findViewById(R.id.image);
        }
    }

    public void add(int position, Product product) {
        product_list.add(position, product);
        notifyItemInserted(position);
    }

    public void remove(int posicion) {
        product_list.remove(posicion);
        notifyItemRemoved(posicion);
    }

    public ProductAdapter(List<Product> data, Context context, RecyclerView recyclerView) {
        product_list = data;
        this.context = context;
        myrecyclerview = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View vista =
                inflater.inflate(R.layout.single_row, parent, false);

        ViewHolder view_holder = new ViewHolder(vista);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder manager, final int position) {

        final Product product = product_list.get(position);
        manager.textview_name.setText("Name: " + product.getName());
        manager.textview_brand.setText("Brand: " + product.getBrand());
        manager.textview_price.setText("Price: " + product.getPrice());
        Picasso.with(context).load("http://192.168.100.51:80"+product.getUrl_image()).placeholder(R.mipmap.ic_launcher_round).into(manager.imageview_image);

        manager.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pick a choice");
                builder.setMessage("Update or remove?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        goToUpdateActivity(product.getId());

                    }
                });
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        product_list.remove(position);
                        myrecyclerview.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, product_list.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    private void goToUpdateActivity(String productId)
    {
        Intent goToUpdate = new Intent(context.getApplicationContext(), UpdateProduct.class);
        context.startActivity(goToUpdate);
    }

    @Override
    public int getItemCount() {
        return product_list.size();
    }

}
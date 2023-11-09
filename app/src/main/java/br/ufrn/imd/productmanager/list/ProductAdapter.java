package br.ufrn.imd.productmanager.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrn.imd.productmanager.R;
import br.ufrn.imd.productmanager.util.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> productsList;

    public ProductAdapter(Context context, int textViewResourceId, ArrayList<Product> productsList) {
        super(context, textViewResourceId, productsList);
        this.productsList = productsList;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_product_item, null);
        }

        Product i = productsList.get(position);

        if (i != null) {
            TextView productName = (TextView) v.findViewById(R.id.ListItem_ProductName);
            TextView productCode = (TextView) v.findViewById(R.id.ListItem_ProductCode);

            if (productName != null) {
                productName.setText(i.getNameProduct());
            }
            if (productCode != null) {
                productCode.setText(i.getCodeProduct());
            }

        }
        return v;

    }
}

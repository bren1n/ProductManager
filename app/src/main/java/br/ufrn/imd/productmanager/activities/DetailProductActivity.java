package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.ufrn.imd.productmanager.R;
import br.ufrn.imd.productmanager.util.Product;

public class DetailProductActivity extends AppCompatActivity {
    private Product product;
    TextView name;
    TextView code;
    TextView desc;
    TextView quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Intent intent = this.getIntent();
        this.name = (TextView) findViewById(R.id.ProductDetail_Name);
        this.code = (TextView) findViewById(R.id.ProductDetail_Code);
        this.desc = (TextView) findViewById(R.id.ProductDetail_Desc);
        this.quantity = (TextView) findViewById(R.id.ProductDetail_Quantity);

        if (intent != null) {
            product = (Product) intent.getSerializableExtra("Product_Entity");
            this.name.setText(product.getNameProduct());
            this.code.setText(product.getCodeProduct());
            this.desc.setText(product.getDescProduct());
            this.quantity.setText(String.valueOf(product.getQuantity()));
        }
    }
}
package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.productmanager.R;
import br.ufrn.imd.productmanager.list.ProductAdapter;
import br.ufrn.imd.productmanager.util.Product;
import br.ufrn.imd.productmanager.database.ProductDbHelper;
import br.ufrn.imd.productmanager.database.ProcuctContract.ProductData;


public class ListaActivity extends AppCompatActivity {

    ArrayList<Product> productList;
    ListView productListView;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        returnButton = (Button) findViewById(R.id.List_buttonReturn);
        productListView = (ListView) findViewById(R.id.List_ListView);
        productList = new ArrayList<>();

        getDatabaseProducts();

        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_product_item, productList);
        productListView.setAdapter(adapter);
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaActivity.this, DetailProductActivity.class);
                intent.putExtra("Product_Entity", productList.get(i));
                startActivity(intent);

            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getDatabaseProducts(){
        ProductDbHelper dbHelper = new ProductDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String stringQuery = "SELECT * from " + ProductData.TABLE_NAME;

        Cursor query = db.rawQuery(stringQuery,null);

        if(query.moveToFirst()){

            while (query != null){
                String tempProductCode = query.getString(1);
                String tempProductName = query.getString(2);
                String tempProductDesc = query.getString(3);
                int tempProductQuantity = Integer.parseInt(query.getString(4));

                Product tempProduct = new Product(tempProductCode, tempProductName, tempProductDesc, tempProductQuantity);
                this.productList.add(tempProduct);

                if(!query.moveToNext()){
                    break;
                }
            }
        }
    }
}
package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrn.imd.productmanager.R;
import br.ufrn.imd.productmanager.database.ProductDbHelper;
import br.ufrn.imd.productmanager.database.ProcuctContract.ProductData;
import br.ufrn.imd.productmanager.util.Product;

public class EdicaoActivity extends AppCompatActivity {
    Button editBtn;
    EditText codeInput;
    EditText nameInput;
    EditText descriptionInput;
    EditText quantityInput;
    Product tempProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);

        this.editBtn = (Button) findViewById(R.id.editButton);
        this.codeInput = (EditText) findViewById(R.id.code);
        this.nameInput = (EditText) findViewById(R.id.name);
        this.descriptionInput = (EditText) findViewById(R.id.description);
        this.quantityInput = (EditText) findViewById(R.id.quantity);

        this.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updateProductData()) {
                    finish();
                }
            }
        });

        this.codeInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if(searchProductData(false)){
                        return true;
                    }
                }
                return false;
            }
        });


    }


    private boolean searchProductData(boolean buttonIsPressed){
        if (codeInput.getText().toString().isEmpty()) {
            Toast.makeText(EdicaoActivity.this, "Preencha o campo com o código do produto.", Toast.LENGTH_LONG).show();
            return false;
        }
        ProductDbHelper dbHelper = new ProductDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String stringQuery = "SELECT * from " + ProductData.TABLE_NAME + " WHERE " +
                ProductData.COLUMN_NAME_CODE + "=" + codeInput.getText().toString();

        Cursor query = db.rawQuery(stringQuery,null);

        if(query.moveToFirst()){

            while (query != null){
                String tempProductCode = query.getString(1);
                String tempProductName = query.getString(2);
                String tempProductDesc = query.getString(3);
                int tempProductQuantity = Integer.parseInt(query.getString(4));

                tempProduct = new Product(tempProductCode, tempProductName, tempProductDesc, tempProductQuantity);

                if(!query.moveToNext()){
                    break;
                }
            }
        } else {
            Toast.makeText(this, "Código inválido. Produto não encontrado.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!buttonIsPressed) {
            nameInput.setText(tempProduct.getNameProduct());
            descriptionInput.setText(tempProduct.getDescProduct());
            int numericValue = Integer.parseInt(tempProduct.getQuantityString());
            quantityInput.setText(String.valueOf(numericValue));
        }

        return true;
    }
    private boolean updateProductData() {
        if (!searchProductData(true)) {
            Toast.makeText(this, "Código inválido. Produto não encontrado.", Toast.LENGTH_LONG).show();
            return false;
        }

        String name = nameInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String quantity = quantityInput.getText().toString();

        ProductDbHelper dbHelper = new ProductDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductData.COLUMN_NAME_CODE, Integer.parseInt(codeInput.getText().toString()));
        values.put(ProductData.COLUMN_NAME_NAME, name.isEmpty() ? tempProduct.getNameProduct() : name);
        values.put(ProductData.COLUMN_NAME_DESCRIPTION, description.isEmpty() ? tempProduct.getDescProduct() : description);
        values.put(ProductData.COLUMN_NAME_QUANTITY, quantity.isEmpty() ? tempProduct.getQuantity() : Integer.parseInt(quantity));

        String selection = ProductData.COLUMN_NAME_CODE + "=?";
        String[] selectionArgs = { codeInput.getText().toString() };

        int count = db.update(
            ProductData.TABLE_NAME,
            values,
            selection,
            selectionArgs
        );

        db.close();

        if (count > 0) {
            Toast.makeText(this, "Produto atualizado com sucesso", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "Erro ao atualizar produto", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
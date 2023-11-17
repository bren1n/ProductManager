package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrn.imd.productmanager.R;
import br.ufrn.imd.productmanager.database.ProcuctContract.ProductData;
import br.ufrn.imd.productmanager.database.ProductDbHelper;

public class DeleteActivity extends AppCompatActivity {

    private Button deleteButton;
    private EditText productCodeEditText;
    private String productCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteButton = (Button) findViewById(R.id.Delete_DeleteButton);
        productCodeEditText = (EditText) findViewById(R.id.Delete_Code);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productCodeEditText.getText().toString().isEmpty()){
                    Toast.makeText(DeleteActivity.this, R.string.Delete_MissCode, Toast.LENGTH_LONG).show();
                    return;
                }

                productCode = productCodeEditText.getText().toString();

                if (deleteProductDatabase(productCode)) {
                    Toast.makeText(DeleteActivity.this, R.string.Delete_DeleteSucess, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(DeleteActivity.this, R.string.Delete_ProductNotFound, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean deleteProductDatabase(String productCode) {
        ProductDbHelper dbHelper = new ProductDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int quantityProductsDeleted = db.delete(ProductData.TABLE_NAME, ProductData.COLUMN_NAME_CODE + "=" + productCode, null);
        db.close();
        if (quantityProductsDeleted != 0) {
            return true;
        } else {
            return false;
        }
    }
}
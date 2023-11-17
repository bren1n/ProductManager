package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrn.imd.productmanager.R;
import br.ufrn.imd.productmanager.database.ProductDbHelper;
import br.ufrn.imd.productmanager.database.ProcuctContract.ProductData;

public class CadastroActivity extends AppCompatActivity {
    Button createBtn;
    EditText codeInput;
    EditText nameInput;
    EditText descriptionInput;
    EditText quantityInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.createBtn = (Button) findViewById(R.id.createButton);
        this.codeInput = (EditText) findViewById(R.id.code);
        this.nameInput = (EditText) findViewById(R.id.name);
        this.descriptionInput = (EditText) findViewById(R.id.description);
        this.quantityInput = (EditText) findViewById(R.id.quantity);

        this.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(createProduct()){
                    finish();
                }
            }
        });
    }

    private boolean createProduct() {
        ProductDbHelper dbHelper = new ProductDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String code = codeInput.getText().toString();
        String name = nameInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String quantity = quantityInput.getText().toString();

        if (!code.isEmpty() && !name.isEmpty() && !description.isEmpty() && !quantity.isEmpty()) {
            ContentValues register = new ContentValues();
            register.put("code", Integer.parseInt(code));
            register.put("name", name);
            register.put("description", description);
            register.put("quantity", quantity);

            db.insert(ProductData.TABLE_NAME, null, register);
            db.close();

            Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_LONG).show();

            this.codeInput.setText("");
            this.nameInput.setText("");
            this.descriptionInput.setText("");
            this.quantityInput.setText("");
            return true;
        } else {
            Toast.makeText(this, "Existem campos vazios", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
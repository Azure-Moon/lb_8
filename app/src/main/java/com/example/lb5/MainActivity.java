package com.example.lb5;

import static com.example.lb5.R.menu.conmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    ListView infoList;
    DatabaseHelper databaseHelper;
    DatabaseAdapter databaseAdapter;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter, listadapter, searchadapter, spindapter1;
    private MenuItem menuUI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner myspinner = findViewById(R.id.itemlist);
        ArrayAdapter<?> spindapter =
                ArrayAdapter.createFromResource(this, R.array.datalist,
                        android.R.layout.simple_spinner_item);
        spindapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner myspinner1 = findViewById(R.id.add_product_category);
        ArrayAdapter<?> spindapter1 =
                ArrayAdapter.createFromResource(this, R.array.categorylist,
                        android.R.layout.simple_spinner_item);
        spindapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner myspinner2 = findViewById(R.id.grouplist);
        ArrayAdapter<?> spindapte2 =
                ArrayAdapter.createFromResource(this, R.array.categorylist,
                        android.R.layout.simple_spinner_item);
        spindapte2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        infoList = findViewById(R.id.listv);
        databaseAdapter = new DatabaseAdapter(this);
        databaseHelper = new DatabaseHelper(this);

        db = databaseHelper.getReadableDatabase();


        registerForContextMenu(infoList);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ListViewUpdate();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.conmenu, menu);
        MenuItem MI = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MI.getActionView();
        searchView.setQueryHint("Напишите здесь для поиска");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.groupby:
                filters_dialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(conmenu, menu);
        Spinner myspinner = findViewById(R.id.itemlist);
        if (myspinner.getSelectedItem().equals("Компании")) {
            MenuItem item3 = menu.findItem(R.id.action_search);
            item3.setVisible(false);
        }
        else if (myspinner.getSelectedItem().equals("Основатели")) {
            MenuItem item3 = menu.findItem(R.id.action_search);
            item3.setVisible(false);
        }
        else if (myspinner.getSelectedItem().equals("Продукты")) {
            MenuItem item3 = menu.findItem(R.id.action_search);
            item3.setVisible(false);
        }
        else if (myspinner.getSelectedItem().equals("Средняя стоимость продуктов")) {
            MenuItem item = menu.findItem(R.id.groupby);
            MenuItem item1 = menu.findItem(R.id.del);
            MenuItem item2 = menu.findItem(R.id.upd);
            MenuItem item3 = menu.findItem(R.id.action_search);
            item.setVisible(false);
            item1.setVisible(false);
            item2.setVisible(false);
            item3.setVisible(false);
        }
        if (myspinner.getSelectedItem().equals("Общая стоимость продуктов")) {
            MenuItem item = menu.findItem(R.id.groupby);
            MenuItem item1 = menu.findItem(R.id.del);
            MenuItem item2 = menu.findItem(R.id.upd);
            MenuItem item3 = menu.findItem(R.id.action_search);
            item.setVisible(false);
            item1.setVisible(false);
            item2.setVisible(false);
            item3.setVisible(false);
        }


    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Spinner myspinner = findViewById(R.id.itemlist);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Object allitems = infoList.getItemAtPosition(info.position);
        Pattern intPattern = Pattern.compile("(\\-?\\d+)");
        Matcher matcher = intPattern.matcher(allitems.toString());
        int id = 0;
        while (matcher.find()){
            id = Integer.parseInt(matcher.group());
            break;
        }
        switch (item.getItemId()) {
            case R.id.upd:
                upd_dialog(String.valueOf(id));
                ListViewUpdate();
                return true;
            case R.id.del:
                if (myspinner.getSelectedItem().equals("Компании")) {
                    databaseAdapter.openDBAdapter();
                    databaseAdapter.delCorps(id);
                    databaseAdapter.closeDBAdapter();
                } else if (myspinner.getSelectedItem().equals("Основатели")) {
                    databaseAdapter.openDBAdapter();
                    databaseAdapter.delFounders(id);
                    databaseAdapter.closeDBAdapter();
                } else if (myspinner.getSelectedItem().equals("Продукты")) {
                    databaseAdapter.openDBAdapter();
                    databaseAdapter.delProducts(id);
                    databaseAdapter.closeDBAdapter();
                }
                ListViewUpdate();
                return true;
            case R.id.groupby:
                filters_dialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ListViewUpdate();
    }

    private void ListViewUpdate() {
        list.clear();
        Spinner myspinner = findViewById(R.id.itemlist);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        infoList.setAdapter(adapter);
        databaseAdapter.openDBAdapter();

        if(myspinner.getSelectedItem().equals("Компании")) {
            ListCorps();
        }
        else if (myspinner.getSelectedItem().equals("Основатели")){
            ListFounders();
        }

        else if(myspinner.getSelectedItem().equals("Продукты")){
            ListProducts();
        }
        else if (myspinner.getSelectedItem().equals("Общая стоимость продуктов")) {
            FullCostView();

        }
        else if (myspinner.getSelectedItem().equals("Средняя стоимость продуктов")) {
            AvgCostView();

        }
        databaseAdapter.closeDBAdapter();
    }

   public void ListCorps(){
       databaseAdapter.openDBAdapter();
       List<CorpsInfo> companies = databaseAdapter.getCorps();
       for (CorpsInfo company : companies) {
           list.add("ID - " + company.getId() + "\nКомпания - " + company.getCorpName());
       }
       adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
       infoList.setAdapter(adapter);
       databaseAdapter.closeDBAdapter();
    }

    public void ListFounders(){
        databaseAdapter.openDBAdapter();
        List<FoundersInfo> founders = databaseAdapter.getFounders();
        for (FoundersInfo founders1: founders) {
            list.add("ID - " + founders1.getId() + "\nОснователь - " + founders1.getFounderName());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        infoList.setAdapter(adapter);
        databaseAdapter.closeDBAdapter();
    }

    public void ListProducts(){
        databaseAdapter.openDBAdapter();
        List<ProductsInfo> product = databaseAdapter.getProducts();
        for (ProductsInfo products : product) {
            list.add("ID - " + products.getId() + "\nПродукт: " + products.getProductName() + "\nКатегория - " + products.getCategory() + "\nЦена - " + products.getPrice() + " рублей");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        infoList.setAdapter(adapter);
        databaseAdapter.closeDBAdapter();
    }


  public void onItemSelected(View view){
        Spinner myspinner = findViewById(R.id.itemlist);

        if (myspinner.getSelectedItem().equals("Компании")) {
            ListViewUpdate();
            CorpsDialog();
        }
        else if (myspinner.getSelectedItem().equals("Основатели")) {
            ListViewUpdate();
            FoundersDialog();
        }
        else if (myspinner.getSelectedItem().equals("Продукты")) {
            ListViewUpdate();
            ProductsDialog();
        }
        else if (myspinner.getSelectedItem().equals("Общая стоимость продуктов")) {
            ListViewUpdate();
            FullCostView();
        }
        else if (myspinner.getSelectedItem().equals("Средняя стоимость продуктов")) {
            ListViewUpdate();
            AvgCostView();
        }
    }

    private void AvgCostView() {
        Cursor cursor = databaseAdapter.getAvgCost();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "База данных пуста!", Toast.LENGTH_SHORT).show();
            list.clear();
            infoList.setAdapter(null);
        }
        else{
            while(cursor.moveToNext()){
                if (cursor.getString(0) == null || cursor.getString(0) == "0")
                {
                    list.add("Продуктов не обнаружено");
                    break;
                }
                list.add("Средняя стоимость продуктов - " + cursor.getString(0) + " рублей");
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            infoList.setAdapter(adapter);
        }
    }

    private void FullCostView() {
        Cursor cursor = databaseAdapter.getFullCost();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "База данных пуста!", Toast.LENGTH_SHORT).show();
            list.clear();
            infoList.setAdapter(null);
        }
        else{
            while(cursor.moveToNext()){
                if (cursor.getString(0) == null || cursor.getString(0) == "0")
                {
                    list.add("Продуктов не обнаружено");
                    break;
                }
                else
                list.add("Общая стоимость продуктов - " + cursor.getString(0) + " рублей");
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            infoList.setAdapter(adapter);
        }
    }

    private void CorpsDialog(){
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.add_corp, null);
        EditText corps_name = view.findViewById(R.id.add_corp_name);
        Button addbtn = view.findViewById(R.id.ceditbtn);
        ad.setView(view);
        AlertDialog alertDialog = ad.show();

        addbtn.setOnClickListener(v -> {
            databaseAdapter.openDBAdapter();
            CorpsInfo corp = new CorpsInfo();
            corp.SetCorpName(corps_name.getText().toString());
            databaseAdapter.addCorps(corp);
            alertDialog.dismiss();
            databaseAdapter.closeDBAdapter();
            ListViewUpdate();
        });

       EditText ed1 = view.findViewById(R.id.add_corp_name);

       Button cls = view.findViewById(R.id.button7);
       cls.setOnClickListener(vie -> {
           ed1.setText("");
       });

    }

    private void FoundersDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.add_founder, null);
        EditText founder_name = view.findViewById(R.id.add_founder_name);
        Button insertBtn = view.findViewById(R.id.feditbtn);
        ad.setView(view);
        AlertDialog alertDialog = ad.show();

        insertBtn.setOnClickListener(v -> {
            databaseAdapter.openDBAdapter();
            FoundersInfo founder = new FoundersInfo();
            founder.SetFounderName(founder_name.getText().toString());
            databaseAdapter.addFounders(founder);
            alertDialog.dismiss();
            databaseAdapter.closeDBAdapter();
            ListViewUpdate();
        });

        EditText ed1 = view.findViewById(R.id.add_founder_name);

        Button cls = view.findViewById(R.id.button7);
        cls.setOnClickListener(vie -> {
            ed1.setText("");
        });
    }

    private void ProductsDialog(){
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.add_product, null);
        EditText products_name = view.findViewById(R.id.add_product_name);
        Spinner products_category = view.findViewById(R.id.add_product_category);
        EditText products_price = view.findViewById(R.id.add_product_price);
        Button addbtn = view.findViewById(R.id.peditbtn);
        ad.setView(view);
        AlertDialog alertDialog = ad.show();
        products_price.setTransformationMethod(null);

        addbtn.setOnClickListener(v -> {
            databaseAdapter.openDBAdapter();
            ProductsInfo product = new ProductsInfo();
            product.SetProductName(products_name.getText().toString());
            product.SetCategory(products_category.getSelectedItem().toString());
            product.SetPrice(products_price.getText().toString());
            databaseAdapter.addProducts(product);
            alertDialog.dismiss();
            databaseAdapter.closeDBAdapter();
            ListViewUpdate();

        }
        );

        EditText ed1 = view.findViewById(R.id.add_product_name);
        EditText ed3 = view.findViewById(R.id.add_product_price);

        Button cls = view.findViewById(R.id.button7);
        cls.setOnClickListener(vie -> {
            ed1.setText("");
            ed3.setText("");
        });
    }



    private void upd_dialog(String id) {
        Spinner myspinner = findViewById(R.id.itemlist);

         if(myspinner.getSelectedItem().equals("Компании")){
             databaseAdapter.openDBAdapter();
            CorpsInfo company = databaseAdapter.getCorp(Integer.parseInt(id));
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.edit_corp, null);
            EditText corpsname = view.findViewById(R.id.ed_corp_name);
            Button updateBtn = view.findViewById(R.id.ceditbtn);
            ad.setView(view);

            corpsname.setText(company.getCorpName());
            databaseAdapter.closeDBAdapter();
            AlertDialog alertDialog = ad.show();

            updateBtn.setOnClickListener(v -> {
                CorpsInfo c = new CorpsInfo();
                c.SetCorpName(corpsname.getText().toString());
                c.setId(Integer.parseInt(id));
                databaseAdapter.openDBAdapter();
                databaseAdapter.editCorps(c);
                databaseAdapter.closeDBAdapter();
                alertDialog.dismiss();
                ListViewUpdate();
            });

             EditText ed1 = view.findViewById(R.id.ed_corp_name);

             Button cls = view.findViewById(R.id.button7);
             cls.setOnClickListener(vie -> {
                 ed1.setText("");
             });
        }
        else if (myspinner.getSelectedItem().equals("Основатели")) {
            databaseAdapter.openDBAdapter();
            FoundersInfo founders = databaseAdapter.getFounder(Integer.parseInt(id));
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.edit_founer, null);
            EditText foundersname = view.findViewById(R.id.edit_founder_name);
            Button updateBtn = view.findViewById(R.id.feditbtn);
            ad.setView(view);

            foundersname.setText(founders.getFounderName());
            databaseAdapter.closeDBAdapter();
            AlertDialog alertDialog = ad.show();

            updateBtn.setOnClickListener(v -> {
                FoundersInfo f = new FoundersInfo();
                f.SetFounderName(foundersname.getText().toString());
                f.setId(Integer.parseInt(id));
                databaseAdapter.openDBAdapter();
                databaseAdapter.editFounders(f);
                databaseAdapter.closeDBAdapter();
                alertDialog.dismiss();
                ListViewUpdate();
            });

             EditText ed1 = view.findViewById(R.id.edit_founder_name);

             Button cls = view.findViewById(R.id.button7);
             cls.setOnClickListener(vie -> {
                 ed1.setText("");
             });
        }
        else if(myspinner.getSelectedItem().equals("Продукты")){
            databaseAdapter.openDBAdapter();
            ProductsInfo product = databaseAdapter.getProduct(Integer.parseInt(id));
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.edit_product, null);
            EditText productsname = view.findViewById(R.id.edit_product_name);
            Spinner products_category = view.findViewById(R.id.edit_product_category);
            EditText products_price = view.findViewById(R.id.edit_product_price);
            Button updateBtn = view.findViewById(R.id.peditbtn);
            ad.setView(view);

            productsname.setText(product.getProductName());
            products_price.setText(product.getPrice());
            databaseAdapter.closeDBAdapter();
            AlertDialog alertDialog = ad.show();

            updateBtn.setOnClickListener(v -> {
                ProductsInfo p = new ProductsInfo();
                p.SetProductName(productsname.getText().toString());
                p.SetCategory(products_category.getSelectedItem().toString());
                p.SetPrice(products_price.getText().toString());
                p.setId(Integer.parseInt(id));
                databaseAdapter.openDBAdapter();
                databaseAdapter.editProducts(p);
                databaseAdapter.closeDBAdapter();
                alertDialog.dismiss();
                ListViewUpdate();
            });
             EditText ed1 = view.findViewById(R.id.edit_product_name);
             EditText ed2 = view.findViewById(R.id.edit_product_price);

             Button cls = view.findViewById(R.id.button7);
             cls.setOnClickListener(vie -> {
                 ed1.setText("");
                 ed2.setText("");
             });
        }
    }


    private void filters_dialog() {
        Spinner myspinner = findViewById(R.id.itemlist);
        if (myspinner.getSelectedItem().equals("Компании")) {
            ListViewUpdate();
            Toast.makeText(this, "Фильтры доступны в разделе 'Продукты'", Toast.LENGTH_SHORT).show();
        } else if (myspinner.getSelectedItem().equals("Основатели")) {
            ListViewUpdate();
            Toast.makeText(this, "Фильтры доступны в разделе 'Продукты'", Toast.LENGTH_SHORT).show();
        }
        else if (myspinner.getSelectedItem().equals("Продукты"))
        {
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.filter_listview, null);
            Spinner groupcategory = view.findViewById(R.id.grouplist);
            Button sortBtn = view.findViewById(R.id.sortbtn);
            Button groupBtn = view.findViewById(R.id.groupbycat);
            ad.setView(view);
            AlertDialog alertDialog = ad.show();

            groupBtn.setOnClickListener(v -> {
                list.clear();
                databaseAdapter.openDBAdapter();
                Cursor cursor = databaseAdapter.groupbycategory(groupcategory.getSelectedItem().toString());
                if (cursor.getCount() == 0){
                    Toast.makeText(this, "База данных пуста!", Toast.LENGTH_SHORT).show();
                    list.clear();
                    infoList.setAdapter(null);
                }
                else{
                    while (cursor.moveToNext()){
                        list.add("ID - " + cursor.getString(0) + "\nПродукт - " + cursor.getString(1)
                                + "\nКатегория - " + cursor.getString(2) + "\nЦена - " + cursor.getString(3));
                    }
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                    infoList.setAdapter(adapter);
                }


                alertDialog.dismiss();
                databaseAdapter.closeDBAdapter();

            });

            sortBtn.setOnClickListener(v -> {
                list.clear();
                databaseAdapter.openDBAdapter();
                Cursor cursor = databaseAdapter.sortbyname();
                if (cursor.getCount() == 0){
                    Toast.makeText(this, "База данных пуста!", Toast.LENGTH_SHORT).show();
                    list.clear();
                    infoList.setAdapter(null);
                }
                else{
                    while (cursor.moveToNext()){
                        list.add("ID - " + cursor.getString(0) + "\nПродукт - " + cursor.getString(1)
                                + "\nКатегория - " + cursor.getString(2) + "\nЦена - " + cursor.getString(3));
                    }
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                    infoList.setAdapter(adapter);
                }


                alertDialog.dismiss();
                databaseAdapter.closeDBAdapter();

            });
        }

    }


    public void onRefresh(View view)
    {
        ListViewUpdate();
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}
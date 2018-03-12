package com.example.valkyzone.myapplication;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.valkyzone.myapplication.adapter.ContactAdapter;
import com.example.valkyzone.myapplication.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> arrayContact;
    private ContactAdapter adapter;
    private EditText edtName;
    private EditText edtNumber;
    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;
    private Button btnAddContact;
    private ListView lvContact;
    private String a = "12356";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate " +a,Toast.LENGTH_LONG ).show();
        setWidget();
        arrayContact = new ArrayList<>();
        adapter = new ContactAdapter(this, R.layout.item_contact, arrayContact);
        lvContact.setAdapter(adapter);



        checkAndRequestPermissions();
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogConfirm(position);
//                Intent itent = new Intent(MainActivity.this, MainActivityA.class);
//                startActivity(itent);
            }
        });


    }

    public void setWidget() {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_phonenum);
        rbtnMale = (RadioButton) findViewById(R.id.rbtn_male);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female);
        btnAddContact = (Button) findViewById(R.id.btn_addcontact);
        lvContact = (ListView) findViewById(R.id.lv_contact);
    }

    /////
    public void addContact(View view) {
        if (view.getId() == R.id.btn_addcontact) {
            String name = edtName.getText().toString().trim();
            String number = edtNumber.getText().toString().trim();
            boolean isMale = true;
            if (rbtnMale.isChecked()) {
                isMale = true;
            } else {
                isMale = false;
            }
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
//                Toast.makeText(this, "Please input your Name and Phone number", Toast.LENGTH_SHORT).show();
            } else {
                Contact contact = new Contact(isMale, name, number);
                arrayContact.add(contact);

            }
            adapter.notifyDataSetChanged();

        }
    }


    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    public void showDialogConfirm(final int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnSendMessage = (Button) dialog.findViewById(R.id.btn_send_message);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall(position);
            }
        });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSendMesseage(position);
            }
        });
        dialog.show();

    }

    private void intentSendMesseage(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + arrayContact.get(position).getPhonenum()));
        startActivity(intent);
    }
    private void intentCall(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + arrayContact.get(position).getPhonenum()));
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
    }
}

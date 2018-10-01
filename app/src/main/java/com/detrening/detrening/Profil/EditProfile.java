package com.detrening.detrening.Profil;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.detrening.detrening.Authentication.Login;
import com.detrening.detrening.Home.Beranda;
import com.detrening.detrening.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class EditProfile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    String Storage_Path = "fotoprofile/";
    public static final String Database_Path = "DeTrening";
    EditText namaEdit, beratEdit, tinggiEdit;
    TextView userEdit;
    ImageView fotoView;
    Uri FilePathUri;
    private StorageReference storageReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog;
    Button btnPilih;
    String uID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        String email = user.getEmail().toString();
        uID = user.getUid();



        storageReference = FirebaseStorage.getInstance().getReference();


        namaEdit = (EditText) findViewById(R.id.namaEdit);
        beratEdit = (EditText) findViewById(R.id.beratEdit);
        tinggiEdit = (EditText) findViewById(R.id.tinggiEdit);
        userEdit = (TextView) findViewById(R.id.userEdit);
        userEdit.setText(email);

        btnPilih = (Button) findViewById(R.id.btnFoto);

        fotoView = (ImageView) findViewById(R.id.fotoEdit);

        progressDialog = new ProgressDialog(EditProfile.this);



    }

    public void pilihFoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Foto"), Image_Request_Code);
    }

    public void fungsiEdit(View view) {
        if (FilePathUri != null){
            progressDialog.setTitle("Menyimpan Profile...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String nama = namaEdit.getText().toString().trim();
                    String tinggi = tinggiEdit.getText().toString().trim();
                    String berat = beratEdit.getText().toString().trim();

                    @SuppressWarnings("VisibleForTests")
                    AdapterProfile imageUploadInfo = new AdapterProfile(nama,tinggi,berat, Beranda.emailUser, taskSnapshot.getDownloadUrl().toString());

                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    databaseReference.child(user.getUid()).setValue(imageUploadInfo);

                    progressDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "Profil berhasil disimpan", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EditProfile.this, ProfilInfo.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.setTitle("Menyimpan profile...");

                }
            });

        } else {
            Toast.makeText(EditProfile.this, "Pilih Gambar", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null){

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                fotoView.setImageBitmap(bitmap);
                btnPilih.setText("Image Selected");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String GetFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
       return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}

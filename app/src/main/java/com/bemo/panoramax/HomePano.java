package com.bemo.panoramax;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bemo.panoramax.views.MainActivity;
import com.yalantis.ucrop.UCrop;

import java.io.File;

public class HomePano extends AppCompatActivity {
    private FlipperLayout flipperLayout;
    private final int CODE_IMG=1;
    private final String DES="sam";
    private ImageView gong;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pano);
        gong=(ImageView)findViewById(R.id.gong);


        flipperLayout=(FlipperLayout)findViewById(R.id.vp);
        flipperLayout.setScrollTimeInSec(500);
          int i[]=new int[]{R.drawable.crops,R.drawable.pano,R.drawable.panoramas};
          String[] t =new String[]{"Crop image","Panorama","Panorama"};
          for (int s=0;s<i.length;s++){
            FlipperView view=new FlipperView(getBaseContext());
            view.setImageDrawable(i[s]).setDescription(t[s]);
             flipperLayout.addFlipperView(view);
          }

        TextView privacyPolicyTextView = findViewById(R.id.textViewPrivacyPolicy);
        privacyPolicyTextView.setOnClickListener(v->{
            Intent i2=new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/arbahokdemo/privacy-policy"));
            startActivity(i2);
        });
    }



    @SuppressLint("ResourceType")
    public void Affix(View view) {
        if(Integer.parseInt(Build.VERSION.SDK)<=19){
            Toast.makeText(this,"No",Toast.LENGTH_LONG).show();



        }else {

            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


    }


    public void CROPER(View view) {
        startActivityForResult(new Intent().setAction(Intent.ACTION_GET_CONTENT).setType("image/*"),CODE_IMG);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==CODE_IMG&&resultCode==RESULT_OK){
            Uri uri=data.getData();
            if (uri!=null){
                StartCrop(uri);
            }

        }else if (requestCode== UCrop.REQUEST_CROP&resultCode==RESULT_OK){
            Uri u=UCrop.getOutput(data);
            if (u!=null){
                Toast.makeText(this,"Save IMG",Toast.LENGTH_LONG).show();



            }
        }
    }
    private void StartCrop(@NonNull Uri uri) {
        String DES = "sam";
        DES += ".jpg";
        UCrop uCrop=UCrop.of(uri,Uri.fromFile(new File(getCacheDir(),DES)));
        uCrop.withAspectRatio(1,1);

        uCrop.withMaxResultSize(450,450);
        uCrop.withOptions(getCropOptions());
        uCrop.start(HomePano.this);
    }
    private UCrop.Options getCropOptions(){
        UCrop.Options options=new UCrop.Options();
        options.setCompressionQuality(70);
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(true);
        options.setToolbarColor(getResources().getColor(R.color.Cared2));
        options.setStatusBarColor(getResources().getColor(R.color.Cared2));
        options.setLogoColor(getResources().getColor(R.color.Cared2));
        options.setActiveControlsWidgetColor(getResources().getColor(R.color.Cared2));
        options.setToolbarTitle("Crop");
        return options;
    }
}

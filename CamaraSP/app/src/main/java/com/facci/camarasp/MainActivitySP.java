package com.facci.camarasp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

public class MainActivitySP extends AppCompatActivity {

    private Button bt_fotoBT;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_sp);
        //Relacionamos con el xml
        img =(ImageView)this.findViewById(R.id.imagenCapturadaIV);
        bt_fotoBT=(Button)this.findViewById(R.id.fotoBT);
        //Añadimos el listener botton
        bt_fotoBT.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Creamos el Intent para llmar a la camara
                Intent camaraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Creamos una carpeta en la memoria interna del terminal
                File imagesFolder =new File (Environment.getExternalStorageDirectory(),"ccc");
                imagesFolder.mkdirs();
                //añadmos el nombre a la imagen
                File image= new File(imagesFolder, "foto.jpg");
                Uri uriSaveImage=Uri.fromFile(image);
                //le decimos al intent que queremos grabar la imagen
                camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSaveImage);
                //Lanzamos la aplicacion de la camara con retorno (forResult)
                startActivityForResult(camaraIntent, 1);
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//comprobamos que la foto se a realizado
        if ( resultCode==1 && resultCode== Activity.RESULT_OK)
        {
            //cremos un bitmap con la imagen reciente //almacenada en la memoria
            Bitmap  bMap = BitmapFactory.decodeFile(Environment.getExternalStorageState()+"/ccc/"+"foto.jpg");
            //añadimos el bitmap al imageView para mostrarlo por pantalla
            img.setImageBitmap(bMap);

        }
    }
}


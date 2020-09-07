package morse.morse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
public class MainActivity extends AppCompatActivity {

    SharedPreferences preferencias_compartilhadas;
    Preferencias preferencias;
    EditText editor;
    TextView copiar_e_colar;

    String  texto_sem_traduzir,
            espaco_entre_palavras,
            texto_antigo[] = new String[50];

    int
            contador_do_texto_antigo = 0,
            espaco_precionado        = 0;


    Float tamanho_da_fonte;


    boolean letras_maiusculas;



    ClipboardManager clipboard;


     private  AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        copiar_e_colar = findViewById(R.id.copiar_e_colar);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        editor = findViewById(R.id.editText);
       this.atualiza_preferencias_de_usuarios(editor);

    }





    public void atualiza_preferencias_de_usuarios(View view){
        copiar_e_colar.setText("");
        preferencias_compartilhadas = getSharedPreferences("preferencias", MODE_PRIVATE);
        preferencias = new Preferencias(preferencias_compartilhadas);

        texto_sem_traduzir    = editor.getText().toString();
        espaco_entre_palavras = preferencias.retorna_tipo_de_espaco();
        letras_maiusculas     = preferencias.retorna_maiuscula_ou_minuscula();
        tamanho_da_fonte      = preferencias.retorna_tamanho_da_fonte();


        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        editor.setTextSize(tamanho_da_fonte);


    }

    public void inicia_configuracoes(View view){
        copiar_e_colar.setText("");
        Intent intent = new Intent(this , Configuracoes.class);
        startActivity(intent);

    }

    public void inicia_tabela(View view){
        copiar_e_colar.setText("");
        Intent intent = new Intent(this , Tabela.class);
        startActivity(intent);

    }

    public void traduz_texto(View view){
        copiar_e_colar.setText("");
        if(contador_do_texto_antigo < 50){
            texto_antigo[contador_do_texto_antigo] = editor.getText().toString();
            ++contador_do_texto_antigo;
        }



        this.atualiza_preferencias_de_usuarios(view);



        String texto_traduzido = Codigo.traduz_texto(texto_sem_traduzir,espaco_entre_palavras,letras_maiusculas);

        editor.setText(texto_traduzido);


    }

    public void limpa(View view){
        copiar_e_colar.setText("");

        if(contador_do_texto_antigo < 50){
            texto_antigo[contador_do_texto_antigo] = editor.getText().toString();
            ++contador_do_texto_antigo;
        }

       espaco_precionado = 0;
        editor.setText("");
    }

    public void apaga_um_digito(View view){
        copiar_e_colar.setText("");
         espaco_precionado = 0;
        String texto_antigo = editor.getText().toString();

        int sobra = 0;

        if(texto_antigo.length() < 1){
            return;
        }

        int tamanho = texto_antigo.length() -1;

        if(texto_antigo.charAt(tamanho) == ' '){
            sobra = 1;
        }

        String texto_novo = texto_antigo.substring(0,(tamanho  - sobra));


        editor.setText(texto_novo);

    }

    public void escreve_ponto(View view){
        copiar_e_colar.setText("");
         this.atualiza_preferencias_de_usuarios(view);
        espaco_precionado =0;
        String texto_antigo = editor.getText().toString();

        String texto_novo = texto_antigo + ".";

        editor.setText(texto_novo);
    }

    public void escreve_traco(View view){
        copiar_e_colar.setText("");
        this.atualiza_preferencias_de_usuarios(view);
        espaco_precionado = 0;
        String texto_antigo = editor.getText().toString();

        String texto_novo = texto_antigo + "-";

        editor.setText(texto_novo);

    }

    public void escreve_espaco(View view){
        copiar_e_colar.setText("");
        this.atualiza_preferencias_de_usuarios(view);
        ++espaco_precionado;

        EditText editor = findViewById(R.id.editText);
        String texto_antigo = editor.getText().toString();

        String texto_novo = texto_antigo + " ";

        if(espaco_precionado ==2){
        texto_novo = texto_antigo + espaco_entre_palavras;

        }

        editor.setText(texto_novo);


    }

    public void retry(View view){
        copiar_e_colar.setText("");
        if(contador_do_texto_antigo  == 0){
            return;
        }
        editor.setText(texto_antigo[contador_do_texto_antigo -1]);

        --contador_do_texto_antigo;
    }

    public void copiar(View view){

        this.atualiza_preferencias_de_usuarios(view);

        String texto = editor.getText().toString();
        ClipData clip = ClipData.newPlainText("", texto);
        clipboard.setPrimaryClip(clip);

        copiar_e_colar.setText(R.string.copiar);

    }

    public void colar(View view){

        this.atualiza_preferencias_de_usuarios(view);

        if(clipboard.getPrimaryClip() == null) {
            return;
        }
        ClipData clip = clipboard.getPrimaryClip();

        if(clip.getItemAt(0) == null) {
            return;
        }
        ClipData.Item item =  clip.getItemAt(0);

        if(item.getText() == null) {return;
        }

        CharSequence texto = item.getText();


        editor.setText(texto);
        copiar_e_colar.setText(R.string.colar);
    }
}

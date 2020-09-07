package morse.morse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Configuracoes extends AppCompatActivity {

    int botao_de_salvar_clicado = 0;
    SharedPreferences preferencias_compartilhadas;
    Preferencias preferencias;
    Spinner tamanho_da_fonte;
    Spinner espaco_entre_palavras;
    Spinner letras_maiusculas_e_minusculas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        preferencias_compartilhadas = getSharedPreferences("preferencias", MODE_PRIVATE);

        preferencias = new Preferencias(preferencias_compartilhadas);


        tamanho_da_fonte = findViewById(R.id.spinner_tamanho_de_fonte);
        espaco_entre_palavras = findViewById(R.id.spinner_espaco_entre_palavras);
        letras_maiusculas_e_minusculas = findViewById(R.id.spinner_de_letras_maiusculas_e_minusculas);



        tamanho_da_fonte.setSelection(preferencias.retorna_posicao_do_vetor_do_tamanho_da_fonte());
        espaco_entre_palavras.setSelection(preferencias.retorna_posicao_do_vetor_do_espaco_entre_palavras());
        letras_maiusculas_e_minusculas.setSelection(preferencias.retorna_posicao_do_vetor_de_letras_maiusculas_e_minusculas());


    }


    public void salvar(View view) throws InterruptedException {



        preferencias.set_tamanho_da_fonte(tamanho_da_fonte.getSelectedItemPosition());
        preferencias.set_espaco_entre_palavras(espaco_entre_palavras.getSelectedItemPosition());
        preferencias.set_letras_maiusculas_ou_minusculas(letras_maiusculas_e_minusculas.getSelectedItemPosition());


        botao_de_salvar_clicado++;
        TextView salvado_com_suscesso = findViewById(R.id.salvado_com_suscesso);
        if (botao_de_salvar_clicado > 1) {
            salvado_com_suscesso.setText(getResources().getString(R.string.salvado_novamente));
        }

        salvado_com_suscesso.setVisibility(View.VISIBLE);

    }
}


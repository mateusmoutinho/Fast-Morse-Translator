package morse.morse;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Preferencias {

    private final float  tamanho_da_fonte[] = {14.0f , 18.0f , 24.0f, 30.0f};

    private final String espaco_entre_palavras[] = {
            " / ",
            " ",
            "  ",
            "   ",
            "    ",
            "     "
    };


    private final boolean letras_maiusculas_e_minusculas[] =  {false, true};


    private SharedPreferences preferencias;

    private SharedPreferences.Editor editor;




    public Preferencias(SharedPreferences args_preferencias){

        preferencias = args_preferencias;

        editor = args_preferencias.edit();


    }

    //------------------------Funcões-------------------------------------------------------



//----------------------------Setters-------------------------------------------------------
   public  void set_tamanho_da_fonte(int posicao_do_vetor) {
       editor.putFloat("tamanho_da_fonte", tamanho_da_fonte[posicao_do_vetor]);
       editor.putInt("posicao_do_tamanho_da_fonte", posicao_do_vetor);
       editor.apply();
}


    public  void set_espaco_entre_palavras(int posicao_do_vetor) {
        editor.putString("espaco_entre_palavras", espaco_entre_palavras[posicao_do_vetor]);
        editor.putInt("posicao_do_espaco_entre_palavras", posicao_do_vetor);
        editor.apply();
    }



    public  void set_letras_maiusculas_ou_minusculas(int posicao_do_vetor) {
        editor.putBoolean("letras_maiusculas_e_minusculas", letras_maiusculas_e_minusculas[posicao_do_vetor]);
        editor.putInt("posicao_de_letras_maiusculas_e_minusculas", posicao_do_vetor);
        editor.apply();
    }


    //--------------------------Getters_de_posicao-----------------------------------------------------
    public int retorna_posicao_do_vetor_do_tamanho_da_fonte(){
        return preferencias.getInt("posicao_do_tamanho_da_fonte",2);
    }
    public int retorna_posicao_do_vetor_do_espaco_entre_palavras(){
        return preferencias.getInt("posicao_do_espaco_entre_palavras",0);
    }

    public int retorna_posicao_do_vetor_de_letras_maiusculas_e_minusculas(){
        return preferencias.getInt("posicao_de_letras_maiusculas_e_minusculas",0);
    }

    //------------------------------Geters_de_vetores--------------------------------------------------


    public float retorna_tamanho_da_fonte(){
        int posicao = retorna_posicao_do_vetor_do_tamanho_da_fonte();
        return tamanho_da_fonte[posicao];

    }
    public String retorna_tipo_de_espaco(){
        int posicao = retorna_posicao_do_vetor_do_espaco_entre_palavras();
        return espaco_entre_palavras[posicao];
    }


    public boolean retorna_maiuscula_ou_minuscula(){
        int posicao = retorna_posicao_do_vetor_de_letras_maiusculas_e_minusculas();
        return letras_maiusculas_e_minusculas[posicao];
    }











}

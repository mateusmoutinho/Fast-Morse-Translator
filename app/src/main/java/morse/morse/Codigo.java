package morse.morse;

import android.util.Log;

public class Codigo {


    private static final String[] CODIGO_MORSE = {

            " .- ",      //|A
            " -... ",    //|B
            " -.-. ",    //|C
            " -.. ",     //|D
            " ..-. ",    //|F
            " --. ",     //|G
            " .... ",    //|H
            " .. ",      //|I
            " .--- ",    //|J
            " -.- ",     //|K
            " .-.. ",    //|L
            " -- ",      //|M
            " -. ",      //|N
            " --- ",     //|O
            " .--. ",    //|P
            " --.- ",    //|Q
            " .-. ",     //|R
            " ... ",     //|S
            " - ",       //|T
            " ..- ",     //|U
            " ...- ",    //|V
            " .-- ",     //|W
            " -..- ",    //|X
            " -.-- ",    //|Y
            " --.. ",    //|Z


            " .---- ",   //|1
            " ..--- ",   //|2
            " ...-- ",   //|3
            " ....- ",   //|4
            " ..... ",   //|5
            " -.... ",   //|6
            " --... ",   //|7
            " ---.. ",   //|8
            " ----. ",   //|9
            " ----- ",   //|0

            " .-..-. ",  //|"
            " --..-- ",   //|,
            " ..--.. ",  //|?
            " .----. ",  //|'
            " -.-.-- ",  //|!
            " -..-. ",   //|/
            " -.--. ",   //|(
            " -.--.- ",  //|)
            " .-... ",   //|&
            " ---··· ",  //|:
            " -·-·-· ",  //|;
            " -···- ",   //|=
            " ··--·- ",  //|_
            " ···-··- ", //|$
            " ·--·-· ",  //|@
    };

    private static final String[] CARACTERES = {

            " a ",
            " b ",
            " c ",
            " d ",
            " f ",
            " g ",
            " h ",
            " i ",
            " j ",
            " k ",
            " l ",
            " m ",
            " n ",
            " o ",
            " p ",
            " q ",
            " r ",
            " s ",
            " t ",
            " u ",
            " v ",
            " w ",
            " x ",
            " y ",
            " z ",

            " 1 ",
            " 2 ",
            " 3 ",
            " 4 ",
            " 5 ",
            " 6 ",
            " 7 ",
            " 8 ",
            " 9 ",
            " 0 ",

            " \" ", //|"
            " , ",
            " ? ",
            " ' ",
            " ! ",
            " / ",
            " ( ",
            " ) ",
            " & ",
            " : ",
            " ; ",
            " = ",
            " _ ",
            " $ ",
            " @ ",

    };

    private static  String  adiciona_espaco_entre_palavras_no_codigo_morse(String texto_em_morse, String espaco_entre_palavras){
        int tamanho_do_texto = texto_em_morse.length();
        boolean espaco_encontrado = false;

        for(int i = 0; i < tamanho_do_texto -1; i++){

            String alvo = texto_em_morse.substring(i, i+2);

            if(texto_em_morse.charAt(i) != ' ' && texto_em_morse.charAt(i) != '/') {
                espaco_encontrado = false;
            }
            if(alvo.equals("  ") == true && espaco_encontrado == false){
                espaco_encontrado = true;
                String inicio = texto_em_morse.substring(0,i);
                String fim = texto_em_morse.substring(i, tamanho_do_texto);
                texto_em_morse = inicio + espaco_entre_palavras + fim;
                tamanho_do_texto = texto_em_morse.length();
              }

        }
        return texto_em_morse;
    }

    private static  String  corrige_caracteres_errados(String texto_sem_conversao){


        final char[] caracteres_errados = {

                'ã',
                'á',
                'à',
                'â',


                'ẽ',
                'é',
                'è',
                'ê',


                'ĩ',
                'í',
                'ì',
                'î',


                'õ',
                'ó',
                'ò',
                'ô',


                'ũ',
                'ú',
                'ù',
                'û',


                'ñ',
                'ń',
                'ǹ',

                'ç',

        };

        final char[] caracteres_corretos = {

                'a',
                'a',
                'a',
                'a',


                'e',
                'e',
                'e',
                'e',


                'i',
                'i',
                'i',
                'i',

                'o',
                'o',
                'o',
                'o',


                'u',
                'u',
                'u',
                'u',


                'n',
                'n',
                'n',


                'c',

        };


        String texto_convertido = texto_sem_conversao.toLowerCase();

        for(int i = 0; i < caracteres_errados.length ; ++i){

            texto_convertido = texto_convertido.replace(caracteres_errados[i], caracteres_corretos[i]);
        }



        return texto_convertido;
    };

    private static  String  traduz_para_morse  (String texto_em_letra){

        String texto_em_morse = texto_em_letra.toLowerCase();


        texto_em_morse = texto_em_morse.replace("", "  ");
        for(int i = 0 ; i < CODIGO_MORSE.length ; ++i){
            texto_em_morse = texto_em_morse.replace(CARACTERES[i], CODIGO_MORSE[i]);
        }
        texto_em_morse = texto_em_morse.replace(" . ", " .-.-.- ");
        texto_em_morse = texto_em_morse.replace(" - ", " -....- ");
        texto_em_morse = texto_em_morse.replace(" e ", " . ");

        texto_em_morse = texto_em_morse.replace("  ", " ");
        texto_em_morse = texto_em_morse.replace("  ", " ");

        texto_em_morse = texto_em_morse.trim();

        return texto_em_morse;

    }

    private static  String  traduz_para_letra  (String texto_em_morse){

        String texto_em_letra = texto_em_morse;


        texto_em_letra = " " + texto_em_letra + " ";
        texto_em_letra = texto_em_letra.replace("/", " ");
        texto_em_letra = texto_em_letra.replace(" ", "  ");


        for(int i = 0 ; i < CODIGO_MORSE.length ; ++i){

            texto_em_letra = texto_em_letra.replace(CODIGO_MORSE[i], CARACTERES[i].substring(1, 2));

        }
        texto_em_letra = texto_em_letra.replace(" . ", "e");
        texto_em_letra = texto_em_letra.replace(" .-.-.- ", ".");
        texto_em_letra = texto_em_letra.replace(" -....- ", "-");

        texto_em_letra = texto_em_letra.replace("  "," ");
        texto_em_letra = texto_em_letra.replace("  "," ");
        texto_em_letra = texto_em_letra.trim();



        return texto_em_letra;

    }

    public static   char    retorna_tipo_de_codigo(String texto){

        int contador_de_morse = 0;
        int contador_de_caracteres =0;


        for(int i = 0; i < texto.length() ; i++){
            if(texto.charAt(i) == '-' || texto.charAt(i) == '.'){
                ++contador_de_morse;
            }
            else if (texto.charAt(i)  != '-' && texto.charAt(i) != '.'
                    &texto.charAt(i)  != '/' && texto.charAt(i) != ' '){
                 ++contador_de_caracteres;
            }
        }

        if(contador_de_morse > contador_de_caracteres){
            return 'M';
        }else{return 'L';}

    }

    public static   String  traduz_texto(String texto, String espaco_entre_palavras,boolean letras_maiusculas ){

        final  char TIPO_DE_TEXTO = retorna_tipo_de_codigo(texto);

        String texto_traduzido = texto;
        switch (TIPO_DE_TEXTO){
            case 'M':

                texto_traduzido = traduz_para_letra(texto_traduzido);

                if(letras_maiusculas == true){
                    texto_traduzido = texto_traduzido.toUpperCase();
                }

                break;
            case 'L':

                texto_traduzido = corrige_caracteres_errados(texto_traduzido);
                texto_traduzido = traduz_para_morse(texto_traduzido);
                texto_traduzido = adiciona_espaco_entre_palavras_no_codigo_morse(texto_traduzido,espaco_entre_palavras);
                break;


        }

        return texto_traduzido;


    }











}

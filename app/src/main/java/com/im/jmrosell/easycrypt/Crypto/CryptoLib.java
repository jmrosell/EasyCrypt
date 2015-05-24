package com.im.jmrosell.easycrypt.Crypto;

import java.util.Arrays;

public class CryptoLib {
    private String texto;

    public CryptoLib(){
        texto = "";
    }

    public CryptoLib(String texto){
        this.texto = texto;
    }

    public String encriptar(String pswd){
        String resultado="";

        float f = (float) texto.length()/pswd.length();
        int filas = (int) Math.ceil(f);
        int columnas = pswd.length();
        char [][] matriz = new char[filas][columnas];

        int caracter = 0;
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                if(texto.length()>caracter){
                    matriz[i][j] = texto.charAt(caracter);
                }
                caracter++;
            }
        }

        char [] key = pswd.toCharArray();
        char [] key_sort = pswd.toCharArray();
        int [] poskeysort = new int [key.length];

        // Ordenamos la key alfabeticamente
        Arrays.sort(key_sort);

        // Iniciamos el valor del array de posiciones a -1
        for(int i=0; i<poskeysort.length;i++){
            poskeysort[i]=-1;
        }

        // Ponemos la posicion de la clave ordenada
        boolean puesto;
        for(int i=0;i<pswd.length();i++){
            puesto=false;
            for(int j=0;j<pswd.length() && !puesto;j++){
                if(key[i]==key_sort[j]){
                    if(!estaEnArray(poskeysort, j)){
                        poskeysort[i]=j;
                        puesto=true;
                    }
                }
            }
        }

        // Cambiamos las columnas a su posicion en funcion de la clave ordenada
        char [][] m_res = new char[filas][columnas];
        for(int i=0; i<poskeysort.length; i++){
            copiaColumnas(matriz, m_res, i, poskeysort[i]);
        }

        // Construimos un string con la matriz de caracteres por filas
        for(int i=0; i<m_res.length; i++){
            for(int j=0; j<m_res[i].length; j++){
                resultado += m_res[i][j];
            }
        }

        return resultado;
    }

    public String desencriptar(String pswd){
        String resultado="";

        float f = (float) texto.length()/pswd.length();
        int filas = (int) Math.ceil(f);
        int columnas = pswd.length();
        char [][] matriz = new char[filas][columnas];

        int caracter = 0;
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                if(texto.length()>caracter){
                    matriz[i][j] = texto.charAt(caracter);
                }
                caracter++;
            }
        }

        char [] key = pswd.toCharArray();
        char [] key_sort = pswd.toCharArray();
        int [] poskeysort = new int [key.length];

        // Ordenamos la key alfabeticamente
        Arrays.sort(key_sort);

        // Iniciamos el valor del array de posiciones a -1
        for(int i=0; i<poskeysort.length;i++){
            poskeysort[i]=-1;
        }

        // Ponemos la posicion de la clave ordenada
        boolean puesto;
        for(int i=0;i<pswd.length();i++){
            puesto=false;
            for(int j=0;j<pswd.length() && !puesto;j++){
                if(key[i]==key_sort[j]){
                    if(!estaEnArray(poskeysort, j)){
                        poskeysort[i]=j;
                        puesto=true;
                    }
                }
            }
        }

        // Cambiamos las columnas a su posicion en funcion de la clave ordenada
        char [][] m_res = new char[filas][columnas];
        for(int i=0; i<poskeysort.length; i++){
            copiaColumnas(matriz, m_res, poskeysort[i], i);
        }

        // Construimos un string con la matriz de caracteres por filas
        for(int i=0; i<m_res.length; i++){
            for(int j=0; j<m_res[i].length; j++){
                resultado += m_res[i][j];
            }
        }

        return resultado;
    }

    private static void intercambiaColumna(int c1, int c2, char[][] matriz){
        for(int i=0; i<matriz.length; i++){
            char aux;
            aux=matriz[i][c1];
            matriz[i][c1]=matriz[i][c2];
            matriz[i][c2]=aux;
        }
    }

    private void imprimeMatriz(char[][] m){
        for(int i=0; i<m.length; i++){
            for(int j=0; j<m[i].length; j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }

    private boolean estaEnArray(int [] a, int b){
        boolean esta = false;

        for(int i=0;i<a.length && !esta; i++){
            if(a[i]==b){
                esta=true;
            }
        }

        return esta;
    }

    private static void copiaColumnas(char [][] mo, char [][] md, int co, int cd){
        for(int i=0; i<mo.length; i++){
            md[i][cd]=mo[i][co];
        }
    }
}

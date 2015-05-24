package com.im.jmrosell.easycrypt;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.content.ClipboardManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.im.jmrosell.easycrypt.Crypto.CryptoLib;

public class EncriptarFragment extends Fragment {

    private EditText introducido;
    private EditText pass;
    private TextView resultado;
    private Button copiar;
    private Button encriptar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_encriptar, container, false);

        introducido = (EditText) rootView.findViewById(R.id.et_introducido);
        pass = (EditText) rootView.findViewById(R.id.et_pass);
        resultado = (TextView) rootView.findViewById(R.id.tv_resultado);
        copiar = (Button) rootView.findViewById(R.id.btn_copiar);
        encriptar = (Button) rootView.findViewById(R.id.btn_encriptar);

        copiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultado.getText() != "") {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copy text",resultado.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getActivity(), getString(R.string.copy_porta), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),getString(R.string.no_copy), Toast.LENGTH_SHORT).show();
                }
            }
        });

        encriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobar()){
                    //Aqui se encripta
                    String texto = introducido.getText().toString();
                    String pswd = pass.getText().toString();

                    CryptoLib mCript = new CryptoLib(texto);
                    resultado.setText(mCript.encriptar(pswd));
                }
            }
        });
        return rootView;
    }

    public boolean comprobar(){
        boolean cancel=true;
        if(TextUtils.isEmpty(introducido.getText().toString())){
            introducido.setError(getString(R.string.error_texto_encriptar));
            cancel = false;
        }
        if(TextUtils.isEmpty(pass.getText().toString())){
            pass.setError(getString(R.string.error_pass_encriptar));
            cancel = false;
        }
        return cancel;
    }
}
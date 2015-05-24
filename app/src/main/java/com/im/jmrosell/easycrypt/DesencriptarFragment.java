package com.im.jmrosell.easycrypt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.im.jmrosell.easycrypt.Crypto.CryptoLib;

public class DesencriptarFragment extends Fragment {

    private EditText introducido;
    private EditText pass;
    private TextView resultado;
    private Button desencriptar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_desencriptar, container, false);

        introducido = (EditText) rootView.findViewById(R.id.et_introducido_des);
        pass = (EditText) rootView.findViewById(R.id.et_pass_des);
        resultado = (TextView) rootView.findViewById(R.id.tv_desencriptado);
        desencriptar = (Button) rootView.findViewById(R.id.btn_desencriptar);

        desencriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobar()){
                    //Aqui se desencripta
                    String texto = introducido.getText().toString();
                    String pswd = pass.getText().toString();

                    CryptoLib mCript = new CryptoLib(texto);
                    resultado.setText(mCript.desencriptar(pswd));
                }
            }
        });
        return rootView;
    }

    public boolean comprobar() {
        boolean cancel = true;
        if (TextUtils.isEmpty(introducido.getText().toString())) {
            introducido.setError(getString(R.string.error_texto_encriptar));
            cancel = false;
        }
        if (TextUtils.isEmpty(pass.getText().toString())) {
            pass.setError(getString(R.string.error_pass_encriptar));
            cancel = false;
        }
        return cancel;
    }
}

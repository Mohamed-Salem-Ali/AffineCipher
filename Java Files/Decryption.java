package com.example.a01a03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class DecryptionClass
{
//this function take 3 parameters and decrypt msg by using a , b
static String decryptMessage (String cipher,int a,int b)
{
	// Plain Text initially empty
    String plainText="";

    for (int i=0;i<cipher.length();i++) {
        char c = cipher.charAt(i);

        int charNum = ((char) c) - 97;
        if ((charNum - b) >= 0)
        {
            int decrypting = ((modInverse(a, 26)) * (charNum - b)) % 26;
        char newT = (char) (decrypting + 97);
        plainText += newT;
        }
        else
        {
            int decrypting = ((modInverse(a, 26)) * (charNum - b)) + 26;

            while(decrypting<0) {
                 decrypting +=  26;

            }
            char newT = (char) (decrypting + 97);
            plainText += newT;
        }

    }
    return plainText;


}
	//this function take two integer parameter and return the inverse of key 
    static int modInverse(int x,int m)
    {
        x=x%m;
        for(int j=1;j<m;j++)
        {
            if((x*j)%m==1) {
                return j;
            }
        }
        return 1;


    }

}

public class Decryption extends AppCompatActivity {

    String msg="";
    EditText CipherText;
    String s="";
    TextView plain;
    Button decr;
    EditText a_t;
    EditText b_t;
    int a, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);
// there are 12 keys that are vaild because they have inverse
        final int[] a_keys ={1,3,5,7,9,11,15,17,19,21,23,25};

        decr = (Button) findViewById(R.id.decrypt);

        a_t = (EditText) findViewById(R.id.a);

        b_t = (EditText) findViewById(R.id.b);

        CipherText = (EditText) findViewById(R.id.CipherText);

        plain = (TextView) findViewById(R.id.plainText_2);
        decr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a =  Integer.parseInt(a_t.getText().toString());
                b = Integer.parseInt(b_t.getText().toString());
       
	//here we remove spaces from string in  edittext and make letters in lowercase         
		msg= CipherText.getText().toString().toLowerCase().trim();
                msg.replaceAll("\\s+","");
                
	//we now check if a key is correct and ciphertext is english letters only
		if (check_a(a_keys,a)&&isStringOnly(msg))
                {
                    Decrypt();
                }
                else if(check_a(a_keys,a)==false)
                {
                    openDialog1();
                }
                else if(isStringOnly(msg)==false)
                {
                    openDialog2();
                }

            }
        });
        CipherText.addTextChangedListener(CheckText);
        a_t.addTextChangedListener(CheckText);
        b_t.addTextChangedListener(CheckText);
    }

// this function used to make button not active until user write the ciphertext and keys
    public TextWatcher CheckText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

	//here check that a key and b key and plaintext not empty
	//if they are not empty make the decrypt button active 
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String a_k= a_t.getText().toString().trim();
            String b_k= b_t.getText().toString().trim();
            String plain =CipherText.getText().toString().trim();
            decr.setEnabled(!a_k.isEmpty() && !b_k.isEmpty() && !plain.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

	//here check that the a key that user entered is valid and correct
	//return true if the key is correct and false if the key is not valid
    public boolean check_a(int[] list ,int key)
    {

        for(int i=0;i<list.length;i++)
        {
            if(list[i]==key)
            {
                return true;
            }
        }
        return false;
    }

	// this is a boolean function check that the ciphertext is english letters only and return true if the ciphertext is correct
    public static boolean isStringOnly (String str)
    {
        return(str.matches("^[a-z]*$"));
    }

	//this function used to show alarm dialog by call java class KeyCheck()
    public void openDialog1()

    {
        KeyCheck encryptionCheck = new KeyCheck();
        encryptionCheck.show(getSupportFragmentManager(), "Decryption Check");
    }

	//this function used to show alarm dialog by call java class TextCheck()
    public void openDialog2()

    {
        TextCheck textCheck = new TextCheck();
        textCheck.show(getSupportFragmentManager(), "Decryption Check");
    }


	// this function used to make object from DecryptionClass and call decryptmessage function
    public void Decrypt()
    {
        DecryptionClass x = new DecryptionClass();
        s=x.decryptMessage(msg,a,b);
        plain.setText(s);

    }

}

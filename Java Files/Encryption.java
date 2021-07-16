package com.example.a01a03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class EncryptionClass {
	//this function take 3 parameters and encrypt msg by using a , b
     String encryptMessage(String msg,int a,int b) {
        // Cipher Text initially empty
        String cipher = "";
        
	for (int i = 0; i < msg.length(); i++) {
            char t = msg.charAt(i);
            int charNum = (char) t - 97;
            int c = (a * charNum + b) % 26;
            char C = (char) (c + 97);
            cipher += C;
        }
        return cipher;
    }
}

    public class Encryption extends AppCompatActivity {
        String msg="";
        EditText plainText;
        String s="";
        TextView cipher;
        Button encr;
         EditText a_t;
         EditText b_t;
         int a, b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

	// there are 12 keys that are vaild because they have inverse
        final int[] a_keys ={1,3,5,7,9,11,15,17,19,21,23,25};

        encr = (Button) findViewById(R.id.Encrypt);

        a_t = (EditText) findViewById(R.id.a);

        b_t = (EditText) findViewById(R.id.b);

        plainText = (EditText) findViewById(R.id.PlanText);

        cipher = (TextView) findViewById(R.id.cipherText_2);


        encr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a =  Integer.parseInt(a_t.getText().toString());
                b = Integer.parseInt(b_t.getText().toString());
               
	 //here we remove spaces from string in  edittext and make letters in lowercase
		msg= plainText.getText().toString().toLowerCase().trim();
                msg.replaceAll("\\s+","");
             
	//we now check if a key is correct and plaintext is english letters only	
	       if (check_a(a_keys,a)&&isStringOnly(msg))
               {
                   Encrypt();
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

        plainText.addTextChangedListener(CheckText);
        a_t.addTextChangedListener(CheckText);
        b_t.addTextChangedListener(CheckText);
    }

	// this function used to make button not active until user write the plaintext and keys
        public TextWatcher CheckText = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

	//here check that a key and b key and plaintext not empty
	//if they are not empty make the encrypt button active 
       	    @Override	
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String a_k= a_t.getText().toString().trim();
                String b_k= b_t.getText().toString().trim();
                String plain =plainText.getText().toString().trim();
                encr.setEnabled(!a_k.isEmpty() && !b_k.isEmpty() && !plain.isEmpty());
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
	// this is a boolean function check that the plaintext is english letters only and return true if the plaintext is correct
    public static boolean isStringOnly (String str)
	{
 	return(str.matches("^[a-z]*$"));
	}

	//this function used to show alarm dialog by call java class KeyCheck()
    public void openDialog1()
    {
            KeyCheck encryptionCheck = new KeyCheck();
            encryptionCheck.show(getSupportFragmentManager(), "Encryption Check");
    }

	//this function used to show alarm dialog by call java class TextCheck()
    public void openDialog2()
    {
            TextCheck textCheck = new TextCheck();
            textCheck.show(getSupportFragmentManager(), "Encryption Check");
    }


	// this function used to make object from EncryptionClass and call encryptmessage function 
    public void Encrypt()
    {
        EncryptionClass x = new EncryptionClass();
        s=x.encryptMessage(msg,a,b);
        cipher.setText(s);

    }
}

package sg.edu.rp.c346.id19047433.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String strName = etName.getText().toString();
                 float gpa = Float.parseFloat(etGPA.getText().toString());
                 int intGenderId = rgGender.getCheckedRadioButtonId();
                 SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                 SharedPreferences.Editor prefEdit = pref.edit();
                 prefEdit.putString("name", strName);
                 prefEdit.putFloat("gpa", gpa);
                 prefEdit.putInt("genderId", intGenderId);
                 prefEdit.commit();
             }
         });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String strName = prefs.getString("name", "");
        float gpa = prefs.getFloat("gpa", 0);
        int intGenderId = prefs.getInt("genderId", R.id.radioButtonGenderFemale);
        etName.setText(strName);
        etGPA.setText(gpa + "");
        rgGender.check(intGenderId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Get the user input and store in a variable called strName
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int intGenderId = rgGender.getCheckedRadioButtonId();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = pref.edit();
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putInt("genderId", intGenderId);
        prefEdit.commit();
    }
}

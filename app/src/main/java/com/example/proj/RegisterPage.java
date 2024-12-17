package com.example.proj;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RegisterPage extends AppCompatActivity {

    EditText etUsername, etPassword, etConfirmPassword, etFirstName, etLastName,
            etEmail, etAddress, etContact;
    TextView tvBday, tvLogin;
    RadioGroup rgGender;
    LinearLayout hobbiesContainer;
    Spinner securityQuestion1, securityQuestion2, securityQuestion3;
    ArrayList<CheckBox> hobbyCheckboxes;
    Calendar calendar;
    boolean isQuestionSelectedSame = false;

    private static final int CAMERA_REQUEST = 1;
    Button btnCamera, btnRegister;
    ImageView ivImage;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_register_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupDatePicker();
        setupHobbies();
        setupSecurityQuestions();
        setupRegisterButton();
    }

    private void initializeViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        tvBday = findViewById(R.id.tvBday);
        rgGender = findViewById(R.id.rgGender);
        etAddress = findViewById(R.id.etAddress);
        etContact = findViewById(R.id.etContact);
        hobbiesContainer = findViewById(R.id.hobbiesContainer);
        securityQuestion1 = findViewById(R.id.securityQuestion1);
        securityQuestion2 = findViewById(R.id.securityQuestion2);
        securityQuestion3 = findViewById(R.id.securityQuestion3);
        calendar = Calendar.getInstance();
        hobbyCheckboxes = new ArrayList<>();
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // camera
        ivImage = findViewById(R.id.ivImage);
        btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterPage.this, Dashboard.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            ivImage.setImageBitmap(photo);
        }
    }

    private void setupDatePicker() {
        tvBday.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String dateFormat = "MM/dd/yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                        tvBday.setText(sdf.format(calendar.getTime()));
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });
    }

    private void setupHobbies() {
        // List of hobbies
        String[] hobbies = {"Dancing", "Singing", "Gaming", "Sleeping",
                "Cooking", "Yapping", "Eating", "Writing", "Running", "Woodworking"};

        // Get the main vertical LinearLayout
        LinearLayout hobbiesContainer = findViewById(R.id.hobbiesContainer);

        // Create a horizontal LinearLayout for each row
        LinearLayout currentRow = null;

        for (int i = 0; i < hobbies.length; i++) {
            // Start a new row every two checkboxes
            if (i % 2 == 0) {
                currentRow = new LinearLayout(this);
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                hobbiesContainer.addView(currentRow);
            }

            // Create the checkbox
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(hobbies[i]);

            // Add margins for spacing
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, // Width: use 0 for even distribution
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f // Weight for equal space distribution
            );
            params.setMargins(8, 8, 8, 8);
            checkBox.setLayoutParams(params);

            // Add the checkbox to the current row
            currentRow.addView(checkBox);

            // Add the checkbox to the list (if needed)
            hobbyCheckboxes.add(checkBox);
        }
    }

    private void setupSecurityQuestions() {
        String[] questions = {
                "Select a security question",
                "What are you wearing?",
                "What is the last 4 digits of your social security number?",
                "What is your favorite security question?",
                "Where are the bodies hidden?",
                "Who would you murder if you get one free pass?"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.spinner_item, questions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        securityQuestion1.setAdapter(adapter);
        securityQuestion2.setAdapter(adapter);
        securityQuestion3.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int q1 = securityQuestion1.getSelectedItemPosition();
                int q2 = securityQuestion2.getSelectedItemPosition();
                int q3 = securityQuestion3.getSelectedItemPosition();

                if (q1 != 0 && (q1 == q2 || q1 == q3) ||
                        q2 != 0 && q2 == q3) {
                    isQuestionSelectedSame = true;
                    showAlert("ATTENTION!", "Please select another security questions");
                } else {
                    isQuestionSelectedSame = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        securityQuestion1.setOnItemSelectedListener(listener);
        securityQuestion2.setOnItemSelectedListener(listener);
        securityQuestion3.setOnItemSelectedListener(listener);
    }

    private void setupRegisterButton() {
        btnRegister.setOnClickListener(v -> {
            if (!validateFields()) {
                return;
            }
            showConfirmationDialog();
        });
    }

    private boolean validateFields() {
        // Check for empty fields
        if (isEmpty(etUsername) || isEmpty(etPassword) || isEmpty(etConfirmPassword) ||
                isEmpty(etFirstName) || isEmpty(etLastName) || isEmpty(etEmail) ||
                isEmpty(etAddress) || isEmpty(etContact) ||
                tvBday.getText().toString().equals("Date of Birth")) {
            showAlert("ATTENTION!", "All fields are required");
            return false;
        }

        // passowrd matching
        if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            showAlert("ATTENTION!", "Passwords do not match");
            return false;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            showAlert("ATTENTION!", "Please select a gender");
            return false;
        }

        // Check if photo is taken
        if (photo == null) {
            showAlert("ATTENTION!", "Please take a photo");
            return false;
        }

        if (tvBday.getText().toString().equals("Date of Birth") ||
                tvBday.getText().toString().isEmpty()) {
            showAlert("ATTENTION!", "Please select your date of birth");
            return false;
        }

        boolean anyHobbySelected = false;
        for (CheckBox checkBox : hobbyCheckboxes) {
            if (checkBox.isChecked()) {
                anyHobbySelected = true;
                break;
            }
        }
        if (!anyHobbySelected) {
            showAlert("ATTENTION", "Please select at least one hobby");
            return false;
        }

        if (securityQuestion1.getSelectedItemPosition() == 0 ||
                securityQuestion2.getSelectedItemPosition() == 0 ||
                securityQuestion3.getSelectedItemPosition() == 0) {
            showAlert("ATTENTION!", "Please select all security questions");
            return false;
        }

        if (isQuestionSelectedSame) {
            showAlert("ATTENTION!", "Please select another security questions");
            return false;
        }

        return true;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Account Details")
                .setMessage(generateSummary())
                .setPositiveButton("Confirm", (dialog, which) -> {

                    saveRegistrationData();
                    Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })
                .show();
    }

    private void saveRegistrationData() {
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("username", etUsername.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.putString("firstName", etFirstName.getText().toString());
        editor.putString("lastName", etLastName.getText().toString());

        if (photo != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            String encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            editor.putString("photo", encodedImage);
        }

        editor.apply();
    }

    private String generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("USERNAME: ").append(etUsername.getText()).append("\n");
        summary.append("FIRST NAME: ").append(etFirstName.getText()).append("\n");
        summary.append("LAST NAME: ").append(etLastName.getText()).append("\n");
        summary.append("EMAIL: ").append(etEmail.getText()).append("\n");
        summary.append("DATE OF BIRTH: ").append(tvBday.getText()).append("\n");
        RadioButton selectedGender = findViewById(rgGender.getCheckedRadioButtonId());
        summary.append("GENDER: ").append(selectedGender.getText()).append("\n");
        summary.append("ADDRESS: ").append(etAddress.getText()).append("\n");
        summary.append("CONTACT: ").append(etContact.getText()).append("\n");

        summary.append("HOBBIES: ");
        for (CheckBox checkBox : hobbyCheckboxes) {
            if (checkBox.isChecked()) {
                summary.append(checkBox.getText()).append(", ");
            }
        }
        summary.setLength(summary.length() - 2); // Remove last comma
        summary.append("\n");

        summary.append("SECURITY QUESTIONS:\n");
        summary.append("Q1: ").append(securityQuestion1.getSelectedItem().toString()).append("\n");
        summary.append("Q2: ").append(securityQuestion2.getSelectedItem().toString()).append("\n");
        summary.append("Q3: ").append(securityQuestion3.getSelectedItem().toString()).append("\n");

        return summary.toString();
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }


}
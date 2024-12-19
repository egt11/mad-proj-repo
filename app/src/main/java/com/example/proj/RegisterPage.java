package com.example.proj;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);

        initialize();
        setupDatePicker();
        setupHobbies();
        setupSecurityQuestions();
        setupRegisterButton();
    }

    private void initialize() {
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
        ivImage = findViewById(R.id.ivImage);
        btnCamera = findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterPage.this, LoginPage.class);
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
        String[] hobbies = {"Photography", "Woodworking", "Running", "Hiking", "Dancing",
                "Coding", "Cooking", "Online Gaming", "Geocaching", "Traveling",};

        LinearLayout hobbiesContainer = findViewById(R.id.hobbiesContainer);
        LinearLayout currentRow = null;
        for (int i = 0; i < hobbies.length; i++) {
            if (i % 2 == 0) {
                currentRow = new LinearLayout(this);
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                hobbiesContainer.addView(currentRow);
            }

            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(hobbies[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
            );

            params.setMargins(8, 8, 8, 8);
            checkBox.setLayoutParams(params);
            currentRow.addView(checkBox);
            hobbyCheckboxes.add(checkBox);
        }
    }

    private void setupSecurityQuestions() {
        String[] questions = {
                "Select a security question",
                "What is your mother's maiden name?",
                "What was the name of your first pet?",
                "What was the name of the street you grew up on?",
                "What is your favorite movie or book?",
                "What is the name of your favorite teacher?"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.item_spinner, questions);
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
        if (isEmpty(etUsername) || isEmpty(etPassword) || isEmpty(etConfirmPassword) ||
                isEmpty(etFirstName) || isEmpty(etLastName) || isEmpty(etEmail) ||
                isEmpty(etAddress) || isEmpty(etContact) ||
                tvBday.getText().toString().equals("Date of Birth")) {
            showAlert("ATTENTION!", "All fields are required");
            return false;
        }

        if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            showAlert("ATTENTION!", "Passwords do not match");
            return false;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            showAlert("ATTENTION!", "Please select a gender");
            return false;
        }

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
                    Intent intent = new Intent(RegisterPage.this, LoginPage.class);
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
        summary.append("Username: ").append(etUsername.getText()).append("\n");
        summary.append("First Name: ").append(etFirstName.getText()).append("\n");
        summary.append("Last Name: ").append(etLastName.getText()).append("\n");
        summary.append("Email: ").append(etEmail.getText()).append("\n");
        summary.append("Date of Birth: ").append(tvBday.getText()).append("\n");
        RadioButton selectedGender = findViewById(rgGender.getCheckedRadioButtonId());
        summary.append("Gender: ").append(selectedGender.getText()).append("\n");
        summary.append("Address: ").append(etAddress.getText()).append("\n");
        summary.append("Contact: ").append(etContact.getText()).append("\n");

        summary.append("Hobbies: ");
        for (CheckBox checkBox : hobbyCheckboxes) {
            if (checkBox.isChecked()) {
                summary.append(checkBox.getText()).append(", ");
            }
        }
        summary.setLength(summary.length() - 2);
        summary.append("\n");
        summary.append("Security Questions:\n");
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

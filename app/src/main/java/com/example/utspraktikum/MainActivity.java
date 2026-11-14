package com.example.utspraktikum;   // sesuaikan dengan nama package project Anda

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etNpm, etAlamat, etTanggalLahir;
    Button btnSimpan, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etNpm = findViewById(R.id.etNpm);
        etAlamat = findViewById(R.id.etAlamat);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnReset = findViewById(R.id.btnReset);

        // Klik kolom tanggal lahir -> tampilkan DatePicker
        etTanggalLahir.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(
                    MainActivity.this,
                    (view, year, month, day) -> etTanggalLahir.setText(
                            String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month + 1, year)),
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            dialog.show();
        });

        // Tombol SIMPAN -> validasi lalu pindah ke HasilActivity
        btnSimpan.setOnClickListener(v -> {
            String nama = etNama.getText().toString().trim();
            String npm = etNpm.getText().toString().trim();
            String alamat = etAlamat.getText().toString().trim();
            String tglLahir = etTanggalLahir.getText().toString().trim();

            if (nama.isEmpty() || npm.isEmpty() || alamat.isEmpty() || tglLahir.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, HasilActivity.class);
            intent.putExtra("nama", nama);
            intent.putExtra("npm", npm);
            intent.putExtra("alamat", alamat);
            intent.putExtra("tglLahir", tglLahir);
            startActivity(intent);
        });

        // Tombol RESET -> kosongkan semua isian
        btnReset.setOnClickListener(v -> {
            etNama.setText("");
            etNpm.setText("");
            etAlamat.setText("");
            etTanggalLahir.setText("");
            etNama.requestFocus();
        });
    }
}

package com.example.utspraktikum;   // sesuaikan dengan nama package project Anda

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class HasilActivity extends AppCompatActivity {

    TextView tvNama, tvNpm, tvAlamat, tvTanggalLahir, tvUmur;
    Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        tvNama = findViewById(R.id.tvNama);
        tvNpm = findViewById(R.id.tvNpm);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvTanggalLahir = findViewById(R.id.tvTanggalLahir);
        tvUmur = findViewById(R.id.tvUmur);
        btnKembali = findViewById(R.id.btnKembali);

        // Ambil data dari MainActivity
        String nama = getIntent().getStringExtra("nama");
        String npm = getIntent().getStringExtra("npm");
        String alamat = getIntent().getStringExtra("alamat");
        String tglLahir = getIntent().getStringExtra("tglLahir");   // format dd/MM/yyyy

        tvNama.setText(nama);
        tvNpm.setText(npm);
        tvAlamat.setText(alamat);
        tvTanggalLahir.setText(tglLahir);
        tvUmur.setText(hitungUmur(tglLahir) + " Tahun");

        // Kembali ke form
        btnKembali.setOnClickListener(v -> finish());
    }

    // Hitung umur dari string "dd/MM/yyyy"
    private int hitungUmur(String tglLahir) {
        String[] bagian = tglLahir.split("/");
        int hari = Integer.parseInt(bagian[0]);
        int bulan = Integer.parseInt(bagian[1]);
        int tahun = Integer.parseInt(bagian[2]);

        Calendar sekarang = Calendar.getInstance();
        int umur = sekarang.get(Calendar.YEAR) - tahun;

        int bulanSekarang = sekarang.get(Calendar.MONTH) + 1;
        int hariSekarang = sekarang.get(Calendar.DAY_OF_MONTH);

        // Jika belum ulang tahun tahun ini, kurangi 1
        if (bulanSekarang < bulan || (bulanSekarang == bulan && hariSekarang < hari)) {
            umur--;
        }
        return umur;
    }
}

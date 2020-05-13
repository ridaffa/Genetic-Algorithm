A.	Analisa Soal 
  
Dengan mencoba bruteforce , nilai yang didapatkan berkisar -2 < h . 
 
B.	Strategi Penyelesaian Masalah 
1.	Menggunakan rumus fitness = -h, karena mencari nilai minimum pada rumus di atas yang hasilnya ada yang kurang dari 0 
2.	Mencari parent menggunakan metode/algoritma Tournament Selection 
 
 
C.	Parameter yang digunakan beserta nilai optimal yang dipakai 
a.	Ukuran Populasi = 100 
b.	Panjang Kromosom = 10 
c.	Probabilitas Mutasi = 0.7 
d.	Probabilitas Crossover = 0.8 
e.	Generasi = 20 

 
D.	Tabel Percobaan 
Percobaan ke- 	X1 	X2 
1 	-0.09003090030900296 	0.7126671266712665 
2 	0.08973089730897277 	-0.7119871198711987 
3 	0.0898508985089852 	-0.7126671266712674 
4 	-0.09003090030900296 	0.7119871198711989 
5 	-0.08973089730897321 	0.7119871198711989 
 	 
E.	Kesimpulan 
GA yang digunakan dalam rumus di atas dalam mencari nilai minimum dengan parameter x1 dan x2 tidak selalu menghasilkan output yang sama. Output yang dihasilkan sangat mendekati nilai minimum asli (jika dihitung secara matematis). Berdasarkan tabel di atas nilai x1 dan x2 untuk nilai minimum adalah (0.089 , -0.712) dan (-0.089 , 0.712) 
 

package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void run(View v){

        int len = 4, exit = -1, y;
        int[] x = new int[len];
        int[][] a = new int[len + 1][len];
        int[][] a_start = new int[len + 1][len];
        int[][] a_a = new int[len + 1][len];
        double[] b = new double[len + 1];
        double[] c = new double[len + 1];
        int mutation = 20;
        int min_itteration = -1, mutation_result = 0;
        double ser = 0;


        int n;
        EditText N;
        N = (EditText) findViewById(R.id.x1);   //Ввод числа
        x[0] = Integer.parseInt(N.getText().toString());
        N = (EditText) findViewById(R.id.x2);   //Ввод числа
        x[1] = Integer.parseInt(N.getText().toString());
        N = (EditText) findViewById(R.id.x3);   //Ввод числа
        x[2] = Integer.parseInt(N.getText().toString());
        N = (EditText) findViewById(R.id.x4);   //Ввод числа
        x[3] = Integer.parseInt(N.getText().toString());
        N = (EditText) findViewById(R.id.y);   //Ввод числа
        y = Integer.parseInt(N.getText().toString());


        TextView txt = (TextView) findViewById(R.id.result);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText("Перше покоління:\n");


        for (int i = 0; i < len + 1; i++) {
            for (int ii = 0; ii < len; ii++) {
                a_start[i][ii]=a[i][ii] = (int) (Math.random() * y / 2);
                txt.setText(txt.getText()+""+x[ii] + "*" + a[i][ii] + " ");
                if (ii != len - 1)
                    txt.setText(txt.getText()+"+ ");
            }
            txt.setText(txt.getText()+"\n");
        }

        do {
            int kol_itteration = 0;
            exit=-1;
            mutation+=10;
            for (int i = 0; i < len + 1; i++)
                for (int ii = 0; ii < len; ii++) {
                    a[i][ii]=a_start[i][ii];
                }


            do {
                kol_itteration += 1;
                double max = 0;
                int i_max = 0;
                //сума поколінь, загальна сума коефіцієнтів
                double sum = 0, koef = 0;
                for (int i = 0; i < len + 1; i++) {
                    b[i] = 0;
                    for (int ii = 0; ii < len; ii++) {
                        b[i] += a[i][ii] * x[ii];
                    }
                    c[i] = b[i];
                    b[i] = b[i] - y;
                    if (b[i] < 0)
                        b[i] *= -1;

                    sum += b[i];

                    if (b[i] > max) {
                        max = b[i];
                        i_max = i;
                    }

                    if (b[i] == 0)
                        exit = i;
                    else
                        koef += 1 / b[i];
                    //System.out.println(" -- " + koef);
                }


                //-----------------------------------------------------------
            /*for (int i = 0; i < len + 1; i++) {
                for (int ii = 0; ii < len; ii++) {
                    txt.setText(txt.getText()+""+x[ii] + "*" + a[i][ii] + " ");
                    if (ii != len - 1)
                        txt.setText(txt.getText()+"+ ");
                }
                txt.setText(txt.getText()+"= " + c[i]+"\n");
            }
            txt.setText(txt.getText()+"\n\n");
            for (int i = 0; i < len + 1; i++) {
                for (int ii = 0; ii < len; ii++) {
                    txt.setText(txt.getText()+""+x[ii] + "*" + a[i][ii] + " ");
                    if (ii != len - 1)
                        txt.setText(txt.getText()+"+ ");
                }
                txt.setText(txt.getText()+"= " + c[i]+"\n");
            }
            txt.setText(txt.getText()+"\n\n");
*/

                if (exit == -1) {
                    //розрахунок ймовірностей, знаходження максимальної ймовірності і сума всіх ймовірностей
                    for (int i = 0; i < len + 1; i++) {
                        b[i] = 1 / b[i] / koef;
                    }


                    int suma = 0;
                    int mutation_rand = (int) (Math.random() * 100);
                    //мутація, якщо потомство гірше предків

                    if (mutation_rand <= mutation) {
                        for (int i = 0; i < len; i++) {
                            a[i_max][i] = (int) (Math.random() * y / 2);
                            suma += a[i_max][i] * x[i];
                        }
                        if (suma - y == 0)
                            exit = i_max;


                        //------
                        System.out.println("Мутація:");
                        for (int i = 0; i < len + 1; i++) {
                            for (int ii = 0; ii < len; ii++) {
                                System.out.print(+x[ii] + "*" + a[i][ii] + " ");
                                if (ii != len - 1)
                                    System.out.print("+ ");
                            }
                            System.out.println();
                        }
                        System.out.println();

                    }


                    //схрещування
                    if (exit == -1) {
                        for (int i = 0; i < len + 1; i++) {
                            int q = (int) (Math.random() * 4);
                            if (q <= b[0])
                                q = 0;
                            else if (q > b[0] && q <= b[0] + b[1])
                                q = 1;
                            else if (q > b[0] && q <= b[0] + b[1] + b[2])
                                q = 2;
                            else if (q > b[0] && q <= b[0] + b[1] + b[2] + b[3])
                                q = 3;
                            else if (q > b[0] && q <= b[0] + b[1] + b[2] + b[3] + b[4])
                                q = 4;

                            int w;
                            do {
                                w = (int) (Math.random() * 4);
                                if (w <= b[0])
                                    w = 0;
                                else if (w > b[0] && w <= b[0] + b[1])
                                    w = 1;
                                else if (w > b[0] && w <= b[0] + b[1] + b[2])
                                    w = 2;
                                else if (w > b[0] && w <= b[0] + b[1] + b[2] + b[3])
                                    w = 3;
                                else if (w > b[0] && w <= b[0] + b[1] + b[2] + b[3] + b[4])
                                    w = 4;
                            } while (w == q);

                            int e = (int) (Math.random() * len - 1);

                            for (int ii = 0; ii <= e; ii++) {
                                a_a[i][ii] = a[w][ii];
                            }
                            for (int ii = e + 1; ii < len; ii++) {
                                a_a[i][ii] = a[q][ii];
                            }


                        }


                        for (int i = 0; i < len + 1; i++)
                            for (int ii = 0; ii < len; ii++) {
                                a[i][ii] = a_a[i][ii];
                            }

                        //-----------------------------------------------------------
                        System.out.println("Схрещування:");
                        for (int i = 0; i < len + 1; i++) {
                            for (int ii = 0; ii < len; ii++) {
                                System.out.print(+x[ii] + "*" + a[i][ii] + " ");
                                if (ii != len - 1)
                                    System.out.print("+ ");
                            }
                            System.out.println();
                        }
                        System.out.println();

                    }
                }
            } while (exit == -1);


            txt.setText(txt.getText() + "\n\nВідповідь:\n");
            for (int i = 0; i < len; i++) {
                txt.setText(txt.getText() + "" + x[i] + "*" + a[exit][i] + " ");
                if (i != len - 1)
                    txt.setText(txt.getText() + "+ ");
            }
            txt.setText(txt.getText() + "\nЙмовірність мутації: "+mutation+"\nКількість ітерацій: "+kol_itteration);

            if(min_itteration==-1){
                min_itteration=kol_itteration;
                mutation_result=mutation;
            }
            else if(min_itteration>kol_itteration){
                min_itteration=kol_itteration;
                mutation_result=mutation;
            }
            System.out.println(mutation);

        }while (mutation<100);
        txt.setText(txt.getText() + "\n\nНайменша кількість ітерацій - "+min_itteration+" при ймовірності мутації - "+mutation_result);

        /*
        if (a * b == n)
            txt.setText(" " + a + " * " + b + " = " + n); //вывод
        else
            txt.setText("Этот метод не дал результата!"); //вывод
            */
    }
}

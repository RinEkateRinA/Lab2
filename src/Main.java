import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        int N = in.nextInt();
        double [][] a = new double[N][];
        //Заполняем двумерный массив
        for (int i = 0; i < a.length; i++) {
            int Mi = in.nextInt();
            a [i] = new double[Mi];
            for(int j = 0 ; j < a[i].length ; j++) {
                a[i][j] = in.nextDouble();
            }
        }
        int [] mine = new int[N];
        double [] symm = new double[N];
        double symmpol = 0;
        int kolot = 0;
        //Создаем два дополнительных массива, один из них заполняем количеством отрицательных чисел в строке, а второй суммой положительных чисел в этой же строке
        for (int i = 0 ; i < a.length ; i++) {
            for (int j = 0 ; j < a[i].length ; j++) {
                if (a[i][j] < 0)
                    kolot++;
                else
                    symmpol += a[i][j];
            }
            mine [i] = kolot;
            symm [i] = symmpol;
            symmpol = 0;
            kolot = 0;
        }
        int dlinst = 0;
        int kolch = 0;
        //Находим самую длинную сторку и считаем количество чисел в ней
        for (int i = 0 ; i < a.length ; i++) {
            for (int j = 0 ; j < a[i].length ; j++)
                kolch++;
            if(dlinst<kolch)
                dlinst = kolch;
            kolch = 0;
        }
        double [] perestonovka;
        //проверяем массив с количеством отрицательных чисел, если они расположены не по возрастанию, то меняем местами цифры, строки в двумерном массиве и массив с суммой положительных цифр тоже,
        // если же количество отрицательных чисел одинаковое, то проверяем массив с суммой положительных чисел и меняем местами значения, если первое значение больше второго
        for (int j = 0; j < a.length; j++) {
            for (int i = 0; i < a.length; i++) {
                if(i>0) {
                    if (mine[i - 1] > mine[i]){
                        kolot = mine[i - 1];
                        mine[i - 1] = mine[i];
                        mine[i] = kolot;
                        perestonovka = a[i - 1];
                        a[i-1] = a[i];
                        a[i] = perestonovka;
                        symmpol = symm[i-1];
                        symm[i-1] = symm[i];
                        symm[i] = symmpol;
                    }
                    else if(mine[i-1] == mine[i]){
                        if(symm[i-1] > symm[i]){
                            perestonovka = a[i - 1];
                            a[i-1] = a[i];
                            a[i] = perestonovka;
                            symmpol = symm[i-1];
                            symm[i-1] = symm[i];
                            symm[i] = symmpol;
                        }
                    }

            }
        }
        }
        double maxchisl = 0;
        int maxi = 0;
        int maxj = 0;
        //Находим максимальное число и запоминаем его индексы
        for (int i = 0 ; i < a.length ; i++) {
            for (int j = 0 ; j < a[i].length ; j++){
                if(maxchisl < a[i][j]){
                    maxchisl = a[i][j];
                    maxi = i;
                    maxj = j;
                }
            }
        }
        //Выводим эти значения на экран
        out.println("Максимальное число: " + maxchisl);
        out.println("Номер строки и позиция в строке: " + maxi + " " + maxj);
        //Делаем так, чтобы длиннна строк была везде одинаковая, заполняя пробелы звездочками и выводим двумерный массив на экран
        for (int i = 0 ; i < a.length ; i++) {
            for (int j = 0 ; j < a[i].length ; j++) {
                out.print((a[i][j]) + " ");
                kolch++;
            }
            while (kolch != dlinst){
                out.print("* ");
                kolch++;
            }
            kolch = 0;
            out.println();
        }
        out.println();
        // Заменяет все числа в массиве на их обратные значения и выводит полученный массив
        for (int i = 0 ; i < a.length ; i++) {
            for (int j = 0; j < a[i].length; j++) {
                double gul = 1/(a[i][j]);
                out.printf("%.3f", gul);
                out.print(" ");
            }
            out.println();
        }
    }
}
import java.util.Scanner;

public class Qtwo26 {
    static int findMainCell(int a[], int b[], int n, int n2){
        if(n == 0)
            return -1;
        if(n == 1)
            if(isMainCell(b, n2, a[0]))
                return a[0];
            else return -1;
        int cnt = 0;
        for(int i =0; i+1 < n; i++){
            if(a[i] == a[i+1]){
                b[cnt++] = a[i];
            }
        }
        int res = findMainCell(b, a, cnt, n);
        if (res == -1 && n % 2 == 1 && isMainCell(a, n, a[n - 1]))
            return a[n - 1];
        else
            return res;

    }

    static boolean isMainCell(int a[],int n,int x){
        int c = 0;
        for(int i = 0;i < n;i++)
            if(a[i] == x)
                c++;
        if(c > n/2)
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        int n ;

        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        String s[] = string.split(" ");
        n = s.length;

        int a[] = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = Integer.parseInt(s[i]);
        int temp = mainCell(a,n);
        if(temp == -1){
            System.out.println("no");
        }else {
            System.out.println(temp);
        }
    }

    public static int mainCell(int a[],int n){
        int i;
        int c;
        int count = 1;
        c = a[0];
        for(i = 1;i < n; i++){
            if(a[i] == c)
                count++;
            else{
                if(count > 0)
                    count--;
                else {
                    c = a[i];
                    count = 1;
                }
            }
        }
        if(count > 0){
            for(i = count = 0;i<n;i++){
                if(a[i] == c)
                    count++;
            }
        }
        if(count >n/2)
            return c;
        else return -1;
    }
}

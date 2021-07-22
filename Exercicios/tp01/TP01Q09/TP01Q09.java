import java.io.*;
import java.nio.charset.*;
import java.io.RandomAccessFile;

public class TP01Q09 {

    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception{
        RandomAccessFile file = new RandomAccessFile("TP01Q09.txt", "rw");
        int n = MyIO.readInt();
        double val = 0.0;

        for (int i = 0; i < n; i++) {
            file.writeDouble(MyIO.readDouble());
        }
        long fSize = file.getFilePointer();
        for (int i = 1; i <= n; i++) {
            file.seek(fSize - (i*8));
            val = file.readDouble();
            if ( val == (int) val) {
                System.out.printf("%.0f\n",val);
            }else {
                System.out.println(val);
            }
        }

        file.close();
    }
    

}
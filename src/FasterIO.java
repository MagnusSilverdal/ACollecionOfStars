import java.io.*;
import java.util.StringTokenizer;
/**
 * Created by magnus on 2022-12-06.
 * IO-routines. Trying to improve performance.
 */
public class FasterIO extends PrintWriter {
    public FasterIO(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public FasterIO(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public void setInputStream(InputStream i) {
        try {
            r.close();
            r = new BufferedReader(new InputStreamReader(i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public boolean hasMoreLines() { return peekLine() != null;}

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    public String getLine() {
        return nextLine();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;
    private int stringTokenizerPosition = 0;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    stringTokenizerPosition = 0;
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String peekLine() {
        if (line == null) {
            try {
                line = r.readLine();
                stringTokenizerPosition = 0;
                return line;
            } catch (IOException e) {
            }
        }

        // Catch half-read line instead
        String newLine = line.substring(stringTokenizerPosition);
        token = null;
        try {
            line = r.readLine();
            stringTokenizerPosition = 0;
            if (line != null)
                st = new StringTokenizer(line);
            else {
                st = new StringTokenizer("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newLine;
    }

    private String nextToken() {
        String ans = peekToken();
        stringTokenizerPosition += token.length();
        if (st.hasMoreTokens())
            while (Character.isWhitespace(line.charAt(stringTokenizerPosition)))
                stringTokenizerPosition++;
        token = null;
        return ans;
    }

    private String nextLine() {
        String ans = peekLine();
        //line = null;
        return ans;
    }

    public void openData(String filename) {
        try {
            setInputStream(new FileInputStream("Inputs/" + filename));
        } catch (IOException e) {
            System.err.println("IO error" + e + " use keyboard");
        }
    }
}
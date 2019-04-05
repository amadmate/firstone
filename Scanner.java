package aufgabe_23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

public class Scanner {

    private Reader reader;
    private Token nextToken;
    private Object nextValue;

    public Scanner(InputStream arg02) {
        // Roughly 1 lines of implementation
        this(new InputStreamReader(arg02));
    }

    public Scanner(Reader arg02) {
        // Roughly 3 lines of implementation
        this.reader = new BufferedReader(arg02);
        this.nextToken = null;
        this.nextValue = null;
        try {
            reader.mark(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getChar() throws IOException {
        // Roughly 1 lines of implementation
        return readChar(false);
    }

    Object getValue() {
        // Roughly 1 lines of implementation
        return this.nextValue;
    }

    Token scanToken() {
        // Roughly 44 lines of implementation
        try {
            int x = readChar(false);
            StringBuilder sb = new StringBuilder();

            switch (x) {
                case -1:
                    return null;
                case '+':
                    return Token.PLUS;                     
                case '-':
                    return Token.MINUS;
                case '/':
                    return Token.SLASH;
                case '*':
                    return Token.STAR;
                case '(':
                    return Token.OPEN;
                case ')':
                    return Token.CLOSE;
                case ';':
                    return Token.END;
                case ' ':
                    return scanToken();
                    
            }
            if(Character.isDigit(x)) {
                sb.append((char) x);

              
                // read all numbers before point or 'e'
                while (Character.isDigit(peekChar()) && peekChar() > -1) {
                    // read current number
                    x = getChar();

                    // if not end of input
                    if (x != -1) {
                        // add current char
                        sb.append((char) x);
                    }
                }
                
                if (peekChar() == '.') {
                    // read point
                    x = getChar();
                    sb.append((char) x);
                    

                    boolean numberAfterPoint = false;

                    // read all numbers before 'e'
                    while (Character.isDigit(peekChar()) && peekChar() > -1) {
                        numberAfterPoint = true;
                        // read current number
                        x = getChar();

                        // if not end of input
                        if (x != -1) {
                            // add current char
                            sb.append((char) x);
                        }
                        
 
                    }
                    
                    if (!numberAfterPoint || peekChar() == '.' ) {
                        throw new IllegalArgumentException("Illegal float number");
                    }
                   
                }
              
                if (peekChar() == 'e' || peekChar() == 'E') {
                    // read e
                     x = getChar();
                    // Add e
                    sb.append((char) x);

                    if (peekChar() == '-') {
                        // read minus
                        x = getChar();
                        sb.append((char) x);
                    }

                    boolean numberAfterExponent = false;

                   
                    // read all numbers after 'e'
                    while (Character.isDigit( peekChar()) && peekChar() > -1 ) {
                        numberAfterExponent = true;
                        
                        // read current number
                        x = getChar();
                    

                        // if not end of input
                        if (x != -1) {
                            // add current char
                            sb.append((char) x);
                        }
                    }
                    if (!numberAfterExponent || peekChar() == '.' || peekChar() == 'e' || peekChar() == 'E') {
                        throw new IllegalArgumentException("Illegal float number");
                    }
                }
                nextValue = Float.valueOf(sb.toString());
                return Token.NUMBER;
            }
            throw new IllegalArgumentException("Illegal input character \"" + (char) x + "\"");
        } catch (IOException e) {

            throw new UncheckedIOException(e);
        }
    }

    Token getToken() {
        // Roughly 2 lines of implementation
        Token r = peekToken();
        nextToken = null;
        return r;
    }

    int readChar(boolean reset) throws IOException {
       
        reader.mark(1);
        int r = reader.read();
         if (reset) {
            reader.reset();
        }
         
          
        
        return r;
    }

    Token peekToken() {
        // Roughly 1 lines of implementation
        if (nextToken == null) {
            nextToken = scanToken();
        }
        return nextToken;
    }

    Object peekValue() {
        // Roughly 1 lines of implementation
        return this.nextValue;
    }

    private int peekChar() throws IOException {
        // Roughly 1 lines of implementation
        return readChar(true);
    }
}

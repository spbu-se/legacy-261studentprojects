package karymov;


import ast.*;
import java.awt.Color;
import java.io.*;
import javax.swing.JTextPane;
import javax.swing.text.*;
import lebedev.*;
import yaskov.*;

public class Controller {

    private IMainForm iMainForm;

    public Controller(IMainForm iMainForm) {
        this.iMainForm = iMainForm;
    }

    public void openFile(File file) {
        iMainForm.clearOutputPane();
        iMainForm.clearErrorPane();
        try {
            InputStream fileInpStream = new FileInputStream(file);
            int size = fileInpStream.available();
            fileInpStream.close();
            char[] buff = new char[size];
            Reader fileReadStream = new FileReader(file);
            int count = fileReadStream.read(buff);
            iMainForm.getTextPanel().setText(String.copyValueOf(buff));
            fileReadStream.close();
        } catch (FileNotFoundException Exception) {
            iMainForm.setTextInErrorPane("File can not open");
        } catch (IOException Exception) {
            iMainForm.setTextInErrorPane("File can't be read");
        }

    }

    public void saveFile(String textProgramm, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(textProgramm);
            fileWriter.close();
        } catch (IOException Exception) {
            iMainForm.setTextInErrorPane("File can't be write");
        } catch (SecurityException Exception) {
            iMainForm.setTextInErrorPane("File can not find");
        }
    }

    public String runProgram(String input) {
        Lexer lexer = new Lexer(input + '\0');
        lexer.analyzeSourceProgram();

        Parser parser = new Parser(lexer.getTokenStream(), lexer.getErrorQnt());
        parser.parseProgramm();

        Interpreter interpreter = new Interpreter(parser.getOutput(), parser.getErrorQnt(), false);
        interpreter.interpretProgram();

        if (lexer.getErrorQnt() + parser.getErrorQnt() + interpreter.getErrorQnt() > 0) {
            String errorLog = lexer.getErrorLog() + parser.getErrorLog() + interpreter.getErrorLog();
            return "there are some errors in source program:\n" + errorLog;
        } else {
            return interpreter.getOutput();
        }
    }

    public void lightKeywords(JTextPane pane) {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Color.blue);
        String[] keywordList = {"true", "false", "let", "in", "begin", "end", "if", "then", "print","else"};
        StyledDocument doc = pane.getStyledDocument();
        String currentTextProgramm = pane.getText();

        doc.setCharacterAttributes(0, pane.getText().length(), new SimpleAttributeSet(), true);

        for (String keyword : keywordList) {
            int pointer = 0;

            while ((pointer = currentTextProgramm.indexOf(keyword, pointer)) != -1) {
                boolean isKeyword = true;
                if (pointer > 0) {
                    if (((currentTextProgramm.charAt(pointer - 1) >= 'a') && (currentTextProgramm.charAt(pointer - 1) <= 'z')) ||
                            ((currentTextProgramm.charAt(pointer - 1) >= 'A') && (currentTextProgramm.charAt(pointer - 1) <= 'Z')) ||
                            ((currentTextProgramm.charAt(pointer - 1) >= '0') && (currentTextProgramm.charAt(pointer - 1) <= '9'))) {
                        isKeyword = false;
                    }
                }
                if (pointer + keyword.length() < currentTextProgramm.length()) {
                    if (((currentTextProgramm.charAt(pointer + keyword.length()) >= 'a') && (currentTextProgramm.charAt(pointer + keyword.length()) <= 'z')) ||
                            ((currentTextProgramm.charAt(pointer + keyword.length()) >= 'A') && (currentTextProgramm.charAt(pointer + keyword.length()) <= 'Z')) ||
                            ((currentTextProgramm.charAt(pointer + keyword.length()) >= '0') && (currentTextProgramm.charAt(pointer + keyword.length()) <= '9'))) {
                        isKeyword = false;
                    }
                }
                if (isKeyword) {
                    doc.setCharacterAttributes(pointer - GetLineNumber(pointer,pane), keyword.length(), attr, false);
                }
                pointer++;
            }
        }
        pane.setCharacterAttributes(new SimpleAttributeSet(), true);
    }
     private int GetLineNumber(int pointer,JTextPane pane) {
        int i = 0;
        int line = 0;
        while (i < pointer) {
            if (pane.getText().charAt(i) == '\n') {
                line++;
            }
            i++;
        }
        return line;
    }


}

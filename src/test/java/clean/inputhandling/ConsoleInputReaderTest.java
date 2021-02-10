package clean.inputhandling;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class ConsoleInputReaderTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void UserInputReaderTest(){
        systemInMock.provideText("ADDITION");
        Scanner sc = new Scanner(System.in);
        assertEquals("ADDITION", sc.nextLine());
    }
}
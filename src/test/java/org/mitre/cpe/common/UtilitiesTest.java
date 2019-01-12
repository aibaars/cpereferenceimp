package org.mitre.cpe.common;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class UtilitiesTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    @Parameters({"cpe:2.3:a:adobe:*:9.*:*:PalmOS:*:*:*:*:*",
        "cpe:2.3:a:hp:insight_diagnostics:7.4.0.1570:-:*:*:online:win2003:x64:*",
        "cpe:2.3:a:adobe:*:9.*:*:PalmOS:with\\:quoted:\\::colons:*:*",
        "cpe:2.3:a:adobe:*:9.*:*:with:quoted:colon:at:the:end\\:"})
    public void testValidateFsHappyCase(String fs) throws ParseException {
        Utilities.validateFS(fs);
    }

    @Test
    @Parameters({"cpe:2.3:a:*:*:*:*:empty:element:at:the:end:",
        "cpe:2.3:a:adobe:*:9.*:empty:element:in:the:middle::*",
        "wrong:start:a:adobe:*:9.*:*:PalmOS:*:*:*:*:*",
        "cpe:2.3:a:adobe:*:9.*:*:*:*:13:elements:*:*:*",
        "cpe:2.3:a:adobe:*:9.*:*:more:than:13:elements:*:*:*:*:*",
        "cpe:2.3:a:adobe:*:9.*:*:*:*:11:elements:*",
        "cpe:2.3:a:adobe:*:less:than:11:elements:*"})
    public void testValidateFsInvalid(String fs) throws ParseException {
        exception.expect(ParseException.class);
        Utilities.validateFS(fs);
    }

    @Test
    @Parameters({"asdf, 0, 4, 0",
        "as\\df, 0, 5, 1",
        "as\\\\df, 0, 6, 1",
        "as\\\\\\df, 0, 7, 2",
        "\\.\\?\\&asdf, 0, 10, 3",
        "\\.\\?\\&asdf, 2, 10, 2",
        "\\.\\?\\&asdf, 3, 10, 1",
        "\\.\\?\\&asdf, 0, 1, 1",
        "\\.\\?\\&asdf, 2, 5, 2",
        "\\.\\?\\&asdf, 0, 0, 0",
        "as\\df, -4, 5, 1",
    })
    public void testCountEscapeCharactersHappyCase(String str, int start, int end, int expected) {
        assertEquals(expected, Utilities.countEscapeCharacters(str, start, end));
    }

    @Test
    public void testCountEscapeCharactersEndOutOfBounds() {
        exception.expect(IndexOutOfBoundsException.class);
        Utilities.countEscapeCharacters("as\\df", 0, 15);
    }

    @Test
    public void testValidateURIHappyCase() throws ParseException {
        Utilities.validateURI("cpe:/a:hp:insight_diagnostics:7.4.0.1570::~~online~win2003~x64~");
    }

    @Test
    @Parameters({"cpe:2.3:/a:wrong:start",
        "cpe:/a:hp:insight_diagnostics:7.4.0.1570::~~online~win2003~x64~::wrong_number_elements"
    })
    public void testValidateURIInvalid(String uri) throws ParseException {
        exception.expect(ParseException.class);
        Utilities.validateURI(uri);
    }

    @Test
    @Parameters({"0, true",
        "1, false",
        "8, true",
        "-7, false",
        "-16, true"})
    public void testIsEvenNumber(int input, boolean expected) {
        assertEquals(expected, Utilities.isEvenNumber(input));
    }

    @Test
    @Parameters({"foo, false",
        "foo\\?, false",
        "foo?, true",
        "\\*bar, false",
        "*bar, true",
        "\\*foo?, true"})
    public void testContainsWildcards(String input, boolean expected) {
        assertEquals(expected, Utilities.containsWildcards(input));
    }
}

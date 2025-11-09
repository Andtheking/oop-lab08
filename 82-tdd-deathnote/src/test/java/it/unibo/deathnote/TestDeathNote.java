package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.impl.DeathNoteImpl;
import it.unibo.deathnote.api.DeathNote;

public class TestDeathNote {
    private final String NAME1 = "L Lawliet";
    private final String NAME2 = "Nate River";


    DeathNote dn;

    @BeforeEach 
    public void setUp() {
        dn = new DeathNoteImpl();
    }

    // TODO: Ask teacher about code repetition in tests
    @Test
    public void testNegativeOrZeroRule() {
        var ruleTests = List.of(-1,0);
        for (final var test : ruleTests) {
            String message = "Rule test failed on case: " + test;
            final var e = assertThrows(
                IllegalArgumentException.class, 
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        dn.getRule(test);
                    }
                }, 
                message
            );
            assertNotNull(e.getMessage(), message);
            assertNotEquals("", e.getMessage().strip(), message);
        }
    }

    @Test
    public void testForEmptyRules() {
        for (String rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertNotEquals("", rule.strip());
        }
    }

    @Test
    public void testWriteHuman() {
        assertFalse(dn.isNameWritten(NAME1));
        dn.writeName(NAME1);
        assertTrue(dn.isNameWritten(NAME1));
        assertFalse(dn.isNameWritten(NAME2));
        assertFalse(dn.isNameWritten(""));
    }

    // @Test
    // public void testCauseOfDeath() {
    //     assertThrows(IllegalStateException.class, new Executable() {
    //         @Override
    //         public void execute() throws Throwable {
    //             dn.writeDeathCause(NAME1);
    //         }
    //     });
    // }


}
package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.impl.DeathNoteImpl;
import it.unibo.deathnote.api.DeathNote;

/**
 * Test class for DeathNote.
 */ 
final class TestDeathNote {
    private static final String L_LAWLIET = "L Lawliet";
    private static final String NATE_RIVER = "Nate River";
    private static final String DEATH_CAUSE = "Karting accident";
    private static final String DEATH_DETAILS = "Ran for too long";
    private static final int DEATH_DETAILS_WAIT = 6100; // milliseconds
    private static final int DEATH_CAUSE_WAIT = 100; // milliseconds
    private DeathNote dn;

    /**
     * Create a new instance of DeathNote for a clean test each time.
     */
    @BeforeEach 
    void setUp() {
        dn = new DeathNoteImpl();
    }

    /**
     * Test that no negative nor zero rule exists.
     */
    @Test
    void testNegativeOrZeroRule() {
        final var ruleTests = List.of(-1, 0);
        for (final var test : ruleTests) {
            final String message = "Rule test failed on case: " + test;
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
            assertException(e);
        }
    }

    /**
     * Test if there is any empty rule.
     */
    @Test
    void testForEmptyRules() {
        for (final String rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertNotEquals("", rule.strip());
        }
    }

    /**
     * Test the write process of a human.
     */
    @Test
    void testWriteHuman() {
        assertFalse(dn.isNameWritten(L_LAWLIET));
        dn.writeName(L_LAWLIET);
        assertTrue(dn.isNameWritten(L_LAWLIET));
        assertFalse(dn.isNameWritten(NATE_RIVER));
        assertFalse(dn.isNameWritten(""));
    }

    /**
     * Test the write process of a death cause.
     */
    @Test
    void testCauseOfDeath() {
        final var deathCauseException = assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                dn.writeDeathCause(DEATH_CAUSE);
            }
        });
        assertException(deathCauseException);
        dn.writeName(L_LAWLIET);
        assertTrue(dn.isNameWritten(L_LAWLIET));
        assertEquals(DeathNote.HEART_ATTACK_DEATH, dn.getDeathCause(L_LAWLIET));
        dn.writeName(NATE_RIVER);
        assertTrue(dn.writeDeathCause(DEATH_CAUSE));
        assertEquals(DEATH_CAUSE, dn.getDeathCause(NATE_RIVER));
        try {
            Thread.sleep(DEATH_CAUSE_WAIT);
        } catch (final InterruptedException e) {
            fail("Test failed due to thread error: ", e);
        }
        assertFalse(dn.writeDeathCause(DeathNote.HEART_ATTACK_DEATH));
        assertEquals(DEATH_CAUSE, dn.getDeathCause(NATE_RIVER));
    }

    /**
     * Test the write process of the death details.
     */
    @Test
    void testDeathDetails() {
        assertFalse(dn.writeDetails(DEATH_DETAILS));
        dn.writeName(L_LAWLIET);
        assertEquals("", dn.getDeathDetails(L_LAWLIET));
        assertTrue(dn.writeDetails(DEATH_DETAILS));
        assertEquals(DEATH_DETAILS, dn.getDeathDetails(L_LAWLIET));
        dn.writeName(NATE_RIVER);
        try {
            Thread.sleep(DEATH_DETAILS_WAIT);
        } catch (final InterruptedException e) {
            fail("Test failed due to thread error: ", e);
        }
        assertFalse(dn.writeDetails(DEATH_DETAILS));
        assertNotEquals(DEATH_DETAILS, dn.getDeathDetails(NATE_RIVER));
    }

    private void assertException(final Exception e) {
        assertNotEquals(null, e.getMessage());
        assertNotEquals("", e.getMessage().strip());
    }
}

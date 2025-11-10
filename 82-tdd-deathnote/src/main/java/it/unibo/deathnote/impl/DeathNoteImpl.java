package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of {@Link DeathNote}.
 */
public class DeathNoteImpl implements DeathNote {
    private final Map<String, Death> kills = new HashMap<>();
    private String lastNameWritten;
    private long timeLastNameWritten;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNotFound(ruleNumber)) {
            throw new IllegalArgumentException(
                "The rule " 
                    + ruleNumber
                    + " does not exists. Try with a number between 0 and " 
                    + RULES.size()
                    + " not included."
            );
        }
        return RULES.get(ruleNumber);
    }

    private boolean ruleNotFound(final int ruleNumber) {
        return ruleNumber > RULES.size() || ruleNumber <= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {
        Objects.requireNonNull(name, "Name to write cannot be null");
        if (isNameWritten(name)) {
            throw new IllegalArgumentException("The person \"" + name + "\" is already written in this DeathNote.");
        }
        kills.put(name, new Death());
        lastNameWritten = name;
        timeLastNameWritten = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (lastNameWritten == null) {
            throw new IllegalStateException("No human name was written.");
        }
        if (System.currentTimeMillis() - timeLastNameWritten > TIME_ALLOWED_FOR_CAUSE_OF_DEATH) {
            return false;
        }
        final var death = Objects.requireNonNull(
            kills.get(lastNameWritten),
            "Inconsistent state..."
        );
        death.setDeathCause(cause);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        if (lastNameWritten == null) {
            return false;
        }
        if (System.currentTimeMillis() - timeLastNameWritten > TIME_ALLOWED_FOR_DETAILS_OF_DEATH) {
            return false;
        }
        final var death = Objects.requireNonNull(
            kills.get(lastNameWritten)
        );
        death.setDeathDetails(details);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        Objects.requireNonNull(name);
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("The name \"" + name + "\" does not appear in the notes");
        }
        return kills.get(name).deathCause;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        Objects.requireNonNull(name);
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("The name \"" + name + "\" does not appear in the notes");
        }
        return kills.get(name).deathDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        Objects.requireNonNull(name);
        return kills.containsKey(name);
    }

    private final class Death {
        private String deathCause;
        private String deathDetails;

        Death() {
            this.deathCause = HEART_ATTACK_DEATH;
            this.deathDetails = "";
        }

        void setDeathCause(final String deathCause) {
            this.deathCause = deathCause;
        }

        void setDeathDetails(final String deathDetails) {
            this.deathDetails = deathDetails;
        }
    }
}


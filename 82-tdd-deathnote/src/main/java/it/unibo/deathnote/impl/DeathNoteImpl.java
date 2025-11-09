package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {
    // Map<String, DeathDetails> kills;

    // public DeathNoteImpl() {
    //     kills = new HashMap<>();
    // }

    @Override
    public String getRule(int ruleNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRule'");
    }

    // @Override
    // public String getRule(final int ruleNumber) {
    //     if (ruleNotFound(ruleNumber)) {
    //         throw new IllegalArgumentException("The rule " + 
    //             ruleNumber + 
    //             " does not exists. Try with a number between 0 and " + 
    //             RULES.size() + 
    //             " not included."
    //         );
    //     }
    //     return RULES.get(ruleNumber);
    // }

    private boolean ruleNotFound(final int ruleNumber) {
        return ruleNumber >= RULES.size() || ruleNumber < 0;
    }

    // @Override
    // public void writeName(String name) {
    //     Objects.requireNonNull(name, "Name to write cannot be null");
    //     if (kills.containsKey(name)) {
    //         throw new IllegalArgumentException("The person \"" + name + "\" is already written in this DeathNote.");
    //     }
    //     kills.put(name, new DeathDetails());
    // }

    @Override
    public void writeName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeName'");
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }



    // private class DeathDetails {
    //     private String deathCause;
    //     private String deathDetails;
        
    //     DeathDetails(String deathCause, String deathDetails) {
    //         this.deathCause = deathCause;
    //         this.deathDetails = deathDetails;
    //     }

    //     DeathDetails() {
    //         this(null,null);
    //     }
    // }
}

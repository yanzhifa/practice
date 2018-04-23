package com.ldy.jackson.write;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A journal entry for a configuration action class.  Currently just an
 * arbitrary map of string -> object pairs, usage of which is up to
 * the configuration action.
 */
public class ConfigurationJournalEntry {

    /**
     * Arbitrary set of strings.
     */
    @JsonProperty("memos")
    private Set<String> memos;

    /**
     * Arbitrary set of strings for each host number.
     */
    @JsonProperty("hostMemos")
    private Map<String, Set<String>> hostMemos;


    public ConfigurationJournalEntry() {
        this.memos = new HashSet<String>();
        this.hostMemos = new HashMap<String, Set<String>>();
    }

    public boolean containsMemo(String key) {
        return this.memos.contains(key);
    }

    public void addMemo(String key) {
        this.memos.add(key);
    }

    public boolean containsHostMemo(Integer hostNumber, String key) {
        if (!this.hostMemos.containsKey(hostNumber.toString())) {
            return false;
        }
        return this.hostMemos.get(hostNumber.toString()).contains(key);
    }

    public void addHostMemo(Integer hostNumber, String key) {
        if (this.hostMemos.containsKey(hostNumber.toString())) {
            this.hostMemos.get(hostNumber.toString()).add(key);
        } else {
            final HashSet<String> newMemos = new HashSet<String>();
            newMemos.add(key);
            this.hostMemos.put(hostNumber.toString(), newMemos);
        }
    }


    public ConfigurationJournalEntry clone() {
        final HashMap<String, Set<String>> clonedHostMemos
                = new HashMap<String, Set<String>>();
        for (final Map.Entry<String, Set<String>> clonedHostMemoEntry:
                this.hostMemos.entrySet()) {
            final HashSet<String> clonedValue = new HashSet<String>(clonedHostMemoEntry.getValue());
            clonedHostMemos.put(clonedHostMemoEntry.getKey(), clonedValue);
        }

        final ConfigurationJournalEntry clone = new ConfigurationJournalEntry();
        clone.memos = new HashSet<String>(this.memos);
        clone.hostMemos = clonedHostMemos;
        return clone;
    }

}

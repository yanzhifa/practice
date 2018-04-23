package com.ldy.jackson.write;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Model encapsulating the journal for configuration.  Intended to be represented
 * in JSON form.
 * <p>
 * It contains a list of completed action classnames, as well as well as a map
 * of action classnames to (optional) journal entries
 */
public class ConfigurationJournal {

    /**
     * Locale
     */
    @JsonProperty("locale")
    Locale locale;


    /**
     * Class name of configuration process
     */
    @JsonProperty("process")
    String processName;

    /**
     * List of completed action classnames
     */
    @JsonProperty("completedActions")
    Set<String> completedActionsByClassName;

    /**
     * Map of action classnames to optional journal entries
     */
    @JsonProperty("journalEntries")
    Map<String, ConfigurationJournalEntry> journalEntriesByClassName;


    public ConfigurationJournal() {
        this.completedActionsByClassName = new HashSet<String>();
        this.journalEntriesByClassName = new HashMap<String, ConfigurationJournalEntry>();
        this.locale = Locale.US;
        this.processName = null;
    }

    public boolean containsCompletedActionByClassName(String className) {
        return this.completedActionsByClassName.contains(className);
    }

    public void addCompletedActionByClassNAme(String className) {
        this.completedActionsByClassName.add(className);
    }

    public boolean containsEntryByClassName(String className) {
        return this.journalEntriesByClassName.containsKey(className);
    }

    public ConfigurationJournalEntry getEntryByClassName(String className) {
        return this.journalEntriesByClassName.get(className);
    }

    public void setEntryByClassName(String className, ConfigurationJournalEntry entry) {
        this.journalEntriesByClassName.put(className, entry);
    }

    public Set<String> getCompletedActionsByClassName() {
        return completedActionsByClassName;
    }

    public void setCompletedActionsByClassName(Set<String> completedActionsByClassName) {
        this.completedActionsByClassName = completedActionsByClassName;
    }

    public Map<String, ConfigurationJournalEntry> getJournalEntriesByClassName() {
        return journalEntriesByClassName;
    }

    public void setJournalEntriesByClassName(Map<String, ConfigurationJournalEntry> journalEntriesByClassName) {
        this.journalEntriesByClassName = journalEntriesByClassName;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }


}

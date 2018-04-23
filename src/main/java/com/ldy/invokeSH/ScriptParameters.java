package com.ldy.invokeSH;

import java.util.ArrayList;

/**
 * A sequence of parameters being passed to a script.
 */
public class ScriptParameters {
    /**
     * Parameters passed to a script.
     */
    final ScriptParameter[] scriptParameters;

    /**
     * Constructor - takes any number of parameters
     */
    public ScriptParameters(ScriptParameter... scriptParameters) {
        this.scriptParameters = scriptParameters;
    }

    /**
     * Get parameters
     */
    public ScriptParameter[] getScriptParameters() {
        return scriptParameters;
    }

    /**
     * Return all plaintext of all parameters (convenience)
     */
    public String[] getPlaintext() {
        return buildPlaintextArray(false);
    }

    /**
     * Returns an escaped version of all the parameters.
     * @return
     */
    public String[] getPlaintextEscaped() {
        return buildPlaintextArray(true);
    }

    /**
     * Check whether contains sensitive parameter or not.
     * @return
     */
    public boolean hasSensitiveParameter() {
        for (ScriptParameter param : scriptParameters) {
            if (param.isSensitive()) {
                return true;
            }
        }
        return false;
    }

    private String[] buildPlaintextArray(boolean escape){
        final ArrayList<String> resultList = new ArrayList<>();
        for (int i = 0; i < this.scriptParameters.length; i++) {
            final String[] subParameters = escape ?
                    this.scriptParameters[i].getPlainTextEscaped() :
                    this.scriptParameters[i].getPlaintext();
            for (int j = 0; j < subParameters.length; j++) {
                resultList.add(subParameters[j]);
            }
        }
        return resultList.toArray(new String[resultList.size()]);
    }

    /**
     * Return the plaintext of parameters separated by spaces, with any sensitive parameters
     * scrubbed out
     */
    @Override
    public String toString() {
        if (this.scriptParameters.length == 0) {
            return "";
        } else {
            final StringBuffer sb = new StringBuffer();
            for (final ScriptParameter scriptParameter : this.scriptParameters) {
                sb.append(' ');
                sb.append(scriptParameter.toString());
            }
            return sb.substring(1);
        }
    }
}

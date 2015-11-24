package com.example.nixoid.taksitest.rest.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggest {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("suggestions")
    @Expose
    private List<Suggestion> suggestions = new ArrayList<Suggestion>();

    /**
     *
     * @return
     * The error
     */
    public Integer getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Integer error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The suggestions
     */
    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    /**
     *
     * @param suggestions
     * The suggestions
     */
    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
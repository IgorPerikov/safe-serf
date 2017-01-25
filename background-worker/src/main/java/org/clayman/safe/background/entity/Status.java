package org.clayman.safe.background.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("clean")
    CLEAN,
    @JsonProperty("unknown")
    UNKNOWN,
    @JsonProperty("malicious")
    MALICIOUS,
    @JsonProperty("phishing")
    PHISHING,
    @JsonProperty("spam")
    SPAM
}

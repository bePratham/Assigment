package com.example.scholarship.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "eligibility")
public class EligibilityCriteria {
    private int science;
    private int maths;
    private int english;
    private int computer;
}

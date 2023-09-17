package com.optimagrowth.license.events.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationChangeModel {

    private String type;
    private String action;
    private String organizationId;
    private String correlationId;


}

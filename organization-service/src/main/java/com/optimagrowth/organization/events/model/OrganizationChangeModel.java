package com.optimagrowth.organization.events.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class OrganizationChangeModel {
    private String type;
    private String action;
    private String organizationId;
    private String correlationId;
}

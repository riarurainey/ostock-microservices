package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.repository.LicenseRepository;
import com.optimagrowth.license.service.client.OrganizationDiscoveryClient;
import com.optimagrowth.license.service.client.OrganizationFeignClient;
import com.optimagrowth.license.service.client.OrganizationRestTemplateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messageSource;
    private final LicenseRepository repository;
    private final ServiceConfig config;
    private final OrganizationDiscoveryClient discoveryClient;
    private final OrganizationFeignClient feignClient;
    private final OrganizationRestTemplateClient restTemplateClient;


    public License getLicense(String licenseId, String organizationId, String clientType) {
        License license = repository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (null == license) {
            throw new IllegalArgumentException(String.format(messageSource.getMessage("license.search.error.message",
                    null, null), licenseId, organizationId));
        }

        Organization organization = retrieveOrganizationInfo(organizationId, clientType);
        if (null != organization) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }

        return license.withComment(config.getProperty());
    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        Organization organization = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = feignClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = restTemplateClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = discoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = restTemplateClient.getOrganization(organizationId);
                break;
        }

        return organization;
    }
    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        repository.save(license);

        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license) {
        repository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId) {
        License license = new License();
        license.setLicenseId(licenseId);
        repository.delete(license);
        return String.format(messageSource.getMessage("license.delete.message", null, null), licenseId);
    }

    public List<License> getLicensesByOrganization(String organizationId) {
        return repository.findByOrganizationId(organizationId);
    }
}
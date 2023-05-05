CREATE TABLE IF NOT EXISTS organizations
(
    organization_id varchar(255) NOT NULL,
    name varchar(255),
    contact_name varchar(255),
    contact_email varchar(255),
    CONSTRAINT organizations_key PRIMARY KEY (organization_id)
);

CREATE TABLE IF NOT EXISTS licenses
(
    license_id varchar(255) NOT NULL,
    organization_id varchar(255) NOT NULL,
    description varchar(255),
    product_name varchar(255) NOT NULL,
    license_type varchar(255) NOT NULL,
    comment varchar(255),
    CONSTRAINT licenses_pkey PRIMARY KEY (license_id),
    CONSTRAINT licenses_organization_id_fkey FOREIGN KEY (organization_id)
        REFERENCES organizations (organization_id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
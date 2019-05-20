package com.zendesk.search.model.data;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @author Sankash on 5/15/2019
 * Data class to hold the User data
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "url",
        "external_id",
        "name",
        "alias",
        "created_at",
        "active",
        "verified",
        "shared",
        "locale",
        "timezone",
        "last_login_at",
        "email",
        "phone",
        "signature",
        "organization_id",
        "tags",
        "suspended",
        "role"
})
public class User implements ZdData {

    private final static long serialVersionUID = 3531563685459955226L;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("external_id")
    private String externalId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("shared")
    private Boolean shared;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("last_login_at")
    private String lastLoginAt;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("organization_id")
    private String organizationId;
    @JsonProperty("tags")
    @Valid
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("suspended")
    private Boolean suspended;
    @JsonProperty("role")
    private String role;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param tags
     * @param shared
     * @param phone
     * @param lastLoginAt
     * @param alias
     * @param locale
     * @param organizationId
     * @param externalId
     * @param suspended
     * @param url
     * @param id
     * @param timezone
     * @param email
     * @param createdAt
     * @param verified
     * @param name
     * @param role
     * @param active
     * @param signature
     */
    public User(String id, String url, String externalId, String name, String alias, String createdAt, Boolean active, Boolean verified, Boolean shared, String locale, String timezone, String lastLoginAt, String email, String phone, String signature, String organizationId, List<String> tags, Boolean suspended, String role) {
        super();
        this.id = id;
        this.url = url;
        this.externalId = externalId;
        this.name = name;
        this.alias = alias;
        this.createdAt = createdAt;
        this.active = active;
        this.verified = verified;
        this.shared = shared;
        this.locale = locale;
        this.timezone = timezone;
        this.lastLoginAt = lastLoginAt;
        this.email = email;
        this.phone = phone;
        this.signature = signature;
        this.organizationId = organizationId;
        this.tags = tags;
        this.suspended = suspended;
        this.role = role;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public User withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public User withUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("external_id")
    public String getExternalId() {
        return externalId;
    }

    @JsonProperty("external_id")
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public User withExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public User withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    public User withActive(Boolean active) {
        this.active = active;
        return this;
    }

    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public User withVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }

    @JsonProperty("shared")
    public Boolean getShared() {
        return shared;
    }

    @JsonProperty("shared")
    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public User withShared(Boolean shared) {
        this.shared = shared;
        return this;
    }

    @JsonProperty("locale")
    public String getLocale() {
        return locale;
    }

    @JsonProperty("locale")
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public User withLocale(String locale) {
        this.locale = locale;
        return this;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public User withTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    @JsonProperty("last_login_at")
    public String getLastLoginAt() {
        return lastLoginAt;
    }

    @JsonProperty("last_login_at")
    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public User withLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
        return this;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @JsonProperty("signature")
    public String getSignature() {
        return signature;
    }

    @JsonProperty("signature")
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User withSignature(String signature) {
        this.signature = signature;
        return this;
    }

    @JsonProperty("organization_id")
    public String getOrganizationId() {
        return organizationId;
    }

    @JsonProperty("organization_id")
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public User withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public User withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @JsonProperty("suspended")
    public Boolean getSuspended() {
        return suspended;
    }

    @JsonProperty("suspended")
    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public User withSuspended(Boolean suspended) {
        this.suspended = suspended;
        return this;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    public User withRole(String role) {
        this.role = role;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public User withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("url", url).append("externalId", externalId).append("name", name).append("alias", alias).append("createdAt", createdAt).append("active", active).append("verified", verified).append("shared", shared).append("locale", locale).append("timezone", timezone).append("lastLoginAt", lastLoginAt).append("email", email).append("phone", phone).append("signature", signature).append("organizationId", organizationId).append("tags", tags).append("suspended", suspended).append("role", role).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tags).append(shared).append(phone).append(lastLoginAt).append(alias).append(locale).append(organizationId).append(externalId).append(suspended).append(url).append(id).append(timezone).append(email).append(additionalProperties).append(verified).append(createdAt).append(name).append(role).append(active).append(signature).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(tags, rhs.tags).append(shared, rhs.shared).append(phone, rhs.phone).append(lastLoginAt, rhs.lastLoginAt).append(alias, rhs.alias).append(locale, rhs.locale).append(organizationId, rhs.organizationId).append(externalId, rhs.externalId).append(suspended, rhs.suspended).append(url, rhs.url).append(id, rhs.id).append(timezone, rhs.timezone).append(email, rhs.email).append(additionalProperties, rhs.additionalProperties).append(verified, rhs.verified).append(createdAt, rhs.createdAt).append(name, rhs.name).append(role, rhs.role).append(active, rhs.active).append(signature, rhs.signature).isEquals();
    }

}

package com.zendesk.search.model.data;

/** Created by @author Sankash on 5/15/2019 Data class to hold the Organization data */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "_id",
  "url",
  "external_id",
  "name",
  "domain_names",
  "created_at",
  "details",
  "shared_tickets",
  "tags"
})
@Data
public class Organization {

  @JsonProperty("_id")
  private String id;

  @JsonProperty("url")
  private String url;

  @JsonProperty("external_id")
  private String externalId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("domain_names")
  @Valid
  private List<String> domainNames = new ArrayList<String>();

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("details")
  private String details;

  @JsonProperty("shared_tickets")
  private Boolean sharedTickets;

  @JsonProperty("tags")
  @Valid
  private List<String> tags = new ArrayList<String>();

  /** No args constructor for use in serialization */
  public Organization() {}

  /**
   * @param tags
   * @param id
   * @param details
   * @param sharedTickets
   * @param createdAt
   * @param name
   * @param domainNames
   * @param externalId
   * @param url
   */
  public Organization(
      String id,
      String url,
      String externalId,
      String name,
      List<String> domainNames,
      String createdAt,
      String details,
      Boolean sharedTickets,
      List<String> tags) {
    super();
    this.id = id;
    this.url = url;
    this.externalId = externalId;
    this.name = name;
    this.domainNames = domainNames;
    this.createdAt = createdAt;
    this.details = details;
    this.sharedTickets = sharedTickets;
    this.tags = tags;
  }

  @JsonProperty("_id")
  public String getId() {
    return id;
  }

  @JsonProperty("_id")
  public void setId(String id) {
    this.id = id;
  }

  public Organization withId(String id) {
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

  public Organization withUrl(String url) {
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

  public Organization withExternalId(String externalId) {
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

  public Organization withName(String name) {
    this.name = name;
    return this;
  }

  @JsonProperty("domain_names")
  public List<String> getDomainNames() {
    return domainNames;
  }

  @JsonProperty("domain_names")
  public void setDomainNames(List<String> domainNames) {
    this.domainNames = domainNames;
  }

  public Organization withDomainNames(List<String> domainNames) {
    this.domainNames = domainNames;
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

  public Organization withCreatedAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  @JsonProperty("details")
  public String getDetails() {
    return details;
  }

  @JsonProperty("details")
  public void setDetails(String details) {
    this.details = details;
  }

  public Organization withDetails(String details) {
    this.details = details;
    return this;
  }

  @JsonProperty("shared_tickets")
  public Boolean getSharedTickets() {
    return sharedTickets;
  }

  @JsonProperty("shared_tickets")
  public void setSharedTickets(Boolean sharedTickets) {
    this.sharedTickets = sharedTickets;
  }

  public Organization withSharedTickets(Boolean sharedTickets) {
    this.sharedTickets = sharedTickets;
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

  public Organization withTags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("url", url)
        .append("externalId", externalId)
        .append("name", name)
        .append("domainNames", domainNames)
        .append("createdAt", createdAt)
        .append("details", details)
        .append("sharedTickets", sharedTickets)
        .append("tags", tags)
        .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(tags)
        .append(id)
        .append(details)
        .append(sharedTickets)
        .append(createdAt)
        .append(name)
        .append(domainNames)
        .append(externalId)
        .append(url)
        .toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof Organization) == false) {
      return false;
    }
    Organization rhs = ((Organization) other);
    return new EqualsBuilder()
        .append(tags, rhs.tags)
        .append(id, rhs.id)
        .append(details, rhs.details)
        .append(sharedTickets, rhs.sharedTickets)
        .append(createdAt, rhs.createdAt)
        .append(name, rhs.name)
        .append(domainNames, rhs.domainNames)
        .append(externalId, rhs.externalId)
        .append(url, rhs.url)
        .isEquals();
  }
}

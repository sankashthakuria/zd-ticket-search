package com.zendesk.search.model.data;

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

/** Created by @author Sankash on 5/15/2019 Data class to hold the Ticket data */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "_id",
  "url",
  "external_id",
  "created_at",
  "type",
  "subject",
  "description",
  "priority",
  "status",
  "submitter_id",
  "assignee_id",
  "organization_id",
  "tags",
  "has_incidents",
  "due_at",
  "via"
})
@Data
public class Ticket {

  @JsonProperty("_id")
  private String id;

  @JsonProperty("url")
  private String url;

  @JsonProperty("external_id")
  private String externalId;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("type")
  private String type;

  @JsonProperty("subject")
  private String subject;

  @JsonProperty("description")
  private String description;

  @JsonProperty("priority")
  private String priority;

  @JsonProperty("status")
  private String status;

  @JsonProperty("submitter_id")
  private String submitterId;

  @JsonProperty("assignee_id")
  private String assigneeId;

  @JsonProperty("organization_id")
  private String organizationId;

  @JsonProperty("tags")
  @Valid
  private List<String> tags = new ArrayList<String>();

  @JsonProperty("has_incidents")
  private Boolean hasIncidents;

  @JsonProperty("due_at")
  private String dueAt;

  @JsonProperty("via")
  private String via;

  /** No args constructor for use in serialization */
  public Ticket() {}

  /**
   * @param tags
   * @param via
   * @param status
   * @param subject
   * @param hasIncidents
   * @param organizationId
   * @param type
   * @param externalId
   * @param url
   * @param dueAt
   * @param id
   * @param submitterId
   * @param createdAt
   * @param description
   * @param priority
   * @param assigneeId
   */
  public Ticket(
      String id,
      String url,
      String externalId,
      String createdAt,
      String type,
      String subject,
      String description,
      String priority,
      String status,
      String submitterId,
      String assigneeId,
      String organizationId,
      List<String> tags,
      Boolean hasIncidents,
      String dueAt,
      String via) {
    super();
    this.id = id;
    this.url = url;
    this.externalId = externalId;
    this.createdAt = createdAt;
    this.type = type;
    this.subject = subject;
    this.description = description;
    this.priority = priority;
    this.status = status;
    this.submitterId = submitterId;
    this.assigneeId = assigneeId;
    this.organizationId = organizationId;
    this.tags = tags;
    this.hasIncidents = hasIncidents;
    this.dueAt = dueAt;
    this.via = via;
  }

  @JsonProperty("_id")
  public String getId() {
    return id;
  }

  @JsonProperty("_id")
  public void setId(String id) {
    this.id = id;
  }

  public Ticket withId(String id) {
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

  public Ticket withUrl(String url) {
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

  public Ticket withExternalId(String externalId) {
    this.externalId = externalId;
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

  public Ticket withCreatedAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  public Ticket withType(String type) {
    this.type = type;
    return this;
  }

  @JsonProperty("subject")
  public String getSubject() {
    return subject;
  }

  @JsonProperty("subject")
  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Ticket withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  public Ticket withDescription(String description) {
    this.description = description;
    return this;
  }

  @JsonProperty("priority")
  public String getPriority() {
    return priority;
  }

  @JsonProperty("priority")
  public void setPriority(String priority) {
    this.priority = priority;
  }

  public Ticket withPriority(String priority) {
    this.priority = priority;
    return this;
  }

  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(String status) {
    this.status = status;
  }

  public Ticket withStatus(String status) {
    this.status = status;
    return this;
  }

  @JsonProperty("submitter_id")
  public String getSubmitterId() {
    return submitterId;
  }

  @JsonProperty("submitter_id")
  public void setSubmitterId(String submitterId) {
    this.submitterId = submitterId;
  }

  public Ticket withSubmitterId(String submitterId) {
    this.submitterId = submitterId;
    return this;
  }

  @JsonProperty("assignee_id")
  public String getAssigneeId() {
    return assigneeId;
  }

  @JsonProperty("assignee_id")
  public void setAssigneeId(String assigneeId) {
    this.assigneeId = assigneeId;
  }

  public Ticket withAssigneeId(String assigneeId) {
    this.assigneeId = assigneeId;
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

  public Ticket withOrganizationId(String organizationId) {
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

  public Ticket withTags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  @JsonProperty("has_incidents")
  public Boolean getHasIncidents() {
    return hasIncidents;
  }

  @JsonProperty("has_incidents")
  public void setHasIncidents(Boolean hasIncidents) {
    this.hasIncidents = hasIncidents;
  }

  public Ticket withHasIncidents(Boolean hasIncidents) {
    this.hasIncidents = hasIncidents;
    return this;
  }

  @JsonProperty("due_at")
  public String getDueAt() {
    return dueAt;
  }

  @JsonProperty("due_at")
  public void setDueAt(String dueAt) {
    this.dueAt = dueAt;
  }

  public Ticket withDueAt(String dueAt) {
    this.dueAt = dueAt;
    return this;
  }

  @JsonProperty("via")
  public String getVia() {
    return via;
  }

  @JsonProperty("via")
  public void setVia(String via) {
    this.via = via;
  }

  public Ticket withVia(String via) {
    this.via = via;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("url", url)
        .append("externalId", externalId)
        .append("createdAt", createdAt)
        .append("type", type)
        .append("subject", subject)
        .append("description", description)
        .append("priority", priority)
        .append("status", status)
        .append("submitterId", submitterId)
        .append("assigneeId", assigneeId)
        .append("organizationId", organizationId)
        .append("tags", tags)
        .append("hasIncidents", hasIncidents)
        .append("dueAt", dueAt)
        .append("via", via)
        .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(tags)
        .append(via)
        .append(status)
        .append(subject)
        .append(hasIncidents)
        .append(organizationId)
        .append(type)
        .append(externalId)
        .append(url)
        .append(dueAt)
        .append(submitterId)
        .append(id)
        .append(assigneeId)
        .append(priority)
        .append(description)
        .append(createdAt)
        .toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof Ticket) == false) {
      return false;
    }
    Ticket rhs = ((Ticket) other);
    return new EqualsBuilder()
        .append(tags, rhs.tags)
        .append(via, rhs.via)
        .append(status, rhs.status)
        .append(subject, rhs.subject)
        .append(hasIncidents, rhs.hasIncidents)
        .append(organizationId, rhs.organizationId)
        .append(type, rhs.type)
        .append(externalId, rhs.externalId)
        .append(url, rhs.url)
        .append(dueAt, rhs.dueAt)
        .append(submitterId, rhs.submitterId)
        .append(id, rhs.id)
        .append(assigneeId, rhs.assigneeId)
        .append(priority, rhs.priority)
        .append(description, rhs.description)
        .append(createdAt, rhs.createdAt)
        .isEquals();
  }
}

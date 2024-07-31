package com.argus.mytodo.filePackage;

import java.util.UUID;

public class FileInfo {
  private String name;
  private String url;
  private UUID uuid;

  public FileInfo(String name, String url) {
    this.name = name;
    this.url = url;
    this.uuid = UUID.randomUUID();;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }
}

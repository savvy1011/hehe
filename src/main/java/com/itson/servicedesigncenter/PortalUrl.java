package com.itson.servicedesigncenter;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class PortalUrl {
  private String host;
  private URL url;

  public PortalUrl(Builder builder) {
    url = builder.toURL();
  }

  public String toString() {
    return url.toString();
  }

  /*
   * http://en.wikipedia.org/wiki/URI_scheme
   */
  public static class Builder {
    private String scheme = "https";
    private int port = 80;
    private String portString = "";
    private String host = "";
    private String path = "";
    private String query = "";
    private boolean ssl = true;
    private String hostURL = "";
    private String ref   = "";

    public Builder() {
    }

    public String toString() {
      String str = "";
      if (hostURL.isEmpty()) {
        str = scheme +
                "://" +
                host +
                portString +
                path +
                query;
      }
      else {
        str = hostURL + path + query;
      }
      return str;
    }

    public URL toURL() {
      try {
        return new URL(toString());
      } catch (MalformedURLException e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Bad Url: "+ toString());
      }
    }

    public Builder setHostUrl(String hostU) {
      try {
        URL url = new URL(hostU);
      } catch (MalformedURLException e) {
        throw new IllegalArgumentException("Bad hostUrl supplied to setHostUrl");
      }
      hostURL = hostU;
      return this;
    }

    public Builder setPort(int port) {
      if(port < 0 || (port > 65535)) {
        throw new IllegalArgumentException("Port number not in range");
      }
      this.port = port;
      if(port != 80) {
        portString = ":" + String.valueOf(this.port);
      }
      return this;
    }

    public Builder setSSL(boolean b) {
      this.ssl = b;
      if(!b) {
        scheme = "http";
      }
      return this;
    }

    public Builder setHost(String host) {
      try {
        InetAddress inetAddress = InetAddress.getByName(host);
      } catch (UnknownHostException e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Bad Hostname: Does not seem to exist");
      }
      this.host = host;
      return this;
    }

    public Builder setPath(String path) {
      if(path == null) {
        throw new IllegalArgumentException("Invalid Path");
      }
      this.path = path;
      return this;
    }

    public Builder setQuery(String q) {
      if(q == null) {
        throw new IllegalArgumentException("Invalid Query");
      }
      this.query = q;
      return this;
    }

    public PortalUrl build() {
      return new PortalUrl(this);
    }
  }

}

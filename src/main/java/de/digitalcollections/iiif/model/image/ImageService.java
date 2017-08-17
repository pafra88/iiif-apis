package de.digitalcollections.iiif.model.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import de.digitalcollections.iiif.model.ImageContent;
import de.digitalcollections.iiif.model.PropertyValue;
import de.digitalcollections.iiif.model.service.Service;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageService extends Service {
  // FIXME: This should be static, but for some reason Jackson ignores it if it's not
  //        on the instance...
  @JsonProperty("protocol")
  public final String PROTOCOL = "http://iiif.io/api/image";

  @JsonProperty("@context")
  public static final String CONTEXT = "http://iiif.io/api/image/2/context.json";

  private Integer width;
  private Integer height;
  private List<TileInfo> tiles;
  private List<Size> sizes;
  @JsonProperty("service")
  private List<Service> services;

  @JsonProperty("attribution")
  private PropertyValue attribution;

  @JsonProperty("license")
  private List<URI> licenses;

  @JsonProperty("logo")
  private List<ImageContent> logos;

  @JsonCreator
  public ImageService(@JsonProperty("@id") String identifier) {
    super(URI.create(CONTEXT));
    this.setIdentifier(URI.create(identifier));
  }

  public ImageService(String identifier, ImageApiProfile profile) {
    this(identifier);
    this.addProfile(profile);
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public List<TileInfo> getTiles() {
    return tiles;
  }

  public void setTiles(List<TileInfo> tiles) {
    this.tiles = tiles;
  }

  public void addTile(TileInfo first, TileInfo... rest) {
    if (this.tiles == null) {
      this.tiles = new ArrayList<>();
    }
    this.tiles.addAll(Lists.asList(first, rest));
  }

  public List<Size> getSizes() {
    return sizes;
  }

  public void setSizes(List<Size> sizes) {
    this.sizes = sizes;
  }

  public void addSize(Size first, Size... rest) {
    if (this.sizes == null) {
      this.sizes = new ArrayList<>();
    }
    this.sizes.addAll(Lists.asList(first, rest));
  }

  public List<Service> getServices() {
    return services;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }

  public void addService(Service first, Service... rest) {
    if (this.services == null) {
      this.services = new ArrayList<>();
    }
    this.services.addAll(Lists.asList(first, rest));
  }
    public PropertyValue getAttribution() {
    return attribution;
  }

  @JsonIgnore
  public String getAttributionString() {
    return attribution.getFirstValue();
  }

  public void setAttribution(PropertyValue attribution) {
    this.attribution = attribution;
  }

  public void addAttribution(String first, String... rest) {
    if (this.attribution == null) {
      this.attribution = new PropertyValue();
    }
    this.attribution.addValue(first, rest);
  }

  public List<URI> getLicenses() {
    return licenses;
  }

  @JsonIgnore
  public URI getFirstLicense() {
    return licenses.get(0);
  }

  public void setLicenses(List<URI> licenses) {
    this.licenses = licenses;
  }

  public void addLicense(String first, String... rest) {
    if (this.licenses == null) {
      this.licenses = new ArrayList<>();
    }
    this.licenses.add(URI.create(first));
    Arrays.stream(rest).map(URI::create).forEach(licenses::add);
  }

  public List<ImageContent> getLogos() {
    return logos;
  }

  @JsonIgnore
  public URI getLogoUri() {
    return logos.get(0).getIdentifier();
  }

  public void setLogos(List<ImageContent> logos) {
    this.logos = logos;
  }

  public void addLogo(String first, String... rest) {
    if (this.logos == null) {
      this.logos = new ArrayList<>();
    }
    this.logos.add(new ImageContent(first));
    Arrays.stream(rest).map(ImageContent::new).forEach(logos::add);
  }

  public void addLogo(ImageContent first, ImageContent... rest) {
    if (this.logos == null) {
      this.logos = new ArrayList<>();
    }
    this.logos.addAll(Lists.asList(first, rest));
  }
}
package be.sleroy.huedk.dto.config;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.sleroy.huedk.dto.HueIdElement;
import be.sleroy.huedk.utilities.JsonDateTimeDeserializer;
import be.sleroy.huedk.utilities.JsonDateTimeSerializer;

/**
 * The Class HueWhiteUser.
 * 
 * @author sleroy
 */
public class HueWhiteUser extends HueIdElement {

	@JsonProperty(value = "last use date")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date dateLastUse;

	@JsonProperty(value = "create date")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date dateCreation;

	@JsonProperty(value = "name")
	private String name;

	/**
	 * Gets the date last use.
	 *
	 * @return the date last use
	 */
	public Date getDateLastUse() {
		return dateLastUse;
	}

	/**
	 * Sets the date last use.
	 *
	 * @param dateLastUse
	 *            the new date last use
	 */
	public void setDateLastUse(Date dateLastUse) {
		this.dateLastUse = dateLastUse;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation
	 *            the new date creation
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HueWhiteUser [id=" + id + ", dateLastUse=" + dateLastUse + ", dateCreation=" + dateCreation + ", name=" + name + "]";
	}

}

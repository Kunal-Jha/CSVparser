package org.rapidminer.csvparser.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Row {
	@Expose
	String label;

	Map<String, Double> attributes;
	Map<String, String> miscAttributes;
	@Expose
	Map<String, Double> medianValues;

	public Optional<String> id;

	public Row(String lab) {
		// TODO Auto-generated constructor stub
		this.label = lab;
		this.attributes = new HashMap<String, Double>();
		this.medianValues = new HashMap<String, Double>();
		this.miscAttributes = new HashMap<String, String>();
		this.id = Optional.empty();
	}

	@Override
	public String toString() {
		StringBuilder a = new StringBuilder(this.label + " : ");
		if (id.isPresent()) {
			a.append("  id :" + this.id.get());
		}
		for (Map.Entry<String, Double> entry : this.attributes.entrySet()) {
			a.append("   " + entry.getKey() + "-->" + entry.getValue());
		}
		if (!this.miscAttributes.isEmpty()) {
			for (Map.Entry<String, String> entry : this.miscAttributes.entrySet()) {
				a.append("  " + entry.getKey() + "-->" + entry.getValue());
			}
		}
		return a.toString();
	}

	public void addAttributeValue(String key, Double value) {
		this.attributes.put(key, value);
	}

	public Double getAttributeValue(String name) {
		return this.attributes.get(name);
	}

	public void addMiscAttributes(String key, String value) {
		this.miscAttributes.put(key, value);
	}

	public String getMiscAttributes(String name) {
		return this.miscAttributes.get(name);
	}

	public void addMedian(String key, Double value) {
		this.medianValues.put(key, value);
	}

	public Double getMedian(String key) {
		return this.medianValues.get(key);
	}

	public Set<String> getAttributesList() {
		return this.attributes.keySet();
	}

	public Set<String> getMiscAttributesList() {
		return this.miscAttributes.keySet();
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the id
	 */
	public Optional<String> getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Optional<String> id) {
		this.id = id;
	}

}

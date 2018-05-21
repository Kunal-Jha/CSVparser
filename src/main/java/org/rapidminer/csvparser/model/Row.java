package org.rapidminer.csvparser.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Row {
	String label;

	Map<String, Double> attributes;

	public Optional<String> id;

	public Row(String lab) {
		// TODO Auto-generated constructor stub
		this.label = lab;
		this.attributes = new HashMap<String, Double>();
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
		return a.toString();
	}

	public Set<String> getAttributesList() {
		return this.attributes.keySet();
	}

	public void addToMap(String key, Double value) {
		this.attributes.put(key, value);
	}

	public Double getfromMap(String name) {
		return this.attributes.get(name);
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
	 * @return the attributes
	 */
	public Map<String, Double> getAttributes() {
		return attributes;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Optional<String> id) {
		this.id = id;
	}

}

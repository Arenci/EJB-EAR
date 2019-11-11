package com.test.hello;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the action_log_descripton database table.
 * 
 */
@Entity
@Table(name="action_log_descripton")
@NamedQuery(name="ActionLogDescripton.findAll", query="SELECT a FROM ActionLogDescripton a")
public class ActionLogDescripton implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="action_log_id")
	private int actionLogId;

	private String description;

	public ActionLogDescripton() {
	}

	public int getActionLogId() {
		return this.actionLogId;
	}

	public void setActionLogId(int actionLogId) {
		this.actionLogId = actionLogId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
package controller;

import ejb.GenericCRUDService;


public abstract class EntityBean {
	private Class clazz;
	private GenericCRUDService service;
	private Object entity;
	private Long entityId;
	
	public EntityBean() {
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	public GenericCRUDService getService() {
		return service;
	}
	public void setService(GenericCRUDService service) {
		this.service = service;
	}
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
		entity = service.find(entityId);
	}
		
}

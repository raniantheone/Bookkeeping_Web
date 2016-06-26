package ranian.bookkeeping.system.persistence.tables.type.vo;

public class TypeVO {

	private Integer typeId;
	
	private String typeName;
	
	private Boolean typeDel;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Boolean getTypeDel() {
		return typeDel;
	}

	public void setTypeDel(Boolean typeDel) {
		this.typeDel = typeDel;
	}
		
}

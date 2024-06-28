package POJO;

import java.util.List;

public class GlobalGroup {
    private int id;
    private String name;
    private String externalId;
    private int typeId;
    private String path;
    private boolean hasChildren;
    private boolean isFranchise;
    private String rowVersion;
    private List<ChildGroup> childGroups;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isFranchise() {
        return isFranchise;
    }

    public void setFranchise(boolean franchise) {
        isFranchise = franchise;
    }

    public String getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(String rowVersion) {
        this.rowVersion = rowVersion;
    }

    public List<ChildGroup> getChildGroups() {
        return childGroups;
    }

    public void setChildGroups(List<ChildGroup> childGroups) {
        this.childGroups = childGroups;
    }
}

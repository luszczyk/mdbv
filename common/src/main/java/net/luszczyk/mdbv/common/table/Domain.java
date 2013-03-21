package net.luszczyk.mdbv.common.table;

import java.io.Serializable;
import java.sql.Blob;

public class Domain implements Serializable {

    private transient Table table;
    private Column column;
    private String mimeType;
    private String content;
    private String linkToView;
    private String startBytes;
    private String icon;

    private int id;
    private Long oid;
    private Blob blob;

    public Domain(Table table, Column column, String content) {
        this(table, column, content, null, null, null, null);
    }

    public Domain(Table table, Column column, String content, Blob blob, String startBytes, String icon) {
        this(table, column, content, null, blob, startBytes, icon);
    }

    public Domain(Table table, Column column, String content, Long oid, String startBytes, String icon) {
        this(table, column, content, oid, null, startBytes, icon);
    }

    public Domain(Table table, Column column, String content, Long oid, Blob blob, String startBytes, String icon) {
        this.table = table;
        this.column = column;
        this.content = content;
        this.oid = oid;
        this.blob = blob;
        this.startBytes = startBytes;
        this.icon = icon;
        this.id = hashCode();
    }

    public Integer getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oId) {
        this.oid = oId;
    }

    public boolean isViewable() {
        return linkToView != null;
    }

    public Column getColumn() {
        return column;
    }

    public String getType() {
        return column.getType();
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPreView() {
        return content;
    }

    public String getLinkToView() {
        return linkToView;
    }

    public void setLinkToView(String linkToView) {
        this.linkToView = linkToView;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public String getStartBytes() {
        return startBytes;
    }

    public void setStartBytes(String startBytes) {
        this.startBytes = startBytes;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Domain)) return false;

        Domain domain = (Domain) o;

        if (!content.equals(domain.content)) return false;
        if (mimeType != null ? !mimeType.equals(domain.mimeType) : domain.mimeType != null) return false;
        if (oid != null ? !oid.equals(domain.oid) : domain.oid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mimeType != null ? mimeType.hashCode() : 0;
        result = 31 * result + content.hashCode();
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        return result;
    }
}

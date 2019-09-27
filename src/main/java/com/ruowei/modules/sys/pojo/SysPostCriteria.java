package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.SysPost;
import io.github.jhipster.service.Criteria;
import com.ruowei.modules.sys.domain.enumeration.PostType;
import com.ruowei.domain.enumeration.PostStatusType;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SysPost} entity. This class is used
 * in {@link com.ruowei.web.rest.SysPostResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-posts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysPostCriteria implements Serializable, Criteria {
    /**
     * Class for filtering PostType
     */
    public static class PostTypeFilter extends Filter<PostType> {

        public PostTypeFilter() {
        }

        public PostTypeFilter(PostTypeFilter filter) {
            super(filter);
        }

        @Override
        public PostTypeFilter copy() {
            return new PostTypeFilter(this);
        }

    }
    /**
     * Class for filtering PostStatusType
     */
    public static class PostStatusTypeFilter extends Filter<PostStatusType> {

        public PostStatusTypeFilter() {
        }

        public PostStatusTypeFilter(PostStatusTypeFilter filter) {
            super(filter);
        }

        @Override
        public PostStatusTypeFilter copy() {
            return new PostStatusTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter postCode;

    private StringFilter postName;

    private PostTypeFilter postType;

    private PostStatusTypeFilter status;

    private StringFilter remarks;

    public SysPostCriteria(){
    }

    public SysPostCriteria(SysPostCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.postCode = other.postCode == null ? null : other.postCode.copy();
        this.postName = other.postName == null ? null : other.postName.copy();
        this.postType = other.postType == null ? null : other.postType.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
    }

    @Override
    public SysPostCriteria copy() {
        return new SysPostCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPostCode() {
        return postCode;
    }

    public void setPostCode(StringFilter postCode) {
        this.postCode = postCode;
    }

    public StringFilter getPostName() {
        return postName;
    }

    public void setPostName(StringFilter postName) {
        this.postName = postName;
    }

    public PostTypeFilter getPostType() {
        return postType;
    }

    public void setPostType(PostTypeFilter postType) {
        this.postType = postType;
    }

    public PostStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(PostStatusTypeFilter status) {
        this.status = status;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysPostCriteria that = (SysPostCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(postCode, that.postCode) &&
            Objects.equals(postName, that.postName) &&
            Objects.equals(postType, that.postType) &&
            Objects.equals(status, that.status) &&
            Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        postCode,
        postName,
        postType,
        status,
        remarks
        );
    }

    @Override
    public String toString() {
        return "SysPostCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (postCode != null ? "postCode=" + postCode + ", " : "") +
                (postName != null ? "postName=" + postName + ", " : "") +
                (postType != null ? "postType=" + postType + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
            "}";
    }

}

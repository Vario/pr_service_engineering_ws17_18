package io.swagger.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Revision {
    private Date revisionId;
    private UUID fileId;

    public Revision(Date revisionId, UUID fileId) {
        this.revisionId = revisionId;
        this.fileId = fileId;
    }

    public Date getRevisionId(){
        return this.revisionId;
    }

    public UUID getFileId(){
        return this.fileId;
    }

    @Override
    public boolean equals(Object o){
        Revision r = (Revision) o;
        if(r.revisionId == this.revisionId && r.fileId == this.fileId){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.revisionId, this.fileId);
    }
}

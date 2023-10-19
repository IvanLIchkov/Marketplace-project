package bg.softuni.errors.models.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    private long objectId;

    private String objectType;

    public ObjectNotFoundException(long objectId, String objectType) {
        super("Object with ID " +objectId+" and type "+ objectType +" not found!");
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public long getObjectId() {
        return objectId;
    }

    public ObjectNotFoundException setObjectId(long objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getObjectType() {
        return objectType;
    }

    public ObjectNotFoundException setObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }
}

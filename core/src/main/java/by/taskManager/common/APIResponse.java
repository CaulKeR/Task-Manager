package by.taskManager.common;

public class APIResponse {

    private Boolean isSuccess;
    private String message;
    private Object object;

    public APIResponse(Boolean isSuccess, String message, Object object) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.object = object;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
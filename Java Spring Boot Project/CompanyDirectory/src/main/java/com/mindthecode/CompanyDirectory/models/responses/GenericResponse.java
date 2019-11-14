package com.mindthecode.CompanyDirectory.models.responses;

public class GenericResponse<T> {

    private T data;
    private Error error;

    public GenericResponse(Error error) {
        this.error = error;
    }

    public GenericResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}

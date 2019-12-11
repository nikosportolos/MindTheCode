package com.mindthecode.CompanyDirectory.models.responses;

public class GenericResponse<T> {

    private T data;
    private ErrorResponse error;

    public GenericResponse() {
    }

    public GenericResponse(ErrorResponse error) {
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

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "data=" + data +
                ", error=" + error +
                '}';
    }
}

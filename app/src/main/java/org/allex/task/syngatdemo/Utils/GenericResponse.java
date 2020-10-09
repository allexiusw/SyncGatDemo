package org.allex.task.syngatdemo.Utils;

//Objeto con dos parametros genericos
public class GenericResponse<Response, Message> {
    Response boolResponse;
    Message messageResponse;

    public GenericResponse(Response boolResponse, Message messageResponse) {
        this.boolResponse = boolResponse;
        this.messageResponse = messageResponse;
    }

    public GenericResponse() {
    }

    public Response getBoolResponse() {
        return boolResponse;
    }

    public void setBoolResponse(Response boolResponse) {
        this.boolResponse = boolResponse;
    }

    public Message getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(Message messageResponse) {
        this.messageResponse = messageResponse;
    }
}

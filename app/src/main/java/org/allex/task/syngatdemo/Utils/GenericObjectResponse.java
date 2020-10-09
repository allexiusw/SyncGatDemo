package org.allex.task.syngatdemo.Utils;

//Objeto con dos parametros genericos
public class GenericObjectResponse<Response, Message> {
    Response boolResponse;
    Message messageResponse;

    public GenericObjectResponse(Response boolResponse, Message messageResponse) {
        this.boolResponse = boolResponse;
        this.messageResponse = messageResponse;
    }

    public GenericObjectResponse() {
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

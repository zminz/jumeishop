package com.neuedu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jdk.net.SocketFlow;

/**
 * 服务端返回到前端的高复用的相应对象
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

    //返回到前端的状态码
    private int status;
    //返回到前端的数据
    private T data;
    //当status ！=0时，封装了错误信息
    private String msg;

    private ServerResponse() {
    }

    private ServerResponse(int status) {
        this.status = status;
    }

    //参数是obiect类型
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    //参数是String类型
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServerResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 调用接口成功时回调
     */
    //仅返回一个状态码的
    public static ServerResponse serverResponseBySuccess(){
        return new ServerResponse(ResponseCode.SUCCESS);
    }
    //返回一个状态码和data数据的
    public static  <T> ServerResponse serverResponseBySuccess(T data){
        return new ServerResponse(ResponseCode.SUCCESS,data);
    }
    //返回一个状态码和data，msg数据的
    public static  <T> ServerResponse serverResponseBySuccess(T data,String msg){
        return new ServerResponse(ResponseCode.SUCCESS,data,msg);
    }

    /**
     *接口调用失败时回调
     */
    public static ServerResponse serverResponseByError(){
        return new ServerResponse(ResponseCode.ERROR);
    }
    public static ServerResponse serverResponseByError(String msg){
        return new ServerResponse(ResponseCode.ERROR,msg);
    }
    public static ServerResponse serverResponseByError(int status){
        return new ServerResponse(status);
    }
    public static ServerResponse serverResponseByError(int status,String msg){
        return new ServerResponse(status,msg);
    }

    /**
     * 判断接口是否正确返回
     * status=0
     */
    @JsonIgnore
    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


    /*@Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    } */

  /*  public static  void main(String[] args){
        ServerResponse serverResponse= new ServerResponse(0,new Object());
        ServerResponse serverResponse1=ServerResponse.serverResponseBySuccess("hello",null);
        //System.out.println(serverResponse);
        System.out.println(serverResponse1);

    }*/

}

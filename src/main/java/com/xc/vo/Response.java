package com.xc.vo;

import com.alibaba.fastjson.JSON;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/13
 * Time: 16:48
 */
public class Response<T> {

    private static Integer def_succ_code = ResponseCode.COMMON_SUCCESS.getValue();
    private static String def_succ_message = ResponseCode.COMMON_SUCCESS.getInfo();
    private static Integer def_faild_code = ResponseCode.COMMON_FAILED.getValue();
    private static String def_faild_message = ResponseCode.COMMON_FAILED.getInfo();


    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 每页显示记录数
     */
    private Integer itemsPerPage = 5;

    /**
     * 当前页码
     */
    private Integer page = 1;

    /**
     * 总记录数
     */
    private Integer total = 0;

    /**
     * 总页数
     */
    private Integer totalPages = 0;



    /**
     * 数据
     */
    private T data;

    public Response() {
        this.success = true;
        this.code = def_succ_code;
        this.message = def_succ_message;
        this.data = null;
    }

    public Response(Integer code, String message, boolean success, Integer itemsPerPage, Integer page, Integer total, Integer totalPages, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.itemsPerPage = itemsPerPage;
        this.page = page;
        this.total = total;
        this.totalPages = totalPages;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return (total + itemsPerPage-1) / itemsPerPage;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response<T> error(){
        this.code = def_faild_code;
        this.message = def_faild_message;
        this.success = false;
        this.data = null;
        return this;
    }

    public Response<T> error(Integer code){
        this.code = code;
        this.message = def_faild_message;
        this.success = false;
        this.data = null;
        return this;
    }

    public Response<T> error(String message){
        this.code = def_faild_code;
        this.message = message;
        this.success = false;
        this.data = null;
        return this;
    }

    public Response<T> error(T data){
        this.code = def_faild_code;
        this.message = def_faild_message;
        this.success = false;
        this.data = data;
        return this;
    }


    public Response<T> error(Integer code, String message){
        this.code = code;
        this.message = message;
        this.success = false;
        this.data = null;
        return this;
    }

    public Response<T> error(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.success = false;
        this.data = data;
        return this;
    }

    public Response<T> error(T data, Integer itemsPerPage, Integer page, Integer total){
        this.code = def_succ_code;
        this.message = def_succ_message;
        this.success = false;
        this.data = data;
        this.itemsPerPage = itemsPerPage;
        this.page = page;
        this.total = total;
        this.totalPages = getTotalPages();
        return this;
    }

    public Response<T> error(Integer code, String message, T data, Integer itemsPerPage, Integer page, Integer total){
        this.code = code;
        this.message = message;
        this.success = false;
        this.data = data;
        this.itemsPerPage = itemsPerPage;
        this.page = page;
        this.total = total;
        this.totalPages = getTotalPages();
        return this;
    }

    /**
     * 返回成功信息对象
     *
     * @return
     */
    public Response<T> success() {
        this.code = def_succ_code;
        this.message = def_succ_message;
        this.success = true;
        this.data = null;
        return this;
    }

    public Response<T> success(Integer code) {
        this.code = code;
        this.message = def_succ_message;
        this.success = true;
        this.data = null;
        return this;
    }

    public Response<T> success(String message) {
        this.code = def_succ_code;
        this.message = message;
        this.success = true;
        this.data = null;
        return this;
    }

    public Response<T> success(Integer code, String message){
        this.code = code;
        this.message = message;
        this.success = true;
        this.data = null;
        return this;
    }

    public Response<T> success(T data){
        this.code = def_succ_code;
        this.message = def_succ_message;
        this.success = true;
        this.data = data;
        return this;
    }

    public Response<T> success(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.success = true;
        this.data = data;
        return this;
    }

    public Response<T> success(T data, Integer itemsPerPage, Integer page, Integer total){
        this.code = def_succ_code;
        this.message = def_succ_message;
        this.success = true;
        this.data = data;
        this.itemsPerPage = itemsPerPage;
        this.page = page;
        this.total = total;
        this.totalPages = getTotalPages();
        return this;
    }

    public Response<T> success(Integer code, String message, T data, Integer itemsPerPage, Integer page, Integer total){
        this.code = code;
        this.message = message;
        this.success = true;
        this.data = data;
        this.itemsPerPage = itemsPerPage;
        this.page = page;
        this.total = total;
        this.totalPages = getTotalPages();
        return this;
    }

    @Override
    public String toString() {
        return "Response [ " +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", itemsPerPage=" + itemsPerPage +
                ", page=" + page +
                ", total=" + total +
                ", totalPages=" + totalPages +
                ", data=" + data +
                ']';
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }


    public static <T> Response<T> err() {
        return new Response<T>().error();
    }

    public static <T> Response<T> err(String message) {
        return new Response<T>().error(message);
    }

    public static <T> Response<T> err(T data) {
        return new Response<T>().error(data);
    }

    public static <T> Response<T> err(Integer code, String message) {
        return new Response<T>().error(code, message);
    }

    public static <T> Response<T> err(Integer code, String message, T data) {
        return new Response<T>().error(code, message, data);
    }

    public static <T> Response<T> err(T data, Integer itemsPerPage, Integer page, Integer total) {
        return new Response<T>().error(data, itemsPerPage, page,total);
    }

    public static <T> Response<T> err(Integer code, String message, T data, Integer itemsPerPage, Integer page, Integer total) {
        return new Response<T>().error(code, message, data, itemsPerPage, page, total);
    }

    public static <T> Response<T> succ() {
        return new Response<T>().success();
    }

    public static <T> Response<T> succ(Integer code) {
        return new Response<T>().success(code);
    }

    public static <T> Response<T> succ(String message) {
        return new Response<T>().success(message);
    }

    public static <T> Response<T> succ(Integer code, String message) {
        return new Response<T>().success(code, message);
    }

    public static <T> Response<T> succ(T data) {
        return new Response<T>().success(data);
    }

    public static <T> Response<T> succ(Integer code, String message, T data) {
        return new Response<T>().success(code, message, data);
    }

    public static <T> Response<T> succ(T data, Integer itemsPerPage, Integer page, Integer total) {
        return new Response<T>().success(data, itemsPerPage, page, total);
    }

    public static <T> Response<T> succ(Integer code, String message, T data, Integer itemsPerPage, Integer page, Integer total) {
        return new Response<T>().success(code, message, data, itemsPerPage, page, total);
    }

}


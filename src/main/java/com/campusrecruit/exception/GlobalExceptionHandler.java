package com.campusrecruit.exception;



import com.campusrecruit.pojo.ResultMap;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 运行时异常
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultMap runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(505, ex);
    }

    /**
     * 空指针异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResultMap nullPointerExceptionHandler(NullPointerException ex) {
        System.err.println("NullPointerException:");
        return resultFormat(505, ex);
    }

    /**
     * 类型转换异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public ResultMap classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(505, ex);
    }

    /**
     * IO异常
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ResultMap iOExceptionHandler(IOException ex) {
        return resultFormat(505, ex);
    }

    /**
     * 未知方法异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public ResultMap noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(505, ex);
    }

    /**
     * 数组越界异常
     * @param ex
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResultMap indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(505, ex);
    }

    /**
     * 400错误
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResultMap requestNotReadable(HttpMessageNotReadableException ex) {
        System.out.println("400..requestNotReadable");
        return resultFormat(400, ex);
    }

    /**
     * 400错误
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    public ResultMap requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        return resultFormat(400, ex);
    }

    /**
     * 400错误
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResultMap requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        return resultFormat(400, ex);
    }

    /**
     * 405错误
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResultMap request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(405, ex);
    }

    /**
     * 406错误
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResultMap request406(HttpMediaTypeNotAcceptableException ex) {
        System.out.println("406...");
        return resultFormat(406, ex);
    }

    /**
     * 500错误
     * @param ex
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public ResultMap server500(RuntimeException ex) {
        System.out.println("500...");
        return resultFormat(500, ex);
    }

    /**
     * 栈溢出
     * @param ex
     * @return
     */
    @ExceptionHandler({StackOverflowError.class})
    public ResultMap requestStackOverflow(StackOverflowError ex) {
        return resultFormat(505, ex);
    }

    /**
     * 除数不能为0
     * @param ex
     * @return
     */
    @ExceptionHandler({ArithmeticException.class})
    public ResultMap arithmeticException(ArithmeticException ex) {
        return resultFormat(505, ex);
    }

    /**
     * 其他错误
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResultMap exception(Exception ex) {
        return resultFormat(505, ex);
    }


    /**
     * 登录认证异常
     */
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public ResultMap authenticationException(Exception ex) {
        return  resultFormat(505,ex);
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ResultMap authorizationException(Exception ex) {
        return  resultFormat(505,ex);
    }


    private <T extends Throwable> ResultMap resultFormat(Integer code, T ex) {
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return new ResultMap(code,"error",ex.getMessage());
    }

}
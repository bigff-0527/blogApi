package com.bigff.blog.common.exception;

import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//捕获全局异常
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//  @ResponseStatus(HttpStatus.UNAUTHORIZED)
//  @ExceptionHandler(value = ShiroException.class)
//  public Result handler(ShiroException e){
//    log.error("运行时异常",e);
//    return ResultUtil.error(401,e.getMessage());
//  }
//
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(value = RuntimeException.class)
//  public Result handler(RuntimeException e){
//    log.error("运行时异常",e);
//    return ResultUtil.error(e.getMessage());
//  }
//}
@Slf4j //日志
@RestControllerAdvice //定义全局控制器异常处理
public class GlobalExceptionHandler {

  /**
   * 全局异常处理
   * @param e
   * @return
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST) //返回状态码
  @ExceptionHandler(value = RuntimeException.class) //指定捕获Exception的各个类型异常
  public Result handler(RuntimeException e){
    log.error("RuntimeException:------------------>{}",e);
    return ResultUtil.error(e.getMessage());
  }


  /**
   * Shiro异常处理
   * @param e
   * @return
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED) //Unauthorized
  @ExceptionHandler(value = ShiroException.class) //@ExceptionHandler表示针对性异常处理
  public Result handler(ShiroException e){
    log.error("ShiroException:------------------>{}",e);
    return ResultUtil.error(401,"您尚未认证: 无权操作");
  }


  /**
   * 实体校验异常
   * @param e
   * @return
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public Result handler(MethodArgumentNotValidException e){
    log.error("MethodArgumentNotValidException:------------------>{}",e);
    //简化异常错误信息
    BindingResult bindingResult = e.getBindingResult();
    ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
    return ResultUtil.error(objectError.getDefaultMessage());
  }

  /**
   * 断言异常
   * @param e
   * @return
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = IllegalArgumentException.class)
  public Result handler(IllegalArgumentException e){
    log.info("Assert:------------------>{}");
    return ResultUtil.error(e.getMessage());
  }

}
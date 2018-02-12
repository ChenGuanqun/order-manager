package order.manager.api;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import order.manager.constant.ApiResponse;
import order.manager.constant.RetureCode;
import order.manager.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Log4j2
public abstract class AbstractApi {


    protected String getOperatorFromContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth == null) {
            log.warn("what the hell? authentication is null?");
            return "UNKNOWN";
        }
        return auth.getName();
    }

    /**
     * 全局的异常处理函数，只要继承了 AbstractController 的方法抛出异常，会进入到这个方法处理
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ApiResponse<String> handleThrowable(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        String params = gson.toJson(request.getParameterMap());

        ApiResponse<String> ret = new ApiResponse(RetureCode.SERVICE_ERROR);
        String msg = "";
        if (ex instanceof IllegalArgumentException || ex instanceof NullPointerException) {
            // 如果是可预见的错误，就不打印完整的异常堆栈了
            msg = "校验错误:" + ex.getMessage();
            log.error(String.format("url=%s, params=%s, error=%s", request.getRequestURI(), params, msg));
        } else if (ex instanceof ServiceException) {
            msg = ex.getMessage();
            log.error(String.format("url=%s, params=%s", request.getRequestURI(), params), ex);
        } else {
            log.error(String.format("url=%s, params=%s", request.getRequestURI(), params), ex);
            msg = "服务器内部错误，请联系管理员";
        }
        ret.setMsg(msg);
        return ret;
    }

}

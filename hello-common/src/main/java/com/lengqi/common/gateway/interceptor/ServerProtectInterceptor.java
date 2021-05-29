package com.lengqi.common.gateway.interceptor;//package com.lengqi.cloud.common.gateway.interceptor;
//
//import com.lengqi.cloud.common.constant.Constant;
//import com.lengqi.cloud.common.gateway.propertis.SaTokenProperties;
//import com.lengqi.cloud.common.result.ResultCode;
//import com.lengqi.cloud.common.result.ResultVo;
//import com.lengqi.cloud.common.utils.StpUserUtil;
//import com.lengqi.cloud.common.utils.WebUtils;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 服务间的拦截，防止绕过网关直接访问服务
// */
//@Component
//public class ServerProtectInterceptor implements HandlerInterceptor {
//    @Resource
//    private SaTokenProperties properties;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        if (!properties.getOnlyFetchByGateway()){
//            return true;
//        }
//        String token = request.getHeader(Constant.SA_TOKEN);
//        //判断绕过网关访问的token是否和用户登录的token一致
//        if (token.equals(StpUserUtil.getTokenValue())){
//            return true;
//        }else {
//            WebUtils.writeFailedToResponse((ServerHttpResponse) response, ResultCode.ACCESS_UNAUTHORIZED);
//        }
//
//        return false;
//    }
//}
